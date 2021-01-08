package com.tianhe.framework.netty.online.codec.kryo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tianhe on 2019/10/11.
 */
public interface NettySerialize {

    void serialize(OutputStream output, Object object) throws IOException;

    Object deserialize(InputStream input) throws IOException;
}
