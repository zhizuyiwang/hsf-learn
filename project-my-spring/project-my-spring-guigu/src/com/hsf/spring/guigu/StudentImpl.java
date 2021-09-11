package com.hsf.spring.guigu;

import org.springframework.stereotype.Component;

@Component
public class StudentImpl implements Student{
    private String name;
    public void study(){
        System.out.println("开始学习");
    }
    public void eat(){
        System.out.println("开始吃饭");
    }
}
