package com.miseat.domain.seat.exception;

import com.miseat.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundSeatException extends BusinessException {

    public NotFoundSeatException() {
        super(HttpStatus.BAD_REQUEST, "좌석 정보를 찾을 수 없습니다.");
    }
}
