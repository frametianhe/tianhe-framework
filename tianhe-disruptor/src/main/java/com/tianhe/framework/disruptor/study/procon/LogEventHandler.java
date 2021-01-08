package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者
 * @author:weifeng.jiang
 * @DATE:2016年12月31日 @TIME: 下午9:14:11
 */
public class LogEventHandler implements EventHandler<LogEvent> {

    private long startTime;
    private int i;

    public LogEventHandler() {
        this.startTime = System.currentTimeMillis();
    }

    public void onEvent(LogEvent logEvent, long seq, boolean bool)
            throws Exception {
        i++;
        if (i == 5000000) {
            long endTime = System.currentTimeMillis();
            System.out.println(" costTime = " + (endTime - startTime) + "ms");
        }
    }

}