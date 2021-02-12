package com.hsf.learn.common.netty;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyBoot implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        NettyServer3 nettyServer3 = new NettyServer3(8022);
//
//        try {
//            nettyServer3.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
