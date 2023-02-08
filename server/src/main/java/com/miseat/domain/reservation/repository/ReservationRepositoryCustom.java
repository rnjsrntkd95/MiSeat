package com.miseat.domain.reservation.repository;

import com.miseat.entity.Reservation;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepositoryCustom {

    Optional<Reservation> findFirstByTeamAndDateAndSeatNumber(Integer teamCode, LocalDate reservationDate, Integer seatNumber);
}
