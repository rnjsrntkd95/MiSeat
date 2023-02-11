package com.miseat.domain.seat.exception;

import com.miseat.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CancelReservationException extends BusinessException {

    public CancelReservationException() {
        super(HttpStatus.BAD_REQUEST, "좌석 예약을 취소할 수 없습니다.");
    }
}
