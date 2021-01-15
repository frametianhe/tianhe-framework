package com.tianhe.writtentest;

/**
 * 泰波那契序列 Tn 定义如下： 
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。n<=38
 * <p>
 * Created by tianhe on 2020/2/19.
 */
public class Fibonacci {

    public static void main(String[] args) {

//        int sum = sum(5);
        int sum = new Solution().sum(5);
        System.out.println(sum);

    }

    //        简单实现 复杂度o(n)
    public static int sum(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return sum(n - 1) + sum(n - 2);
        }
    }

}

//        时间复杂度：o(1)，预计算 39 个斐波那契数字，并在数组中检索。
//        空间复杂度：o(1)，存储 38 个斐波那契数字的数组。
class Tri {
    private int n = 38;
    public int[] nums = new int[n];

    Tri() {
        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 1;
        for (int i = 3; i < n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
    }
}

class Solution {
    public static Tri t = new Tri();

    public int sum(int n) {
        return t.nums[n];
    }
}
