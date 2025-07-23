package com.mahopon.cs2_smokes.user.internal.service.impl;

import com.mahopon.cs2_smokes.auth.api.model.Auth;
import com.mahopon.cs2_smokes.auth.api.service.IAuthAPI;
import com.mahopon.cs2_smokes.auth.api.util.JwtUtil;
import com.mahopon.cs2_smokes.user.internal.mapper.UserMapping;
import com.mahopon.cs2_smokes.user.internal.model.dto.UpdateProfileRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahopon.cs2_smokes.user.internal.model.User;
import com.mahopon.cs2_smokes.user.internal.repository.UserRepository;
import com.mahopon.cs2_smokes.user.internal.service.IUserService;

import java.util.Optional;
import java.util.UUID;

@Service("UserService")
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final IAuthAPI authService;
    private UserMapping userMapping;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, IAuthAPI authService, UserMapping userMapping) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
        this.userMapping = userMapping;
    }

    private User createUser(UUID id) {
        User newUser = new User();
        Auth auth = authService.getAuthById(id);
        newUser.setId(id);
        newUser.setAuth(auth);
        return userRepository.save(newUser);
    }

    @Override
    public User getUser(String token) {
        UUID id = jwtUtil.extractId(token);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            user = Optional.of(createUser(id));
        }
        return user.get();
    }

    @Override
    public boolean updateUser(UUID id, UpdateProfileRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapping.updateUserFromDto(dto, user);
        userRepository.save(user);
        return true;
    }
}
