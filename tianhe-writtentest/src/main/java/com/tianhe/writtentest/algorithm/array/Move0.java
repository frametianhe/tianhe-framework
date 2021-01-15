package com.tianhe.writtentest.algorithm.array;

import java.util.Arrays;

public class Move0 {

    public static void main(String[] args) {

        /**
         * 移动0
         * 给定一个数组nums，编写一个函数所有0移动到数组的末尾，同时保持非0元素的相对顺序
         *
         * 示例:
         *
         * 输入: [0,1,0,3,12]
         * 输出: [1,3,12,0,0]
         */
        int[] arr = {0,1,0,3,12};
        test01(arr);
    }

    /**
     * 双指针
     * 使用双指针，左指针指向当前已处理好的序列尾部，右指针指向待处理序列的头部，右指针不断向右移动，每次右指针指向非0数，则将左右指针对应的数交换，同时左指针右移
     * 因为每次交换都是将左指针的0与右指针的非0值进行交换，则非0数的相对顺序并未改变
     * @return
     */
    public static void test01(int[] nums){
        int n = nums.length,left = 0,right = 0;
        while (right < n){
            if(nums[right] != 0){
                swap(nums,left,right);
                left++;
            }
            right++;
        }
        System.out.println(Arrays.toString(nums));
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
