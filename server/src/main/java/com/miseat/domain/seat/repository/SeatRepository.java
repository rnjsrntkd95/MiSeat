package com.miseat.domain.seat.repository;

import com.miseat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>, SeatRepositoryCustom {

}
