package com.tianhe.framework.httpcomponents.online.httppool.sync;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequester {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequester.class);

    private PoolingHttpClientConnectionManager connectionManager;

    private RequestConfig requestConfig;

    /**
     * httpCfg
     */
    private HttpCfg httpCfg;

    public HttpRequester(HttpCfg httpCfg) {
        try {
            this.httpCfg = httpCfg;
            connectionManager = getConnManager(httpCfg);
            requestConfig = RequestConfig.custom()
                    .setSocketTimeout(httpCfg.getReadTimeOut())
                    .setConnectionRequestTimeout(httpCfg.getReadTimeOut())
                    .setConnectTimeout(httpCfg.getConnTimeOut())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HttpClient getConnection() {
        PoolingHttpClientConnectionManager connManager = this.connectionManager;
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig).build();
        return httpClient;
    }

    private static PoolingHttpClientConnectionManager getConnManager(HttpCfg httpCfg) throws Exception {
        System.setProperty("jsse.enableSNIExtension", "false");
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        if ("https".equals(httpCfg.getProtocol())) {
            ConnectionSocketFactory connectionSocketFactory = getSSLFactory();
            registryBuilder.register("https", connectionSocketFactory);
        }else{
        	ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        	registryBuilder.register("http", plainSF);
        } 

        try {
            Registry<ConnectionSocketFactory> registry = registryBuilder.build();
            return new PoolingHttpClientConnectionManager(registry);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    private static SSLConnectionSocketFactory getSSLFactory() {
        SSLConnectionSocketFactory sslsf = null;  
        try {  
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {  
  
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
                    return true;  
                }  
            }).build();  
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {  
  
                @Override  
                public boolean verify(String arg0, SSLSession arg1) {  
                    return true;  
                }  
  
                @Override  
                public void verify(String host, SSLSocket ssl) throws IOException {  
                }  
  
                @Override  
                public void verify(String host, X509Certificate cert) throws SSLException {  
                }  
  
                @Override  
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {  
                }  
            });  
        } catch (GeneralSecurityException e) {  
            e.printStackTrace();  
        }  
        return sslsf;  
    }  

    public HttpRes sendPostString(HttpReq httpRequest) throws Exception {
        HttpPost httpPost = new HttpPost(httpCfg.getUrl());
        StringEntity formEntity = new StringEntity(httpRequest.getRequestBody(), httpCfg.getCharset());
        httpPost.setEntity(formEntity);
        return this.send(httpRequest, httpPost);
    }
    
    public HttpRes sendPostForm(HttpReq httpRequest) throws Exception {
        HttpPost httpPost = new HttpPost(httpCfg.getUrl());
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : httpRequest.getParamMap().entrySet()) {
            if (httpCfg.isEncode()) {
                params.add(new BasicNameValuePair(entry.getKey(), URLEncoder.encode(entry.getValue())));
            } else {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        HttpEntity formEntity = new UrlEncodedFormEntity(params, httpCfg.getCharset());
        httpPost.setEntity(formEntity);
        return this.send(httpRequest, httpPost);
    }


    private HttpRes send(HttpReq httpRequest, HttpRequestBase httpMethod)
            throws Exception {
        HttpClient httpclient = this.getConnection();
        HttpResponse response = null;
        HttpRes respons = new HttpRes();
        try {
            response = httpclient.execute(httpMethod);
            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode())
            {
                throw new IOException("响应失败 "+response.getStatusLine().getStatusCode());
            }
            String rspMsg = EntityUtils.toString(response.getEntity(), httpCfg.getCharset());
            respons.setContent(rspMsg);
        } catch (UnknownHostException e) {
            throw e;
        } catch (HttpHostConnectException e) {
            throw e;
        } catch (ConnectTimeoutException e) {
            throw e;
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return respons;
    }
}
