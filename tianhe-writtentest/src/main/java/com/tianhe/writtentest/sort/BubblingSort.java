package com.tianhe.writtentest.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 时间复杂度 o(n^2)
 * 空间复杂度 o(1)
 * 稳定
 * 从左往右依次排序，右边的区域是有序区域
 * Created by tianhe on 2020/2/19.
 */
public class BubblingSort {

    public static void main(String[] args) {
//        比较两个数，小的冒起来，大的数下沉
//        比较相邻的两个数据，如果第二个数小，就交换位置。从后向前两两比较，一直到比较最前两个数据。最终最小数被交换到起始的位置，这样第一个最小数的位置就排好了。
        int[] arr = {5, 2, 1, 3, 4};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
//            先推算内循环，在推外循环
            for (int j = 0; j < arr.length - i - 1; j++) {// i+1 是右边排序区长度
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
