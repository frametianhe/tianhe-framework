
package com.tianhe.framework.netty.study.example.codec.protostuff;


import com.tianhe.framework.netty.study.example.codec.AbstractMessageEncoder;
import com.tianhe.framework.netty.study.example.codec.MessageCodecService;

/**
 * ProtostuffEncoder.
 * @author he.tianhe
 */
public class ProtostuffEncoder extends AbstractMessageEncoder {

    public ProtostuffEncoder(final MessageCodecService util) {
        super(util);
    }
}

