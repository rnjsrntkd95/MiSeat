package com.miseat.domain.reservation.service;

import com.miseat.domain.reservation.exception.NotFoundReservationException;
import com.miseat.domain.reservation.repository.ReservationRepository;
import com.miseat.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationFindService {

    private final ReservationRepository reservationRepository;

    public Reservation getReservationElseThrow(Integer teamCode, Integer seatNumber, LocalDate reservationDate) {
        return reservationRepository
                .findFirstByTeamAndDateAndSeatNumber(teamCode, reservationDate, seatNumber)
                .orElseThrow(NotFoundReservationException::new);
    }
}
