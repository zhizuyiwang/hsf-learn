package com.hsf.gupao.responsibilitychain;

public class AppMainProcess {

    public static void main(String[] args) {
        PrintRequestProcess printRequestProcess = new PrintRequestProcess();
        printRequestProcess.start();

        SaveRequestProcess saveRequestProcess = new SaveRequestProcess(printRequestProcess);
        saveRequestProcess.start();

        PreRequestProcess preRequestProcess = new PreRequestProcess(saveRequestProcess);
        preRequestProcess.start();

        Request request = new Request();
        request.setName("hsf");

        preRequestProcess.process(request);

    }
}
