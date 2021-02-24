package com.hsf.learn.demo.datastructure;

import com.hsf.learn.demo.datastructure.sort.SortUtils;

public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        arrayList.remove(1);
        System.out.println(arrayList);
        int[] kk = {2,1,2,4,8,1,3,5};
        int[] ints = SortUtils.bubbleSort3(kk);
        for (int item : ints){
            System.out.println(item);
        }
    }
}
