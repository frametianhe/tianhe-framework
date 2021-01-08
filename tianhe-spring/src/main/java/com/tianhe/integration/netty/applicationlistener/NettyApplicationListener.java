package com.tianhe.integration.netty.applicationlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author: he.tian
 * @time: 2018-08-13 16:40
 */
@Component
@Slf4j
public class NettyApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private AwareNettyServer nettyServer;

    //spring上下文初始化完成后发布刷新完成事件会执行这个方法
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent){
            nettyServer.start();
        }else if(event instanceof ContextClosedEvent){
            try {
                nettyServer.destroy();
            } catch (Exception e) {
                log.error("停止nettyServer服务",e);
            }
        }
    }
}
