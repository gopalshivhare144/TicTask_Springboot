package com.gopal.tictask.modules.task.adapter.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopal.tictask.modules.task.adapter.persistence.entity.TaskEntity;

public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("select t from TaskEntity t where lower(t.title) like lower(concat('%', :title, '%'))")
    Page<TaskEntity> searchByTitle(@Param("title") String title, Pageable pageable);

    List<TaskEntity> findByTaskDate(LocalDate taskDate);
    
}    
