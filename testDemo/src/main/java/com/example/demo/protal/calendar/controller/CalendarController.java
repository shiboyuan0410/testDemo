package com.example.demo.protal.calendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.common.model.ReturnResult;
import com.example.demo.protal.calendar.model.CalendarEvent;
import com.example.demo.protal.calendar.service.CalendarService;

/**
 * 日历模块
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;
	
	/**
	 * 展示页面
	 */
	@RequestMapping("/show")
	public String showCalendar() {
		return "/calendar/calendar";
	}
	
	/**
	 * 添加日历任务
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ReturnResult addCalendarEvent(CalendarEvent calendarEvent) {
		ReturnResult returnResult = calendarService.addCalendarEvent(calendarEvent);
		return returnResult;
	}
	
	/**
	 * 更新日历任务
	 * @param calendarEvent
	 * @return
	 */
	@RequestMapping("/update")
	public void update(CalendarEvent calendarEvent) {
		calendarService.updateCalendarEvent(calendarEvent);
	}
	
	
	/**
	 * 获取所有的日历任务事件
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public String getAllCalendarEvent(){
		List<CalendarEvent> allCalendarEvent = calendarService.getAllCalendarEvent();
		return JSON.toJSONStringWithDateFormat(allCalendarEvent,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteDateUseDateFormat);
	}
}
