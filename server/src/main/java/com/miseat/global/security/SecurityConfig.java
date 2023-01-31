package com.miseat.global.security;

import com.miseat.global.profile.AppProfiles;
import com.miseat.global.security.filter.CustomAccessDeniedHandler;
import com.miseat.global.security.filter.CustomAuthenticationEntryPoint;
import com.miseat.global.security.filter.JwtAuthenticationFilter;
import com.miseat.global.security.jwt.provider.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Environment environment;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(header -> header.frameOptions().sameOrigin())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/test").permitAll() // test controller
                )
                .addFilterBefore(createJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .build();
    }

    private JwtAuthenticationFilter createJwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtAuthenticationProvider);
    }

    @Bean
    @Order(0)
    public SecurityFilterChain ignoringFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(getIgnoringPattern())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable()
                .build();
    }

    private String[] getIgnoringPattern() {
        List<String> pattern = new ArrayList<>(List.of("/favicon.ico", "/health.html", "/robots.txt", "/error"));

        if (!environment.acceptsProfiles(Profiles.of(AppProfiles.KR_PRODUCTION))) {
            List<String> swaggerPattern = List.of("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**");
            pattern.addAll(swaggerPattern);
        }

        return pattern.toArray(new String[0]);
    }
}
