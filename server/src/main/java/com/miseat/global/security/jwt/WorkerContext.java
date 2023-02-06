package com.miseat.global.security.jwt;

import com.miseat.global.security.CustomUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.Serial;
import java.security.Principal;
import java.util.Set;

@Getter
public class WorkerContext extends CustomUser implements Principal {

    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // TODO: 유저 필드 정의
    private final String name;

    private final Integer teamCode;

    public WorkerContext(String name, Integer teamCode, Set<? extends GrantedAuthority> authorities) {
        super(name, null, authorities);  // TODO: username, password, authorities 재정의
        this.name = name;
        this.teamCode = teamCode;
    }
}
