package com.miseat.domain.worker.service;

import com.miseat.domain.worker.exception.NotFoundWorkerException;
import com.miseat.domain.worker.repository.WorkerRepository;
import com.miseat.entity.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkerFindService {

    private final WorkerRepository workerRepository;

    public Worker getWorkerElseThrow(String userId, Integer teamCode) {
        return workerRepository.findByUserIdAndTeamCode(userId, teamCode)
                .orElseThrow(NotFoundWorkerException::new);
    }
}
