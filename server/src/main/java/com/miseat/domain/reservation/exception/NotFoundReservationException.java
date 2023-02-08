package com.miseat.domain.reservation.exception;

import com.miseat.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundReservationException extends BusinessException {

    public NotFoundReservationException() {
        super(HttpStatus.BAD_REQUEST, "예약 정보를 찾을 수 없습니다.");
    }
}
