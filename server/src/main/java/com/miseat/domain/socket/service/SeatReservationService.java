package com.miseat.domain.socket.service;

import com.miseat.domain.reservation.service.ReservationFindService;
import com.miseat.domain.socket.model.rq.CheckSeatReservationRq;
import com.miseat.domain.socket.model.rs.CheckSeatReservationRs;
import com.miseat.entity.Reservation;
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

    private final ReservationFindService reservationFindService;

    public CheckSeatReservationRs checkSeatReservation(WorkerContext context, CheckSeatReservationRq rq) {
        Reservation reservation = reservationFindService.getReservationElseThrow(
                context.getTeamCode(),
                rq.getSeatNumber(),
                rq.getReservationDate()
        );
        Worker worker = reservation.getWorker();
        Boolean reservationYn = Objects.nonNull(worker);

        return CheckSeatReservationRs.create(reservationYn);
    }

}
