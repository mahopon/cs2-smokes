package com.mahopon.cs2_smokes.auth.api.util;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(Key key) {
        this.key = key;
    }

    public String generateAccessToken(UUID id) {
        return Jwts.builder()
                .subject(id.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(UUID id) {
        return Jwts.builder()
                .subject(id.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000))
                .signWith(key)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) key).build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UUID extractId(String token) {
        String id = Jwts.parser().verifyWith((SecretKey) key).build()
                .parseSignedClaims(token).getPayload().getSubject();
        return UUID.fromString(id);
    }

    public String extractToken(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }
}
