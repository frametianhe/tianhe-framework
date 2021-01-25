package com.tianhe.writtentest.algorithm.string;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueStr {

    public static void main(String[] args) {

        /**
         * 字符串中的第一个唯一字符
         * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
         *
         * 示例：
         *
         * s = "leetcode"
         * 返回 0
         *
         * s = "loveleetcode"
         * 返回 2
         */
    }

    /*
    使用哈希表存储频数
    我们可以对字符串进行两次遍历。

在第一次遍历时，我们使用哈希映射统计出字符串中每个字符出现的次数。在第二次遍历时，我们只要遍历到了一个只出现一次的字符，
那么就返回它的索引，否则在遍历结束后返回 -1−1。

复杂度分析
时间复杂度 o(n)，n是字符串长度，需要遍历2遍,空间复杂度，字符串个数
     */
    public static int test01(String s){
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for (int i = 0; i < s.length(); i++){
            if(map.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }
}
