package com.gopal.tictask.modules.habit.adapter.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gopal.tictask.modules.habit.domain.model.TimeSection;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habits")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    private String quote;

    @Column(nullable = false)
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeSection timeSection;

    @Column(nullable = false)
    private boolean isCompleted;

    @Column(nullable = false)
    private int goalDays;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
