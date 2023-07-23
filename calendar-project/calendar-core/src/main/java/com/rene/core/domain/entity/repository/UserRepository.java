package com.rene.core.domain.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.rene.core.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
