package com.tianhe.writtentest.sort;

import java.util.Arrays;

/**
 * Created by tianhe on 2020/3/29.
 */
public class BubblingSortBorder {

    public static void main(String[] args) {

//        记录顺序区域边界，6，7，8，9 不用排序
        int[] arr = {5, 1, 3, 4, 2, 6, 7, 8, 9};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void sort(int[] arr) {
        int temp = 0;
        int border = arr.length - 1;
        int lastCompareIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < border; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                    lastCompareIndex = j;
                }
            }
            border = lastCompareIndex;
            if (flag) {
                break;
            }
        }
    }
}
