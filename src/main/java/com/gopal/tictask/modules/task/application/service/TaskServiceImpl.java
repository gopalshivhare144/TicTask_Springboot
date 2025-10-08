package com.gopal.tictask.modules.task.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;    
import org.springframework.stereotype.Service;

import com.gopal.tictask.modules.task.application.port.inbound.TaskUseCase;
import com.gopal.tictask.modules.task.application.port.outbound.TaskRepositoryPort;
import com.gopal.tictask.modules.task.domain.model.Task;
import com.gopal.tictask.shared.exception.NotFoundException;
import com.gopal.tictask.shared.exception.ValidationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    
    @Override
    public Task createTask(Task task) {
        validate(task);
        System.out.println("task data "+task);
        return taskRepositoryPort.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existing = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + id));

        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setCompleted(task.isCompleted());
        existing.setPriority(task.getPriority());
        return taskRepositoryPort.save(existing);
    }

    @Override
    public void deleteTask(Long id) {
        Task existing = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + id));
        taskRepositoryPort.deleteByid(existing.getId());        
    }

    @Override
    public Task getTask(Long id) {
        return taskRepositoryPort.findById(id)
        .orElseThrow(() -> new NotFoundException("Task not found with id: " + id));
    }

    @Override
    public Page<Task> listTasks(Pageable pageable) {
        return taskRepositoryPort.findAll(pageable);
    }

    @Override
    public Page<Task> searchTask(String title, Pageable pageable) {
        return taskRepositoryPort.searchByTitle(title, pageable);
    }

    private void validate(Task task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new ValidationException("Title must not be empty");
        }
    }
    
}
