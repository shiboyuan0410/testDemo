package com.example.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.api.service.WeatherService;
import com.example.demo.common.model.ReturnResult;

@RequestMapping("/weather")
@Controller
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	/**
	 * 获取天气展示页面
	 */
	@RequestMapping("/weatherShow")
	public String weatherShow(Model model) {
		String citykey = "101010100";
		ReturnResult weatherInfo = weatherService.getWeather(citykey);
		model.addAttribute("weatherInfo", weatherInfo);
		return "weather/weatherShow";
	}
	
	/**
	 * 获取天气
	 */
	@RequestMapping("/getWeather/{citykey}")
	@ResponseBody
	public ReturnResult getWeather(@PathVariable String citykey) {
		return weatherService.getWeather(citykey);
	}
}
