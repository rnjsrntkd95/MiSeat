package com.miseat.domain.team.exception;

import com.miseat.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundTeamException extends BusinessException {

    public NotFoundTeamException() {
        super(HttpStatus.BAD_REQUEST, "팀을 찾을 수 없습니다.");
    }
}
