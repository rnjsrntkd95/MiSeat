package com.miseat.domain.team.repository;

import com.miseat.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {

    Optional<Team> findFirstByTeamCode(Integer teamCode);

    List<Team> findAll();
}
