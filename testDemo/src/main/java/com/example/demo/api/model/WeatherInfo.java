package com.example.demo.api.model;


import com.alibaba.fastjson.JSONObject;

import lombok.Data;

/**
 * 天气的实体类
 * @author Administrator
 *
 */
@Data
public class WeatherInfo {

	/**
	 * 时间
	 */
	private String date;
	
	/**
	 * 高温
	 */
	private String high;
	/**
	 * 低温
	 */
	private String low;
	/**
	 * 风力
	 */
	private String fengli;
	/**
	 * 风向
	 */	
	private String fengxiang;
	
	/**
	 * 天气类型
	 */
	private String type;

	public WeatherInfo() {
		super();
	}

	public WeatherInfo(String date, String high, String low, String fengli, String fengxiang, String type) {
		super();
		this.date = date;
		this.high = high;
		this.low = low;
		this.fengli = fengli;
		this.fengxiang = fengxiang;
		this.type = type;
	}

	public WeatherInfo(JSONObject jsonObject) {
		super();
		this.date = jsonObject.getString("date");
		this.high = jsonObject.getString("high").substring(2);
		this.low = jsonObject.getString("low").substring(2);
		this.fengli = jsonObject.getString("fengli") == null ? jsonObject.getString("fl") : jsonObject.getString("fengli");
		this.fengxiang = jsonObject.getString("fengxiang") == null ? jsonObject.getString("fx") : jsonObject.getString("fengxiang");
		this.type = jsonObject.getString("type");
	}
	
}
