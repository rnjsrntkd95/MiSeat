package com.miseat.domain.seat.repository;

import com.miseat.entity.Seat;

import java.time.LocalDate;
import java.util.Optional;

public interface SeatRepositoryCustom {

    Optional<Seat> findFirstByTeamAndDateAndSeatNumber(Integer teamCode, LocalDate reservationDate, Integer seatNumber);
}
