package com.miseat.domain.space.service;

import com.miseat.domain.space.exception.NotFoundSpaceException;
import com.miseat.domain.space.repository.SpaceRepository;
import com.miseat.entity.Space;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpaceFindService {

    private final SpaceRepository spaceRepository;

    public Space findSpaceElseThrow(Long spaceSn) {
        return spaceRepository.findById(spaceSn)
                .orElseThrow(NotFoundSpaceException::new);
    }

    public Optional<Space> findSpace(Long teamSn, LocalDate reservationDate) {
        return spaceRepository.findByTeamSnAndReservationDate(teamSn, reservationDate);
    }
}
