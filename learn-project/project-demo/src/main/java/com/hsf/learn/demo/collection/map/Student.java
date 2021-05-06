package com.hsf.learn.demo.collection.map;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Student {
    public String name;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            while (!serverSocket.isClosed()){
                Socket accept = serverSocket.accept();
                handle(accept);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handle(Socket accept) {
        System.out.println("收到连接");
        AtomicInteger index = new AtomicInteger();
        new Thread(() -> {
            boolean isFlag = true;
            while (isFlag){
                System.out.println("isConnected:"+accept.isConnected());
                System.out.println("isClosed:"+accept.isClosed());
                System.out.println("isBound:"+accept.isBound());
                index.getAndIncrement();
                try {
                    OutputStream outputStream = accept.getOutputStream();
                    String info = "TCP测试"+index+"\n";
                    System.out.println("发送数据+++"+info);
                    outputStream.write(info.getBytes("UTF-8"));

                    Thread.sleep(2000);
                } catch (Exception exception) {
                    isFlag = false;
                    exception.printStackTrace();
                }
            }
        }).start();

    }
}
