package com.example.demo.api.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
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
		
		
		String ppm_main = "{\"belongdeptname\" : \"304北京京航计算通讯研究所\","
				+ "\"model\" : \"XX-01\","
				+ "\"productname\" :\"燃烧室\"," 
				+ "\"batch\":\"201911\","
				+ "\"drawnumber\":\"R1\"}";

		String ppm_detail = "["
				+ "{\"number\":\"1\",\"procedurename\":\"工序名称1\",\"productnumber\":\"20\","
				+ "\"serialnumber\":\"2\",\"charactername\":\"检验特性2\","
				+ "\"begindate\":\"2019-09-27\","+ "\"finishdate\":\"2019-10-20\",\"productdefect\":\"1\",\"weightingcoefficient\":\"1\",\"isimportant\":\"是\"},"
				+ "{\"number\":\"2\",\"procedurename\":\"工序名称2\",\"productnumber\":\"30\","
				+ "\"serialnumber\":\"1\",\"charactername\":\"检验特性1\",\"productdefect\":\"2\",\"weightingcoefficient\":\"1\","
				+ "\"begindate\":\"2019-09-17\","+ "\"finishdate\":\"2019-09-27\",\"isimportant\":\"否\"},"
				+ "{\"number\":\"2\",\"procedurename\":\"工序名称2\",\"productnumber\":\"30\","
				+ "\"serialnumber\":\"2\",\"charactername\":\"检验特性1\","
				+ "\"begindate\":\"2019-09-17\","+ "\"finishdate\":\"2019-09-27\",\"productdefect\":\"1\",\"weightingcoefficient\":\"1\",\"isimportant\":\"是\"},"
				
				+ "{\"number\":\"3\",\"procedurename\":\"\",\"productnumber\":\"null\","
				+ "\"serialnumber\":\"3\",\"charactername\":\"\","
				+ "\"begindate\":\"2019-10-27\","+ "\"finishdate\":\"2019-11-27\",\"productdefect\":\"null\",\"weightingcoefficient\":\"2\",\"isimportant\":\"是\"}"
				+ "]";
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ppm_main", ppm_main);
		map.put("ppm_detail", ppm_detail);
		
		
		Map<String, Object> filters = new HashMap<String, Object>();
		
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://192.168.199.199:8082/CCP3/v1/qbdPpmCollect.ht");

			
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

}
