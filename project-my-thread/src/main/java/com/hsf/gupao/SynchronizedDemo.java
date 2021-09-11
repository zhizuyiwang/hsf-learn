package com.hsf.gupao;


public class SynchronizedDemo {

    public static void main(String[] args) {
//        waitDemo();
        joinDemo();


    }

    private static void joinDemo(){
        Object lock = new Object();
        Object lock2 = new Object();
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "获得锁，开始处理自己的任务");
                try {
                    Thread.sleep(50000);
                    System.out.println(Thread.currentThread().getName() + "处理完任务，通知其他线程要合并任务");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }, "thread2");
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "获得锁，开始处理自己的任务");
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "处理完任务，阻塞，释放锁，等待其他线程处理完任务");
                    thread2.join();
                    System.out.println(Thread.currentThread().getName() + "阻塞结束，重新获取锁");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "处理完成合并任务，退出");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }

        }, "thread1");
        thread1.start();
        thread2.start();
    }
    private static void waitDemo(){
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"获得锁，开始处理自己的任务");
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"处理完任务，阻塞，释放锁，等待其他线程处理完任务");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName()+"阻塞结束，重新获取锁");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"处理完成合并任务，退出");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }

        },"thread1").start();

        new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"获得锁，开始处理自己的任务");
                try {
                    Thread.sleep(50000);
                    System.out.println(Thread.currentThread().getName()+"处理完任务，通知其他线程要合并任务");
                    lock.notify();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        },"thread2").start();
    }
}
