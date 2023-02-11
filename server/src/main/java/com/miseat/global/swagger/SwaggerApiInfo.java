package com.miseat.global.swagger;

import com.miseat.global.path.ApiPath;

import java.lang.reflect.Field;
import java.util.Arrays;

public enum SwaggerApiInfo {

    MISEAT_API(ApiPath.class, "MiSeat API"),
    ;

    public final Class<?> pathClass;
    public final String title;

    SwaggerApiInfo(Class<?> pathClass, String title) {
        this.pathClass = pathClass;
        this.title = title;
    }

    public String[] getDeclaredPath() {
        return Arrays.stream(this.pathClass.getDeclaredFields())
                .map(this::getPathValue)
                .toArray(String[]::new);
    }

    private String getPathValue(Field field) {
        try {
            return field.get(this).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
