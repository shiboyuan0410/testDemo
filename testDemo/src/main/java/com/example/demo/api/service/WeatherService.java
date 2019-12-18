package com.example.demo.api.service;

import com.example.demo.common.model.ReturnResult;

public interface WeatherService {

	ReturnResult getWeather(String address);

}
