package com.miseat.global.security.filter;

import com.miseat.global.security.jwt.JwtExceptionCode;
import com.miseat.global.security.jwt.provider.JwtAuthenticationProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String JWT_EXCEPTION = "exception";

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            authenticate(authorization);
        } catch (ExpiredJwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.EXPIRED_TOKEN);
        } catch (SignatureException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.INVALID_SIGNATURE);
        } catch (UnsupportedJwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.UNSUPPORTED_TOKEN);
        } catch (JwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.INVALID_TOKEN);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String authorization) {
        if (StringUtils.isBlank(authorization)) {
            return;
        }
        Authentication auth = jwtAuthenticationProvider.authenticate(authorization);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
