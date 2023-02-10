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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "unique_space_seat_number",
                columnNames = {"col1", "col2"}
        ))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseDateTimeEntity {

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
    @JoinColumn(name = "worker_sn")
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_sn", nullable = false)
    private Space space;

    public static Seat create(SeatType seatType, Integer seatNumber, SeatLocation location, Space space) {
        Seat seat = new Seat();
        seat.seatType = seatType;
        seat.seatNumber = seatNumber;
        seat.location = location;
        seat.space = space;

        return seat;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
