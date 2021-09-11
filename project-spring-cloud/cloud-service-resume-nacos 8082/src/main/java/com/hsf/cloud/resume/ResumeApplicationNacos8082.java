package com.hsf.cloud.resume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan("com.hsf.cloud.common.pojo")
@EnableDiscoveryClient

public class ResumeApplicationNacos8082 {

    public static void main(String[] args) {
        SpringApplication.run(ResumeApplicationNacos8082.class, args);
    }
}
