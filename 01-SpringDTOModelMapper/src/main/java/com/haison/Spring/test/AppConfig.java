package com.haison.Spring.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public IPeople customer() {
        return new Customer("Thu",12,"123sdaj","COnCat","project manager");
    }

    @Bean
    public IPeople student() {
        return new Student("Son",22,"213e809akjs1212","Giao thong van tai");
    }

    @Bean
    public PeopleImpl peopleImpl() {
        return new PeopleImpl(student());
    }
}
