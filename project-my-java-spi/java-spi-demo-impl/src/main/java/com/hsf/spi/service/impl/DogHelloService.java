package com.hsf.spi.service.impl;

import com.hsf.spi.service.HelloService;

public class DogHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "汪汪汪！";
    }

    @Override
    public String sayHello(String url) {
        return null;
    }
}
