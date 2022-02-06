package com.example.spring_security_in_action_second_practics.services;


import com.example.spring_security_in_action_second_practics.entity.Otp;
import com.example.spring_security_in_action_second_practics.entity.User;
import com.example.spring_security_in_action_second_practics.repository.OtpRepository;
import com.example.spring_security_in_action_second_practics.repository.UserRepository;
import com.example.spring_security_in_action_second_practics.utils.GenerateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.GeneratedValue;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpRepository otpRepository;

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

//Метод проверки аунтефикации
    public void auth(User user){
        Optional<User> o = userRepository.findUserByUsername(user.getUsername());

        if(o.isPresent()){
            User u = o.get();
            if(passwordEncoder.matches(
                    user.getPassword(),
                    u.getPassword())){
                renewOtp(u);

            }else {
                throw new BadCredentialsException("Bad Credentials");
            }

            }else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    //Метод создфния нового OTP
    private void renewOtp(User u) {
        String code = GenerateCodeUtil.generateCode();


        Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());



        if(userOtp.isPresent()){
            Otp otp = userOtp.get();
            otp.setCode(code);
        }else{
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }


    }
    //проверка OTP для пользователя
 public boolean check(Otp otpValidate){
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpValidate.getUsername());

        if(userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otpValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }return false;

 }




}
