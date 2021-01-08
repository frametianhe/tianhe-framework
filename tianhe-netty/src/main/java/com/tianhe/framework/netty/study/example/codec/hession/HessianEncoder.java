
package com.tianhe.framework.netty.study.example.codec.hession;


import com.tianhe.framework.netty.study.example.codec.AbstractMessageEncoder;
import com.tianhe.framework.netty.study.example.codec.MessageCodecService;

/**
 * HessianEncoder.hessian解码器
 * @author he.tian
 */
public class HessianEncoder extends AbstractMessageEncoder {

    public HessianEncoder(final MessageCodecService util) {
        super(util);
    }
}

