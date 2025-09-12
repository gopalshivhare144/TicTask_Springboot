package com.gopal.tictask.modules.auth.adapter.mapper;


import org.mapstruct.Mapper;

import com.gopal.tictask.modules.auth.adapter.persistence.entity.UserEntity;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.UserResponse;
import com.gopal.tictask.modules.auth.domain.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
    default User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(entity.getId(), entity.getEmail(), entity.getPassword(),
                entity.getRoles());
    }

    default UserEntity toEntity(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRoles(user.getRoles());
        return entity;
    }

    default UserResponse toResponse(User user) {
        if (user == null) return null;
        return new UserResponse(user.getId(), user.getEmail(), user.getRoles().name());
    }
}
