package com.hsf.learn.demo.collection.map;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MapCapacityGet {

    public static void main(String[] args) {
        HashMap<String, Object> m = new HashMap<>(16);
        Class<?> mapType = m.getClass();
        try {
            //反射获取私有属性threshold的值
            Field threshold = mapType.getDeclaredField("threshold");
            //使得私有属性可以被访问
            threshold.setAccessible(true);

            //获取指定方法，因为HashMap没有容量这个属性，但是capacity方法会返回容量值
            Method capacity = mapType.getDeclaredMethod("capacity");
            capacity.setAccessible(true);

            System.out.println("未添加数据时");
            System.out.println("容量=="+capacity.invoke(m)+"  "+"阈值=="+threshold.get(m)+"  "+"size=="+m.size());
            System.out.println("添加数据后");
            for(int i = 0; i<17; i++){
                m.put("name"+i,"hsf");
                System.out.println("容量=="+capacity.invoke(m)+"  "+"阈值=="+threshold.get(m)+"  "+"size=="+m.size());
            }
            System.out.println("减少数据后");
            for(int i = 0; i < 10; i++){
                m.remove("name"+i);
                System.out.println("容量=="+capacity.invoke(m)+"  "+"阈值=="+threshold.get(m)+"  "+"size=="+m.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
