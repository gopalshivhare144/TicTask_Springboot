package com.gopal.tictask.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "complete_status", nullable = false)
    @Builder.Default
    private boolean completeStatus = false;


    @Enumerated(EnumType.STRING) //tells JPA how to store the enum, It will store it as a string
    @Column(nullable = false)
    @Builder.Default   // @Builder ignore default value from build so we use here Builder.Default
    private PriorityType priority = PriorityType.MEDIUM;
    
}
