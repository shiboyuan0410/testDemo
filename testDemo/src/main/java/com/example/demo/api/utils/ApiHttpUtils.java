package com.example.demo.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class ApiHttpUtils {

	private final static int SUCCESS =200;

	private final static String UTF8 = "UTF-8";

	private HttpClient client;

	private static ApiHttpUtils instance = new ApiHttpUtils();

	/**
	 * 私有化构造器
	 */
	private ApiHttpUtils() {
		HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = httpConnectionManager.getParams();
		params.setConnectionTimeout(5000);
		params.setSoTimeout(20000);
		params.setDefaultMaxConnectionsPerHost(1000);
		params.setMaxTotalConnections(1000);
		client = new HttpClient(httpConnectionManager);
	}

	/**
	 * get请求
	 */
	public static String get(URL url) {
		return instance.doGet(url);
	}

	private String doGet(URL url) {
		String respStr = StringUtils.EMPTY;

		HttpMethod method = new GetMethod(url.toString());
		//中文转码
		method.getParams().setContentCharset(UTF8);

		try {
			client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
			System.out.println("发送HTTP GET给\r\n" +url+ "\r\n HTTP异常");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("发送HTTP GET给\r\n" +url+ "\r\n IO异常");
		}
		if(method.getStatusCode() == SUCCESS) {
			try {
				respStr = method.getResponseBodyAsString();
				respStr.replace("\\", "").replace("\"{", "{").replace("\"}", "}");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("发送HTTP GET给\r\n" +url+ "\r\n IO异常");
			}
		}
		//释放连接
		method.releaseConnection();
		return respStr;
	}


	/**
	 * post请求
	 */

	public static String post(URL url,Part[] parts) {
		return instance.doPost(url,parts);
	}

	private String doPost(URL url, Part[] parts) {
		String respStr = StringUtils.EMPTY;

		PostMethod method = new PostMethod(url.toString());
		RequestEntity requestEntity = new MultipartRequestEntity(parts, method.getParams());
		//设置参数
		method.setRequestEntity(requestEntity);
		//中文转码
		method.getParams().setContentCharset(UTF8);

		try {
			client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
			System.out.println("发送HTTP POST给\r\n" +url+ "\r\n HTTP异常");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("发送HTTP POST给\r\n" +url+ "\r\n IO异常");
		}
		if(method.getStatusCode() == SUCCESS) {
			try {
				respStr = method.getResponseBodyAsString();
				respStr.replace("\\", "").replace("\"{", "{").replace("\"}", "}");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("发送HTTP POST给\r\n" +url+ "\r\n IO异常");
			}
		}
		//释放连接
		method.releaseConnection();
		return respStr;
	}


	@SuppressWarnings("deprecation")
	public static HttpEntity doPost(String url, Map<String, Object> param) {
		HttpEntity entity = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);

		try {

			String jsonString = JSON.toJSONString(param);
			
			//String encode = URLEncoder.encode(jsonString,HTTP.UTF_8);
			
			method.setEntity(new StringEntity(jsonString));
			HttpResponse response = client.execute(method);

			if(response.getStatusLine().getStatusCode() == SUCCESS) {
				entity = response.getEntity();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		//释放连接
		method.releaseConnection();
		return entity;
	}


}
