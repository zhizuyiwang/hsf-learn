package com.hsf.learn.demo.collection.singleton;

/**
 * 静态代码块
 */
public class SingleTon4 {

    private SingleTon4(){}
    private static final SingleTon4 instance;

    static {
        instance = new SingleTon4();
    }

    public static final SingleTon4 getInstance(){
        return instance;
    }

}
