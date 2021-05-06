package com.hsf.cloud.resume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan("com.hsf.cloud.common.pojo")
@EnableDiscoveryClient
@EnableEurekaClient
public class ResumeApplication8080 {

    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication8080.class, args);
    }
}
