package com.hsf.learn.redis.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisBootApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisBootApp.class, args);
    }
}
