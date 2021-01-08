package com.tianhe.framework.netty.online.client;

/**
 * Created by weifeng.jiang on 2017-11-03 10:35.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        DefaultTestServiceChannel handler = new DefaultTestServiceChannel();
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setFrontIp("127.0.0.1");
        channelInfo.setFrontPort("8084");
        channelInfo.setUrl("/demo");
        TestChannelHandler serviceHandler = new TestChannelHandler(channelInfo);
        handler.setChannelInfo(channelInfo);
        handler.service("hello",serviceHandler);
    }
}
