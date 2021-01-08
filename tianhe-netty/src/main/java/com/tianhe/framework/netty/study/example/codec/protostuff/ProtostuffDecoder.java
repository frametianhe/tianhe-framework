
package com.tianhe.framework.netty.study.example.codec.protostuff;


import com.tianhe.framework.netty.study.example.codec.AbstractMessageDecoder;
import com.tianhe.framework.netty.study.example.codec.MessageCodecService;

/**
 * ProtostuffDecoder.
 * @author he.tian
 */
public class ProtostuffDecoder extends AbstractMessageDecoder {

    public ProtostuffDecoder(final MessageCodecService util) {
        super(util);
    }
}

