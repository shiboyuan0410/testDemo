package com.example.demo.protal.calendar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.common.dao.BaseMapper;
import com.example.demo.protal.calendar.model.CalendarEvent;


@Mapper
public interface CalendarEventMapper extends BaseMapper<CalendarEvent>{

	void add(CalendarEvent calendarEvent);

	void update(CalendarEvent calendarEvent);

	CalendarEvent getById(Long id);

	void delById(Long id);

	List<CalendarEvent> getAllCalendarEvent();

	
}
