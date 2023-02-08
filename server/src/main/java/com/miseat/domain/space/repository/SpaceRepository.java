package com.miseat.domain.space.repository;

import com.miseat.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long>, SpaceRepositoryCustom {
}
