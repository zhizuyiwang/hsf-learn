package com.hsf.learn.demo.datastructure.sort;

public class SortUtils {
    /**
     * 一、直接插入排序，下面为排序算法
     * （1）从第一个元素开始，该元素可以认为已经被排序，
     * （2）取出下一个元素，在已经排序的元素序列中从后向前扫描，
     * （3）如果该元素（已排序）大于新元素，将该元素移到下一位置，
     * （4）重复步骤3，直到找到已排序的元素小于或者等于新元素的位置，
     * （5）将新元素插入到下一位置中，
     * （6）重复步骤2；
     */
    public static int[] directInsert(int[] arg){
        //从数组第二个元素开始排序
        for(int i = 1; i < arg.length; i++){
            int temp = arg[i]; //缓存待排序的数据
            int j = i -1; //从右向左在有序区a[0...i-1]中找a[i]的插入位置
            //将大于temp的数据后移
            while (j>=0 && temp<arg[j]){
                arg[j+1] = arg[j--];
            }
            //在j+1处插入待排数据
            arg[j+1] = temp;
//            int j = i;
//            while(j >=1  && arg[j] < arg[j-1]){
//                int temp = arg[j-1];
//                arg[j-1] = arg[j];
//                arg[j] = temp;
//                j--;
//            }
        }

        return arg;
    }

    /**
     * 二、二分法插入排序
     * @param array
     * @return
     */
    public static int[] binInsertSort(int[] array){

        return array;
    }

}
