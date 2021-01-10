package com.tianhe.writtentest.procon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueProCon {

	public static void main(String[] args){

		BlockingQueue queue = new ArrayBlockingQueue(10);

		new Thread(){

			public void run(){

				for(;;){

					try{
                        System.out.println(queue.take());

				}catch(Exception e){
					e.printStackTrace();
				}

				}
			}
		}.start();

		new Thread(){

			public void run(){

				for(;;){

					try{

						queue.put("a");

					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
