package com.tianhe.writtentest.algorithm.string;

import java.util.Arrays;

public class ReverseStr {

    public static void main(String[] args) {

        /**
         * 反转字符串
         *
         * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
         *
         * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
         *
         * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
         *
         * 示例 1：
         *
         * 输入：["h","e","l","l","o"]
         * 输出：["o","l","l","e","h"]
         *
         * 示例 2：
         *
         * 输入：["H","a","n","n","a","h"]
         * 输出：["h","a","n","n","a","H"]
         *
         */
        char[] arr = {'A','B','C','D','E'};
        System.out.println(Arrays.toString(test01(arr)));
    }

    /**
     * 双指针
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 NN 为字符数组的长度。一共执行了 N/2 次的交换。
     * 空间复杂度：O(1)。只使用了常数空间来存放若干变量。
     * @return
     */
    public static char[] test01(char[] s){
        int n = s.length;
        for (int left = 0,right = n-1;left < right;left++,right--){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
        return s;
    }
}
