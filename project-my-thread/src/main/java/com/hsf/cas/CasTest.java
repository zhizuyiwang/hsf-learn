package com.hsf.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasTest {
    private static AtomicReference<String> atomicReference = new AtomicReference<>("A");
    private static AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("A", 1);
    public static void main(String[] args) {
//        atomicTest();
//        spinLockTest();
        abaTest();

    }

    public static void abaTest(){
        int stamp = stampedReference.getStamp();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"获取的版本号"+stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            atomicReference.compareAndSet("A","B");
            atomicReference.compareAndSet("B","A");
            stampedReference.compareAndSet("A","B", stamp,stamp+1);
            stampedReference.compareAndSet("B","A",stamp+1,stamp+2);
            System.out.println(Thread.currentThread().getName()+"操作后的版本号"+stampedReference.getStamp());

        },"t1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"获取的版本号"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"普通原子类操作"+atomicReference.compareAndSet("A","C"));
            System.out.println(Thread.currentThread().getName()+"带版本号的原子类操作"+stampedReference.
                    compareAndSet("A","C",stamp,stamp+1));



        },"t2").start();
    }
    public static void atomicTest(){
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println("当前值"+atomicInteger);
        atomicInteger.compareAndSet(5,10);
        System.out.println("更新后的值"+atomicInteger);
        int i = atomicInteger.incrementAndGet();
        System.out.println(i);
    }
    public static void spinLockTest(){
        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.lock();
            System.out.println("线程一获得自旋锁");
            try {
                Thread.sleep(3000);
                System.out.println("线程一执行完毕");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }finally {
                spinLock.unLock();
                System.out.println("线程一释放锁");
            }
        }).start();
        new Thread(() -> {
            spinLock.lock();
            System.out.println("线程二获得自旋锁");
            try {
                Thread.sleep(3000);
                System.out.println("线程二执行完毕");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }finally {
                spinLock.unLock();
                System.out.println("线程二释放锁");
            }
        }).start();
        new Thread(() -> {
            spinLock.lock();
            System.out.println("线程三获得自旋锁");
            try {
                Thread.sleep(3000);
                System.out.println("线程三执行完毕");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }finally {
                spinLock.unLock();
                System.out.println("线程三释放锁");
            }
        }).start();
        new Thread(() -> {
            spinLock.lock();
            System.out.println("线程四获得自旋锁");
            try {
                Thread.sleep(3000);
                System.out.println("线程四执行完毕");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }finally {
                spinLock.unLock();
                System.out.println("线程四释放锁");
            }
        }).start();
    }
}
