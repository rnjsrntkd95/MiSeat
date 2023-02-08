package com.miseat.domain.team.repository;

import com.miseat.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findFirstByTeamCode(Integer teamCode);
}
