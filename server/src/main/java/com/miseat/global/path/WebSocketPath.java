package com.miseat.global.path;


public class WebSocketPath {

    // Root Path
    public static final String PATH = "/";
    public static final String WS_MISEAT = "/ws-miseat";
    public static final String APP = "/app";
    public static final String TOPIC = "/topic";
    public static final String QUEUE = "/queue";
    public static final String WORKER = "/worker";

    // Client To Send Path
    public static final String TEAM = "/team";
    public static final String SEAT_RESERVATION_CHECK = "/seat/reservation/check";
    public static final String RESERVATION = "/reservation";

    // @SendToUser Path
    public static final String USER_TOPIC_TEAM = TOPIC + "/team";

    // @SendTo Path
    public static final String TOPIC_TEAM = TOPIC + "/team";
}
