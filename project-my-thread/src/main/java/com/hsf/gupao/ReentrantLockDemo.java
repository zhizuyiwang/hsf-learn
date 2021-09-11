package com.hsf.gupao;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        new Thread(() -> {
            reentrantLock.lock();
        }).start();
    }


}
