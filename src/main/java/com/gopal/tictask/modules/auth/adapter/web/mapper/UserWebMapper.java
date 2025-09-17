package com.gopal.tictask.modules.auth.adapter.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.modules.auth.domain.model.User;

@Mapper(componentModel = "spring")
public interface UserWebMapper {

    @Mapping(target = "token", ignore = true)
    LoginResponseDto toLoginResponse(User user); //convert user into dto


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", expression = "java(Roles.USER)")
    User signupRequestToDomain(SignupRequest dto);

}




