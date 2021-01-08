
package com.tianhe.framework.netty.study.example.codec.hession;


import com.tianhe.framework.netty.study.example.codec.AbstractMessageDecoder;
import com.tianhe.framework.netty.study.example.codec.MessageCodecService;

/**
 * HessianDecoder.hessian解码器
 * @author he.tian
 */
public class HessianDecoder extends AbstractMessageDecoder {

    public HessianDecoder(final MessageCodecService util) {
        super(util);
    }
}

