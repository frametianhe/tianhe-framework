package com.tianhe.framework.httpcomponents.test.online.httppool.sync.test;

import com.tianhe.framework.httpcomponents.online.httppool.sync.HttpCfg;
import com.tianhe.framework.httpcomponents.online.httppool.sync.HttpReq;
import com.tianhe.framework.httpcomponents.online.httppool.sync.HttpRequester;
import com.tianhe.framework.httpcomponents.online.httppool.sync.HttpRes;

/**
 * Created by tianhe on 2019/11/5.
 */
public class HttpRequesterTest {

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8090/demoAction";
        HttpReq req = new HttpReq();
        req.getParamMap().put("userName","userName");
//        req.setRequestBody("userName");

        HttpCfg cfg = new HttpCfg();
        cfg.setCharset("utf-8");
        cfg.setEncode(true);
        cfg.setUrl(url);
        cfg.setProtocol("http");

        HttpRequester httpRequester = new HttpRequester(cfg);
        HttpRes res = httpRequester.sendPostForm(req);
//        HttpRes res = httpRequester.sendPostString(req);
        String resInfo = res.getContent();
        System.out.println(resInfo);
    }
}
