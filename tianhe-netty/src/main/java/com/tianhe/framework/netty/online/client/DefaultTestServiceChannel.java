package com.tianhe.framework.netty.online.client;

import io.netty.channel.ChannelHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weifeng.jiang on 2017-11-02 21:41.
 */
public class DefaultTestServiceChannel extends AbstractChannel implements ServiceChannel {

    private ChannelInfo channelInfo;

    @Override
    public void service(String param, ChannelHandler channelHandler) throws Exception {
        TestChannelHandler serviceHandler = new TestChannelHandler(channelInfo);
        super.setIdleStateHandler(new IdleStateHandler(0,65,0));//nettyд��ʱ65��
        super.setChannelInfo(channelInfo);
        super.setCharsetName("UTF-8");
        super.setHandel(channelHandler);
        Map map = new HashMap();
        map.put("content","hello");
        super.connect(map);
    }

    @Override
    public void setChannelInfo(ChannelInfo channelInfo) {
        this.channelInfo = channelInfo;
    }
}
