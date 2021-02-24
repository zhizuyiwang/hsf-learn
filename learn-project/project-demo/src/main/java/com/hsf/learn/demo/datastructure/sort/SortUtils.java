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
            while (j >= 0 && temp < arg[j]){
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
     * 二分排序的思想和直接插入排序一样，只是二分排序寻找插入位置的方式不一样。按照二分法查找位置，首先获取序列中最小位置low
     * 和最大位置high，计算出中间位置mid。将中间位置mid的数据与待排序数据data作比较，如果mid位置处的数据大于data，则data
     * 应排在mid的左边，high变为mid-1。如果mid位置处的数据小于data，则data应排在mid的右侧，low变为mid+1。直到
     * low大于等于high时，就确定了data所在的位置
     * 步骤：
     * 1、确定查找的范围low=0，high=array.length-1，计算出中项 mid = (low+high) / 2
     * 2、如果array[mid]=data 或者 low>=high，则查找结束返回，否则继续往下执行
     * 3、如果array[mid]<data，low=mid+1，并重新计算mid,转去执行步骤2；
     *   如果array[mid]>data，high=mid-1，并重新计算mid,转去执行步骤2
     * 4、找到新元素的插入位置后，将其按直接插入排序方法插入即可
     *
     * @param array
     * @return
     */
    public static int[] binInsertSort(int[] array){
        int low, high, mid;
        int temp;
        //从数组的第二个元素开始排序
        for(int i = 1; i < array.length; i++){
            temp = array[i];//缓存要排序的数据
            low = 0;
            high = i -1;
            while (low <= high){
                mid = (high + low) / 2;
                if(array[mid] > temp){
                    high = mid -1;
                }else{
                    low = mid + 1;
                }
            }
            //将a[low]--a[i-1]的数都想后移一位
            for(int j = i; j > low; j--){
                array[j] = array[j-1];
            }
            array[low] = temp;
        }
        return array;
    }

    /**
     * 三、希尔排序
     *
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array){


        return array;
    }

    /**
     * 四、选择排序之简单选择排序
     * 每一趟从待排序的记录中选出关键字最小的记录，顺序放在已排好序的子文件的最后，直到全部记录排序完毕。
     * 如对于一组关键字{K1,K2,…,Kn}，首先从K1,K2,…,Kn中选择最小值，假如它是 Kz，则将Kz与 K1对换；
     * 然后从K2，K3，…，Kn中选择最小值 Kz，再将Kz与K2对换。如此进行选择和调换n-2趟，第(n-1)趟，
     * 从Kn-1、Kn中选择最小值 Kz将Kz与Kn-1对换，最后剩下的就是该序列中的最大值，一个由小到大的有序序列就这样形成。
     * @param array
     * @return
     */
    public static int[] selectSort(int[] array){
        int k, min;
        for(int i = 0; i < array.length - 1; i++){
            k = i;
            min = array[i];
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < min){
                    min = array[j];
                    k = j;
                }
            }
            if(k != i){
                array[k] = array[i];
                array[i] = min;
            }

        }
        return array;
    }

    /**
     * 五、选择排序之堆排序
     * @param array
     * @return
     */
    public static int[] heapSort(int[] array){
        return array;
    }

    /**
     * 六、交换排序之冒泡排序
     * 步骤：
     * 1、比较相邻两个元素，较大的元素排到后面
     * 2、对每一对相邻的元素都比较，从第一对到最后一对，这样最后的元素就是最大的
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        for(int i = 0; i < array.length - 1; i++){
            for (int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 冒泡排序的另一种形式是记录循环一次，已排序序列中值值最小的数的位置
     * @param array
     * @return
     */
    public static int[] bubbleSort2(int[] array){
        int i = array.length - 1;//开始排序时，假设最后一个存最大的
        while (i > 0){
            int flag = 0;//每次循环比较开始时清空上一次循环记录的已排序序列中最小值的位置
            for(int j = 0; j < i; j++){
                if(array[j] > array[j+1]){
                    flag = j;
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
            i = flag;//flag存放已排序序列最小值的位置，也是未排序序列中值最大的位置
        }
        return array;
    }

    /**
     * 冒泡排序第三种方式，每循坏排序一次，正向冒泡一次，记录从最大位置开始已排序序列最小值的位置high，
     * 然后反向冒泡，在从最小位置开始已排序序列中找到值最大的数据的位置low，当 low>=high时，循环结束
     * 这样的好处是一次外部循环，可以把原来就已经有序的序列剔除未排序序列之外
     * @param array
     * @return
     */
    public static int[] bubbleSort3(int[] array){
        int low = 0;//开始时，假设第一个是，从最小位置开始已排序序列的最大值
        int high = array.length - 1;//开始排序时，假设最后一个是，从最大位置开始已排序序列的最小值
        while (low < high){
            int tempLow = 0, tempHigh = 0;//每次循环比较开始时清空最大、最小值的记录
            for(int i = low; i < high; i++){//正向冒泡
                if(array[i] > array[i+1]){
                    int temp = array[i+1];
                    array[i+1] = array[i];
                    array[i] = temp;
                    tempHigh = i;
                }
            }
            high = tempHigh;//flag存放已排序序列最小值的位置，也是未排序序列中值最大的位置

            for(int j = high; j > low; j--){//反向冒泡
                if(array[j] < array[j-1]){
                    int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                    tempLow = j;
                }
            }
            low = tempLow;//flag存放已排序序列最大值的位置，也是未排序序列中值最小的位置

        }
        return array;
    }


    /**
     * 七、交换排序之快速排序
     *
     *
     * @param array
     * @return
     */
    public static int[] quickSort(int[] array){

        return array;
    }

    /**
     * 八、归并排序
     *
     *
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array){

        return array;
    }

    /**
     * 九、基数排序
     *
     *
     * @param array
     * @return
     */
    public static int[] baseSort(int[] array){

        return array;
    }
}
