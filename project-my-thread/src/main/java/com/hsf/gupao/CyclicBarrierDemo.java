package com.hsf.gupao;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo extends Thread{

    @Override
    public void run() {
        System.out.println("开始进行数据分析");
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrierDemo());
        new Thread(new DataImportTaskDemo(cyclicBarrier,"1")).start();
        new Thread(new DataImportTaskDemo(cyclicBarrier,"2")).start();
        new Thread(new DataImportTaskDemo(cyclicBarrier,"3")).start();
        new Thread(new DataImportTaskDemo(cyclicBarrier,"4")).start();
        new Thread(new DataImportTaskDemo(cyclicBarrier,"5")).start();
        new Thread(new DataImportTaskDemo(cyclicBarrier,"6")).start();

    }
}
