package com.miseat.global.websocket.handler;

import com.miseat.global.security.jwt.WorkerContext;
import com.miseat.global.security.jwt.provider.JwtAuthenticationProvider;
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

import static org.springframework.messaging.simp.stomp.StompCommand.CONNECT;
import static org.springframework.messaging.simp.stomp.StompCommand.DISCONNECT;
import static org.springframework.messaging.simp.stomp.StompCommand.SEND;
import static org.springframework.messaging.simp.stomp.StompCommand.SUBSCRIBE;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientToServerMessageHandler implements ChannelInterceptor {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

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

        if (CONNECT.equals(command)) {
            log.info("Connect User : " + sessionId);
            login(accessor);
        }
        if (DISCONNECT.equals(command)) {
            log.info("Disconnect User : " + sessionId);
        }
        if (SUBSCRIBE.equals(command)) {
            log.info("Subscribe User : " + sessionId);
//            login(accessor);
        }
        if (SEND.equals(command)) {
            log.info("Send To Server" + sessionId);
            login(accessor);
        }
    }

    private void login(StompHeaderAccessor accessor) {
        String authorization = accessor.getFirstNativeHeader("Authorization");
        WorkerContext workerContext = jwtAuthenticationProvider.createWorkerContext(authorization);
        accessor.setUser(workerContext);
    }
}