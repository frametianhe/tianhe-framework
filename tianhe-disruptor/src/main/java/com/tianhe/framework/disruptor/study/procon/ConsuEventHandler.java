package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.WorkHandler;
  
/**
 * 事件处理对象 消费者
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 下午8:08:33
 */
public class ConsuEventHandler<T> implements WorkHandler<T>{  
  
    @Override  
    public void onEvent(T event) throws Exception {
    	System.out.println(Thread.currentThread().getName()+">>>>>>>>>>> "+event);
    }  
  
}  