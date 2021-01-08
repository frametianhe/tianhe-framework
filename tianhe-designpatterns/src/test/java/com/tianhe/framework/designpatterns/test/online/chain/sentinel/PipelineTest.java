package com.tianhe.framework.designpatterns.test.online.chain.sentinel;

import com.tianhe.framework.designpatterns.online.chain.netty.ChannelPipeline;
import com.tianhe.framework.designpatterns.online.chain.netty.DefaultChannelPipeline;
import com.tianhe.framework.designpatterns.online.chain.netty.DemoHandler;
import com.tianhe.framework.designpatterns.online.chain.netty.TestHandler;

/**
 * Created by tianhe on 2019/11/8.
 */
public class PipelineTest {

    public static void main(String[] args) {
        ChannelPipeline pipeline = new DefaultChannelPipeline();
        pipeline.addLast(new DemoHandler());
        pipeline.addLast(new TestHandler());
        pipeline.fireExecute("你好");
    }
}
