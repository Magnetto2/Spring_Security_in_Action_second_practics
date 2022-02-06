package com.example.spring_security_in_action_second_practics.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Otp {


    @Id
    String username;

    String code;

    public Otp() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
