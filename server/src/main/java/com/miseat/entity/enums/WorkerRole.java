package com.miseat.entity.enums;

import lombok.Getter;

@Getter
public enum WorkerRole {

    ROLE_LEADER,
    ROLE_MEMBER,
    ;

    public String getAuthorityName() {
        return this.name().replace("ROLE_", "");
    }
}
