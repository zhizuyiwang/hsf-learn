package com.hsf.learn.demo.reflect;

import java.lang.reflect.*;

public class ReflectTest {


    public static void main(String[] args) {


        try {
            Class<?> clazz = DataHandler.class;

            Constructor constructor = clazz.getConstructor();

//            DataHandler dataHandler = (DataHandler) constructor.newInstance();
//
//            dataHandler.setMsg("hsf");
//            dataHandler.printMag();

            Object o = constructor.newInstance();
            Field msg = clazz.getDeclaredField("msg");

            Method setMsg = clazz.getMethod("setMsg", String.class);
            Method setAge = clazz.getMethod("setAge", int.class);
            Method printMag = clazz.getDeclaredMethod("printMag");


            printMag.setAccessible(true);


            setMsg.invoke(o,"hsf123");
            setAge.invoke(o,20);
            msg.setAccessible(true);
            msg.set(o,"直接获取属性修改属性的值");
            String name = msg.getName();
            System.out.println(name);
            printMag.invoke(o);

            Class<?> strClz = Class.forName("java.lang.String");
            Object array = Array.newInstance(strClz, 5);
            Array.set(array,0,"hsf0");
            Array.set(array,1,"hsf1");
            Array.set(array,2, "hsf2");
            System.out.println(Array.get(array,1));



        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
