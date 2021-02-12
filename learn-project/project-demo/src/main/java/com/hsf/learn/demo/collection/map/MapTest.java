package com.hsf.learn.demo.collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {

        HashMap<String, Object> map = new HashMap<>();

        map.put("name","hsf");
        map.put("age",123);
        map.put("sex","女");


        for (Map.Entry<String, Object> entry : map.entrySet()){
            System.out.println("key=="+entry.getKey()+",value=="+entry.getValue());
        }

        for(String key : map.keySet()){
            System.out.println("key=="+key+",value=="+map.get(key));
        }

        for (Object value : map.values()){
            System.out.println("value=="+value);
        }

        map.forEach((k,v) -> {
            System.out.println("key==="+k+",value==="+v);
        });


    }
}
