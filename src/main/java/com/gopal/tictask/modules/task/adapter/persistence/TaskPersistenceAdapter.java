package com.gopal.tictask.modules.task.adapter.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gopal.tictask.modules.task.adapter.persistence.entity.TaskEntity;
import com.gopal.tictask.modules.task.adapter.persistence.mapper.TaskEntityMapper;
import com.gopal.tictask.modules.task.adapter.persistence.repository.SpringDataTaskRepository;
import com.gopal.tictask.modules.task.application.port.outbound.TaskRepositoryPort;
import com.gopal.tictask.modules.task.domain.model.Task;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskPersistenceAdapter implements TaskRepositoryPort {
    private final SpringDataTaskRepository springDataTaskRepository;
    private final TaskEntityMapper mapper;

    @Override
    public Task save(Task task) {
        TaskEntity entity = mapper.toEntity(task);
        TaskEntity saved = springDataTaskRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return springDataTaskRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteByid(Long id) {
        springDataTaskRepository.deleteById(id);
    }

    @Override
    public Page<Task> findByUserId(Long userId, Pageable pageable) {
        return springDataTaskRepository.findByUserId(userId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Task> searchByTitleAndUserId(String title, Long userId, Pageable pageable) {
        return springDataTaskRepository.searchByTitleAndUserId(title, userId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public List<Task> findByTaskDateAndUserId(LocalDate taskDate, Long userId) {
        return springDataTaskRepository.findByTaskDateAndUserId(taskDate, userId)
                .stream().map(mapper::toDomain).toList();
    }
}
