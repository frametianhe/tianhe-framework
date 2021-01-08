package com.tianhe.framework.java.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by tianhe on 2019/10/23.
 */
public class Server {

    public static void main(String[] args) throws IOException {
//        创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        设置非阻塞，这个地方可以根据需要在不同的时间设置，设置之后就会使用非阻塞模式，比如在连接时使用阻塞模式，连接之后切换到非阻塞模式
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
//        创建一个selector
        Selector selector = Selector.open();
//        注册连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for (;selector.select() > 0;){
//            获取selector上的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            for (;iterator.hasNext();){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
//                    连接上了，获取socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
//                    设置非阻塞
                    socketChannel.configureBlocking(false);
//                    注册读取事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
//                    可读事件到来
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
//                    创建缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    for (;(len = channel.read(buffer)) > 0;){
                        System.out.println(new String(buffer.array(),0,len));
                        buffer.clear();
                    }
                }
//                删除此事件，下次就不会进到这个事件中来
                iterator.remove();
            }
        }
    }
}
