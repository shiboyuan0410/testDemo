package com.example.demo.protal.mail.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.protal.mail.model.Mail;
import com.example.demo.protal.mail.model.MailConfig;
import com.example.demo.protal.mail.service.MailService;
import com.example.demo.protal.mail.util.MailUtil;
import com.example.demo.protal.sysUser.model.SysUser;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private MailConfig mailConfig;

	@Override
	public Map<String, Object> getAllMail(int currentPage, int size,SysUser sysUser) {

		Map<String,Object> mailMap = new HashMap<String,Object>();
		List<Mail> mailList =new ArrayList<Mail>();
		
		try {

			Store store = MailUtil.getMailConnect(mailConfig.getMailConfig());

			// 获得收件箱 
			Folder folder = store.getFolder("INBOX"); 
			// 以读写模式打开收件箱 
			folder.open(Folder.READ_WRITE); 
			
			//总邮件数量
			mailMap.put("inboxNum", folder.getMessageCount());
			//未读邮件数量
			mailMap.put("unreadNum", folder.getUnreadMessageCount());
			//已删除邮件数量
			mailMap.put("deletedNum", folder.getDeletedMessageCount());

			int start = 0;
			int end = 0;
			start = (currentPage - 1) * size + 1;
			end = currentPage * size;

			double a = folder.getMessageCount();
			double b = size;
			double totalPage = Math.ceil(a / b);

			/**
			 * 分页的属性
			 */
			//当前页
			mailMap.put("currentPage", currentPage);
			//每页个数
			mailMap.put("size", size);
			//总页数
			mailMap.put("totalPage", totalPage);
			
			//展示几个页码
			mailMap.put("maxShowPages", 5);
			

			//判断是否有值
			if(end >= folder.getMessageCount()) {
				end = folder.getMessageCount();
			}

			System.out.println("start:"+start+",end:"+end);

			Message[] msgs = folder.getMessages(folder.getMessageCount() - end + 1,folder.getMessageCount() - start + 1);//分页获取
			for (int i = 0; i < msgs.length; i++) {

				Mail mail = MailUtil.mesTansToMail(msgs[msgs.length - 1 - i]);
				mailList.add(mail);
			}

			mailMap.put("mailList", mailList);

			// 关闭资源 
			folder.close(false); 
			store.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return mailMap;
	}

	/**
	 * 获取邮件数量
	 */
	@Override
	public int getMailNum() {


		Store store;
		int total = 0;
		try {
			store = MailUtil.getMailConnect(mailConfig.getMailConfig());
			// 获得收件箱 
			Folder folder = store.getFolder("INBOX"); 
			// 以读写模式打开收件箱 
			folder.open(Folder.READ_WRITE); 
			//获取未读邮件数量
			total = folder.getUnreadMessageCount();

			// 关闭资源 
			folder.close(false); 
			store.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return total;
	}



	/**
	 * 获取邮件详情
	 * @throws MessagingException 
	 */
	@Override
	public Mail getMailDetail(int msgnum){
		Mail mail = new Mail();


		try {
			Store store = MailUtil.getMailConnect(mailConfig.getMailConfig());
			// 获得收件箱 
			Folder folder = store.getFolder("INBOX"); 
			// 以读写模式打开收件箱 
			folder.open(Folder.READ_WRITE);

			//message 转 mail
			Message mes = folder.getMessage(msgnum);
			mail = MailUtil.mesTansToMail(mes);

			// 关闭资源 
			folder.close(false); 
			store.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return mail;
	}



}
