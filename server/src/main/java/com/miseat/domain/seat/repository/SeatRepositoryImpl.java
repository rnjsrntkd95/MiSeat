package com.miseat.domain.seat.repository;

import com.miseat.entity.Seat;
import com.miseat.entity.Worker;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

import static com.miseat.entity.QSeat.seat;
import static com.miseat.entity.QSpace.space;
import static com.miseat.entity.QTeam.team;

@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Seat> findFirstByTeamAndDateAndSeatNumber(Integer teamCode, LocalDate reservationDate,
                                                              Integer seatNumber) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(seat)
                .innerJoin(seat.space, space)
                .innerJoin(space.team, team)
                .where(team.teamCode.eq(teamCode),
                        seat.seatNumber.eq(seatNumber),
                        space.reservationDate.eq(reservationDate)
                )
                .fetchFirst());
    }

    @Override
    public Optional<Seat> findFirstBySpaceSnAndSeatNumber(Long spaceSn, Integer seatNumber) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(seat)
                .innerJoin(seat.space, space)
                .where(space.sn.eq(spaceSn))
                .fetchFirst());
    }

    public long updateWorkerBySeatSnAndSeatNumber(Worker worker, Long seatSn, Integer seatNumber) {
        return jpaQueryFactory
                .update(seat)
                .set(seat.worker, worker)
                .where(seat.sn.eq(seatSn),
                        seat.seatNumber.eq(seatNumber),
                        seat.worker.isNull())
                .execute();
    }
}
