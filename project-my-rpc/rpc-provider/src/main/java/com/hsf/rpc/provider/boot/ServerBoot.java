package com.hsf.rpc.provider.boot;

import com.hsf.rpc.provider.service.UserServiceImpl;

public class ServerBoot {
    public static void main(String[] args) throws InterruptedException {
        UserServiceImpl.startServer("127.0.0.1",8999);
    }
}
