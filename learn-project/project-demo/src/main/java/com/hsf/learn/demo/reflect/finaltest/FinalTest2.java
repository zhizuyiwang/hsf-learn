package com.hsf.learn.demo.reflect.finaltest;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FinalTest2 {
    public final Integer age = 1;

    public static void main(String[] args) {

        try {
            FinalTest2 finalTest2 = new FinalTest2();
            System.out.println("before=="+finalTest2.age);

            Field age = FinalTest2.class.getDeclaredField("age");
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            // 输出17:表示修饰符为:public final
            System.out.println(age.getModifiers());

            /* 这里就是要修改修饰符了,至于为什么是f.getModifiers() & ~Modifier.FINAL,大家看一下Modifier的源码就知道了*/
            modifiersField.setInt(age,age.getModifiers() & ~Modifier.FINAL);
            // 输出1:表示修饰符已经被修改为:public
            System.out.println(age.getModifiers());
            age.setAccessible(true);

            //修改age字段的修饰符
//            age.setInt(finalTest2,2);
            age.set(finalTest2,2);//自动装箱拆箱
            System.out.println("after=="+finalTest2.age);
            //发现反射没有修改age的值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
