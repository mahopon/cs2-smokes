package com.mahopon.cs2_smokes.auth.api.service;

import java.util.UUID;

import com.mahopon.cs2_smokes.auth.api.model.Auth;

public interface IAuthAPI {
    Auth getAuthByEmail(String email);
    Auth getAuthById(UUID id);
}
