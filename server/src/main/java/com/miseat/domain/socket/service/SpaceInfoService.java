package com.miseat.domain.socket.service;

import com.miseat.domain.seat.service.SeatService;
import com.miseat.domain.socket.model.rs.FindSpaceWithSeatsRs;
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

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpaceInfoService {

    private final TeamFindService teamFindService;
    private final SpaceFindService spaceFindService;
    private final SpaceService spaceService;
    private final SeatService seatService;

    public FindSpaceWithSeatsRs findSpaceWithSeats(@NotNull Integer teamCode, LocalDate reservationDate) {
        Team team = teamFindService.getTeamElseThrow(teamCode);
        Space spaceOnToday = spaceFindService
                .findSpace(team.getSn(), reservationDate)
                .orElseGet(() -> createSpaceAndSeats(team));

        return FindSpaceWithSeatsRs.create(teamCode, spaceOnToday);
    }

    private Space createSpaceAndSeats(Team team) {
        Space space = spaceService.create(team);
        List<Seat> seats = seatService.createAllByGridMap(team.getGridMap(), space);
        space.addSeats(seats);

        return space;
    }
}
