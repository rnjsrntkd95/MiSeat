package com.miseat.domain.team.service;

import com.miseat.domain.team.exception.NotFoundTeamException;
import com.miseat.domain.team.repository.TeamRepository;
import com.miseat.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamFindService {

    private final TeamRepository teamRepository;

    public Team getTeamElseThrow(Integer teamCode) {
        return teamRepository
                .findFirstByTeamCode(teamCode)
                .orElseThrow(NotFoundTeamException::new);
    }
}
