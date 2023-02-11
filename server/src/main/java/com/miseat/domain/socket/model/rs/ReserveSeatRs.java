package com.miseat.domain.socket.model.rs;

import com.miseat.domain.socket.model.TeamMessageType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ReserveSeatRs {

    @Schema(description = "팀에 전달 되는 메시지 타입")
    private TeamMessageType type;

    @Schema(description = "예약 날짜")
    private LocalDate reservationDate;

    @Schema(description = "좌석 번호")
    private Integer seatNumber;

    @Schema(description = "예약자 이름")
    private String workerName;

    public static ReserveSeatRs create(LocalDate reservationDate, Integer seatNumber, String workerName) {
        ReserveSeatRs rs = new ReserveSeatRs();
        rs.type = TeamMessageType.RESERVATION;
        rs.reservationDate = reservationDate;
        rs.seatNumber = seatNumber;
        rs.workerName = workerName;

        return rs;
    }
}
