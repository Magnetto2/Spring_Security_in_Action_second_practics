package com.example.spring_security_in_action_second_practics.repository;

import com.example.spring_security_in_action_second_practics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String username);
}
