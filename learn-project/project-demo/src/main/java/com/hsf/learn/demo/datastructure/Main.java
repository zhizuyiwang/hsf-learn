package com.hsf.learn.demo.datastructure;

public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        arrayList.remove(1);
        System.out.println(arrayList);
    }
}
