package com.gopal.tictask.modules.habit.application.sevice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gopal.tictask.modules.habit.application.port.inbound.HabitUseCase;
import com.gopal.tictask.modules.habit.application.port.outbound.HabitRepositoryPort;
import com.gopal.tictask.modules.habit.domain.model.Habit;
import com.gopal.tictask.shared.holder.UserContextHolder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitUseCase {

    private final HabitRepositoryPort repository;

    @Override
    public Habit create(Habit habit) {
        habit.setUserId(UserContextHolder.getUserId());
        habit.setCreatedAt(LocalDateTime.now());
        habit.setUpdatedAt(LocalDateTime.now());
        return repository.save(habit);
    }

    @Override
    public Habit update(Long id, Habit habit) {
        habit.setId(id);
        habit.setUserId(UserContextHolder.getUserId());
        habit.setUpdatedAt(LocalDateTime.now());
        return repository.save(habit);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Habit> getAllByUser(Long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }

    public List<Habit> getActiveHabitsByDate(Long userId, LocalDate date) {
        return repository.findActiveHabitsByDate(userId, date);
    }

}
