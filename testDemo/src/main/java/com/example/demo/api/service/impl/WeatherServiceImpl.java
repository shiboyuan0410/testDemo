package com.example.demo.api.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

import org.springframework.stereotype.Service;

import com.example.demo.api.model.WeatherInfo;
import com.example.demo.api.service.WeatherService;
import com.example.demo.common.model.ReturnResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class WeatherServiceImpl implements WeatherService {


	@Override
	public ReturnResult getWeather(String address) {
		ReturnResult returnResult = null;
		
		StringBuilder sb=new StringBuilder();
		WeatherInfo weatherInfo = null;
        try {
        	address = URLEncoder.encode(address, "UTF-8");
            String weatherRrl = "http://wthrcdn.etouch.cn/weather_mini?city="+address;
//            String weatherRrl = "https://free-api.heweather.net/s6/weather/now?location="+cityName+"&key=db86a5196f304e52a4369818c5182e60";

            URL url = new URL(weatherRrl);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            GZIPInputStream gzin = new GZIPInputStream(is);

            // 设置读取流的编码格式，自定义编码
            InputStreamReader isr = new InputStreamReader(gzin, "utf-8");
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            while((line=reader.readLine())!=null) {
                sb.append(line).append(" ");
            }
            reader.close();
            weatherInfo = transToWeather(sb.toString());
            return returnResult.success(weatherInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return returnResult.fail();
        }
		
	}
	
	 /**
     * 将JSON格式数据进行解析 ，返回一个weather对象
     * @param weatherInfoByJson
     * @return
     */
    public static WeatherInfo transToWeather(String weatherInfoByJson){
        JSONObject dataOfJson = JSONObject.fromObject(weatherInfoByJson);
        if(dataOfJson.getInt("status")!=1000) {
            return null;
        }

        //创建WeatherInfo对象，提取所需的天气信息
        WeatherInfo weatherInfo = new WeatherInfo();

        //从json数据中提取数据
        String data = dataOfJson.getString("data");

        //System.out.println(data);
        dataOfJson = JSONObject.fromObject(data);
        weatherInfo.setCityName(dataOfJson.getString("city"));
        weatherInfo.setGanMao(dataOfJson.optString("ganmao"));
        weatherInfo.setTemperature(dataOfJson.optString("wendu")+"℃");

        //获取预测的天气预报信息
        JSONArray forecast = dataOfJson.getJSONArray("forecast");
        //取得当天的
        JSONObject result=forecast.getJSONObject(0);
        weatherInfo.setDate(result.getString("date"));

        String high = result.getString("high").substring(2);
        String low  = result.getString("low").substring(2);

        weatherInfo.setTemperatureRange(low+"~"+high);
        weatherInfo.setWeather(result.getString("type"));

        return weatherInfo;
    }

}
