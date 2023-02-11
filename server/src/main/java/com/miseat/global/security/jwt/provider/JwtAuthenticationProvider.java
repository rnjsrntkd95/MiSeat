package com.miseat.global.security.jwt.provider;

import com.miseat.global.exception.jwt.NotFoundJwtException;
import com.miseat.global.security.jwt.JwtClaimKey;
import com.miseat.global.security.jwt.WorkerContext;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider {

    private static final String BEARER_TOKEN_REGEX = "Bearer (.*)";
    private static final int GROUP_FIRST = 1;

    private final JwtTokenProvider jwtTokenProvider;

    public Authentication authenticate(@NotNull String authorization) throws AuthenticationException {
        WorkerContext workerContext = createWorkerContext(authorization);
        return new UsernamePasswordAuthenticationToken(workerContext, null, null);
    }

    public WorkerContext createWorkerContext(@NotNull String authorization) {
        String accessToken = getAccessToken(authorization);
        Claims claims = jwtTokenProvider.parseClaims(accessToken);
        String userId = claims.get(JwtClaimKey.USER_ID.getKeyName(), String.class);
        Integer teamCode = claims.get(JwtClaimKey.TEAM_CODE.getKeyName(), Integer.class);

        return new WorkerContext(userId, teamCode, null);
    }

    public String getAccessToken(String bearerToken) {
        Pattern pattern = Pattern.compile(BEARER_TOKEN_REGEX);
        Matcher matcher = pattern.matcher(bearerToken);

        if (matcher.find()) {
            return matcher.group(GROUP_FIRST);
        }
        throw new NotFoundJwtException();
    }
}
