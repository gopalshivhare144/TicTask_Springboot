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

    Page<Task> findAll(Pageable pageable);

    Page<Task> searchByTitle(String title, Pageable pageable);

    List<Task> findByTaskDate(LocalDate taskDate); 
    
} 
