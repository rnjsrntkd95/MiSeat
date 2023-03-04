package com.miseat.domain.space.repository;

import com.miseat.entity.Space;
import com.miseat.entity.Team;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SpaceRepositoryCustom {

    Optional<Space> findByTeamSnAndReservationDate(Long teamSn, LocalDate reservationDate);

    Optional<Space> findByTeamCodeAndReservationDate(Integer teamCode, LocalDate reservationDate);

    long updateMapLockByTeamAndReservationDates(Team team, List<LocalDate> reservationDates, Boolean mapLockYn);
}
