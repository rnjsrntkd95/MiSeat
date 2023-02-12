package com.miseat.domain.space.service;

import com.miseat.domain.space.repository.SpaceRepository;
import com.miseat.entity.GridMap;
import com.miseat.entity.Space;
import com.miseat.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public Space create(Team team, LocalDate reservationDate) {
        GridMap gridMap = team.getGridMap();
        Space space = Space.create(
                gridMap.getXSize(),
                gridMap.getYSize(),
                reservationDate,
                team
        );

        return spaceRepository.save(space);
    }

    public void unlockMap(Team team, List<LocalDate> reservationDates) {
        spaceRepository.updateMapLockByTeamAndReservationDates(team, reservationDates, Boolean.FALSE);
    }
}
