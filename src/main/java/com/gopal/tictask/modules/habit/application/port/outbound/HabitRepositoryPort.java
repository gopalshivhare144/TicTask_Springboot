package com.gopal.tictask.modules.habit.application.port.outbound;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gopal.tictask.modules.habit.domain.model.Habit;

public interface HabitRepositoryPort {

    Habit save(Habit habit);

    Page<Habit> findAllByUserId(Long userId, Pageable pageable);

    void deleteById(Long id);

    List<Habit> findActiveHabitsByDate(Long userId, LocalDate date);
   
}
