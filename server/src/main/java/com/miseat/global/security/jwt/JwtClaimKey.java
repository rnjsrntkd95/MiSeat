package com.miseat.global.security.jwt;

import lombok.Getter;

@Getter
public enum JwtClaimKey {

    USER_ID("userId"),
    TEAM_CODE("teamCode"),
    ;

    private final String keyName;

    JwtClaimKey(String keyName) {
        this.keyName = keyName;
    }
}
