package com.tianhe.framework.httpcomponents.online.httppool.async;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.nio.DefaultHttpClientIODispatch;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.nio.protocol.BasicAsyncRequestProducer;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestExecutor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianhe on 2019/9/17.
 */
public class HttpAsyncRequester {

    private int defaultMaxPerRoute = 100;

    private int maxTotal = 100;

    private ExecutorService executorService;

    private HttpProcessor httpProcessor;

    private HttpAsyncRequestExecutor httpAsyncRequestExecutor;

    private DefaultHttpClientIODispatch iOEventDispatch;

    private DefaultConnectingIOReactor ioReactor;

    private BasicNIOConnPool connPool;

    private org.apache.http.nio.protocol.HttpAsyncRequester httpAsyncRequester;

    private HttpCfg httpCfg;

    private HttpCoreContext httpCoreContext;

    public HttpAsyncRequester(HttpCfg httpCfg){
        this.defaultMaxPerRoute = httpCfg.getDefaultMaxPerRoute();
        this.maxTotal = httpCfg.getMaxTotal();
        this.httpCfg = httpCfg;
    }


    public org.apache.http.nio.protocol.HttpAsyncRequester start(){
        this.executorService = Executors.newSingleThreadExecutor();
        this.httpProcessor = getHttpProcessor();
        this.httpAsyncRequestExecutor = new HttpAsyncRequestExecutor();
        this.iOEventDispatch = new DefaultHttpClientIODispatch(httpAsyncRequestExecutor, ConnectionConfig.DEFAULT);
        try {
            ioReactor = new DefaultConnectingIOReactor();
        } catch (IOReactorException e) {
            e.printStackTrace();
        }

        this.connPool = new BasicNIOConnPool(ioReactor,ConnectionConfig.DEFAULT);
        connPool.setDefaultMaxPerRoute(defaultMaxPerRoute);
        connPool.setMaxTotal(maxTotal);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ioReactor.execute(iOEventDispatch);
                } catch (InterruptedIOException e) {
                    e.printStackTrace();
                } catch (IOReactorException e) {
                    e.printStackTrace();
                }
            }
        });
        this.httpCoreContext = HttpCoreContext.create();
        return new org.apache.http.nio.protocol.HttpAsyncRequester(httpProcessor);
    }

    private HttpProcessor getHttpProcessor(){
        return HttpProcessorBuilder.create()
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .add(new RequestUserAgent())
                .add(new RequestExpectContinue(true))
                .build();
    }

    public HttpRes executePost(HttpReq httpReq, org.apache.http.nio.protocol.HttpAsyncRequester httpAsyncRequester){
        HttpRes httpRes = new HttpRes();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        HttpHost httpHost = new HttpHost(httpReq.getHost(),httpReq.getPort(),httpReq.getProtocol());

        BasicHttpRequest request = new BasicHttpRequest("POST",getUri(httpReq));
        BasicHttpParams params = new BasicHttpParams();
        for (String paramKey : httpReq.getParamMap().keySet()){
            params.setParameter(paramKey,httpReq.getParamMap().get(paramKey));
        }
        request.setParams(params);
        httpAsyncRequester.execute(new BasicAsyncRequestProducer(httpHost, request), new BasicAsyncResponseConsumer(), connPool, httpCoreContext, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse response) {
                countDownLatch.countDown();
                try {
                    httpRes.setContent(EntityUtils.toString(response.getEntity()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {
                countDownLatch.countDown();
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                countDownLatch.countDown();
                System.out.println(httpHost +"  cancelled");
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return httpRes;
    }

    public void shutdown(){
        try {
            this.ioReactor.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.executorService.shutdown();
    }

    public String getUri(HttpReq httpReq){
        return new StringBuilder()
                .append(httpReq.getProtocol())
                .append("://")
                .append(httpReq.getHost())
                .append(":")
                .append(httpReq.getPort())
                .append("/")
                .append(httpReq.getRequestMapping())
                .toString();
    }
}
