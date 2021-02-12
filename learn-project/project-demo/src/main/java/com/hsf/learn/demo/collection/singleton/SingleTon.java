package com.hsf.learn.demo.collection.singleton;


/**
 *     懒汉模式
 */
public class SingleTon {

    private SingleTon(){};

    private static SingleTon instance = null;

    public static synchronized SingleTon getInstance(){
        if(instance == null){
            synchronized (SingleTon.class){
                if(instance == null){
                    instance = new SingleTon();
                }
            }
        }
        return instance;
    }
}
