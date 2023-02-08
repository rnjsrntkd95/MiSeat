package com.miseat.global.websocket.handler;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.messaging.simp.stomp.StompCommand.SEND;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServerToClientMessageHandler implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (Objects.nonNull(accessor)) {
            executeExtraCommand(accessor);
        }
        return message;
    }

    private void executeExtraCommand(@NotNull StompHeaderAccessor accessor) {
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();

        if (SEND.equals(command)) {
            log.info("Send To Server" + sessionId);
        }
    }
}