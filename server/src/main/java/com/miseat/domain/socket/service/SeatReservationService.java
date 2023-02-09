package com.miseat.domain.socket.service;

import com.miseat.domain.seat.service.SeatFindService;
import com.miseat.domain.socket.exception.NotAvailableSeatException;
import com.miseat.domain.socket.model.rq.CheckSeatReservationRq;
import com.miseat.domain.socket.model.rq.ReserveSeatRq;
import com.miseat.domain.socket.model.rs.CheckSeatReservationRs;
import com.miseat.domain.socket.model.rs.ReserveSeatRs;
import com.miseat.domain.worker.service.WorkerFindService;
import com.miseat.entity.Seat;
import com.miseat.entity.Space;
import com.miseat.entity.Worker;
import com.miseat.entity.enums.SeatType;
import com.miseat.global.security.jwt.WorkerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatReservationService {

    private final SeatFindService seatFindService;
    private final WorkerFindService workerFindService;

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

    public ReserveSeatRs reserveSeat(WorkerContext context, ReserveSeatRq rq) {
        Worker worker = workerFindService.getWorkerElseThrow(context.getUserId(), context.getTeamCode());
        Seat seat = seatFindService.getSeatElseThrow(rq.getSpaceSn(), rq.getSeatNumber());

        if (!SeatType.FREE.equals(seat.getSeatType())) {
            throw new NotAvailableSeatException();
        }
        seat.setWorker(worker);
        Space space = seat.getSpace();

        return ReserveSeatRs.create(
                space.getReservationDate(),
                seat.getSeatNumber(),
                worker.getName()
        );
    }
}
