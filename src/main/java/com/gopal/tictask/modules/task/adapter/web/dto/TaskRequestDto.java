package com.gopal.tictask.modules.task.adapter.web.dto;

import com.gopal.tictask.modules.task.domain.model.TaskPriority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDto {

    @NotBlank(message = "title is required")
    private String title;

    private String description;

    @NotNull(message = "priority is required")
    private TaskPriority priority;

    @Builder.Default
    private Boolean completed = false;
}
