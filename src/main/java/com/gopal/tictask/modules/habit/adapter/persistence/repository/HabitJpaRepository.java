package com.gopal.tictask.modules.habit.adapter.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopal.tictask.modules.habit.adapter.persistence.entity.HabitEntity;

public interface HabitJpaRepository extends JpaRepository<HabitEntity, Long> {

    Page<HabitEntity> findByUserId(Long userId, Pageable pageable);

    @Query(value = """
              SELECT * FROM habits h
            WHERE h.user_id = :userId
            AND :date BETWEEN h.start_date AND (h.start_date + (h.goal_days - 1) * INTERVAL '1 day')
            """, nativeQuery = true)
    List<HabitEntity> findActiveHabitsByDate(@Param("userId") Long userId, @Param("date") LocalDate date);

}
