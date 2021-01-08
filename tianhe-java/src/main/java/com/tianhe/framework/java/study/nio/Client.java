package com.tianhe.framework.java.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by tianhe on 2019/10/23.
 */
public class Client {

    public static void main(String[] args) throws IOException {
//        创建socketChannel
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8080));
//        设置非阻塞
        socketChannel.configureBlocking(false);
//        创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        往缓冲区中写入数据
        buffer.put("hello world".getBytes());
//        切换读模式
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        socketChannel.close();
    }
}
