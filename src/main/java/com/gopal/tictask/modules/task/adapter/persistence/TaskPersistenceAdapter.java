package com.gopal.tictask.modules.task.adapter.persistence;

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
        System.out.println("TaskPersistenceAdpter ====>task " + task);
        TaskEntity entity = mapper.toEntity(task);
        System.out.println("TaskPersistenceAdpter ====>entity " + entity);
        TaskEntity saved = springDataTaskRepository.save(entity);
        System.out.println("TaskPersistenceAdpter ====>saved "+ saved);
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
    public Page<Task> findAll(Pageable pageable) {
        return springDataTaskRepository.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public Page<Task> searchByTitle(String title, Pageable pageable) {
        return springDataTaskRepository.searchByTitle(title, pageable).map(mapper::toDomain);
    }
}
