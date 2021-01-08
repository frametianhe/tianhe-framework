package com.tianhe.framework.commons.study.utils;

/**
 * 99乘法表
 * @author:weifeng.jiang
 * @DATE:2016年12月10日 @TIME: 上午12:25:02
 */
public class MultiplicationTable {

	public static void main(String[] args) {
		for(int i = 1;i<=9;i++){
			for(int j = 1;j<=i;j++){
				System.out.print(i+"*"+j+"="+i*j+"  ");
				
			}
			System.out.println();
		}

	}

}
