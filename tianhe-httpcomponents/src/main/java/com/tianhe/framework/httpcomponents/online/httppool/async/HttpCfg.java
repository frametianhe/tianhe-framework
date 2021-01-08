package com.tianhe.framework.httpcomponents.online.httppool.async;

import lombok.Data;

/**
 * Created by tianhe on 2019/9/17.
 */
@Data
public class HttpCfg {

    private String charset = "utf-8";

    private boolean encode = false;

    private int defaultMaxPerRoute = 50;

    private int maxTotal = 100;
}
