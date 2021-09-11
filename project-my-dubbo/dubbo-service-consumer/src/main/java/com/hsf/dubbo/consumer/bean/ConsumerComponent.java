package com.hsf.dubbo.consumer.bean;

import com.hsf.dubbo.api.service.HelloDubbo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class ConsumerComponent {

    @Reference(loadbalance = "onlyFirst")
    HelloDubbo helloDubbo;
    public String hello(String name){
        return helloDubbo.sayHello(name,100);
    }
}
