package com.miseat.domain.socket.controller;

import com.miseat.domain.socket.model.rq.CheckSeatReservationRq;
import com.miseat.domain.socket.model.rs.CheckSeatReservationRs;
import com.miseat.domain.socket.service.SeatReservationService;
import com.miseat.global.path.WebSocketPath;
import com.miseat.global.security.jwt.WorkerContext;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.messaging.simp.SimpMessageHeaderAccessor.USER_HEADER;

@RestController
@RequiredArgsConstructor
public class SeatReservationController {

    private final SeatReservationService seatReservationService;

    @Operation(summary = "[SEND] 자리 예약 정보 확인")
    @MessageMapping(WebSocketPath.SEAT_RESERVATION_CHECK)
    public CheckSeatReservationRs checkSeatReservation(@Header(USER_HEADER) WorkerContext context,
                                                       @Validated CheckSeatReservationRq rq) {
        return seatReservationService.checkSeatReservation(context, rq);
    }
}
