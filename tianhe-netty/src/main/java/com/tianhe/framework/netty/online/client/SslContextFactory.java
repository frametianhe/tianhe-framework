package com.tianhe.framework.netty.online.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public final class SslContextFactory {

	private static final Logger logger = LoggerFactory.getLogger(SslContextFactory.class);
    private static final SSLContext CLIENT_CONTEXT;

    static {
        SSLContext clientContext;
        try {
            clientContext = SSLContext.getInstance("TLS");
            TrustManager[] tm = { new TrustAnyTrustManager() }; 
            clientContext.init(null, tm, null);
        } catch (Exception e) {
            throw new Error("Failed to initialize the client-side SSLContext", e);
        }

        CLIENT_CONTEXT = clientContext;
    }

    public static SSLContext getClientContext() {
        return CLIENT_CONTEXT;
    }

    private SslContextFactory() {

    }
}