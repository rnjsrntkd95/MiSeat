package com.miseat.domain.socket.controller;

import com.miseat.domain.socket.model.rq.CheckSeatReservationRq;
import com.miseat.domain.socket.model.rq.ReserveSeatRq;
import com.miseat.domain.socket.model.rs.CheckSeatReservationRs;
import com.miseat.domain.socket.model.rs.ReserveSeatRs;
import com.miseat.domain.socket.service.SeatReservationService;
import com.miseat.global.path.WebSocketPath;
import com.miseat.global.security.jwt.WorkerContext;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SeatReservationController {

    private final SimpMessagingTemplate template;
    private final SeatReservationService seatReservationService;

    @Operation(summary = "[SEND] 자리 예약 정보 확인")
    @MessageMapping(WebSocketPath.SEAT_RESERVATION_CHECK)
    @SendToUser(WebSocketPath.USER_TOPIC_TEAM)
    public CheckSeatReservationRs checkSeatReservation(@AuthenticationPrincipal WorkerContext context,
                                                       @Validated CheckSeatReservationRq rq) {
        return seatReservationService.checkSeatReservation(context, rq);
    }

    @MessageMapping(WebSocketPath.RESERVATION)
    public void reserveSeat(@AuthenticationPrincipal WorkerContext context,
                            @Validated ReserveSeatRq rq) {
        ReserveSeatRs rs = seatReservationService.reserveSeat(context, rq);
        template.convertAndSend(WebSocketPath.TOPIC_TEAM + WebSocketPath.PATH + context.getTeamCode(), rs);
    }
}
