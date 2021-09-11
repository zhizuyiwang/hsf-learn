package com.hsf.gupao;

public class VolatileDemo {
    private static boolean stop = false;
    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            int i = 0;
            while (!stop){
                i++;
            }
        },"VolatileDemo").start();
        Thread.sleep(1000);
        stop = true;


    }
}
