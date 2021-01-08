package com.tianhe.framework.java.algorithm;

/**
 * 二分查找
 * Created by tianhe on 2020/2/19.
 */
public class Binarysearch {

    public static void main(String[] args) {

//        二分查找就是将查找的键和子数组的中间键作比较，如果被查找的键小于中间键，就在左子数组继续查找；如果大于中间键，就在右子数组中查找，否则中间键就是要找的元素。
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;//数组元素必须是有序的
        System.out.println(find(arr,6));
    }

    public static int find(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (key < arr[mid]) {
                right = mid - 1;
            } else if (key > arr[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
