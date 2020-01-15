package com.example.demo.protal.calendar.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 日历任务
 * @author Administrator
 *
 */
@Data
@Table(name="SYS_CALENDAR_EVENT")
public class CalendarEvent {
	
	/**
	 * 重复的事件具有相同的id
	 */
	@Id
	private Long id;//
	
	/**
	 * 事件在日历上显示的title
	 */
	private String title;
	
	/**
	 * true or false，是否是全天事件
	 */
	private boolean allDay;
	
	/**
	 * 事件的开始时间
	 */
	private Date start;	
	
	/**
	 * 事件的结束时间
	 */
	private Date end;	
		
	/**
	 * 事件被点击将打开对应url
	 */
	private String url;	
						
	/**
	 * 事件的样式
	 */
	private String className;	
	
	/**
	 * 事件是否可编辑，可编辑是指可以移动, 改变大小等
	 */
	private String editable;	
	
	/**
	 * 背景和边框颜色
	 */
	private String color;	
	
	/**
	 * 背景颜色
	 */
	private String backgroundColor;	
	
	/**
	 * 边框颜色
	 */
	private String borderColor;	
	
	/**
	 * 文本颜色
	 */
	private String textColor;	
				
}
