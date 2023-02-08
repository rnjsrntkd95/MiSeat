package com.miseat.domain.socket.model.dto;

import com.miseat.entity.Reservation;
import com.miseat.entity.SeatLocation;
import com.miseat.entity.Worker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReservationDto {

    @Schema(description = "좌석 번호")
    private Integer seatNumber;

    @Schema(description = "X 좌표")
    private Integer x;

    @Schema(description = "Y 좌표")
    private Integer y;

    @Schema(description = "예약자 이름")
    private String workerName;

    @Schema(description = "예약자 이메일")
    private String workerEmail;

    public static List<ReservationDto> createAll(List<Reservation> reservations) {
        return reservations
                .stream()
                .map(ReservationDto::create)
                .collect(Collectors.toList());
    }

    private static ReservationDto create(Reservation reservation) {
        SeatLocation location = reservation.getLocation();
        Worker worker = reservation.getWorker();

        ReservationDto dto = new ReservationDto();
        dto.seatNumber = reservation.getSeatNumber();
        dto.x = location.getX();
        dto.y = location.getY();
        dto.workerName = worker.getName();
        dto.workerEmail = worker.getEmail();

        return dto;
    }
}
