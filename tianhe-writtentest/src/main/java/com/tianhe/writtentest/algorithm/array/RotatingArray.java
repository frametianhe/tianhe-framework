package com.tianhe.writtentest.algorithm.array;

import java.util.Arrays;

public class RotatingArray {

    public static void main(String[] args) {

        /**
         * 旋转数组
         *
         * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
         * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
         *
         * 示例1
         * 输入: nums = [1,2,3,4,5,6,7], k = 3
         * 输出: [5,6,7,1,2,3,4]
         * 解释:
         * 向右旋转 1 步: [7,1,2,3,4,5,6]
         * 向右旋转 2 步: [6,7,1,2,3,4,5]
         * 向右旋转 3 步: [5,6,7,1,2,3,4]
         *
         * 示例2
         * 输入：nums = [-1,-100,3,99], k = 2
         * 输出：[3,99,-1,-100]
         * 解释:
         * 向右旋转 1 步: [99,-1,-100,3]
         * 向右旋转 2 步: [3,99,-1,-100]
         */
        int[] arr = {1,2,3,4,5,6,7};
        int[] arr2 = {-1,-100,3,99};
//        rotateTmpArr(arr2,2);
        rotateManyTimes(arr2,2);
    }


    /**
     * 使用临时数组
     * 可以使用一个临时数组，先把原数组的值存放到一个临时数组中，然后再把临时数组的值重新赋给原数组，重新赋值的时候要保证每个元素都要往后移k位，如果超过数组的长度就从头开始，所以这里可以使用(i + k) % length来计算重新赋值的元素下标
     * @param nums
     * @param k
     */
    public static void rotateTmpArr(int nums[],int k){
        int length = nums.length;
        int temp[] = new int[length];
//        把原数组放在临时数组中
        for (int i = 0;i< length;i++){
            temp[i] = nums[i];
        }
//        然后把临时数组的值放到原数组，并且往右移动k位
        for (int i=0; i<length; i++){
            nums[(i+k) % length] = temp[i];
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 多次旋转
     * 先反转全部数组，再反转前k个，最后在反转剩余的
     * @param nums
     * @param k
     */
    public static void rotateManyTimes(int[] nums,int k){
        int length = nums.length;
        k %= length;
        reverse(nums,0,length-1);//先反转全部元素
        reverse(nums,0,k-1);//在反转前k个元素
        reverse(nums,k,length-1);//接着反转剩余的
        System.out.println(Arrays.toString(nums));
    }

    //把数组[start,end]之间的元素两两交换，就是旋转
    public static void reverse(int[] nums,int start,int end){
        while (start < end){
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
