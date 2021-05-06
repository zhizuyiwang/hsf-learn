package com.hsf.learn.demo.reflect.finaltest;

import java.lang.reflect.Field;

public class Demo {

    public void change(final String str){
        Class<?> v = str.getClass();
        try {
            Field field = v.getDeclaredField("value"); //修改值
            Field field1 = v.getDeclaredField("count"); //修改长度属性
            field1.setAccessible(true);
            field.setAccessible(true);
            Object object = field.get(str);
            char [] charValue = {'s','u','c','c','e','s','s'};
            /*for (int i = 0; i < charValue.length; i++) {
                charValue[i] = 'a';
            }*/
            field1.set(str, charValue.length);
            field.set(str, charValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Demo demo = new Demo();
        final String string = "dd";
        demo.change(string);
        System.out.println(string);

    }

}