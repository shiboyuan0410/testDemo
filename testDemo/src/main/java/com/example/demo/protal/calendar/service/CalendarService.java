package com.example.demo.protal.calendar.service;

import java.util.List;

import com.example.demo.common.model.ReturnResult;
import com.example.demo.protal.calendar.model.CalendarEvent;

public interface CalendarService {

	/**
	 * 添加日历事件
	 * @param calendarEvent
	 * @return 
	 */
	ReturnResult addCalendarEvent(CalendarEvent calendarEvent);

	/**
	 * 获取所有的日历事件
	 * @return
	 */
	List<CalendarEvent> getAllCalendarEvent();
	
	/**
	 * 根据id获取日历事件
	 * @param id
	 * @return
	 */
	CalendarEvent getCalendarEventById(Long id);
	
	/**
	 * 更新任务事件
	 * @param calendarEvent
	 */
	void updateCalendarEvent(CalendarEvent calendarEvent);
	
	/**
	 * 根据id删除任务事件
	 * @param id
	 */
	void delCalendarEventById(Long id);
	
	
}
