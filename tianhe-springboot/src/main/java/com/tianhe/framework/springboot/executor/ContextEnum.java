package com.tianhe.framework.springboot.executor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 上下文枚举
 *
 * @author humin@didichuxing.com
 * @since 2017/7/14
 */
public enum ContextEnum {
    TRACE_ID("didi-header-rid", "traceid"),
    SPAN_ID("didi-header-spanid", "spanid"),
    DIDIID("didiid", "didiid"),
    MOBILE("mobile", "mobile"),
    MID("mid", "mid"),
    ROLE("role", "role"),
    APP_KEY("appkey", "appkey"),
    CHANNEL("channel", "channel");

    public static final Map<String, String> HEADER_NAME =
            Arrays.stream(values()).collect(Collectors.toMap(o -> o.header, o -> o.name));

    public static final Map<String, String> NAME_HEADER =
            Arrays.stream(values()).collect(Collectors.toMap(o -> o.name, o -> o.header));

    /**
     * http头
     */
    public final String header;

    /**
     * 日志tag
     */
    public final String name;

    ContextEnum(String header, String name) {
        this.header = header;
        this.name = name;
    }
}
