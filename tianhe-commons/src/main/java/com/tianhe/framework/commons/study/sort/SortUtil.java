package com.tianhe.framework.commons.study.sort;
import java.util.Arrays;

public class SortUtil {

	public static void main(String[] args) {
		int[] ary = {8,5,2,1,6,4};
		//selectionSort(ary);
		System.out.println(Arrays.toString(selectionSort(ary)));
		//System.out.println(Arrays.toString(ary));
	}
	
	public static void insertSort(int[] ary){
		int i,j,t;
		for( i=1; i<ary.length; i++){
			t=ary[i];
			for(j=i-1;j>=0 && ary[j]>t; j--){
				ary[j+1] = ary[j];
			}
			ary[j+1]=t;
		}
	}
	
	public static void bubbleSort(int[] ary){
		for(int i=0; i<ary.length-1; i++){
			for(int j=0; j<ary.length-i-1; j++){
				if(ary[j]>ary[j+1]){
					int t=ary[j];ary[j]=ary[j+1];ary[j+1]=t;
				}
			}
		}
	}
	
	public static int[] selectionSort(int[] ary){
		for(int i=0; i<ary.length-1; i++){
			for(int j=i+1; j<ary.length; j++){
				if(ary[i]>ary[j]){
					int t=ary[i];ary[i]=ary[j];ary[j]=t;
					
				}
			}
		}
		return ary;
	}

}