package com.gopal.tictask.modules.auth.adapter.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gopal.tictask.modules.auth.adapter.persistence.entity.UserEntity;



public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
