package com.gopal.tictask.modules.task.domain.model;

import java.time.Instant;
import java.time.LocalDate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private LocalDate taskDate;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean completed;
    private TaskPriority priority;
}
