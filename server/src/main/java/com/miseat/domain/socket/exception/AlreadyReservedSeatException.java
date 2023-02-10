package com.miseat.domain.socket.exception;

import com.miseat.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AlreadyReservedSeatException extends BusinessException {

    public AlreadyReservedSeatException() {
        super(HttpStatus.BAD_REQUEST, "이미 예약된 좌석입니다.");
    }
}
