package com.miseat.domain.worker.repository;

import com.miseat.entity.Worker;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.miseat.entity.QTeam.team;
import static com.miseat.entity.QWorker.worker;

@RequiredArgsConstructor
public class WorkerRepositoryImpl implements WorkerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Worker> findByUserIdAndTeamCode(String userId, Integer teamCode) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(worker)
                .join(worker.team, team)
                .where(worker.userId.eq(userId),
                        team.teamCode.eq(teamCode))
                .fetchFirst());
    }
}
