package com.miseat.domain.worker.exception;

import com.miseat.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundWorkerException extends BusinessException {

    public NotFoundWorkerException() {
        super(HttpStatus.BAD_REQUEST, "근로자를 찾을 수 없습니다.");
    }
}
