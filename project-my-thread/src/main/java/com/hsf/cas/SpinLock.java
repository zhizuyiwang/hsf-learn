package com.hsf.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 用AtomicReference实现自旋锁
 */
public class SpinLock {
    AtomicReference<Thread> lock = new AtomicReference<Thread>();

    public void lock(){
        Thread current = Thread.currentThread();
        //利用CAS
        while (!lock.compareAndSet(null,current)){
            //todo
        }
    }
    public void unLock(){
        Thread current = Thread.currentThread();
        lock.compareAndSet(current,null);
    }
}
