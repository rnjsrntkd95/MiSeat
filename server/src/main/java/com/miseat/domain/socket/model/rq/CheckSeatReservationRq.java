package com.miseat.domain.socket.model.rq;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CheckSeatReservationRq {

    @Schema(description = "예약 일자")
    @NotNull
    private LocalDate reservationDate;

    @Schema(description = "좌석 번호")
    @NotNull
    private Integer seatNumber;
}
