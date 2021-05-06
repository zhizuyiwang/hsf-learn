package com.hsf.rpc.consumer.boot;

import com.hsf.rpc.common.service.IUserService;
import com.hsf.rpc.consumer.client.RpcConsumer;

public class ConsumerBoot {
    //参数定义
    private static final String PROVIDER_NAME = "UserService#sayHello#";

    public static void main(String[] args) throws InterruptedException {
        //1、创建代理对象
        IUserService userService = (IUserService) RpcConsumer.createProxy(IUserService.class, PROVIDER_NAME);

        //2、循环给服务器写数据
        while (true){
            String result = userService.sayHello("are you ok !!");
            System.out.println(result);
            Thread.sleep(2000);
        }

    }
}
