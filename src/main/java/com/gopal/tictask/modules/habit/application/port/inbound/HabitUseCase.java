package com.gopal.tictask.modules.habit.application.port.inbound;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gopal.tictask.modules.habit.domain.model.Habit;

public interface HabitUseCase {
    Habit create(Habit habit);

    Habit update(Long id, Habit habit);

    void delete(Long id);

    Page<Habit> getAllByUser(Long userId, Pageable pageable);

    List<Habit> getActiveHabitsByDate(Long userId, LocalDate date);
}
