package com.gopal.tictask.modules.task.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.gopal.tictask.modules.task.adapter.persistence.entity.TaskEntity;
import com.gopal.tictask.modules.task.domain.model.Task;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskEntityMapper {

    TaskEntityMapper INSTANCE = Mappers.getMapper(TaskEntityMapper.class);

    TaskEntity toEntity(Task domain);

    Task toDomain(TaskEntity entity);
}
