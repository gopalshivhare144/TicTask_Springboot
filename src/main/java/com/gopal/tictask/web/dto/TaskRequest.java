package com.gopal.tictask.web.dto;
import com.gopal.tictask.domain.model.PriorityType;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank
    private String title;
    private String description;
    private Boolean completeStatus;
    private PriorityType priorityType;
}


