package com.example.demo.api.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.api.model.WeatherInfo;
import com.example.demo.api.service.WeatherService;
import com.example.demo.common.model.ReturnResult;


@Service
public class WeatherServiceImpl implements WeatherService {


	@Override
	public ReturnResult getWeather(String citykey) {

		StringBuilder sb=new StringBuilder();
		Map<String, Object> weatherInfo = new HashMap<String, Object>();
        try {
            //北京 101010100
            String weatherRrl = "http://wthrcdn.etouch.cn/weather_mini?citykey="+citykey;
            //http://wthrcdn.etouch.cn/weather_mini?city=北京
            //String weatherRrl = "https://free-api.heweather.net/s6/weather/now?location="+cityName+"&key=db86a5196f304e52a4369818c5182e60";

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
            
            return ReturnResult.success(weatherInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnResult.fail();
        }
		
	}
	
	 /**
     * 将JSON格式数据进行解析 ，封装weather对象
     * @param weatherInfoByJson
     * @return
     */
    public static Map<String, Object> transToWeather(String weatherInfoByJson){
    	
    	Map<String, Object> mapData = new HashMap<String, Object>();
    	LinkedList<WeatherInfo> linkedList = new LinkedList<WeatherInfo>();
    	
        JSONObject dataOfJson = JSONObject.parseObject(weatherInfoByJson);
        if(dataOfJson.getIntValue("status") != 1000) {
            return null;
        }

        //从json数据中提取数据
        String data = dataOfJson.getString("data");
        dataOfJson = JSONObject.parseObject(data);
        
        //昨日天气情况
        JSONObject yesterday = dataOfJson.getJSONObject("yesterday");
        linkedList.add(new WeatherInfo(yesterday));
        
        //当前城市
        String city = dataOfJson.getString("city");
        mapData.put("city", city);
        //预测天气(5天)
        JSONArray forecast = dataOfJson.getJSONArray("forecast");
        for (int i = 0; i < forecast.size(); i++) {
        	linkedList.add(new WeatherInfo(forecast.getJSONObject(i)));
		}
        mapData.put("forecast", linkedList);
        
        //ganmao
        String ganmao = dataOfJson.getString("ganmao");
        mapData.put("ganmao", ganmao);
        //当前温度
        String wendu = dataOfJson.getString("wendu")+"℃";
        mapData.put("wendu", wendu);
        
        return mapData;
    }

}
