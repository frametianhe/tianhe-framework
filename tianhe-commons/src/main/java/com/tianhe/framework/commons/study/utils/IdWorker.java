package com.tianhe.framework.commons.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能说明：单机QPS为1w
 * @author:weifeng.jiang
 */
public class IdWorker {

    private final long initMilliseconds = 1493708348776L;
    private final int workerIdBits = 2;
    // 机器编号，十进制两位，0-99
    private final int maxWorkerId = 99;
    private final int sequenceBits = 1;
    // 毫秒内序列号
    private final int sequenceMask = 10;
    // 数据库表尾号0-999，共3位  
    private final int tableIndexBits = 3;  
    // 十进制偏移量  
    private final int sequenceShift = tableIndexBits;  
    private final int workerIdShift = sequenceBits + tableIndexBits;  
    private final int timestampLeftShift = workerIdBits + sequenceBits + tableIndexBits;  
   
    private final Random random = new Random();
   
    private int workerId;  
    private int sequence = 0;  
    private long lastTimestamp = -1L;
    private Lock lock = new ReentrantLock();
   
   
    public IdWorker(int workerId) {
        if (workerId > maxWorkerId || workerId < 0) {  
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));  
        }  
        this.workerId = workerId;  
    }  
   
    public long nextId() {  
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
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));  
            }  
            if (lastTimestamp == timestamp) {  
                sequence = (sequence + 1) % sequenceMask;  
                if (sequence == 0) {  
                    timestamp = tilNextMillis(lastTimestamp);  
                }  
            } else {  
                sequence = 0;  
            }  
   
            lastTimestamp = timestamp;  
           
            timestampShiftValue = new Double(Math.pow(10, timestampLeftShift)).longValue();  
            workerShiftValue = new Double(Math.pow(10, workerIdShift)).longValue();  
            sequenceShiftValue = new Double(Math.pow(10, sequenceShift)).longValue();  
           
            tableIndex = random.nextInt(1000);  
        } finally {  
                lock.unlock();  
        }  

        long id = (timestamp + initMilliseconds) * timestampShiftValue + workerId * workerShiftValue + sequence * sequenceShiftValue + tableIndex;
        System.out.println("==================================");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(timestamp * 10)));
        System.out.println("timestamp           "+timestamp);
        System.out.println("timestamp2          "+((id-tableIndex-0-(workerId * workerShiftValue))/timestampShiftValue-initMilliseconds));
        System.out.println("==================================");
        return id;
    }  
   
    protected long tilNextMillis(long lastTimestamp) {  
        long timestamp = timeGen();  
        while (timestamp <= lastTimestamp) {  
            timestamp = timeGen();  
        }  
        return timestamp;  
    }  
   
    protected long timeGen() {  
        return System.currentTimeMillis() / 10;  
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(new Date().getTime());
        IdWorker worker = new IdWorker(11);
        System.out.println(worker.nextId());
    }
}  