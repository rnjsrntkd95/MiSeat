package com.miseat.entity.enums;

public enum SeatType {

    FIX("고정 좌석"),
    FREE("자율 좌석"),
    NONE("좌석 없음");

    private String description;

    SeatType(String description) {
        this.description = description;
    }
}
