package com.tianhe.writtentest.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度 o(n^2)
 * 空间复杂度 o(1)
 * 不稳定
 * Created by tianhe on 2020/2/19.
 */
public class SelectionSort {

//    在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
//    第二次遍历n-2个数，找到最小的数值与第二个元素交换；直到排序完毕
//    在未排序的序列中找出最小(大)元素与第一个位置的元素交换位置

//    注意选择排序与冒泡排序的区别：冒泡排序通过依次交换相邻两个顺序不合法的元素位置，从而将当前最小（大）元素放到合适的位置；而选择排序每遍历一次都记住了当前最小（大）元素的位置，最后仅需一次交换操作即可将其放到合适的位置。
//    然后在剩下的元素中再找最小(大)元素与第二个元素的位置交换，依此类推，直到所有元素排序排序完成。根据上述描述，一共进行n-1趟比较后，就能完成整个排队过程

    public static void main(String[] args) {
        int[] arr = {5,2,1,3,4};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
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
    }
}
