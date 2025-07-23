package com.mahopon.cs2_smokes.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class JwtConfig {
    @Bean
    public Key jwtSigningKey() {
        return Jwts.SIG.HS256.key().build();
    }
}
