package com.hsf.spi.service.impl;

import com.hsf.spi.service.HelloService;

public class HumanHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "你好，人类";
    }

    @Override
    public String sayHello(String url) {
        return url;
    }
}
