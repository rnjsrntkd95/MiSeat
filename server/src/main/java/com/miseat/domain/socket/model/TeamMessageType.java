package com.miseat.domain.socket.model;

import lombok.Getter;

@Getter
public enum TeamMessageType {

    RESERVATION("예약"),
    RESERVATION_CANCEL("예약 취소"),
    ;

    private final String description;

    TeamMessageType(String description) {
        this.description = description;
    }
}
