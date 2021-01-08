package com.tianhe.framework.java.study.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
 
/**
 * 多个线程并发线程消费一个集合
 * @author:weifeng.jiang
 * @DATE:2016年12月29日 @TIME: 上午10:58:51
 */
public class MoreThreadCostList {
 
	LinkedList<Integer> list = new LinkedList<Integer>();
	
	public void execute(){
		
        for(int i=0;i<10;i++){
        	list.add(i);
        }
         
        for (int i=0;i<2;i++) {
              new Thread(new Run(list)).start();
        }
	}
	
    public static void main(String[] args){
    	new MoreThreadCostList().execute();
    }
}  

    class Run implements Runnable{
    	LinkedList<Integer> list;
        public Run(LinkedList<Integer> list){
           this.list=list;
        }
     
        @Override 
        public void run() { 
            Integer num=null;
            while(true){
                synchronized (list) { 
                	try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    if(!list.isEmpty())
                    	num=list.removeFirst();
                    else
                        break;
                }  
                System.out.println(Thread.currentThread().getName()+"=========="+num);              
            }
        }
    }