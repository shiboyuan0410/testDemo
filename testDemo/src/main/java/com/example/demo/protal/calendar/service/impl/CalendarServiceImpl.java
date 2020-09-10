package com.example.demo.protal.calendar.service.impl;

import com.example.demo.common.model.ReturnResult;
import com.example.demo.common.utils.UniqueIdUtils;
import com.example.demo.protal.calendar.dao.CalendarEventMapper;
import com.example.demo.protal.calendar.model.CalendarEvent;
import com.example.demo.protal.calendar.service.CalendarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Resource
	private CalendarEventMapper calendarEventMapper;
	
	@Override
	public ReturnResult addCalendarEvent(CalendarEvent calendarEvent,Long userId) {
		
		try {
			Long id = UniqueIdUtils.getId();
			calendarEvent.setId(id);
			calendarEvent.setUserId(userId);
			
			calendarEventMapper.add(calendarEvent);
			return ReturnResult.success(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnResult.fail();
		} 
		
	}
	
	@Override
	public List<CalendarEvent> getAllCalendarEvent(Long userId) {
		List<CalendarEvent> calendarEventList = calendarEventMapper.getAllCalendarEvent(userId);
		return calendarEventList;
	}

	/**
	 * 根据id获取日历事件
	 */
	@Override
	public CalendarEvent getCalendarEventById(Long id,Long userId) {
		return calendarEventMapper.getById(id,userId);
	}

	/**
	 * 更新日历任务事件
	 */
	@Override
	public ReturnResult updateCalendarEvent(CalendarEvent calendarEvent) {
		
		try {
			calendarEventMapper.update(calendarEvent);
			return ReturnResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnResult.fail();
		} 
		
	}

	/**
	 * 删除日历任务事件
	 */
	@Override
	public ReturnResult delCalendarEventById(Long id,Long userId) {
		
		try {
			calendarEventMapper.delById(id,userId);
			return ReturnResult.success(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnResult.fail();
		} 
		
	}
	
}
