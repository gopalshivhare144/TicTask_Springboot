package com.gopal.tictask.modules.habit.adapter.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.gopal.tictask.modules.habit.adapter.web.dto.HabitRequestDto;
import com.gopal.tictask.modules.habit.adapter.web.dto.HabitResponseDto;
import com.gopal.tictask.modules.habit.domain.model.Habit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HabitWebMapper {

    HabitWebMapper INSTANCE = Mappers.getMapper(HabitWebMapper.class);

    Habit toDomain(HabitRequestDto dto);

    HabitResponseDto toResponse(Habit domain);
}
