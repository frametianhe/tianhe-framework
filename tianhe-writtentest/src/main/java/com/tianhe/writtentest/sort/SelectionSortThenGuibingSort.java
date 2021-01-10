package com.tianhe.writtentest.sort;

import java.util.Arrays;

/**
 * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，
 * 因为始终都是O(n log n)的时间复杂度。代价是需要额外的内存空间。
 *
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为2-路归并。
 *
 * 把长度为 n 的输入序列分成两个长度为 n / 2 的子序列；
 对这两个子序列分别采用归并排序；
 将两个排序好的子序列合并成一个最终的排序序列。
 *
 * 时间复杂度o(nlogn)
 * 空间复杂度o(n)
 * 稳定
 * Created by tianhe on 2020/3/29.
 */
public class SelectionSortThenGuibingSort {

    public static void main(String[] args) {

        int[] arr = {2,3,5,8,1,6};
        System.out.println(Arrays.toString(arr));
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    private static void sort(int[] arr,int low,int high){
        if(low >= high){
            return;
        }

        //计算中间值
        int mid = low+ ((high - low) >> 1);
//        int mid = (high - low) >> 1; 栈溢出

        //对左边排序
        sort(arr,low,mid);

        //对右边排序
        sort(arr,mid+1,high);

        //归并两个有序的序列
        merge(arr,low,mid,high);
    }

    private static void merge(int[] arr,int low,int mid,int high){
        int[] temp = new int[high-low+1];//临时数组，包左不包右，额外+1
        int left = low;
        int right = mid+1;
        int index = 0;
        //当两个子序列还有元素时，从小到大放入temp[]中
        while (left <= mid && right <= high){
            if(arr[left] < arr[right]){
                temp[index++] = arr[left++];
            }else{
                temp[index++] = arr[right++];
            }
        }

        //要么左边没元素
        while (left <= mid){
            temp[index++] = arr[left++];
        }

        //要么右边没元素
        while (right <= high){
            temp[index++] = arr[right++];
        }

        //重新赋值给对应的区间
        for (int i=0;i<temp.length;i++){
            arr[low+i] = temp[i];
        }
    }
}
