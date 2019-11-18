package com.example.demo.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/yqjApi/v1/")
public class YqjApiTest {

	@RequestMapping("/test1")
	@ResponseBody
	public String test(HttpServletRequest request) {
		
		//获取参数
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String sort = request.getParameter("sort");
		String filter = request.getParameter("filter");
		
		System.out.println(start);
		System.out.println(limit);
		System.out.println(sort);
		System.out.println(filter);
		
		//返回数据
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		map.put("success", true);
		map.put("code", 0);
		map.put("total", 0);
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("Category", "A");
		map1.put("Data", 0.0);
		dataList.add(map1);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("Category", "B");
		map2.put("Data", 10.0);
		dataList.add(map2);
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("Category", "V");
		map3.put("Data", 5.0);
		dataList.add(map3);
		
		map.put("data", dataList);
		
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		return jsonpCallback+"("+JSON.toJSONString(map)+")";
	}
	
	
	
	@RequestMapping("/test2")
	@ResponseBody
	public String test2(HttpServletRequest request) {
		
		//获取参数
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String sort = request.getParameter("sort");
		String filter = request.getParameter("filter");
		
		System.out.println(start);
		System.out.println(limit);
		System.out.println(sort);
		System.out.println(filter);
		
		//返回数据
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		map.put("success", true);
		map.put("code", 0);
		map.put("total", 0);
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("Category", "集成电路");
		map1.put("Data", 6100.0);
		dataList.add(map1);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("Category", "光耦");
		map2.put("Data", 5200.0);
		dataList.add(map2);
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("Category", "电感器");
		map3.put("Data", 300.0);
		dataList.add(map3);
		Map<String,Object> map4 = new HashMap<String,Object>();
		map4.put("Category", "电容器");
		map4.put("Data", 500.0);
		dataList.add(map4);
		Map<String,Object> map5 = new HashMap<String,Object>();
		map5.put("Category", "电阻器");
		map5.put("Data", 0.0);
		dataList.add(map5);
		
		map.put("data", dataList);
		
		return JSON.toJSONString(map);
	}
	
	
	@RequestMapping("/test3")
	@ResponseBody
	public String test3(HttpServletRequest request) {
		
        try {
        	
        	// 读取请求内容
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
        	
			while((line = br.readLine())!=null){
			    sb.append(line);
			}
			System.out.println(sb.toString());
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
		
		//返回数据
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		map.put("success", true);
		map.put("code", 0);
		map.put("total", 0);
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("Category", "/");
		map1.put("Data", 300.0);
		dataList.add(map1);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("Category", "国内厂家");
		map2.put("Data", 6100.0);
		dataList.add(map2);
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("Category", "国外厂家");
		map3.put("Data", 700.0);
		dataList.add(map3);
		
		map.put("data", dataList);
		
		return JSON.toJSONString(map);
	}
}
