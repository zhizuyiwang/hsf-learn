package com.hsf.gupao.responsibilitychain;

import java.util.concurrent.LinkedBlockingQueue;

public class PreRequestProcess extends Thread implements IRequestProcess{

    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();
    IRequestProcess nexRequestProcess;
    private boolean isFinished = false;

    public PreRequestProcess(IRequestProcess nexRequestProcess){
        this.nexRequestProcess = nexRequestProcess;
    }

    @Override
    public void run() {
        while (!isFinished){
            try {
                Request request = queue.take();
                System.out.println("第一步预处理请求==="+request);
                nexRequestProcess.process(request);
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
