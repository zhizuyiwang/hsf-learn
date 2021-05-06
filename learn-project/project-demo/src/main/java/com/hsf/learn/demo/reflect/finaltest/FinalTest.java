package com.hsf.learn.demo.reflect.finaltest;

import java.lang.reflect.Field;

public class FinalTest {
    public int age = 1;

    public static void main(String[] args) {

        try {
            FinalTest finalTest = new FinalTest();
            System.out.println("before=="+finalTest.age);

            Field age = FinalTest.class.getDeclaredField("age");
            age.setInt(finalTest,2);
//            age.set(finalTest,3);//自动装箱拆箱
            System.out.println("after=="+finalTest.age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
