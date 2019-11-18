package com.example.demo.mail.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.utils.BeanUtils;
import com.example.demo.mail.model.Mail;
import com.example.demo.mail.model.MailConfig;
import com.example.demo.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private MailConfig mailConfig;

	@Override
	public Map<String, Object> getAllMail(int currentPage, int size) {

		Map<String,Object> mailMap = new HashMap<String,Object>();

		List<Mail> mailList =new ArrayList<Mail>();

		// 准备连接服务器的会话信息 
		Properties props = new Properties(); 
		props.setProperty("mail.store.protocol", mailConfig.getProtocol()); 
		props.setProperty("mail.imap.host", mailConfig.getHost()); 
		props.setProperty("mail.imap.port", mailConfig.getPort()); 

		// 创建Session实例对象 
		Session session = Session.getInstance(props); 

		// 创建IMAP协议的Store对象 
		Store store;
		try {
			store = session.getStore(mailConfig.getProtocol());

			// 连接邮件服务器 
			store.connect(mailConfig.getUsername(),mailConfig.getPassword()); 

			// 获得收件箱 
			Folder folder = store.getFolder("INBOX"); 

			// 以读写模式打开收件箱 
			folder.open(Folder.READ_WRITE); 

			// 获得收件箱的邮件列表 
			Message[] messages = folder.getMessages(); 

			// 打印不同状态的邮件数量 
			//System.out.println("收件箱中共" + messages.length + "封邮件!"); 

			//总邮件数量
			mailMap.put("inboxNum", messages.length);
			//未读邮件数量
			mailMap.put("unreadNum", folder.getUnreadMessageCount());
			//已删除邮件数量
			mailMap.put("deletedNum", folder.getDeletedMessageCount());

			//int total = folder.getMessageCount();
			//System.out.println("-----------------您的邮箱共有邮件：" + total + " 封--------------");
			// 得到收件箱文件夹信息，获取邮件列表
			//Message[] msgs = folder.getMessages();

			int start = 0;
			int end = 0;
			if(currentPage == 1) {
				start = 1;
				end = size;
			}else {
				start = (currentPage - 1) * size;
				end = currentPage * size;
			}

			int totalPage = messages.length / size;


			/**
			 * 分页的属性
			 */
			//当前页
			mailMap.put("currentPage", currentPage);
			//每页个数
			mailMap.put("size", size);
			//总页数
			mailMap.put("totalPage", totalPage);

			Message[] msgs = folder.getMessages(start, end);//分页获取

			for (int i = 0; i < msgs.length; i++) {

				Mail mail = new Mail();

				Message mes = msgs[i];
				//   获取邮箱邮件名字及时间
				//String from =InternetAddress.toString(a.getFrom());  

				String from = MimeUtility.decodeText(mes.getFrom()[0].toString());
				InternetAddress ia = new InternetAddress(from);

				//发件人
				mail.setFrom(ia.getPersonal());
				//发件人地址
				mail.setFromAddress(ia.getAddress());
				//邮件主题
				mail.setSubject(mes.getSubject());

				//邮件接收时间
				mail.setReceivedDate(mes.getReceivedDate());

				String flag ="";
				Flags flags = mes.getFlags();
				if(BeanUtils.isNotEmpty(flags)) {
					String string = mes.getFlags().toString();
					flag = string.substring(1, string.length());
				}

				mail.setFlag(flag);
				//System.out.println(mes.getFlags()+":"+flag);
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
		// 准备连接服务器的会话信息 
		Properties props = new Properties(); 
		props.setProperty("mail.store.protocol", mailConfig.getProtocol()); 
		props.setProperty("mail.imap.host", mailConfig.getHost()); 
		props.setProperty("mail.imap.port", mailConfig.getPort()); 

		// 创建Session实例对象 
		Session session = Session.getInstance(props); 

		// 创建IMAP协议的Store对象 
		Store store;
		int total = 0;
		try {
			store = session.getStore(mailConfig.getProtocol());
			// 连接邮件服务器 
			store.connect(mailConfig.getUsername(),mailConfig.getPassword()); 
			// 获得收件箱 
			Folder folder = store.getFolder("INBOX"); 
			// 以读写模式打开收件箱 
			folder.open(Folder.READ_WRITE); 
			total = folder.getMessageCount();

			// 关闭资源 
			folder.close(false); 
			store.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return total;
	}

}
