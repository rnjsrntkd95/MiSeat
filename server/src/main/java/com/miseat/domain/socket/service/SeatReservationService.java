package com.miseat.domain.socket.service;

import com.miseat.domain.seat.repository.SeatRepository;
import com.miseat.domain.seat.service.SeatFindService;
import com.miseat.domain.socket.exception.AlreadyReservedSeatException;
import com.miseat.domain.socket.exception.NotAvailableSeatException;
import com.miseat.domain.socket.model.dto.ReservationInfoDto;
import com.miseat.domain.socket.model.rq.CheckSeatReservationRq;
import com.miseat.domain.socket.model.rs.CheckSeatReservationRs;
import com.miseat.domain.socket.model.rs.ReserveSeatRs;
import com.miseat.domain.socket.model.rs.UserReservationResultRs;
import com.miseat.domain.space.service.SpaceFindService;
import com.miseat.domain.worker.service.WorkerFindService;
import com.miseat.entity.Seat;
import com.miseat.entity.Space;
import com.miseat.entity.Worker;
import com.miseat.entity.enums.SeatType;
import com.miseat.global.path.WebSocketPath;
import com.miseat.global.security.jwt.WorkerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

import static com.miseat.global.config.AsyncConfig.THREAD_POOL_TASK_EXECUTOR;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatReservationService {

    private final SimpMessagingTemplate messenger;

    private final SeatFindService seatFindService;
    private final WorkerFindService workerFindService;
    private final SpaceFindService spaceFindService;

    private final SeatRepository seatRepository;

    public CheckSeatReservationRs checkSeatReservation(WorkerContext context, CheckSeatReservationRq rq) {
        Seat seat = seatFindService.getSeatElseThrow(
                context.getTeamCode(),
                rq.getSeatNumber(),
                rq.getReservationDate()
        );
        Worker worker = seat.getWorker();
        Boolean reservationYn = Objects.nonNull(worker);

        return CheckSeatReservationRs.create(reservationYn);
    }

    @Async(THREAD_POOL_TASK_EXECUTOR)
    public void reserveSeat(WorkerContext context, ReservationInfoDto rq) {
        Integer teamCode = context.getTeamCode();

        Space space = spaceFindService.findSpaceElseThrow(teamCode, rq.getDate());
        Seat seat = seatFindService.getSeatElseThrow(space.getSn(), rq.getSeatNumber());

        if (!SeatType.FREE.equals(seat.getSeatType())) {
            throw new NotAvailableSeatException();  // TODO: FAIL ????????? ????????? ?????? ???
        }

        Worker worker = workerFindService.getWorkerElseThrow(context.getUserId(), teamCode);
        reserveElseThrowIfFailed(worker, seat);
        sendReservationInfoToSubscriber(worker, teamCode, seat, space);
    }

    private void reserveElseThrowIfFailed(Worker worker, Seat seat) {
        Integer seatNumber = seat.getSeatNumber();
        long result = seatRepository.updateWorkerBySeatSnAndSeatNumber(
                worker,
                seat.getSn(),
                seatNumber
        );
        boolean isFailed = result == 0;

        if (isFailed) {
            throw new AlreadyReservedSeatException();     // TODO: FAIL ????????? ????????? ?????? ???
        }

        Space space = seat.getSpace();
        sendSuccessReservationToUser(worker, space.getReservationDate(), seatNumber);
    }

    private void sendSuccessReservationToUser(Worker worker, LocalDate reservationDate, Integer seatNumber) {
        UserReservationResultRs rs = UserReservationResultRs.createSuccessType(reservationDate, seatNumber);
        messenger.convertAndSendToUser(worker.getUserId(), WebSocketPath.USER_SUB_RESERVATION_RESULT, rs);
    }

    private void sendReservationInfoToSubscriber(Worker worker, Integer teamCode, Seat seat, Space space) {
        ReserveSeatRs rs = ReserveSeatRs.create(
                space.getReservationDate(),
                seat.getSeatNumber(),
                worker.getName()
        );
        messenger.convertAndSend(WebSocketPath.SUB_TEAM + WebSocketPath.PATH + teamCode, rs);
    }
}
