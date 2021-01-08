package com.tianhe.framework.java.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 * Created by tianhe on 2020/2/19.
 */
public class BubblingSort {

    public static void main(String[] args) {
//        比较两个数，小的冒起来，大的数下沉
//        比较相邻的两个数据，如果第二个数小，就交换位置。从后向前两两比较，一直到比较最前两个数据。最终最小数被交换到起始的位置，这样第一个最小数的位置就排好了。
        int[] arr = new int[5];
        arr[0] = 5;
        arr[1] = 2;
        arr[2] = 1;
        arr[3] = 3;
        arr[4] = 4;
        System.out.println(Arrays.toString(arr));
        sort(arr);
    }

    public static void sort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
//                减少循环次数
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
