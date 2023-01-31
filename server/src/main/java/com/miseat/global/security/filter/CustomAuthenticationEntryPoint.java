package com.miseat.global.security.filter;

import com.miseat.global.exception.ErrorRs;
import com.miseat.global.exception.FailedAuthenticationException;
import com.miseat.global.security.jwt.JwtExceptionCode;
import com.miseat.global.util.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import static com.miseat.global.security.filter.JwtAuthenticationFilter.JWT_EXCEPTION;
import static com.miseat.global.security.jwt.JwtExceptionCode.EXPIRED_TOKEN;
import static com.miseat.global.security.jwt.JwtExceptionCode.INVALID_SIGNATURE;
import static com.miseat.global.security.jwt.JwtExceptionCode.UNSUPPORTED_TOKEN;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        JwtExceptionCode exceptionCode = (JwtExceptionCode) request.getAttribute(JWT_EXCEPTION);
        ErrorRs errorRs = toErrorRs(exceptionCode);
        setExceptionResponse(response, errorRs);
    }

    private void setExceptionResponse(HttpServletResponse response, ErrorRs errorRs) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JsonUtils.toJson(errorRs));
    }

    private ErrorRs toErrorRs(JwtExceptionCode exceptionCode) {
        if (EXPIRED_TOKEN.equals(exceptionCode)) {
            return ErrorRs.of(exceptionCode.getCode(), exceptionCode.getMessage());
        } else if (INVALID_SIGNATURE.equals(exceptionCode)) {
            return ErrorRs.of(exceptionCode.getCode(), exceptionCode.getMessage());
        } else if (UNSUPPORTED_TOKEN.equals(exceptionCode)) {
            return ErrorRs.of(exceptionCode.getCode(), exceptionCode.getMessage());
        } else {
            return ErrorRs.ofException(new FailedAuthenticationException());
        }
    }
}