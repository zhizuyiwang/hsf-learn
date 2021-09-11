package com.hsf.learn.job2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public Student student(){
        return new Student();
    }
}
