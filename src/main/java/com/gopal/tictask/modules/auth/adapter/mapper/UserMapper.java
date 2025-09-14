package com.gopal.tictask.modules.auth.adapter.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.gopal.tictask.modules.auth.adapter.persistence.entity.UserEntity;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.modules.auth.domain.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // SignupRequestDto -> Domain User
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", constant = "USER")
    User signupRequestToDomain(SignupRequest dto);

    // Domain User -> Persistence Entity
    UserEntity toEntity(User domain);

    // Persistence Entity -> Domain User
    User toDomain(UserEntity entity);

    // Domain User -> LoginResponseDto (token is handled separately)
    @Mapping(target = "token", ignore = true)
    LoginResponseDto toLoginResponse(User domain);
}
