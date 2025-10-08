package com.gopal.tictask.modules.auth.adapter.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.gopal.tictask.modules.auth.adapter.web.dto.request.LoginRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.modules.auth.domain.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserWebMapper {

    UserWebMapper INSTANCE = Mappers.getMapper(UserWebMapper.class);

  
    LoginResponseDto toLoginResponseDto(User user); //convert user into dto

    User signupRequestToDomain(SignupRequest dto);

    User loginRequestToDomain(LoginRequest loginRequest);

}




