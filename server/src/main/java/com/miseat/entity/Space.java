package com.miseat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// TODO: Team, reservationDate UNIQUE
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn", nullable = false)
    private Long sn;

    @Column(nullable = false)
    private Integer xSize;

    @Column(nullable = false)
    private Integer ySize;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private Boolean mapLockYn = Boolean.FALSE;

    @OneToMany(mappedBy = "space")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_sn", nullable = false)
    private Team team;

    public static Space create(Integer xSize, Integer ySize, LocalDate reservationDate, Team team) {
        Space space = new Space();
        space.xSize = xSize;
        space.ySize = ySize;
        space.reservationDate = reservationDate;
        space.team = team;

        return space;
    }
}
