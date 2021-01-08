package com.tianhe.framework.netty.study.example;

import io.netty.buffer.ByteBuf;

/**
 * @author: he.tian
 * @time: 2018-08-02 10:06
 */
public abstract class CodecUtil {

    /**
     * byteBuf转换成string
     * @param byteBuf
     */
    public static String byteBuf2String(ByteBuf byteBuf){
        byte[] byteArr = new byte[byteBuf.capacity()];
        byteBuf.readBytes(byteArr);
        String result = new String(byteArr);
        return result;
    }
}
