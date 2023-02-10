package com.miseat.domain.seat.repository;

import com.miseat.entity.Seat;
import com.miseat.entity.Worker;

import java.time.LocalDate;
import java.util.Optional;

public interface SeatRepositoryCustom {

    Optional<Seat> findFirstByTeamAndDateAndSeatNumber(Integer teamCode, LocalDate reservationDate, Integer seatNumber);

    Optional<Seat> findFirstBySpaceSnAndSeatNumber(Long spaceSn, Integer seatNumber);

    long updateWorkerBySeatSnAndSeatNumber(Worker worker, Long seatSn, Integer seatNumber);
}
