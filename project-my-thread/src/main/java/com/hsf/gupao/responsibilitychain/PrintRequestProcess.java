package com.hsf.gupao.responsibilitychain;

import java.util.concurrent.LinkedBlockingQueue;

public class PrintRequestProcess extends Thread implements IRequestProcess{

    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    private boolean isFinished = false;
    @Override
    public void run() {
        while (!isFinished){
            try {
                Request request = queue.take();
                System.out.println("第三步打印理请求==="+request);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
    @Override
    public void process(Request request) {
        queue.add(request);
    }
}
