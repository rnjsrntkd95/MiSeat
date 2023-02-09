package com.miseat.domain.socket.exception;

import com.miseat.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotAvailableSeatException extends BusinessException {

    public NotAvailableSeatException() {
        super(HttpStatus.BAD_REQUEST, "이용 불가능한 좌석입니다.");
    }
}
