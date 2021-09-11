package com.hsf.boot.test.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class TestConfiguration {
    @Bean
    public Teacher teacher(){
        Teacher teacher = new Teacher();
        teacher.setStudent(student());
        return teacher;
    }
    @Bean
    Student student(){
        Student student = new Student();
        student.setName("hsf123");
        return student;
    }
}
