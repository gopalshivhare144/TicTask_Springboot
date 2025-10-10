package com.gopal.tictask.modules.habit.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.gopal.tictask.modules.habit.adapter.persistence.entity.HabitEntity;
import com.gopal.tictask.modules.habit.domain.model.Habit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HabitEntityMapper {

    HabitEntityMapper INSTANCE = Mappers.getMapper(HabitEntityMapper.class);

    HabitEntity toEntity(Habit domain);

    Habit toDomain(HabitEntity entity);
}