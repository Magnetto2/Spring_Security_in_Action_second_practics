package com.example.spring_security_in_action_second_practics.repository;

import com.example.spring_security_in_action_second_practics.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp,String > {


    Optional<Otp> findOtpByUsername(String s);
}
