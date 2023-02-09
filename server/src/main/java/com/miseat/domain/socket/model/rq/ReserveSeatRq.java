package com.miseat.domain.socket.model.rq;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReserveSeatRq {

    @Schema(description = "스페이스 SN")
    @NotNull
    private Long spaceSn;

    @Schema(description = "좌석 번호")
    @NotNull
    private Integer seatNumber;
}
