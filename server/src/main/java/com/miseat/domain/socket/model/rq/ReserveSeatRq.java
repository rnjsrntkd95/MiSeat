package com.miseat.domain.socket.model.rq;

import com.miseat.domain.socket.model.dto.ReservationInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReserveSeatRq {

    @Schema(description = "예약 신청 정보")
    @Valid
    List<ReservationInfoDto> reservations = new ArrayList<>();
}
