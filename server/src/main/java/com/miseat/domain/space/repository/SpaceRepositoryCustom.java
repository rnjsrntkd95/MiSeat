package com.miseat.domain.space.repository;

import com.miseat.entity.Space;

import java.time.LocalDate;
import java.util.Optional;

public interface SpaceRepositoryCustom {

    Optional<Space> findByTeamSnAndReservationDate(Long teamSn, LocalDate reservationDate);

    Optional<Space> findByTeamCodeAndReservationDate(Integer teamCode, LocalDate reservationDate);
}
