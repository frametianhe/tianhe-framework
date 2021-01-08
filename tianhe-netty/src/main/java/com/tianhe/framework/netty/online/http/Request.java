package com.tianhe.framework.netty.online.http;

import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianhe on 2019/12/13.
 */
@Getter
@Setter
public class Request {

    private Map<String,Object> params = new HashMap<>();

    private byte[] body;

    @Override
    public String toString() {
        String requestInfo = "";
        StringBuilder builder = new StringBuilder();
        if(!params.isEmpty()){
            builder.append("params=");
        }
        for (Map.Entry<String, Object> param : params.entrySet()) {
            builder.append(param.getKey()).append(":").append(param.getValue()).append(",");
        }
        try {
           if(body != null){
               builder.append("body=").append(new String(body,"utf-8"));
               requestInfo = builder.toString();
           }else{
               requestInfo = builder.substring(0, builder.lastIndexOf(","));
           }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return requestInfo;
    }
}
