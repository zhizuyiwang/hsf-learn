package com.hsf.boot.test.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    public static void main(String[] args) {
        boolean debug = Boolean.getBoolean("logback.debug");
        System.out.println(debug);
    }
}
