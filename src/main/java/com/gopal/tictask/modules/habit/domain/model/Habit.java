package com.gopal.tictask.modules.habit.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Habit {
    private Long id;
    private Long userId;
    private String title;
    private String quote;
    private LocalDate startDate;
    private TimeSection timeSection;
    private boolean isCompleted;
    private int goalDays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
