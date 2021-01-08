package com.tianhe.framework.netty.study.example;


import com.tianhe.framework.netty.study.example.codec.kryo.KryoServerChannelInit;

/**
 * @author: he.tian
 * @time: 2018-08-02 12:24
 */
public class NettyServer {

    public static void main(String[] args) {
//        BootstrapUtil.initServer(new DatagramServerChannelInit());
//        BootstrapUtil.initServer(new BaseServerChannelInit());
//        BootstrapUtil.initServer(new ProtoBufServerChannelInit());
//        BootstrapUtil.initServer(new MarshallingServerChannelInit());
//        BootstrapUtil.initServer(new ProtoBufNanoServerChannelInit());
//        BootstrapUtil.initServer(new LineServerChannelInit());
//        BootstrapUtil.initServer(new ProtoBufServerChannelInit());
//        BootstrapUtil.initServer(new StringServerChannelInit());
//        BootstrapUtil.initServer(new ProtobufRaincatServerChannelInit());
//        BootstrapUtil.initServer(new HessianServerChannelInit());
        BootstrapUtil.initServer(new KryoServerChannelInit());
    }
}
