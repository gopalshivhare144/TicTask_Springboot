package com.gopal.tictask.modules.task.application.port.inbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gopal.tictask.modules.task.domain.model.Task;

public interface TaskUseCase {

    Task createTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    Task getTask(Long id);

    Page<Task> listTasks(Pageable pageable);

    Page<Task> searchTask(String title, Pageable pageable);
    
} 
