package com.mahopon.cs2_smokes.user.internal.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahopon.cs2_smokes.auth.api.util.JwtUtil;
import com.mahopon.cs2_smokes.user.internal.model.User;
import com.mahopon.cs2_smokes.user.internal.model.dto.GetProfileResponseDTO;
import com.mahopon.cs2_smokes.user.internal.model.dto.UpdateProfileRequestDTO;
import com.mahopon.cs2_smokes.user.internal.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    private final JwtUtil jwtUtil;
    
    public UserController(IUserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/")
    public ResponseEntity<GetProfileResponseDTO> profile(@RequestHeader("Authorization") String authorizationHeader) {
        String token = jwtUtil.extractToken(authorizationHeader);
        User user = this.userService.getUser(token);
        GetProfileResponseDTO response = new GetProfileResponseDTO();
        response.setName(user.getName());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/")
    public ResponseEntity<Boolean> updateProfile(@RequestHeader("Authorization") String authorizationHeader, @Valid @ModelAttribute UpdateProfileRequestDTO updateProfileDTO) {
        UUID id = jwtUtil.extractId(authorizationHeader);
        boolean updated;
        try {
            updated = userService.updateUser(id, updateProfileDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(updated);
    }
}
