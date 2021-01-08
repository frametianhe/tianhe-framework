package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 固定线程池
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:12:16
 */
public class FixCountDemo {

    public static void main(String[] args) {
        ExecutorService executorService = null;
        CountDownLatch end = null;
        int processors = Runtime.getRuntime().availableProcessors();//java虚拟机可用处理器线程数
        //无界队列，执行小数量并发，大数量有可能资源耗尽,分页小点
        executorService = Executors.newFixedThreadPool(processors);//线程工厂数量

        end = new CountDownLatch(10);//一般是数据库查询出list.size()
        for(int i=0;i<10;i++){
            //循环次数一般也是数据库查询list.size()
            try {
            	//实现Callable接口可以扑捉到异常，实现Runable接口不会
    			//线程实现callable接口，重写call方法（不需要返回值），线程池提交用submit方法，会返回future对象
    			//线程实现runnable接口，重写run方法，线程池提交用execute方法
    			//线程可以用匿名内部类，也可以用内部类（要调用主线程的方法需要放在主类中，类似主类的一个方法，主线程通过子线程的构造器
    			//传递参数）
                Future<String> future = executorService.submit(new SyncThread(end));//可以扑捉到异常，execute方法扑捉到异常
                //但是需要实现Thread.UncaughtExceptionHandler接口，默认异常是抛出，异常抛出时事务才会回滚
            } catch (Exception e) {
               e.printStackTrace();
                continue;//实现runnable不会抛异常
            }
        }

        try {
        	 //等待所有子线程执行完毕，在执行主线程,用CountDownLatch计数器实现
            end.await();//主线程阻塞，直到 count.countDown()==0 所有线程执行完毕
        } catch (Exception e) {
            e.printStackTrace();
        }

        //等待所有任务执行完毕，关闭线程池
        executorService.shutdown();

    }

}

 class SyncThread implements Callable<String> {
    private CountDownLatch count;

    public SyncThread(CountDownLatch count) {//业务逻辑处理需要的参数，比如对象需要构造参数传递过来
        super();
        this.count = count;
    }

    public String call() throws Exception {
        try {
            System.out.println("业务逻辑");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            count.countDown();//当前线程调用一次，计数器值-1，倒数计数器
        }
        return null;
    }



}
