package com.miseat.domain.socket.model.rq;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReserveSeatRq {

    @NotNull
    private Integer teamCode;

    @NotNull
    private Long spaceId;

    @NotNull
    private Integer seatNumber;
}
