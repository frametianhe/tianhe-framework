package com.tianhe.framework.httpcomponents.study.sync;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能说明：
 * @author:weifeng.jiang
 */
public class BaseHttpClient {

	static Logger logger = LoggerFactory.getLogger(BaseHttpClient.class);

	public static String get(String url, Map<String, String> paramMap, int timeOut, String characterEncode) {
		String urlParams = appendParams(url, paramMap, characterEncode);
		url += "?" + urlParams;
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeOut)
				.setConnectTimeout(timeOut)
				.build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		return execute(httpGet);
	}

	public static String post(String url, Map<String, String> param, int timeout, String characterEncode) {
		HttpPost post = createHttpPost(url, param, characterEncode);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout)
				.setConnectTimeout(timeout)
				.build();
		post.setConfig(requestConfig);
		return execute(post);
	}

	public static String post(String url, Map<String, String> param, int timeout, String reqCharacterEncode,String rspCharacterEncode) {
		HttpPost post = createHttpPost(url, param, reqCharacterEncode);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout)
				.setConnectTimeout(timeout)
				.build();
		post.setConfig(requestConfig);
		return execute(post,rspCharacterEncode);
	}
	
	private static HttpPost createHttpPost(String url, Map<String, String> param, String characterEncode) {
		HttpPost post = new HttpPost(url);
		initPostByMap(post, param, characterEncode);
		return post;
	}

	public static void initPostByMap(HttpPost post, Map<String, String> param, String characterEncode) {
		if ((param != null) && (param.size() > 0)) {
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> it : param.entrySet())
				nvps.add(new BasicNameValuePair(it.getKey(), it.getValue()));
			try {
				post.setEntity(new UrlEncodedFormEntity(nvps, characterEncode));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(characterEncode + " is not supported", e);
			}
		}
	}

	public static String execute(HttpUriRequest req) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpclient.execute(req);
			HttpEntity entity = checkResponse(response);
			String str = null;
			if (entity != null) {
				String charset = ContentType.getOrDefault(entity).getCharset().toString();
				str = EntityUtils.toString(entity, charset);
			}
			return str;
		} catch (Exception e) {
			logger.error("# execute error", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("# close httpclient error", e);
			}
		}
		return null;
	}
	public static String execute(HttpUriRequest req,String rspCharacterEncode) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpclient.execute(req);
			HttpEntity entity = checkResponse(response);
			String str = null;
			if (entity != null) {
				
				String charset = null;
				if(rspCharacterEncode !=null)
					charset = rspCharacterEncode;
				else
					charset = ContentType.getOrDefault(entity).getCharset().toString();
				
				str = EntityUtils.toString(entity, charset);
			}
			return str;
		} catch (Exception e) {
			logger.error("# execute error", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("# close httpclient error", e);
			}
		}
		return null;
	}

	public static HttpEntity checkResponse(HttpResponse response) throws IOException, ClientProtocolException {
		int status = response.getStatusLine().getStatusCode();
		if ((status >= 200) && (status < 300)) {
			return response.getEntity();
		}
		throw new ClientProtocolException("Unexpected response status: " + status);
	}



	public static String appendParams(String url, Map<String, String> paramMap, String characterEncode) {
		StringBuffer sbuff = new StringBuffer(url);
		if (paramMap != null) {
			try {
				for (Map.Entry<String, String> it : paramMap.entrySet()) {
					sbuff.append(it.getKey()).append("=");
					if (StringUtils.isNotBlank(it.getValue())){
						sbuff.append(URLEncoder.encode(it.getValue(), characterEncode));
					}
					sbuff.append("&");
				}
				if (!paramMap.isEmpty()) {
					sbuff.deleteCharAt(sbuff.length() - 1);
				}
			} catch (Exception e) {
				logger.error("#HttpClient error", e);
				throw new RuntimeException("The exception of append the parameter.", e);
			}
		}
		return sbuff.toString();
	}
}