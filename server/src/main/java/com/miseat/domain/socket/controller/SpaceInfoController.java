package com.miseat.domain.socket.controller;

import com.miseat.domain.socket.model.rs.FindSpaceWithReservationRs;
import com.miseat.domain.socket.service.SpaceInfoService;
import com.miseat.global.path.WebSocketPath;
import com.miseat.global.security.jwt.WorkerContext;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.messaging.simp.SimpMessageHeaderAccessor.USER_HEADER;

@RestController
@RequiredArgsConstructor
public class SpaceInfoController {

    private final SpaceInfoService spaceInfoService;

    @Operation(description = "[SUBSCRIBE] 스페이스 예약 정보 조회")
    @SubscribeMapping(WebSocketPath.TEAM)
    public FindSpaceWithReservationRs findSpaceWithReservation(@Header(USER_HEADER) WorkerContext context) {
        return spaceInfoService.findSpaceWithReservation(context.getTeamCode());
    }
}
