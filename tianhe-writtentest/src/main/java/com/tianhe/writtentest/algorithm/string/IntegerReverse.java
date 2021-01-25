package com.tianhe.writtentest.algorithm.string;

public class IntegerReverse {

    public static void main(String[] args) {

        /**
         * 整数反转
         *
         * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
         *
         * 示例 1：
         *
         * 输入：x = 123
         * 输出：321
         *
         * 示例 2：
         *
         * 输入：x = -123
         * 输出：-321
         *
         * 示例 3：
         *
         * 输入：x = 120
         * 输出：21
         *
         * 示例 4：
         *
         * 输入：x = 0
         * 输出：0
         */
        System.out.println(test01(123));
    }

    /**
     * 弹出和推入数字 & 溢出前进行检查
     * 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     * @return
     */
    public static int test01(int x){
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
            x /= 10;
            //溢出判断
            if(rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)){
                return 0;
            }
            if(rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
