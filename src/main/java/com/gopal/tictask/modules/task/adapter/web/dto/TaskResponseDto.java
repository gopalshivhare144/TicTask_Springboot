package com.gopal.tictask.modules.task.adapter.web.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.gopal.tictask.modules.task.domain.model.TaskPriority;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate taskDate;
    private boolean completed;
    private TaskPriority priority;
    private Instant createdAt;
    private Instant updatedAt;
}
