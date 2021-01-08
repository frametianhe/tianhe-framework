package com.tianhe.framework.commons.study.utils;
import java.util.Arrays;
import java.util.Random;

/**
 * 随机生成双色球号码
 * @author:weifeng.jiang
 * @DATE:2016年12月10日 @TIME: 上午12:48:27
 */
public class DoubleColorBall {
	public static void main(String[] args) {
		String[] pools = new String[33];
		for(int i=1;i<pools.length;i++){
			if(i<10){//保证排序正确， 系统排序是一位一位的排序的，4 vs13 升序 13会排在前面 04 13
				//这样的话 升序排列的话 04 13
				pools[i-1]="0"+i;
			}else{
				pools[i-1]=" "+i;
			}
		}
		boolean[] used =new boolean[pools.length];
		String[] balls = new String[6];
		Random r = new Random();
		int i = 0;
		while(true){
			int index = r.nextInt(pools.length);
			if(used[index]){
				continue;
			}
			balls[i++]=pools[index];
			used[index]=true;
			if(i==balls.length){//balls.length等于6时退出
				break;
			}
		}
		
		Arrays.sort(balls);
		balls = Arrays.copyOf(balls, balls.length);
		balls[balls.length-1] = pools[r.nextInt(16)];
		System.out.println(Arrays.toString(balls));
		
	}

}