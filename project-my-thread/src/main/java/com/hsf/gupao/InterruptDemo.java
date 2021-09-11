package com.hsf.gupao;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
//        interruptFlag();
//        interruptReset();
//        interruptResetByException();
        Thread thread = new Thread(() -> {

            System.out.println(Thread.interrupted());
        });
        thread.start();

    }


    /**
     * 通过异常实现线程中断复位操作
     */
    private static void interruptResetByException() throws InterruptedException {
        Thread thread3 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("循环开始==="+new Date());
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("线程没有被终止==="+Thread.currentThread().isInterrupted()+"=="+new Date());
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    System.out.println("出现中断异常，唤醒线程=="+new Date());
//                    break;
                }
            }
            System.out.println("出现异常，结束线程，释放资源=="+Thread.currentThread().isInterrupted());
        },"thread_interrupt");

        thread3.start();
        TimeUnit.SECONDS.sleep(2);
        thread3.interrupt();
        System.out.println("出现异常的线程的中断标识==="+thread3.isInterrupted()+"==="+new Date());
    }
    /**
     * Thread.interrupted()线程中断复位操作
     */
    private static void interruptReset() throws InterruptedException {
        Thread thread2 = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before===线程的interrupt标志位==="+Thread.currentThread().isInterrupted());
                    Thread.interrupted();//对线程的中断标识进行复位
                    System.out.println("after===线程的interrupt标志位==="+Thread.currentThread().isInterrupted());
                }
            }
        },"thread_interrupt");

        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.interrupt();
    }

    /**
     * 通过标志位或者中断操作修改标志位来停止线程，可以有机会使线程停止时释放资源
     * @throws InterruptedException
     */
    private static void interruptFlag() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("线程终止，释放资源==="+i);
        },"thread_interrupt");

        thread1.start();
        TimeUnit.SECONDS.sleep(3);
        thread1.interrupt();
    }
}
