package com.miseat.domain.space.service;

import com.miseat.domain.space.repository.SpaceRepository;
import com.miseat.entity.GridMap;
import com.miseat.entity.Space;
import com.miseat.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public Space create(Team team) {
        GridMap gridMap = team.getGridMap();
        Space space = Space.create(
                gridMap.getXSize(),
                gridMap.getYSize(),
                LocalDate.now(),
                team
        );

        return spaceRepository.save(space);
    }
}
