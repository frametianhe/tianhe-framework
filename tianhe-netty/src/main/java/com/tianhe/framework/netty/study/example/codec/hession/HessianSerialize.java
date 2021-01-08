
package com.tianhe.framework.netty.study.example.codec.hession;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.tianhe.framework.netty.study.example.codec.NettyTransferSerialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * HessianSerialize.hessian序列化
 * @author he.tian
 */
public class HessianSerialize implements NettyTransferSerialize {

    @Override
    public void serialize(final OutputStream output, final Object object) {
        Hessian2Output ho = new Hessian2Output(output);
        try {
            ho.startMessage();
            ho.writeObject(object);
            ho.completeMessage();
            ho.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(final InputStream input) {
        Object result = null;
        try {
            Hessian2Input hi = new Hessian2Input(input);
            hi.startMessage();
            result = hi.readObject();
            hi.completeMessage();
            hi.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
