package com.example.demo.protal.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void addCalendarEvent(CalendarEvent calendarEvent) {
		calendarService.addCalendarEvent(calendarEvent);
	}
	
}
