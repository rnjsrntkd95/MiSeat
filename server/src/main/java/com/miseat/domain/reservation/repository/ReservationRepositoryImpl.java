package com.miseat.domain.reservation.repository;

import com.miseat.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

import static com.miseat.entity.QReservation.reservation;
import static com.miseat.entity.QSpace.space;
import static com.miseat.entity.QTeam.team;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Reservation> findFirstByTeamAndDateAndSeatNumber(Integer teamCode, LocalDate reservationDate,
                                                                     Integer seatNumber) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(reservation)
                .innerJoin(reservation.space, space)
                .innerJoin(space.team, team)
                .where(team.teamCode.eq(teamCode),
                        reservation.seatNumber.eq(seatNumber),
                        space.reservationDate.eq(reservationDate)
                )
                .fetchFirst());
    }
}
