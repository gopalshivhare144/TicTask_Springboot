package com.gopal.tictask.web.controller;

import java.util.stream.Collectors;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.gopal.tictask.domain.model.Task;
import com.gopal.tictask.domain.model.User;
import com.gopal.tictask.service.TaskService;
import com.gopal.tictask.service.UserService;
import com.gopal.tictask.web.dto.ApiResponse;
import com.gopal.tictask.web.dto.TaskRequest;
import com.gopal.tictask.web.dto.TaskResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


//Task CRUD endpoints. All endpoints require authentication (configured in SecurityConfig).
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    private TaskResponse toResponse(Task t) {
        return TaskResponse.builder()
                .id(t.getId())
                .title(t.getTitle())
                .description(t.getDescription())
                .completeStatus(t.isCompleteStatus())
                .priorityType(t.getPriority())
                .createdAt(t.getCreatedAt())
                .build();
    }

    private User currentUser(UserDetails principal) {
        return userService.getByEmail(principal.getUsername());
    }

    @Operation(summary = "Create a task")
    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> create(
        @AuthenticationPrincipal UserDetails principal,@Valid @RequestBody TaskRequest req) {
        Task created = taskService.create(currentUser(principal), req);
        return ResponseEntity.ok(ApiResponse.ok("Task created", toResponse(created)));
    }

    @Operation(summary = "List tasks (paged)")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<TaskResponse>>> list(@AuthenticationPrincipal UserDetails principal,
                                                               @ParameterObject Pageable pageable) {
        Page<Task> page = taskService.list(currentUser(principal), pageable);
        Page<TaskResponse> mapped = new PageImpl<>(
                page.getContent().stream().map(this::toResponse).collect(Collectors.toList()),
                page.getPageable(), page.getTotalElements());
        return ResponseEntity.ok(ApiResponse.ok("Tasks fetched", mapped));
    }

    @Operation(summary = "Get a task by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> get(@AuthenticationPrincipal UserDetails principal,
                                                         @PathVariable Long id) {
        Task t = taskService.get(currentUser(principal), id);
        return ResponseEntity.ok(ApiResponse.ok("Task fetched", toResponse(t)));
    }

    @Operation(summary = "Update a task by id")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> update(@AuthenticationPrincipal UserDetails principal,
                                                            @PathVariable Long id,
                                                            @RequestBody TaskRequest req) {
        Task t = taskService.update(currentUser(principal), id, req);
        return ResponseEntity.ok(ApiResponse.ok("Task updated", toResponse(t)));
    }

    @Operation(summary = "Delete a task by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@AuthenticationPrincipal UserDetails principal,
                                                    @PathVariable Long id) {
        taskService.delete(currentUser(principal), id);
        return ResponseEntity.ok(ApiResponse.ok("Task deleted", null));
    }
}

