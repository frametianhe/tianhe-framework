package com.tianhe.framework.commons.study.utils;

/**
 * 完数
 * 
 * 如果一个数等于其所有因子之和,
 我们就称这个数为"完数",
 例如 6的因子为1,2,3  6=1+2+3   
 6就是一个完数.
 请编程打印出1000以内所有的完数
 * @author:weifeng.jiang
 * @DATE:2016年12月10日 @TIME: 上午12:21:39
 */
public class PerfectNumber {
	public static void main(String[] args) {
		for (int i = 1; i <= 1000; i++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i) {
				System.out.println(i);
			}

		}
	}

}
