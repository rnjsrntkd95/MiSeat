package com.miseat.domain.space.exception;

import com.miseat.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundSpaceException extends BusinessException {

    public NotFoundSpaceException() {
        super(HttpStatus.BAD_REQUEST, "스페이스를 찾을 수 없습니다.");
    }
}
