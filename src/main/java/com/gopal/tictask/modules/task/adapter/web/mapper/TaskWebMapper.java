package com.gopal.tictask.modules.task.adapter.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.gopal.tictask.modules.task.adapter.web.dto.TaskRequestDto;
import com.gopal.tictask.modules.task.adapter.web.dto.TaskResponseDto;
import com.gopal.tictask.modules.task.domain.model.Task;

@Mapper(componentModel = "spring")
public interface TaskWebMapper {

    TaskWebMapper INSTANCE = Mappers.getMapper(TaskWebMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toDomain(TaskRequestDto dto);

    TaskResponseDto toResponseDto(Task domain);
}
