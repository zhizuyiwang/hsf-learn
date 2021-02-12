package com.hsf.learn.muxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hsf.learn")
@MapperScan(basePackages = {"com.hsf.learn"})
public class MuXinApplication {
    public static void main(String[] args) {
        SpringApplication.run(MuXinApplication.class, args);
    }
}
