package com.hsf.learn.demo.collection.singleton;


/**
 * 静态内部类
 */
public class SingleTon2 {

    private SingleTon2(){}

    private static class LazyHolder{
        private static final SingleTon2 INSTANCE = new SingleTon2();
    }

    public static final SingleTon2 getInstance(){
        return LazyHolder.INSTANCE;
    }
}
