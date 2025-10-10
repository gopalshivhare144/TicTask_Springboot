package com.gopal.tictask.modules.habit.adapter.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.gopal.tictask.modules.habit.adapter.persistence.entity.HabitEntity;
import com.gopal.tictask.modules.habit.adapter.persistence.mapper.HabitEntityMapper;
import com.gopal.tictask.modules.habit.adapter.persistence.repository.HabitJpaRepository;
import com.gopal.tictask.modules.habit.application.port.outbound.HabitRepositoryPort;
import com.gopal.tictask.modules.habit.domain.model.Habit;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HabitRepositoryAdapter implements HabitRepositoryPort {

    private final HabitJpaRepository repository;
    private final HabitEntityMapper mapper;

    @Override
    public Habit save(Habit habit) {
        HabitEntity entity = mapper.toEntity(habit);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Page<Habit> findAllByUserId(Long userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Habit> findActiveHabitsByDate(Long userId, LocalDate date) {
        return repository.findActiveHabitsByDate(userId, date)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

}
