package com.gopal.tictask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gopal.tictask.domain.model.PriorityType;
import com.gopal.tictask.domain.model.Task;
import com.gopal.tictask.domain.model.User;
import com.gopal.tictask.domain.repo.TaskRepository;
import com.gopal.tictask.web.dto.TaskRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional
    public Task create(User owner, TaskRequest req) {

        Task t = Task.builder()
                .user(owner)
                .title(req.getTitle())
                .description(req.getDescription())
                .priority(req.getPriorityType() != null ? req.getPriorityType() : PriorityType.MEDIUM)
                .completeStatus(req.getCompleteStatus() != null && req.getCompleteStatus())
                .build();
        return taskRepository.save(t);
    }
    
    public Page<Task> list(User owner, Pageable pageable) {
        return taskRepository.findAllByUser(owner, pageable);
    }

    public Task get(User owner, Long id) {
        return taskRepository.findByIdAndUser(id, owner)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Transactional
    public Task update(User owner, Long id, TaskRequest req) {
        Task t = get(owner, id);
        if (req.getTitle() != null)
            t.setTitle(req.getTitle());
        if (req.getDescription() != null)
            t.setDescription(req.getDescription());
        if (req.getPriorityType() != null)
            t.setPriority(req.getPriorityType());
        if (req.getCompleteStatus() != null)
            t.setCompleteStatus(req.getCompleteStatus());
        return t; // JPA dirty checking will save
    }
    
    @Transactional
    public void delete(User owner, Long id) {
        Task t = get(owner, id);
        taskRepository.delete(t);
    }

}
