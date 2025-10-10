package com.gopal.tictask.modules.task.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.gopal.tictask.modules.task.application.port.inbound.TaskUseCase;
import com.gopal.tictask.modules.task.application.port.outbound.TaskRepositoryPort;
import com.gopal.tictask.modules.task.domain.model.Task;
import com.gopal.tictask.shared.exception.NotFoundException;
import com.gopal.tictask.shared.exception.ValidationException;
import com.gopal.tictask.shared.holder.UserContextHolder;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Page<Task> listTasks(Pageable pageable) {
        Long userId = UserContextHolder.getUserId();
        return taskRepositoryPort.findByUserId(userId, pageable);
    }

    @Override
    public Page<Task> searchTask(String title, Pageable pageable) {
        Long userId = UserContextHolder.getUserId();
        return taskRepositoryPort.searchByTitleAndUserId(title, userId, pageable);
    }

    @Override
    public List<Task> getTasksByDate(LocalDate taskDate) {
        Long userId = UserContextHolder.getUserId();
        return taskRepositoryPort.findByTaskDateAndUserId(taskDate, userId);
    }

    @Override
    public Task createTask(Task task) {
        Long userId = UserContextHolder.getUserId();
        if (userId == null) {
            throw new RuntimeException("UserId not found in context. Make sure JWT is sent in Authorization header.");
        }
        task.setUserId(UserContextHolder.getUserId());
        validate(task);
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

    private void validate(Task task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new ValidationException("Title must not be empty");
        }
    }
}