package com.miseat.domain.worker.repository;

import com.miseat.entity.Worker;

import java.util.Optional;

public interface WorkerRepositoryCustom {

    Optional<Worker> findByUserIdAndTeamCode(String userId, Integer teamCode);
}
