package com.gopal.tictask.modules.auth.adapter.mapper;

import org.mapstruct.Mapper;

import com.gopal.tictask.modules.auth.adapter.persistence.entity.UserEntity;
import com.gopal.tictask.modules.auth.domain.model.User;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User domain);

    User toDomain(UserEntity saved);
}