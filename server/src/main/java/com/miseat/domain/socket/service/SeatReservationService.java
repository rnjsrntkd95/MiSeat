package com.miseat.domain.socket.service;

import com.miseat.domain.seat.service.SeatFindService;
import com.miseat.domain.socket.model.rq.CheckSeatReservationRq;
import com.miseat.domain.socket.model.rs.CheckSeatReservationRs;
import com.miseat.entity.Seat;
import com.miseat.entity.Worker;
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

}
