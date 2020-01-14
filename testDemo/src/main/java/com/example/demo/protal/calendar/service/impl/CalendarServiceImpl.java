package com.example.demo.protal.calendar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.protal.calendar.dao.CalendarEventMapper;
import com.example.demo.protal.calendar.model.CalendarEvent;
import com.example.demo.protal.calendar.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private CalendarEventMapper calendarEventMapper;
	
	@Override
	public void addCalendarEvent(CalendarEvent calendarEvent) {
		calendarEventMapper.insert(calendarEvent);
	}
	
	@Override
	public List<CalendarEvent> getAllCalendarEvent() {
		return calendarEventMapper.selectAll();
	}

	/**
	 * 根据id获取日历事件
	 */
	@Override
	public CalendarEvent getCalendarEventById(Long id) {
		return calendarEventMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新日历任务事件
	 */
	@Override
	public void updateCalendarEvent(CalendarEvent calendarEvent) {
		calendarEventMapper.updateByPrimaryKey(calendarEvent);
	}

	/**
	 * 删除日历任务事件
	 */
	@Override
	public void delCalendarEventById(Long id) {
		calendarEventMapper.deleteByPrimaryKey(id);
	}
	
}
