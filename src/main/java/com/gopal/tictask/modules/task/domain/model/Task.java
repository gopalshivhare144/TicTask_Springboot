package com.gopal.tictask.modules.task.domain.model;

import java.time.Instant;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean completed;
    private TaskPriority priority;
}
