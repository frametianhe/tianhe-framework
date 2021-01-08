package com.tianhe.framework.httpcomponents.online.httppool.async;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianhe on 2019/9/17.
 */
@Data
public class HttpReq {

    private String host;

    private int port;

    private String protocol = "http";

    private String requestMapping;

    private Map<String,String> paramMap = new HashMap<>();
}
