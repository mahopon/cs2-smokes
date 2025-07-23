package com.mahopon.cs2_smokes.auth.api.service;

import com.mahopon.cs2_smokes.auth.api.model.Auth;
import com.mahopon.cs2_smokes.auth.internal.model.dto.LoginDTO;

import java.util.UUID;

public interface IAuthAPI {
    Auth getAuthByEmail(String email);
    Auth getAuthById(UUID id);
}
