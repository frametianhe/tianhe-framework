package com.tianhe.framework.httpcomponents.online.httppool.sync;

import java.util.HashMap;
import java.util.Map;


public class HttpReq {

    private String requestBody = "";
    
    private Map<String, String> paramMap = new HashMap<String, String>();

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}
}
