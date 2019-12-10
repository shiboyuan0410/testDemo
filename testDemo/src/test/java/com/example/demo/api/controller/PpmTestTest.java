package com.example.demo.api.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PpmTestTest {

	@Test
	public void test() {


		String ppm_main = "{\"belongdeptname\" : \"HTSY-239\","
				+ "\"model\" : \"XX-01\","
				+ "\"productname\" :\"发动机\"," 
				+ "\"batch\":\"201911\","
				+ "\"drawnumber\":\"F1\"}";

		String ppm_detail = "["
				+ "{\"number\":\"1\",\"procedurename\":\"工序名称1\",\"productnumber\":\"30\","
				+ "\"serialnumber\":\"2\",\"charactername\":\"检验特性2\","
				+ "\"begindate\":\"2019-03-27\","+ "\"finishdate\":\"2019-10-21\",\"productdefect\":\"1\",\"weightingcoefficient\":\"1\",\"isimportant\":\"是\"},"
				+ "{\"number\":\"2\",\"procedurename\":\"工序名称2\",\"productnumber\":\"30\","
				+ "\"serialnumber\":\"1\",\"charactername\":\"检验特性1\",\"productdefect\":\"2\",\"weightingcoefficient\":\"1\","
				+ "\"begindate\":\"2019-09-17\","+ "\"finishdate\":\"2019-09-28\",\"isimportant\":\"否\"},"
				+ "{\"number\":\"2\",\"procedurename\":\"工序名称2\",\"productnumber\":\"30\","
				+ "\"serialnumber\":\"2\",\"charactername\":\"检验特性1\","
				+ "\"begindate\":\"2019-09-17\","+ "\"finishdate\":\"2019-09-28\",\"productdefect\":\"1\",\"weightingcoefficient\":\"1\",\"isimportant\":\"是\"},"
				+ "{\"number\":\"3\",\"procedurename\":\"\",\"productnumber\":\"null\","
				+ "\"serialnumber\":\"3\",\"charactername\":\"\","
				+ "\"begindate\":\"2019-10-27\","+ "\"finishdate\":\"2020-12-28\",\"productdefect\":\"null\",\"weightingcoefficient\":\"2\",\"isimportant\":\"是\"},"
				+ "{\"number\":\"1\",\"procedurename\":\"工序名称1\",\"productnumber\":\"20\","
				+ "\"serialnumber\":\"2\",\"charactername\":\"检验特性2\","
				+ "\"begindate\":\"2019-09-27\","+ "\"finishdate\":\"2019-11-21\",\"productdefect\":\"1\",\"weightingcoefficient\":\"1\",\"isimportant\":\"是\"},"
				+ "{\"number\":\"2\",\"procedurename\":\"工序名称2\",\"productnumber\":\"30\","
				+ "\"serialnumber\":\"1\",\"charactername\":\"检验特性1\",\"productdefect\":\"2\",\"weightingcoefficient\":\"1\","
				+ "\"begindate\":\"2019-09-17\","+ "\"finishdate\":\"2019-10-28\",\"isimportant\":\"否\"},"
				+ "{\"number\":\"2\",\"procedurename\":\"工序名称2\",\"productnumber\":\"30\","
				+ "\"serialnumber\":\"2\",\"charactername\":\"检验特性1\","
				+ "\"begindate\":\"2019-09-17\","+ "\"finishdate\":\"2019-10-28\",\"productdefect\":\"1\",\"weightingcoefficient\":\"1\",\"isimportant\":\"是\"},"

				+ "{\"number\":\"3\",\"procedurename\":\"\",\"productnumber\":\"null\","
				+ "\"serialnumber\":\"3\",\"charactername\":\"\","
				+ "\"begindate\":\"2019-10-27\","+ "\"finishdate\":\"2019-12-28\",\"productdefect\":\"null\",\"weightingcoefficient\":\"2\",\"isimportant\":\"是\"}"
				+ "]";

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ppm_main", ppm_main);
		map.put("ppm_detail", ppm_detail);

		String jsonString = JSON.toJSONString(map);
		Map<String, Object> filters = new HashMap<String, Object>();

		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://192.168.199.199:8080/CCP2392/v1/qbdPpmCollect.ht");


			// 将JSON进行UTF-8编码,以便传输中文
			String encoderJson = URLEncoder.encode(JSON.toJSONString(map), HTTP.UTF_8);

			// 提交表单类型的参数

			post.addHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(encoderJson));

			HttpResponse response = client.execute(post);

			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();

				ObjectMapper mapper = new ObjectMapper();


				//Map<String, Object> map2 = mapper.readValue(EntityUtils.toString(entity), HashMap.class);
				System.out.println(EntityUtils.toString(entity));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取登录cookie信息
	 */
	//用来存取cookies信息的变量
	private CookieStore store;
	@Test
	public void testGetCookie() {
		//从配置文件中，提取并拼接url 
		//String testUrl = "http://localhost:8080/CCP3/login.ht?username=shiboyuan&password=1"; 
		String testUrl = "http://localhost:8080/CCP3/login.ht?username=shiboyuan&password=1"; 
		
		//获取body 
		String result; 
		HttpPost get = new HttpPost(testUrl); 
		DefaultHttpClient client = new DefaultHttpClient(); 
		try {
			HttpResponse response = client.execute(get); 
			result = EntityUtils.toString(response.getEntity(),"utf-8");
			
			System.out.println(result); 
			//获取cookies信息 
			store = client.getCookieStore(); 
			List<Cookie> cookieList = store.getCookies(); 
			for (Cookie cookie : cookieList){
				String name =cookie.getName();
				String value = cookie.getValue();
				System.out.println("访问/getcookies接口成功，cookie name = "+name+", cookie value = "+value); 
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		

	}

	@Test
	public void testGetData() {
		//从配置文件中，提取并拼接url 
		//String testUrl = "http://localhost:8080/CCP3/login.ht?username=shiboyuan&password=1"; 
		String testUrl = "http://localhost:8080/CCP3/login.ht?username=shiboyuan&password=1"; 
		
		String getData = "http://localhost:8080/CCP3/platform/system/sysProperty/list.ht"; 
		
		//获取body 
		String result; 
		String result2; 
		HttpPost get = new HttpPost(testUrl); 
		
		HttpPost getDataPost = new HttpPost(getData); 
		
		
		DefaultHttpClient client = new DefaultHttpClient(); 
		try {
			HttpResponse response = client.execute(get); 
			result = EntityUtils.toString(response.getEntity(),"utf-8");
			
			System.out.println(result); 
			//获取cookies信息 
			store = client.getCookieStore(); 
			List<Cookie> cookieList = store.getCookies(); 
			for (Cookie cookie : cookieList){
				String name =cookie.getName();
				String value = cookie.getValue();
				System.out.println("访问/getcookies接口成功，cookie name = "+name+", cookie value = "+value); 
			}
			
			
			client.setCookieStore(store);
			HttpResponse response2 = client.execute(getDataPost); 
			result2 = EntityUtils.toString(response2.getEntity(),"utf-8");
			
			
			System.out.println(result2); 
			 
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		

	}

}
