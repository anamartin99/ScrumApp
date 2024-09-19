package com.example.ScrumApp2.repository;

import com.example.ScrumApp2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository <User, Long> {
    Optional<User> findByUsername(String username);
}