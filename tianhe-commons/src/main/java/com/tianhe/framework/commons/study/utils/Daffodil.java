package com.tianhe.framework.commons.study.utils;

/**
 * 水仙花
 * @author:weifeng.jiang
 * @DATE:2016年12月10日 @TIME: 上午12:21:09
 */
public class Daffodil {
	
  public static void main(String args[]){
	  //1 获得100--999 之间的3位数
	  for(int num=100;num<=999;num++){
		  int b = num/100;
		  int s = (num%100)/10;
		  int g = num%10;
		 
		  if((b*b*b+s*s*s+g*g*g)==num){
		    System.out.println("水仙花数为 "+num);	  
		  }
		}
  }
}
