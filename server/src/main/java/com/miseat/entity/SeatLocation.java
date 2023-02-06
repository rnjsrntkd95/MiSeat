package com.miseat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatLocation {

    @Column(nullable = false)
    private Integer x;

    @Column(nullable = false)
    private Integer y;
}
