package com.example.demo.protal.calendar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.model.ReturnResult;
import com.example.demo.common.utils.UniqueIdUtils;
import com.example.demo.protal.calendar.dao.CalendarEventMapper;
import com.example.demo.protal.calendar.model.CalendarEvent;
import com.example.demo.protal.calendar.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private CalendarEventMapper calendarEventMapper;
	
	@Override
	public ReturnResult addCalendarEvent(CalendarEvent calendarEvent) {
		
		try {
			Long id = UniqueIdUtils.getId();
			calendarEvent.setId(id);
			calendarEventMapper.add(calendarEvent);
			return ReturnResult.success(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnResult.fail();
		} 
		
	}
	
	@Override
	public List<CalendarEvent> getAllCalendarEvent() {
		List<CalendarEvent> calendarEventList = calendarEventMapper.getAllCalendarEvent();
		return calendarEventList;
	}

	/**
	 * 根据id获取日历事件
	 */
	@Override
	public CalendarEvent getCalendarEventById(Long id) {
		return calendarEventMapper.getById(id);
	}

	/**
	 * 更新日历任务事件
	 */
	@Override
	public void updateCalendarEvent(CalendarEvent calendarEvent) {
		calendarEventMapper.update(calendarEvent);
	}

	/**
	 * 删除日历任务事件
	 */
	@Override
	public void delCalendarEventById(Long id) {
		calendarEventMapper.delById(id);
	}
	
}
