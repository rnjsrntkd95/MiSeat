package com.miseat.domain.seat.service;

import com.miseat.domain.seat.exception.NotFoundSeatException;
import com.miseat.domain.seat.repository.SeatRepository;
import com.miseat.entity.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SeatFindService {

    private final SeatRepository seatRepository;

    public Seat getSeatElseThrow(Integer teamCode, Integer seatNumber, LocalDate reservationDate) {
        return seatRepository
                .findFirstByTeamAndDateAndSeatNumber(teamCode, reservationDate, seatNumber)
                .orElseThrow(NotFoundSeatException::new);
    }
}
