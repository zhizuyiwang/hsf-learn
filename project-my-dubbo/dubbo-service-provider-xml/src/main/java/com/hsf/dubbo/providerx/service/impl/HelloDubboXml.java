package com.hsf.dubbo.providerx.service.impl;

import com.hsf.dubbo.api.service.HelloDubbo;

public class HelloDubboXml implements HelloDubbo {
    @Override
    public String sayHello(String name, long timeout) {
        return "你好啊"+name + "==20881";
    }
}
