package com.hsf.learn.gmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hsf.learn")
public class GmallManageWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GmallManageWebApplication.class, args);
    }
}
