package com.tianhe.framework.netty.online.codec.kryo;

/**
 * Created by tianhe on 2019/10/11.
 */
public class KryoDecoder extends AbstractMessageDecoder {

    public KryoDecoder(MessageCoder messageCoder) {
        super(messageCoder);
    }
}
