package com.mahopon.cs2_smokes.user.internal.mapper;

import com.mahopon.cs2_smokes.user.internal.model.User;
import com.mahopon.cs2_smokes.user.internal.model.dto.UpdateProfileRequestDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapping {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateProfileRequestDTO dto, @MappingTarget User entity);
}
