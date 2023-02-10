package com.miseat.domain.seat.service;

import com.miseat.domain.seat.exception.CancelReservationException;
import com.miseat.domain.seat.model.rq.CancelSeatReservationRq;
import com.miseat.domain.seat.repository.SeatRepository;
import com.miseat.domain.worker.service.WorkerFindService;
import com.miseat.entity.GridMap;
import com.miseat.entity.GridMapSeat;
import com.miseat.entity.Seat;
import com.miseat.entity.Space;
import com.miseat.entity.Worker;
import com.miseat.global.security.jwt.WorkerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {

    private final WorkerFindService workerFindService;
    private final SeatFindService seatFindService;

    private final SeatRepository seatRepository;

    public List<Seat> createAllByGridMap(GridMap gridMap, Space space) {
        List<GridMapSeat> gridMapSeats = gridMap.getGridMapSeats();
        List<Seat> seats = gridMapSeats
                .stream()
                .map(gridMapSeat -> copyGridMapSeat(gridMapSeat, space))
                .collect(Collectors.toList());

        return seatRepository.saveAll(seats);
    }

    private Seat copyGridMapSeat(GridMapSeat gridMapSeat, Space space) {
        return Seat.create(
                gridMapSeat.getSeatType(),
                gridMapSeat.getSeatNumber(),
                gridMapSeat.getLocation(),
                space
        );
    }

    public void cancelSeatReservation(WorkerContext context, CancelSeatReservationRq rq) {
        Worker worker = workerFindService.getWorkerElseThrow(context.getUserId(), context.getTeamCode());
        Seat seat = seatFindService.getSeatElseThrow(
                context.getTeamCode(),
                rq.getSeatNumber(),
                rq.getReservationDate()
        );
        Worker reservedWorker = seat.getWorker();

        if (!worker.equals(reservedWorker)) {
            throw new CancelReservationException();
        }
        seat.setWorker(worker);
    }
}
