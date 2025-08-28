package com.gopal.tictask.web.dto;

import java.time.Instant;

import com.gopal.tictask.domain.model.PriorityType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private boolean completeStatus;
    private PriorityType priorityType;
    private Instant createdAt;
}
