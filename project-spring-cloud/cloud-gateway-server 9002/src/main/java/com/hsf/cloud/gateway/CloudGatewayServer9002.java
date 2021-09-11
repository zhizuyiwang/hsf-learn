package com.hsf.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewayServer9002 {
    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayServer9002.class, args);
    }
}
