package com.tianhe.writtentest.algorithm.string;

public class Str {

    public static void main(String[] args) {

        /*
        给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
         */
    }

    /**
     * 子串逐一比较 - 线性时间复杂度
     * 最直接的方法 - 沿着字符换逐步移动滑动窗口，将窗口内的子串与 needle 字符串比较。
     *
     * 复杂度分析
     *
     * 时间复杂度：O((N - L)L)，其中 N 为 haystack 字符串的长度，L 为 needle 字符串的长度。内循环中比较字符串的复杂度为 L，总共需要比较 (N - L) 次。
     *
     * 空间复杂度：O(1)。
     */
    public static int test01(String s,String str){
        int length = str.length();
        int n = s.length();
        for (int start = 0; start < n - length + 1; start++){
            if(s.substring(start,start + length).equals(str)){
                return start;
            }
        }
        return -1;
    }
}
