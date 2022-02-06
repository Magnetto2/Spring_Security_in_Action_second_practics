package com.example.spring_security_in_action_second_practics.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {// Класс для создания рандомного кода
    private GenerateCodeUtil(){};

    public static String generateCode(){
        String code;

        try{
            SecureRandom  random = SecureRandom.getInstanceStrong();

            int c = random.nextInt(9000) + 1000;
            code = String.valueOf(c);


        } catch (NoSuchAlgorithmException e) {
            throw  new RuntimeException("Problem when generating the random code");
        }
return code;

    }

}
