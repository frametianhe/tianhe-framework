package com.tianhe.framework.httpcomponents.study.sync;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * 功能说明：httpClient
 * @author:weifeng.jiang
 */
public class HttpClient {

	public static final String DEFAULT_ENCODE = "UTF-8";
	public static final int DEFAULT_TIME_OUT = 10000;

	public static String get(String url, Map<String, String> paramMap) {
		return get(url, paramMap, DEFAULT_TIME_OUT, DEFAULT_ENCODE);
	}

	public static String get(String url, Map<String, String> paramMap, int timeout) {
		return get(url, paramMap, timeout, DEFAULT_ENCODE);
	}
	public static String get(String url, Map<String, String> paramMap, String characterEncode) {
		return get(url, paramMap, DEFAULT_TIME_OUT, characterEncode);
	}

	public static String get(String url, Map<String, String> paramMap, int timeout, String characterEncode) {
		return BaseHttpClient.get(url, paramMap, timeout, characterEncode);
	}

	public static String post(String url, Map<String, String> paramMap) {
		return post(url, paramMap, DEFAULT_TIME_OUT, DEFAULT_ENCODE);
	}

	public static String post(String url, Map<String, String> paramMap, int timeout) {
		return post(url, paramMap, timeout, DEFAULT_ENCODE);
	}

	public static String post(String url, Map<String, String> paramMap, String characterEncode) {
		return post(url, paramMap, DEFAULT_TIME_OUT, characterEncode);
	}
	/**
	 *
	 * @param url
	 * @param paramMap
	 * @param reqCharacterEncode  请求字符集编码
	 * @param rspCharacterEncode  响应字符集编码
	 * @return
	 */
	public static String post(String url, Map<String, String> paramMap, String reqCharacterEncode,String rspCharacterEncode) {
		return post(url, paramMap, DEFAULT_TIME_OUT, reqCharacterEncode,rspCharacterEncode);
	}

	public static String post(String url, Map<String, String> paramMap, int timeout, String reqCharacterEncode,String rspCharacterEncode) {
		return BaseHttpClient.post(url, paramMap, timeout, reqCharacterEncode, rspCharacterEncode);
	}

	public static String post(String url, Map<String, String> paramMap, int timeout, String characterEncode) {
		return BaseHttpClient.post(url, paramMap, timeout, characterEncode);
	}

	/**
	 * 发送 Post 请求
	 * @param strData 参数
	 * @param url 地址
	 * @return String
	 */
	public static String post(String url,String strData,String charset) throws NoSuchAlgorithmException, KeyManagementException, IOException{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(strData.getBytes(charset));
		// 刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return new String(outStream.toByteArray(),charset);
		}
		return null;
	}
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
}