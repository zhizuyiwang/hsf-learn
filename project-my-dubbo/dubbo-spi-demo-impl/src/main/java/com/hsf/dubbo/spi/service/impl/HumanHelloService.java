package com.hsf.dubbo.spi.service.impl;

import com.hsf.dubbo.spi.service.HelloService;
import org.apache.dubbo.common.URL;

public class HumanHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "你好，人类";
    }

    @Override
    public String sayHello(URL url) {
        return "human Url";
    }

}
