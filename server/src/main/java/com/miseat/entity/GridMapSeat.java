package com.miseat.entity;

import com.miseat.entity.enums.SeatType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GridMapSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn", nullable = false)
    private Long sn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatType seatType = SeatType.NONE;

    @Column
    private Integer seatNumber;

    @Embedded
    private SeatLocation location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grid_map_sn", nullable = false)
    private GridMap gridMap;
}
