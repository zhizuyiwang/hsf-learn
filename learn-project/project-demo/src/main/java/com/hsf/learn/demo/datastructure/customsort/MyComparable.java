package com.hsf.learn.demo.datastructure.customsort;

public interface MyComparable<T> {
    //>0调用者大   <0参数大 ==0一样大
    public int compare(T a);
}
