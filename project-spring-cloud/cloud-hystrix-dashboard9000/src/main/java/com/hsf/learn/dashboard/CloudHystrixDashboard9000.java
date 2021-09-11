package com.hsf.learn.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class CloudHystrixDashboard9000{

    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixDashboard9000.class,args);
    }
}
