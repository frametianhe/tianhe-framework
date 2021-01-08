package com.tianhe.framework.disruptor.study.procon;
  
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
  
/**
 * 生产者
 * 
 * long seq = 0;  
for (int i = 0; i < 20; i++) {  
    try {  
        seq = ringBuffer.next();// 占个坑 ,ringBuffer一个可用区块  
        ringBuffer.get(seq).setValue((long) (Math.random() * 9999));// 给这个区块放入数据  
    } finally {  
        ringBuffer.publish(seq);// 发布这个区块的数据使handler(consumer)可见  
        System.out.println("Producer " + seq);  
    }  
}  

下面的实现是简化形式
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:07:40
 */
public class SimpleEventProducer {  
    private final RingBuffer<SimpleEvent> ringBuffer;  
  
    public SimpleEventProducer(RingBuffer<SimpleEvent> ringBuffer) {  
        this.ringBuffer = ringBuffer;  
    }  
  
    private static final EventTranslatorOneArg TRANSLATOR = new EventTranslatorOneArg<SimpleEvent, String>() {  
        @Override  
        public void translateTo(SimpleEvent event, long sequence, String value) {  
            System.out.println("Producer --- " + sequence + ", event = "+ value);  
            event.setValue(value);  
        }  
    };  
  
   /**
    * onData用来发布事件 
    * @param value
    * @author: weifeng.jiang
    * @DATE:2017年1月1日 @TIME: 上午1:07:32
    */
    public void onData(final String value) {  
        ringBuffer.publishEvent(TRANSLATOR, value);  
    }  
  
}  