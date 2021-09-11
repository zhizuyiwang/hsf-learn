package com.hsf.gupao;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DataImportTaskDemo extends Thread{
    private CyclicBarrier cyclicBarrier;
    private String path;

    public DataImportTaskDemo(CyclicBarrier cyclicBarrier, String path){
        this.cyclicBarrier = cyclicBarrier;
        this.path = path;
    }
    @Override
    public void run() {
        try {
            System.out.println("开始导入"+path+"位置处的数据");
            cyclicBarrier.await();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
