package com.gopal.tictask.modules.auth.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gopal.tictask.modules.auth.adapter.persistence.entity.UserEntity;
import com.gopal.tictask.modules.auth.domain.model.User;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);
    
    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}