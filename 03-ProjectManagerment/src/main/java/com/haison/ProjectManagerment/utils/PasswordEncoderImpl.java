package com.haison.ProjectManagerment.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("haison"));
        System.out.println(passwordEncoder.encode("minhthu"));
        System.out.println(passwordEncoder.encode("hoainam"));
    }
}
