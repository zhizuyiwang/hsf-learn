package com.hsf.learn.demo.datastructure.customsort;


public class CustomArraysSort {


    /**
     * 真正控制排序逻辑的代码
     * @param arrayData
     */
    public static void Sort(MyComparable[] arrayData){
        for(int i = 1; i < arrayData.length; i++){
            MyComparable tem = arrayData[i];
            int j = i - 1;
            while(j >= 0 && arrayData[j].compare(tem) <= 0){
                arrayData[j+1] = arrayData[j--];
            }
            arrayData[j+1] = tem;
        }

    }

}
