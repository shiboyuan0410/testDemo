package com.example.demo.mail.model;

import java.util.Date;

import javax.mail.Flags;

import lombok.Data;

/**
 * 邮件的实体类
 * @author Administrator
 *
 */
@Data
public class Mail {

	
	//邮件号
	private int msgnum;
	//发送人
	private String from;
	//发送人地址
	private String fromAddress;
	
	//接收人
	private String replyTo;
	//抄送人
	private String copyPerson;
	//邮件主题
	private String subject;
	//发送时间
	private Date sentDate;
	//接收时间
	private Date receivedDate;
	//内容
	private String content;
	
	//是否包含附件
	private Boolean isAttachment;
	//附件ids
	private String[] attachmentIds;
	
	
	
	//邮件标记
	/**
	* Flag 类型列举如下
	* Flags.Flag.ANSWERED 邮件回复标记，标识邮件是否已回复。
	* Flags.Flag.DELETED 邮件删除标记，标识邮件是否需要删除。
	* Flags.Flag.DRAFT 草稿邮件标记，标识邮件是否为草稿。
	* Flags.Flag.FLAGGED 表示邮件是否为回收站中的邮件。
	* Flags.Flag.RECENT 新邮件标记，表示邮件是否为新邮件。
	* Flags.Flag.SEEN 邮件阅读标记，标识邮件是否已被阅读。
	* Flags.Flag.USER 底层系统是否支持用户自定义标记，只读。
	*/
	private String flag;
	
	
}
