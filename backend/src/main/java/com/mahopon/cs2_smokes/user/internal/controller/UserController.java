package com.mahopon.cs2_smokes.user.internal.controller;

import com.mahopon.cs2_smokes.auth.api.util.JwtUtil;
import com.mahopon.cs2_smokes.user.internal.model.dto.GetProfileResponseDTO;
import com.mahopon.cs2_smokes.user.internal.model.dto.UpdateProfileRequestDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mahopon.cs2_smokes.user.internal.model.User;
import com.mahopon.cs2_smokes.user.internal.service.IUserService;

import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    private final JwtUtil jwtUtil;
    
    @Autowired
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
        try {
            boolean updated = userService.updateUser(id, updateProfileDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }
}
