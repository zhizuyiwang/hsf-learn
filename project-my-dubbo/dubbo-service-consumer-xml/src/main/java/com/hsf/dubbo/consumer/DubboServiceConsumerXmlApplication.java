package com.hsf.dubbo.consumer;

import com.hsf.dubbo.api.service.HelloDubbo;
import lombok.SneakyThrows;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.Future;

public class DubboServiceConsumerXmlApplication {
    @SneakyThrows
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        context.start();
        HelloDubbo helloDubbo = context.getBean(HelloDubbo.class);
        while (true){
         System.in.read();
            String msg = helloDubbo.sayHello("小庄",300);
            Future<Object> future = RpcContext.getContext().getFuture();
            System.out.println("result:"+msg);
            System.out.println("future result:"+ future.get());
        }
    }
}
