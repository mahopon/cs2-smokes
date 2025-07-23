package com.mahopon.cs2_smokes.config;

import java.security.Key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;

@Configuration
public class JwtConfig {
    @Bean
    public Key jwtSigningKey() {
        return Jwts.SIG.HS256.key().build();
    }
}
