package com.gopal.tictask.modules.task.application.port.outbound;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gopal.tictask.modules.task.domain.model.Task;

public interface TaskRepositoryPort {

    Task save(Task task);

    Optional<Task> findById(Long id);

    void deleteByid(Long id);

    Page<Task> findByUserId(Long userId, Pageable pageable);

    Page<Task> searchByTitleAndUserId(String title, Long userId, Pageable pageable);

    List<Task> findByTaskDateAndUserId(LocalDate taskDate, Long userId);
    
} 
