package com.tianhe.writtentest.algorithm.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExistRepeat {

    public static void main(String[] args) {

        /**
         * 存在重复元素
         * 给定一个整数数组，判断是否存在重复元素，如果存在一个值在数组中出现至少2次，函数返回true，如果数组中每个元素都不相同，则返回false
         *
         * 示例 1:
         *
         * 输入: [1,2,3,1]
         * 输出: true
         *
         * 示例 2:
         *
         * 输入: [1,2,3,4]
         * 输出: false
         *
         * 示例 3:
         *
         * 输入: [1,1,1,3,3,4,3,2,4,2]
         * 输出: true
         *
         */
        int[] arr = {1,1,2,3,4};
        System.out.println(test01(arr));
        System.out.println(test02(arr));
    }

    /**
     * 排序
     * 在对数字从小到大排序之后，数组的重复元素一定会出现在相邻位置中，可以扫描已排序的数组，
     * 每次判断相邻的元素是否相等，如果相等则说明存在重复的元素
     *
     * 复杂度分析
     * 时间复杂度，O(N logN)，N是数组的长度，需要对数组进行排序
     * 空间复杂度，O(logN)，N为数组长度，应该考虑递归调用栈的深度
     */
    public static boolean test01(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        for (int i=0;i<n-1;i++){
            if(nums[i] == nums[i+1]){
                return true;
            }
        }
        return false;
    }

    /**
     * 对数组的每个元素，我们将它插入到哈希表中，如果插入元素时发现改元素已经存在哈希表中说明存在重复元素
     *
     * 复杂度分析
     * 时间复杂度 O(N)，N 数组的长度
     * 空间复杂度，O(N)，N 数组的长度
     * @param nums
     * @return
     */
    public static boolean test02(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int x : nums){
            if(!set.add(x)){
                return true;
            }
        }
        return false;
    }
}
