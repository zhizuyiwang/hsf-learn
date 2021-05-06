package com.hsf.learn.demo.reflect.finaltest;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FinalStringTest {

    public final String name = "a";//直接给字符串赋值字面量，通过反射也修改不了final属性的值
//    public final String name = new String("a");

    public static void main(String[] args) {
        FinalStringTest finalStringTest = new FinalStringTest();
        System.out.println("before=="+finalStringTest.name);

        try {
            Field name = FinalStringTest.class.getDeclaredField("name");
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            System.out.println(name.getModifiers());
            modifiers.setInt(name,name.getModifiers() & ~Modifier.FINAL);
            System.out.println(name.getModifiers());
            name.setAccessible(true);
            name.set(finalStringTest,"b");
            System.out.println("after=="+finalStringTest.name);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
