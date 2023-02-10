package com.miseat.domain.socket.model.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.miseat.domain.socket.model.enums.ReservationResultType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserReservationResultRs {

    @Schema(description = "결과 타입")
    private ReservationResultType type;

    @Schema(description = "예약 일자")
    private LocalDate reservationDate;

    @Schema(description = "좌석 번호")
    private Integer seatNumber;

    @Schema(description = "실패 메시지")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static UserReservationResultRs createSuccessType(LocalDate reservationDate, Integer seatNumber) {
        UserReservationResultRs rs = new UserReservationResultRs();
        rs.type = ReservationResultType.SUCCESS;
        rs.reservationDate = reservationDate;
        rs.seatNumber = seatNumber;

        return rs;
    }

    public static UserReservationResultRs createFailType(LocalDate reservationDate, Integer seatNumber,
                                                         String message) {
        UserReservationResultRs rs = new UserReservationResultRs();
        rs.type = ReservationResultType.FAIL;
        rs.reservationDate = reservationDate;
        rs.seatNumber = seatNumber;
        rs.message = message;

        return rs;
    }
}
