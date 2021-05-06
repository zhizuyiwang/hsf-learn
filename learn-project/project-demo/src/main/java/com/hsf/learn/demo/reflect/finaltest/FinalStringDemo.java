package com.hsf.learn.demo.reflect.finaltest;

import java.lang.reflect.Field;

public class FinalStringDemo {

    public void change(final String name){
        Class<?> strClass = name.getClass();
        try {
            Field value = strClass.getDeclaredField("value");//值属性
            Field count = strClass.getDeclaredField("count");//长度属性
            value.setAccessible(true);
            count.setAccessible(true);
            Object object = value.get(name);
            char [] charValue = {'s','u','c','c','e','s','s'};
            count.set(name,charValue.length);
            value.set(name,charValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        FinalStringDemo finalStringDemo = new FinalStringDemo();
        final String name = "abc";
        System.out.println("before==" + name);
        finalStringDemo.change(name);
        System.out.println("after==" + name);


    }
}
