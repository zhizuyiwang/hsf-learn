package com.hsf.dubbo.consumer;

import com.hsf.dubbo.consumer.bean.ConsumerComponent;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

public class ConsumerBootApplication {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConsumerConfig.class);
        context.start();

        ConsumerComponent service = context.getBean(ConsumerComponent.class);
        while (true){
            try {
                System.in.read();
                String msg = service.hello("hsf");
                System.out.println("result:"+msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.hsf.dubbo.consumer.bean")
    @PropertySource("classpath:/dubbo-consumer.properties")
    @ComponentScan(value = {"com.hsf.dubbo.consumer"})
    static class ConsumerConfig{

    }

}
