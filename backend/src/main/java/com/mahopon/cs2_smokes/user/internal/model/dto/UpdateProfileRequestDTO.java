package com.mahopon.cs2_smokes.user.internal.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UpdateProfileRequestDTO {
    @NotEmpty
    String name;
}
