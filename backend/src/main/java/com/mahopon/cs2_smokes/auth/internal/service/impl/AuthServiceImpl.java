package com.mahopon.cs2_smokes.auth.internal.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mahopon.cs2_smokes.auth.api.model.Auth;
import com.mahopon.cs2_smokes.auth.api.util.JwtUtil;
import com.mahopon.cs2_smokes.auth.internal.model.dto.LoginDTO;
import com.mahopon.cs2_smokes.auth.internal.repository.AuthRepository;
import com.mahopon.cs2_smokes.auth.internal.service.IAuthService;

@Service("AuthService")
public class AuthServiceImpl implements IAuthService {
    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(AuthRepository authRepository, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Auth getAuthByEmail(String email) {
        Optional<Auth> authOpt = authRepository.findByEmail(email);
        Auth auth = authOpt.orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
        return auth;
    }

    @Override
    public Auth getAuthById(UUID id) {
        Optional<Auth> auth = authRepository.findById(id);
        return auth.get();
    }

    @Override
    public LoginDTO loginAuth(String email) {
        Auth auth = authRepository.findByEmail(email)
            .orElseGet(() -> createAuth(email));

        auth.setLastLogin(LocalDateTime.now());
        authRepository.save(auth);

        UUID id = auth.getId();

        LoginDTO response = new LoginDTO();
        response.setId(id);
        response.setAccessToken(jwtUtil.generateAccessToken(id));
        response.setRefreshToken(jwtUtil.generateAccessToken(id));
        return response;
    }

    private Auth createAuth(String email) {
        Auth newAuth = new Auth();
        newAuth.setEmail(email);
        return authRepository.save(newAuth);
    }

    @Override
    public String refreshToken(String token) {
        UUID id = jwtUtil.extractId(token);
        return jwtUtil.generateRefreshToken(id);
    }
}
