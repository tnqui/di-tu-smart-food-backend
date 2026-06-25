package com.tranngocqui.ditusmartfoodbackend.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGen {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        System.out.println(passwordEncoder.encode("28092003"));
    }
}
