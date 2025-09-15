package com.gopal.tictask.modules.auth.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
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
    User toEntity(User domain);

    // Persistence Entity -> Domain User
    User toDomain(User entity);

    // Domain User -> LoginResponseDto (token is handled separately)    
    // Domain User -> LoginResponseDto
    // We ignore token (set in service) but map role -> role (MapStruct will map
    // enum->String via name())

    @Mapping(target = "token", ignore = true)
    LoginResponseDto toLoginResponse(User domain);

}
