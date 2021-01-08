package com.tianhe.framework.commons.study.utils;

/**
 * 等腰三角形
 * @author:weifeng.jiang
 * @DATE:2016年12月10日 @TIME: 上午12:21:19
 */
public class EquicruralTriangle {
	
	public static void main(String[] args) {
		int x = 3;//3行  要打印的行数                      
    for(int i = 1;i<=3;i++){//3行                
    	for(int j =1;j<=5;j++){//5列          
    		if(j<=(x-i)||j>=(x+i)){//空格输出范围  要输出的行数减去减去行数i          
    			System.out.print(" ");      
    		}else{
    			System.out.print("*");
    		}
    		
    	}
    	System.out.println();
    }
	}

}
