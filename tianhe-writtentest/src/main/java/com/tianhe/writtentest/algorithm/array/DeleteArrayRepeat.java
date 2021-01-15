package com.tianhe.writtentest.algorithm.array;

import java.util.Arrays;

public class DeleteArrayRepeat {

    public static void main(String[] args) {

        /**
         * 删除数组中重复项
         *
         * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除数组后数组的新长度，不适用额外的数组空间
         * 你必须在原地修改输入数组并在使用o(1)额外空间的条件下完成
         */
        int[] arr = {0,1,1,2,2,3,4};
        int arrLength = deleteRepeat(arr);
        System.out.println(arrLength);
        System.out.println(Arrays.toString(Arrays.copyOf(arr,5)));

    }

//    双指针法，时间复杂度o(n)，假设数组长度是n，i和j最多遍历n步，空间复杂度o(1)
    public static int deleteRepeat(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        int i = 0;
        for (int j = 1;j < nums.length;j++){
            if(nums[j] != nums[i]){//比较相邻2位不相等就交换
                System.out.println(nums[j] + "," + nums[i]);
                i++;
                nums[i] = nums[j];
                System.out.println(Arrays.toString(nums));
            }
        }
        return i + 1;
    }
}
