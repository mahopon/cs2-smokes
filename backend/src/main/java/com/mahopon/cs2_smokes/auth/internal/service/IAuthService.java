package com.mahopon.cs2_smokes.auth.internal.service;

import com.mahopon.cs2_smokes.auth.api.service.IAuthAPI;
import com.mahopon.cs2_smokes.auth.internal.model.dto.LoginDTO;

public interface IAuthService extends IAuthAPI {
    LoginDTO loginAuth(String email);
    String refreshToken(String token);
}
