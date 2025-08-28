package com.gopal.tictask.domain.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gopal.tictask.domain.model.Task;
import com.gopal.tictask.domain.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByUser(User user, Pageable pageable);

    Optional<Task> findByIdAndUser(Long id, User user);
    
}
