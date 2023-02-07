package com.miseat.global.config;

import com.miseat.global.path.WebSocketPath;
import com.miseat.global.websocket.handler.ClientToServerMessageHandler;
import com.miseat.global.websocket.handler.ServerToClientMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final ClientToServerMessageHandler clientToServerMessageHandler;
    private final ServerToClientMessageHandler serverToClientMessageHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(WebSocketPath.WS_MISEAT)
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes(WebSocketPath.APP);
        config.enableSimpleBroker(WebSocketPath.TOPIC, WebSocketPath.QUEUE);
        config.setUserDestinationPrefix(WebSocketPath.WORKER);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(clientToServerMessageHandler);
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(serverToClientMessageHandler);
    }
}
