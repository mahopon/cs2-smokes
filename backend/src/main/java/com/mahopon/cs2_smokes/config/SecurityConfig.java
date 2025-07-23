package com.mahopon.cs2_smokes.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mahopon.cs2_smokes.auth.api.util.JwtUtil;
import com.mahopon.cs2_smokes.config.middleware.JwtAuthFilter;


@Configuration
@ConditionalOnProperty(name = "security.oauth2.enabled", havingValue = "true", matchIfMissing = true)
public class SecurityConfig {

    
    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil) {
        return new JwtAuthFilter(jwtUtil);
    }

    @Bean
    @Order(1)
    public SecurityFilterChain oAuth2FilterChain(HttpSecurity http) throws Exception {
        http
        .securityMatcher("/auth/**")
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()
        )
        .oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer.jwt(jwt -> {}) // Enable JWT-based OAuth2 Resource Server
        )
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
        .securityMatcher("/api/**")
        .authorizeHttpRequests(auth -> 
        auth
        .requestMatchers("/api/**").authenticated())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
    @Bean
    @Order(99)
    public SecurityFilterChain fallbackChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth.anyRequest().denyAll())  // or permitAll() depending on your use case
          .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
