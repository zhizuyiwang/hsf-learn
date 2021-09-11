package com.hsf.dubbo.spi.service.impl;

import com.hsf.dubbo.spi.service.HelloService;
import org.apache.dubbo.common.URL;

public class DogHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "汪汪汪！";
    }

    @Override
    public String sayHello(URL url) {
        return "汪 url";
    }


}
