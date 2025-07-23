package com.mahopon.cs2_smokes.user.internal.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateProfileRequestDTO {
    @NotEmpty
    String name;
}
