package com.miseat.domain.socket.controller;

import com.miseat.domain.socket.model.rs.FindSpaceWithSeatsRs;
import com.miseat.domain.socket.service.SpaceInfoService;
import com.miseat.global.path.WebSocketPath;
import com.miseat.global.security.jwt.WorkerContext;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class SpaceInfoController {

    private final SpaceInfoService spaceInfoService;

    @Operation(description = "[SEND] 스페이스 예약 정보 조회")
    @MessageMapping(WebSocketPath.TEAM)
    @SendToUser(WebSocketPath.USER_TOPIC_TEAM)
    public FindSpaceWithSeatsRs findSpaceWithSeats(@AuthenticationPrincipal WorkerContext context) {
        return spaceInfoService.findSpaceWithSeats(context.getTeamCode(), LocalDate.now());
    }
}
