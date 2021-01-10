package com.tianhe.writtentest.sort;

import java.util.Arrays;

/**
 * 插入排序
 * Created by tianhe on 2020/2/19.
 */
public class InsertionSort {

    public static void main(String[] args) {

//      在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
//        和打扑克整理扑克的道理一样一样的

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
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j-1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }else{
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
