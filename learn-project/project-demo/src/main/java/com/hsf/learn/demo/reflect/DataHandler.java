package com.hsf.learn.demo.reflect;

public class DataHandler {

    private String msg = "哈哈哈";

    private int age;

    private void printMag(){
        System.out.println(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
