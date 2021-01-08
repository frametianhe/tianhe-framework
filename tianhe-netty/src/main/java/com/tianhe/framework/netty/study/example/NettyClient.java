package com.tianhe.framework.netty.study.example;


import com.tianhe.framework.netty.study.example.codec.kryo.KryoClientChannelInit;

/**
 * @author: he.tian
 * @time: 2018-08-02 11:32
 */
public class NettyClient {

    public static void main(String[] args) {
//        BootstrapUtil.initClient(new DatagramClientChannelInit());
//        BootstrapUtil.initClient(new BaseClientChannelInit());
//        BootstrapUtil.initClient(new ProtoBufClientChannelInit());
//        BootstrapUtil.initClient(new MarshallingClientChannelInit());
//        BootstrapUtil.initClient(new ProtoBufNanoClientChannelInit());
//        BootstrapUtil.initClient(new LineClientChannelInit());
//        BootstrapUtil.initClient(new ProtoBufClientChannelInit());
//        BootstrapUtil.initClient(new StringClientChannelInit());
//        BootstrapUtil.initClient(new ProtobufRaincatClientChannelInit());
//        BootstrapUtil.initClient(new HessianClientChannelInit());
        BootstrapUtil.initClient(new KryoClientChannelInit());
    }
}
