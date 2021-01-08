package com.tianhe.framework.commons.study.utils;
/*斐波纳提数列 (递归)
   序号 0 1 2 3 4 5 6 7
   值   0 1 1 2 3 5 8 13
   
   做一个函数 输入序号 得到对应斐波纳提数列
   值
   
*/
public class FibonacciSequence {
  public static void main(String args[]){
	  int result = fa(7);
	  System.out.println(result);
  }
  
  public static int fa(int position){
	  if(position==0){
		  return 0;//下标为0返回值为0
	  }else if(position==1){
		  return 1;//下标为1返回值为1
	  }else
	    return fa(position-1)+fa(position-2);//递归，自己调用自己
      } 
  }
