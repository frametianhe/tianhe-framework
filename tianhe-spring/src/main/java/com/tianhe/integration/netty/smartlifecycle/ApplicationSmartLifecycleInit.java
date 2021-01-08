package com.tianhe.integration.netty.smartlifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author: he.tian
 * @time: 2018-08-13 16:40
 */
@Component
public class ApplicationSmartLifecycleInit implements SmartLifecycle {

    @Autowired
    private AwareNettyServer nettyServer;

//    服务启动标识
    private boolean isRunning;

    private Object lock = new Object();

//    spring启动时是否自动加载
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        synchronized (lock){
            stop();
            callback.run();
        }
    }

    @Override
    public void start() {
        synchronized (lock){
            nettyServer.start();
            isRunning = true;
        }
    }

    @Override
    public void stop() {
        synchronized (lock){
            try {
                nettyServer.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isRunning() {
        synchronized (lock){
            return isRunning;
        }
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }
}
