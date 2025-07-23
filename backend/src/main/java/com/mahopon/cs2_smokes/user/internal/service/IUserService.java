package com.mahopon.cs2_smokes.user.internal.service;

import com.mahopon.cs2_smokes.user.internal.model.User;
import com.mahopon.cs2_smokes.user.internal.model.dto.UpdateProfileRequestDTO;

import java.util.UUID;

public interface IUserService {
    User getUser(String token);
    boolean updateUser(UUID id, UpdateProfileRequestDTO dto);
}
