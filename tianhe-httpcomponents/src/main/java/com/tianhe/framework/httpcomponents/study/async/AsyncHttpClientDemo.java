package com.tianhe.framework.httpcomponents.study.async;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 功能说明：异步httpClient
 *
 * @author:weifeng.jiang
 * @DATE:2017/5/12 @TIME:18:46
 */
public class AsyncHttpClientDemo {

    public static void main(String[] args){
        final CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.createDefault();
        httpAsyncClient.start();

        final CountDownLatch latch = new CountDownLatch(1);
//        final HttpGet request = new HttpGet("https://www.baidu.com");  //get方式
        final HttpPost request = new HttpPost("http://172.28.11.231:8080/test"); //post方式
        Map<String,String> map = new HashMap<>();
        map.put("name","name");
        map.put("age","age");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(params,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.setEntity(formEntity);
        System.out.println("调用线程="+Thread.currentThread().getName());
        httpAsyncClient.execute(request, new FutureCallback<HttpResponse>() {

            @Override
            public void completed(HttpResponse httpResponse) {
                latch.countDown();
                System.out.println("调用线程="+Thread.currentThread().getName());
                System.out.println("请求信息响应码="+request.getRequestLine()+"->"+ httpResponse.getStatusLine());
                try {
                    String content = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
                    System.out.println("响应信息="+content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {
                latch.countDown();
                System.out.println("请求失败，请求信息="+request.getRequestLine()+"->"+e);
                System.out.println("回调线程="+Thread.currentThread().getName());
            }

            @Override
            public void cancelled() {
                latch.countDown();
                System.out.println("请求取消，请求信息="+request.getRequestLine());
                System.out.println("回调线程="+Thread.currentThread().getName());
            }
        });
    }
}
