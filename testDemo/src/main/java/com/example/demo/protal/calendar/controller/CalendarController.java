package com.example.demo.protal.calendar.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.common.model.ReturnResult;
import com.example.demo.protal.calendar.model.CalendarEvent;
import com.example.demo.protal.calendar.service.CalendarService;
import com.example.demo.protal.sysUser.model.SysUser;

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
	public ReturnResult addCalendarEvent(CalendarEvent calendarEvent,HttpServletRequest request,HttpServletResponse response) {
		ReturnResult returnResult = null;
		try {
			SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
			returnResult = calendarService.addCalendarEvent(calendarEvent,sysUser.getId());
		} catch (Exception e) {
			returnResult = ReturnResult.fail(302, "/");
		}
		 
		return returnResult;
	}
	
	/**
	 * 更新日历任务
	 * @param calendarEvent
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ReturnResult update(CalendarEvent calendarEvent,HttpServletRequest request,HttpServletResponse response) {
		ReturnResult returnResult = null;
		try {
			SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
			calendarEvent.setUserId(sysUser.getId());
			returnResult = calendarService.updateCalendarEvent(calendarEvent);
		} catch (Exception e) {
			returnResult = ReturnResult.fail(302, "/");
		}
		return returnResult;
	}
	
	
	/**
	 * 获取所有的日历任务事件
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public String getAllCalendarEvent(HttpServletRequest request){
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		
		List<CalendarEvent> allCalendarEvent = calendarService.getAllCalendarEvent(sysUser.getId());
		return JSON.toJSONStringWithDateFormat(allCalendarEvent,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * 任务清单页面
	 */
	@RequestMapping("/list")
	public String listCalendar() {
		return "/calendar/calendarList";
	}
	
}
