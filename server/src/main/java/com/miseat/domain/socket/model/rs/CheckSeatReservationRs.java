package com.miseat.domain.socket.model.rs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckSeatReservationRs {

    @Schema(description = "예약 완료 여부")
    private Boolean reservationYn;

    public static CheckSeatReservationRs create(Boolean reservationYn) {
        CheckSeatReservationRs rs = new CheckSeatReservationRs();
        rs.reservationYn = reservationYn;

        return rs;
    }
}
