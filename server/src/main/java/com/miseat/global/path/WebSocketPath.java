package com.miseat.global.path;


public class WebSocketPath {

    // Root Path
    public static final String PATH = "/";
    public static final String WS_MISEAT = "/ws-miseat";
    public static final String PUB = "/pub";
    public static final String SUB = "/sub";
    public static final String DM = "/dm";
    public static final String WORKER = "/worker";

    // Client To Send Path
    public static final String TEAM = "/team";
    public static final String SEAT_RESERVATION_CHECK = "/seat/reservation/check";
    public static final String RESERVATION = "/reservation";

    // @SendToUser Path
    public static final String USER_TOPIC_TEAM = SUB + "/team";

    // @SendTo Path
    public static final String TOPIC_TEAM = SUB + "/team";
}
