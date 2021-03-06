package com.example.demo.protal.calendar.service;

import java.util.List;

import com.example.demo.common.model.ReturnResult;
import com.example.demo.protal.calendar.model.CalendarEvent;

public interface CalendarService {

	/**
	 * 添加日历事件
	 * @param calendarEvent
	 * @param userId 
	 * @return 
	 */
	ReturnResult addCalendarEvent(CalendarEvent calendarEvent, Long userId);

	/**
	 * 获取所有的日历事件
	 * @param userId 
	 * @return
	 */
	List<CalendarEvent> getAllCalendarEvent(Long userId);
	
	/**
	 * 根据id获取日历事件
	 * @param id
	 * @return
	 */
	CalendarEvent getCalendarEventById(Long id,Long userId);
	
	/**
	 * 更新任务事件
	 * @param calendarEvent
	 * @return 
	 */
	ReturnResult updateCalendarEvent(CalendarEvent calendarEvent);
	
	/**
	 * 根据id删除任务事件
	 * @param id
	 * @return 
	 */
	ReturnResult delCalendarEventById(Long id,Long userId);
	
	
}
