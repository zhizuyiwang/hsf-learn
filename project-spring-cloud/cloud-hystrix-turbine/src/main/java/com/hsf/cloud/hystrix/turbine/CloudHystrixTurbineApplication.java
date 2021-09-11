package com.hsf.cloud.hystrix.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
public class CloudHystrixTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixTurbineApplication.class,args);
    }
}
