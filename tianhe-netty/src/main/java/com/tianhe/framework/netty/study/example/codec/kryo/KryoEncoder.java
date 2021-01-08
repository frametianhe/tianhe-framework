
package com.tianhe.framework.netty.study.example.codec.kryo;


import com.tianhe.framework.netty.study.example.codec.AbstractMessageEncoder;
import com.tianhe.framework.netty.study.example.codec.MessageCodecService;

/**
 * KryoEncoder.kryo编码器
 * @author xiaoyu
 */
public class KryoEncoder extends AbstractMessageEncoder {

    public KryoEncoder(final MessageCodecService util) {
        super(util);
    }
}
