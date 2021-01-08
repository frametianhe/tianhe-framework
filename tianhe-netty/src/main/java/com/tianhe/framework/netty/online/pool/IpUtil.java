package com.tianhe.framework.netty.online.pool;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * Created by tianhe on 2019/10/10.
 */
@Slf4j
public final class IpUtil {

    public static String getLocalIp() {
        String localIp = "";
        try {
            localIp = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取本地ip异常，异常信息={}", e);
        }
        return localIp;
    }

    public static InetSocketAddress toInetSocketAddress(String address) {
        int i = address.indexOf(':');
        String host;
        int port;
        if (i > -1) {
            host = address.substring(0, i);
            port = Integer.parseInt(address.substring(i + 1));
        } else {
            host = address;
            port = 0;
        }
        return new InetSocketAddress(host, port);
    }

    public static String toStringAddress(SocketAddress address) {
        return toStringAddress((InetSocketAddress) address);
    }

    public static String toStringAddress(InetSocketAddress address) {
        return address.getAddress().getHostAddress() + ":" + address.getPort();
    }

    public static String getAddressFromContext(ChannelHandlerContext ctx) {
        return getAddressFromChannel(ctx.channel());
    }

    public static String getAddressFromChannel(Channel channel) {
        SocketAddress socketAddress = channel.remoteAddress();
        String address = socketAddress.toString();
        if (socketAddress.toString().indexOf("/") == 0) {
            address = socketAddress.toString().substring("/".length());
        }
        return address;
    }
}
