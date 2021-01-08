package com.tianhe.framework.netty.online.codec.kryo;

/**
 * Created by tianhe on 2019/10/11.
 */
public class KryoEncoder extends AbstractMessageEncoder {

    public KryoEncoder(final MessageCoder messageCoder) {
        super(messageCoder);
    }
}
