package com.gopal.tictask.modules.task.adapter.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gopal.tictask.modules.task.adapter.web.dto.TaskRequestDto;
import com.gopal.tictask.modules.task.adapter.web.dto.TaskResponseDto;
import com.gopal.tictask.modules.task.adapter.web.mapper.TaskWebMapper;
import com.gopal.tictask.modules.task.application.port.inbound.TaskUseCase;
import com.gopal.tictask.modules.task.domain.model.Task;
import com.gopal.tictask.shared.api.ApiResponse;
import com.gopal.tictask.shared.api.PagedResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskUseCase taskService;
    private final TaskWebMapper taskWebMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(@Valid @RequestBody TaskRequestDto request) {
        Task task = taskWebMapper.toDomain(request);
        Task created = taskService.createTask(task);
        TaskResponseDto response = taskWebMapper.toResponseDto(created);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Task created successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> updateTask(@PathVariable Long id,
            @Valid @RequestBody TaskRequestDto request) {
        Task updated = taskService.updateTask(id, taskWebMapper.toDomain(request));
        TaskResponseDto response = taskWebMapper.toResponseDto(updated);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Task updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Task deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> getTask(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        TaskResponseDto response = taskWebMapper.toResponseDto(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Task featch successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponseDto<TaskResponseDto>>> listTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskService.listTasks(pageable);
        Page<TaskResponseDto> dtoPage = tasks.map(taskWebMapper::toResponseDto);
        PagedResponseDto<TaskResponseDto> response = PagedResponseDto.fromPage(dtoPage);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Task featch successfully", response));

    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PagedResponseDto<TaskResponseDto>>> searchTasks(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskService.searchTask(title, pageable);
        Page<TaskResponseDto> dtoPage = tasks.map(taskWebMapper::toResponseDto);
        PagedResponseDto<TaskResponseDto> response = PagedResponseDto.fromPage(dtoPage);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Task featch successfully", response));
    }
}
