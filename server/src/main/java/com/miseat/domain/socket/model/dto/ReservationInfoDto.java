package com.miseat.domain.socket.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationInfoDto {

    @Schema(description = "예약 날짜")
    @NotNull
    private LocalDate date;

    @Schema(description = "좌석 번호")
    @NotNull
    private Integer seatNumber;
}
