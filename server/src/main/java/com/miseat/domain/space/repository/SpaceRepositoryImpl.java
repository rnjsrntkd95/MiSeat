package com.miseat.domain.space.repository;

import com.miseat.entity.Space;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

import static com.miseat.entity.QSeat.seat;
import static com.miseat.entity.QSpace.space;
import static com.miseat.entity.QTeam.team;

@RequiredArgsConstructor
public class SpaceRepositoryImpl implements SpaceRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<Space> findByTeamSnAndReservationDate(Long teamSn, LocalDate reservationDate) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(space)
                .innerJoin(space.team, team)
                .leftJoin(space.seats, seat).fetchJoin() // TODO: fetchJoin 확인
                .where(team.sn.eq(teamSn),
                        space.reservationDate.eq(reservationDate)
                )
                .fetchFirst());
    }
}
