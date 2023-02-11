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

    private final String userId;
    private final Integer teamCode;

    public WorkerContext(String userId, Integer teamCode, Set<? extends GrantedAuthority> authorities) {
        super(userId, null, authorities);
        this.userId = userId;
        this.teamCode = teamCode;
    }

    @Override
    public String getName() {
        return userId;
    }
}
