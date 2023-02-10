package com.miseat.domain.seat.model.rq;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CancelSeatReservationRq {

    @Schema(description = "예약 날짜")
    @NotNull
    private LocalDate reservationDate;

    @Schema(description = "좌석 번호")
    @NotNull
    private Integer seatNumber;
}
