package com.miseat.domain.seat.controller;

import com.miseat.domain.seat.model.rq.CancelSeatReservationRq;
import com.miseat.domain.seat.service.SeatService;
import com.miseat.global.path.ApiPath;
import com.miseat.global.security.jwt.WorkerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @DeleteMapping(ApiPath.RESERVATION)
    public void cancelSeatReservation(@AuthenticationPrincipal WorkerContext context,
                                      @Validated CancelSeatReservationRq rq) {
        seatService.cancelSeatReservation(context, rq);
    }

}
