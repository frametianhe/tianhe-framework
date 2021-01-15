package com.tianhe.writtentest.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayIntersection {

    public static void main(String[] args) {

        /**
         * 两个数组的交集
         * 给定两个数组，编写一个函数来计算他们的交集
         *
         * 示例 1：
         *
         * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
         * 输出：[2,2]
         *
         * 示例 2:
         *
         * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
         * 输出：[4,9]
         */
        int[] num1 = {1,2,2,1};
        int[] num2 = {2,2};
        System.out.println(Arrays.toString(test01(num1,num2)));
    }

    /**
     * 哈希表
     * 同一个数字在两个数组中都可能出现多次，需要哈希表存储数字出现的次数，对于一个数字，在交集中出现的次数等于改数字在两个数组中出现次数的最小值
     * 遍历第一个数组，在哈希表中记录第一个数组中每个数字以及对应出现的次数，然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在
     * 这个数字则将改数字添加到答案，并减少哈希表中改数字出现的次数
     * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及数字出现的次数，然后遍历较长的数组得到交集
     * @param num1
     * @param num2
     * @return
     */
    public static int[] test01(int[] num1,int[] num2){
        if(num1.length > num2.length){
            return test01(num2,num1);
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : num1){
            int count = map.getOrDefault(num,0) + 1;
            map.put(num,count);
        }
        int[] arr = new int[num1.length];
        int index = 0;
        for (int num : num2){
            int count = map.getOrDefault(num,0);
            if(count > 0){
                arr[index ++] = num;
                count--;
                if(count >0){
                    map.put(num,count);
                }else{
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(arr,0,index);
    }
}
