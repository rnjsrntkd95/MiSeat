package com.miseat.global.swagger;

import java.util.List;

public enum SwaggerApiInfo {

    MISEAT_API(List.of("/**"), "MiSeat API"), // TODO: Path 지정 필요
    ;

    public final List<String> path;
    public final String title;

    SwaggerApiInfo(List<String> path, String title) {
        this.path = path;
        this.title = title;
    }

    public static String[] getApiPathArray(SwaggerApiInfo api) {
        return api.path.toArray(new String[0]);
    }
}
