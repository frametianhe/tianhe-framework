package com.tianhe.framework.netty.online.client;

/**
 * Created by weifeng.jiang on 2017-11-02 21:18.
 */
public class ChannelInfo {

    private String frontIp;

    private String frontPort;

    private String url;

    public void setFrontIp(String frontIp) {
        this.frontIp = frontIp;
    }

    public String getFrontIp() {
        return frontIp;
    }

    public void setFrontPort(String frontPort) {
        this.frontPort = frontPort;
    }

    public String getFrontPort() {
        return frontPort;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
