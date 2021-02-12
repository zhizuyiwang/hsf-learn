package com.hsf.learn.demo.collection.singleton;

/**
 * 饿汉
 */
public class SingleTon3 {
    private SingleTon3(){};

    private static final SingleTon3 instance = new SingleTon3();

    public static SingleTon3 getInstance(){
        return instance;
    }
}
