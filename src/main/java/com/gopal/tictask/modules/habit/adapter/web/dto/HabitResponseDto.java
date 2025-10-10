package com.gopal.tictask.modules.habit.adapter.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gopal.tictask.modules.habit.domain.model.TimeSection;

import lombok.Data;

@Data
public class HabitResponseDto {
    private Long id;
    private String title;
    private String quote;
    private LocalDate startDate;
    private TimeSection timeSection;
    private boolean isCompleted;
    private int goalDays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
