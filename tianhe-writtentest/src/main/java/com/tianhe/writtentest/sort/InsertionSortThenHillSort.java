package com.tianhe.writtentest.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * o(nlogn) < 时间复杂度 < o(n^2) O(n1.3)
 * 空间复杂度o(1)
 * 不稳定
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序，同时该算法是冲破O(n2)的第一批算法之一。
 * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 * Created by tianhe on 2020/3/29.
 */
public class InsertionSortThenHillSort {

    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 3, 4};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        int temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }
}
