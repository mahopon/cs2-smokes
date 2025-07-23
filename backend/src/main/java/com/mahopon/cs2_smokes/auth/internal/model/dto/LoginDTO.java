package com.mahopon.cs2_smokes.auth.internal.model.dto;

import lombok.Setter;

import java.util.UUID;

@Setter
public class LoginDTO {
    public String refreshToken;
    public String accessToken;
    public UUID id;
}
