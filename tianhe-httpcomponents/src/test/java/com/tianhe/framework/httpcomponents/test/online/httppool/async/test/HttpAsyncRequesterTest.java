package com.tianhe.framework.httpcomponents.test.online.httppool.async.test;

import com.tianhe.framework.httpcomponents.online.httppool.async.HttpAsyncRequester;
import com.tianhe.framework.httpcomponents.online.httppool.async.HttpCfg;
import com.tianhe.framework.httpcomponents.online.httppool.async.HttpReq;
import com.tianhe.framework.httpcomponents.online.httppool.async.HttpRes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianhe on 2019/11/5.
 */
public class HttpAsyncRequesterTest {

    public static void main(String[] args) {
        HttpAsyncRequester httpAsyncRequester = new HttpAsyncRequester(new HttpCfg());
        org.apache.http.nio.protocol.HttpAsyncRequester asyncRequester = httpAsyncRequester.start();

        HttpReq httpReq = new HttpReq();
        httpReq.setHost("localhost");
        httpReq.setPort(8080);
        httpReq.setProtocol("http");
        httpReq.setRequestMapping("/user");
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("userName","userName");
        paramMap.put("pwd","pwd");
        httpReq.setParamMap(paramMap);

        HttpRes httpRes = httpAsyncRequester.executePost(httpReq,asyncRequester);
        System.out.println(httpRes.getContent());
    }
}
