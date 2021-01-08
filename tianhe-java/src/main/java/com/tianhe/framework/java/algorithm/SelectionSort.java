package com.tianhe.framework.java.algorithm;

import java.util.Arrays;

/**
 * 选择排序
 * Created by tianhe on 2020/2/19.
 */
public class SelectionSort {

//    在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
//    第二次遍历n-2个数，找到最小的数值与第二个元素交换；直到排序完毕

    public static void main(String[] args) {
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
        for (int i=0;i<arr.length-1;i++){
            int smallIndex = i;
            int temp = 0;
            for (int j=i+1;j<arr.length;j++){
                if(arr[j] < arr[smallIndex]){
                    temp = arr[j];
                    arr[j] = arr[smallIndex];
                    arr[smallIndex] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
