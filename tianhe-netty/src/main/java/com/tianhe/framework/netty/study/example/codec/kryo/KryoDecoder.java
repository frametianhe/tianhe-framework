
package com.tianhe.framework.netty.study.example.codec.kryo;


import com.tianhe.framework.netty.study.example.codec.AbstractMessageDecoder;
import com.tianhe.framework.netty.study.example.codec.MessageCodecService;

/**
 * KryoDecoder.kryo解码器
 * @author he.tian
 */
public class KryoDecoder extends AbstractMessageDecoder {

    public KryoDecoder(final MessageCodecService service) {
        super(service);
    }
}
