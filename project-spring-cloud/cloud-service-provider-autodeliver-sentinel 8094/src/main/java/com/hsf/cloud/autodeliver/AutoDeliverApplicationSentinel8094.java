package com.hsf.cloud.autodeliver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // 开启Feign
public class AutoDeliverApplicationSentinel8094 {
    public static void main(String[] args) {
        SpringApplication.run(AutoDeliverApplicationSentinel8094.class, args);
    }

}
