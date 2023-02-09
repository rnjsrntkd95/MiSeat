package com.miseat.domain.worker.repository;

import com.miseat.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long>, WorkerRepositoryCustom {
}
