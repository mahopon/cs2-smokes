package com.mahopon.cs2_smokes.auth.internal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahopon.cs2_smokes.auth.internal.model.dto.LoginDTO;
import com.mahopon.cs2_smokes.auth.internal.model.dto.RefreshTokenDTO;
import com.mahopon.cs2_smokes.auth.internal.service.IAuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@AuthenticationPrincipal Jwt jwt) { // Checks Authorization header
        String email = jwt.getClaimAsString("email");
        LoginDTO response = this.authService.loginAuth(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenDTO> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String newToken = this.authService.refreshToken(token);
        RefreshTokenDTO response = new RefreshTokenDTO();
        response.setRefreshToken(newToken);
        return ResponseEntity.ok(response);
    }
}
