package com.tianhe.framework.java.online.distributed;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能说明：分布式主键
 * @author:weifeng.jiang
 */
@Slf4j
public class IdWorker {

    private final Random random = new Random();
    private int sequence = 0;
    private long lastTimestamp = -1L;
    private Lock lock = new ReentrantLock();

    private final int WORK_ID = 11;
    private final int MAX_WORK_ID = 99;
    private final int SEQUENCE_MASK = 10;
    public  final int WORKER_ID_BITS = 2;
    public  final int SEQUENCE_BITS = 1;
    public  final int TABLE_INDEX = 3;
    private final long INIT_MILLISECONDS = 1493708348776L;
   
    public long getId() {
        if (WORK_ID > MAX_WORK_ID || WORK_ID < 0) {
            log.info("【分布式主键】财务服务编号大于99或者小于0");
            throw new RuntimeException("分布式主键获取财务服务编号大于99或者小于0");
        }
            lock.lock();

        // 偏移的倍数  
        long timestampShiftValue;  
        long workerShiftValue;  
        long sequenceShiftValue;  
        // 随机获取数据库表尾号  
        int tableIndex;
        long timestamp = timeGen();

        try {
            if (timestamp < lastTimestamp) {
                log.info("【分布式主键】本次获取的毫秒数比上一次的毫秒数小，本次毫秒数={},上一次毫秒数={}",timestamp,lastTimestamp);
            }  
            if (lastTimestamp == timestamp) {  
                sequence = (sequence + 1) % SEQUENCE_MASK;
                if (sequence == 0) {  
                    timestamp = tillNextMillis(lastTimestamp);
                }  
            } else {  
                sequence = 0;  
            }  
   
            lastTimestamp = timestamp;
            System.err.println("timetamp         "+(timestamp * 10));
           
            timestampShiftValue = new Double(Math.pow(10, WORKER_ID_BITS + SEQUENCE_BITS + TABLE_INDEX)).longValue();
            workerShiftValue = new Double(Math.pow(10, SEQUENCE_BITS + TABLE_INDEX)).longValue();
            sequenceShiftValue = new Double(Math.pow(10, TABLE_INDEX)).longValue();
           
            tableIndex = random.nextInt(1000);  
        } finally {  
            lock.unlock();
        }  

        return (timestamp + INIT_MILLISECONDS) * timestampShiftValue + WORK_ID * workerShiftValue + sequence * sequenceShiftValue + tableIndex;
    }

    protected long tillNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis() / 10;
    }
}
