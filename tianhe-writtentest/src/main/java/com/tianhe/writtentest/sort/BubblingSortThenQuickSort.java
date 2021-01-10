package com.tianhe.writtentest.sort;

import java.util.Arrays;

/**
 * 快速排序是一种排序执行效率很高的排序算法，它利用分治法来对待排序序列进行分治排序，它的思想主要是通过一趟排序将待排记录分隔成独立的两部分，
 * 其中的一部分比关键字小，后面一部分比关键字大，然后再对这前后的两部分分别采用这种方式进行排序，通过递归的运算最终达到整个序列有序
 *
 * 快速排序之所以比较快，是因为相比冒泡排序，每次的交换都是跳跃式的，每次设置一个基准值，将小于基准值的都交换到左边，
 * 大于基准值的都交换到右边，这样不会像冒泡一样每次都只交换相邻的两个数，因此比较和交换的此数都变少了，速度自然更高。
 * 当然，也有可能出现最坏的情况，就是仍可能相邻的两个数进行交换
 *
 * 时间复杂度 o(nlogn)
 * 空间复杂度 o(nlog2^n)
 * 不稳定
 * 类似2分查找
 * Created by tianhe on 2020/3/29.
 */
public class BubblingSortThenQuickSort {

    public static void main(String[] args) {
        int[] arr = {5,1,2,3,6,7,8};
        System.out.println(Arrays.toString(arr));
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    private static void sort(int[] arr,int left,int right){
        if(left > right){
            return;
        }
        int base = arr[left];
        int i = left,j = right;
        while (i != j){
//            从右往左找，找到比base值大的数
            while (arr[j] >= base && i < j){
                j--;
            }
//            从左往右找，直到找到比base值大的数
            while (arr[i] <= base && i<j){
                i++;
            }

//            找到位置，数据交换
            if(i<j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
//        把基准数放到中间位置
        arr[left] = arr[i];
        arr[i] = base;

//        继续向基准数的左右两边执行响应的逻辑
        sort(arr,left,i-1);
        sort(arr,i+1,right);
    }
}
