package com.miseat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn", nullable = false)
    private Long sn;

    @Column(nullable = false, unique = true)
    private Integer teamCode;

    @OneToOne(mappedBy = "team")
    private GridMap gridMap;

    @OneToMany(mappedBy = "team")
    private List<Space> spaces = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Worker> workers = new ArrayList<>();
}