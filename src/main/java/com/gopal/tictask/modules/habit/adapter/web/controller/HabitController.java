package com.gopal.tictask.modules.habit.adapter.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.tictask.modules.habit.adapter.web.dto.HabitRequestDto;
import com.gopal.tictask.modules.habit.adapter.web.dto.HabitResponseDto;
import com.gopal.tictask.modules.habit.adapter.web.mapper.HabitWebMapper;
import com.gopal.tictask.modules.habit.application.port.inbound.HabitUseCase;
import com.gopal.tictask.modules.habit.domain.model.Habit;
import com.gopal.tictask.shared.api.ApiResponse;
import com.gopal.tictask.shared.holder.UserContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitUseCase habitUseCase;
    private final HabitWebMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponse<HabitResponseDto>> createHabit(@RequestBody HabitRequestDto dto) {
        Habit habit = mapper.toDomain(dto);
        Habit saved = habitUseCase.create(habit);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Habit created successfully", mapper.toResponse(saved)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<HabitResponseDto>>> getAllHabits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Long userId = UserContextHolder.getUserId();
        Page<HabitResponseDto> habits = habitUseCase.getAllByUser(userId, PageRequest.of(page, size))
                .map(mapper::toResponse);
        return ResponseEntity.ok(ApiResponse.success("Habits fetched successfully", habits));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HabitResponseDto>> updateHabit(
            @PathVariable Long id, @RequestBody HabitRequestDto dto) {
        Habit habit = mapper.toDomain(dto);
        Habit updated = habitUseCase.update(id, habit);
        return ResponseEntity.ok(ApiResponse.success("Habit updated successfully", mapper.toResponse(updated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteHabit(@PathVariable Long id) {
        habitUseCase.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Habit deleted successfully"));
    }

  @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<Habit>>> getActiveHabits(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Long userId = UserContextHolder.getUserId();
        List<Habit> habits = habitUseCase.getActiveHabitsByDate(userId, date);
        return ResponseEntity.ok(ApiResponse.success("Habit fetched successfully", habits));
    }

}
