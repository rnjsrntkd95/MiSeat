package com.miseat.domain.socket.service;

import com.miseat.domain.seat.service.SeatService;
import com.miseat.domain.space.model.rq.FindSpaceWithSeatsRq;
import com.miseat.domain.space.model.rs.FindSpaceWithSeatsRs;
import com.miseat.domain.space.service.SpaceFindService;
import com.miseat.domain.space.service.SpaceService;
import com.miseat.domain.team.service.TeamFindService;
import com.miseat.entity.Seat;
import com.miseat.entity.Space;
import com.miseat.entity.Team;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpaceInfoService {

    private final TeamFindService teamFindService;
    private final SpaceFindService spaceFindService;
    private final SpaceService spaceService;
    private final SeatService seatService;

    public FindSpaceWithSeatsRs findSpaceWithSeats(@NotNull Integer teamCode, FindSpaceWithSeatsRq rq) {
        Team team = teamFindService.getTeamElseThrow(teamCode);
        Space space = spaceFindService
                .findSpace(team.getSn(), rq.getDate())
                .orElseGet(() -> createSpaceAndSeats(team));

        return FindSpaceWithSeatsRs.create(teamCode, space);
    }

    private Space createSpaceAndSeats(Team team) {
        Space space = spaceService.create(team);
        List<Seat> seats = seatService.createAllByGridMap(team.getGridMap(), space);
        space.addSeats(seats);

        return space;
    }
}
