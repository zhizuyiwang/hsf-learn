package com.hsf.dubbo.provider.service.impl;

import com.hsf.dubbo.api.service.HelloDubbo;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HelloDubboImpl implements HelloDubbo {
    @Override
    public String sayHello(String name,long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return "你好，"+name + "===20880";
    }
}
