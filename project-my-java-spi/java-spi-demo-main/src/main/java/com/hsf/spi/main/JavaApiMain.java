package com.hsf.spi.main;


import com.hsf.spi.service.EatFood;
import com.hsf.spi.service.HelloService;

import java.util.ServiceLoader;

public class JavaApiMain {
    public static void main(String[] args) {
        final ServiceLoader<HelloService> serviceLoader = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : serviceLoader){
            System.out.println(helloService.getClass().getName()+":"+helloService.sayHello());
        }
        final ServiceLoader<EatFood> eatFoodServices = ServiceLoader.load(EatFood.class);
        for (EatFood eatFood : eatFoodServices){
            System.out.println(eatFood.getClass().getName()+":"+eatFood.eat());
        }
    }
}
