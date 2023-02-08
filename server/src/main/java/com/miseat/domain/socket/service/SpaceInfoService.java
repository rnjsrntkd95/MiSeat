package com.miseat.domain.socket.service;

import com.miseat.domain.socket.model.rs.FindSpaceWithSeatsRs;
import com.miseat.domain.space.service.SpaceFindService;
import com.miseat.domain.space.service.SpaceService;
import com.miseat.domain.team.service.TeamFindService;
import com.miseat.entity.Space;
import com.miseat.entity.Team;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class SpaceInfoService {

    private final TeamFindService teamFindService;
    private final SpaceFindService spaceFindService;
    private final SpaceService spaceService;

    public FindSpaceWithSeatsRs findSpaceWithSeats(@NotNull Integer teamCode) {
        Team team = teamFindService.getTeamElseThrow(teamCode);
        Space spaceOnToday = spaceFindService
                .findSpace(team.getSn(), LocalDate.now())
                .orElse(spaceService.create(team));

        return FindSpaceWithSeatsRs.create(teamCode, spaceOnToday);
    }
}
