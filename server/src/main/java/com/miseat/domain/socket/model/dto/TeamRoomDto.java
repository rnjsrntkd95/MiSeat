package com.miseat.domain.socket.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

public record TeamRoomDto(
        String roomId,
        Set<WebSocketSession> sessions
) {
    public TeamRoomDto(String roomId) {
        this(roomId, new HashSet<>());
    }
}
