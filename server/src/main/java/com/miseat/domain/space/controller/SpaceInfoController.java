package com.miseat.domain.space.controller;

import com.miseat.domain.socket.service.SpaceInfoService;
import com.miseat.domain.space.model.rq.FindSpaceWithSeatsRq;
import com.miseat.domain.space.model.rs.FindSpaceWithSeatsRs;
import com.miseat.global.path.ApiPath;
import com.miseat.global.security.jwt.WorkerContext;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpaceInfoController {

    private final SpaceInfoService spaceInfoService;

    @Operation(description = "스페이스 예약 정보 조회")
    @GetMapping(ApiPath.SPACE)
    public FindSpaceWithSeatsRs findSpaceWithSeats(@AuthenticationPrincipal WorkerContext context,
                                                   @Validated FindSpaceWithSeatsRq rq) {
        return spaceInfoService.findSpaceWithSeats(context.getTeamCode(), rq);
    }
}
