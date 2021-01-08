package com.tianhe.framework.commons.study.utils;

public class Ean13BarCodeValidate {

/**
 * 超市条形码验证
 * @param args
 * @author: weifeng.jiang
 * @DATE:2016年12月10日 @TIME: 上午12:38:01
 */
	public static void main(String[] args) {//超市扫描商品
		            //0 123456 78901 2    
		String bar ="6932454173525";//5是前面12位算出来的
		int c1=0;
		int c2=0;
		for(int i=0; i<12; i+=2){
			char c = bar.charAt(i);//'6','3','4','4'...
			int n = c-'0';//6,3,4,4...
			c1+=n;
			c2 +=bar.charAt(i+1) - '0';
		}
		int cc = c1+c2*3;
		int code = (10-cc%10)%10;
		System.out.println(code);//5是前面12位算出来的编码
		System.out.println(code==bar.charAt(12)-'0');//比较算出来的编码和第13位码
		//相等不相等
		// TODO Auto-generated method stub

	}
	//public static boolean check(String ean13){
		//return false;根据12位码生成13位条码
	//public static String ean13 (String code12){
	//return " ";
	
	}