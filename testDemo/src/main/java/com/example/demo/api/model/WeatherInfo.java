package com.example.demo.api.model;

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
	 * 城市名
	 */
	private String cityName;

	/**
	 * 天气
	 */
	private String weather;
	/**
	 * 气温
	 */
	private String temperatureRange;

	/**
	 * 感冒
	 */
	private String ganMao;

	/**
	 * 当前温度
	 */
	private String temperature;


	@Override
	public String toString() {
		return "WeatherInfo [date=" + date + ", cityName=" + cityName
				+ ", weather=" + weather + ", temperature=" + temperature+"]";
	}
}
