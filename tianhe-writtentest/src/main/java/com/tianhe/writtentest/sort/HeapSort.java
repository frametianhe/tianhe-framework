package com.tianhe.writtentest.sort;

import java.util.Arrays;

/**
 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，
 * 并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 *
 * 堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
 * 或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 *
 * 我们对堆中的结点按层进行编号
 * 该数组从逻辑上讲就是一个堆结构，我们用简单的公式来描述一下堆的定义就是：

 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]

 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]

 堆排序的基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了

 1、将给定无序序列构造成一个大顶堆（一般升序采用大顶堆，降序采用小顶堆)。
 2、将堆顶元素与末尾元素进行交换，使末尾元素最大。然后继续调整堆，再将堆顶元素与末尾元素交换，得到第二大元素。如此反复进行交换、重建、交换

 再简单总结下堆排序的基本思路：

 　　a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;

 　　b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;

 　　c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。

时间复杂度 o(nlog2^n)
空间复杂度o(1)
不稳定

 *
 * Created by tianhe on 2020/3/30.
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {5,1,2,3,4};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr){
        //构建大顶堆
        for(int i = arr.length/2-1;i>=0;i--){
            //从第一个非叶子节点从下往上，从右往左调整结构
            adjustHeap(arr,i,arr.length);
        }
        //调整堆结构，交换堆顶元素与末尾元素
        for (int j = arr.length-1;j>0;j--){
            swap(arr,0,j);//调整堆顶元素与末尾元素交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }
    }

    private static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];//取出当前元素
        for (int k = i*2+1;k<length;k=k*2+1){
            //从i的左子节点开始，2i+1处
            if(k+1 < length && arr[k] < arr[k+1]){
                //如果左子节点小于右子节点，k指向右子节点
                k++;
            }
            if(arr[k] > temp){
                //如果子节点大于父节点，将子节点赋值给父节点，不进行交换
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
}
