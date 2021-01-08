package com.tianhe.framework.disruptor.study.procon;
  
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
  
/** 
 * EventHandlers是BatchEventProcessor要用到的， WorkHandler是WorkerPool要用到的。为简便放在了一起 
 */  
public class SimpleHandler implements EventHandler<SimpleEvent>,  
        WorkHandler<SimpleEvent> {  //实现一个即可
    private String handlerName;  
  
    public SimpleHandler(String handlerName) {  
        this.handlerName = handlerName;  
    }  
    
    public SimpleHandler() {}
  
    @Override  
    // Override---EventHandler  事件处理，消费者
    public void onEvent(SimpleEvent event, long sequence, boolean endOfBatch)  
            throws Exception {  
//        TimeUnit.MILLISECONDS.sleep(100);  
//        System.out.println(handlerName + " before deal : sequence = "+ sequence + ", Event: " + event.getValue());  
        event.setValue(event.getValue() + "--" + handlerName);  
        System.out.println(Thread.currentThread().getName());
//        System.out.println(handlerName + " after  deal : "+ "Event: " + event.getValue());  
    }  
  
    @Override  
    // Override---WorkHandler  
    public void onEvent(SimpleEvent event) throws Exception {  
//        TimeUnit.MILLISECONDS.sleep(100);  
//        System.out.println(handlerName + " before deal :  Event: "+ event.getValue());  
        event.setValue(event.getValue() + "--" + handlerName);  
//        System.out.println(handlerName + " after  deal : "+ " Event: " + event.getValue());  
    }  
  
}  