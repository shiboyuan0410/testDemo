package com.example.demo.mail.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import com.example.demo.common.utils.BeanUtils;
import com.example.demo.mail.model.Mail;
import com.example.demo.mail.model.MailConfig;

public class MailUtil {



	/**
	 * 获取邮箱连接
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public static Store getMailConnect(MailConfig mailConfig) throws MessagingException, UnsupportedEncodingException {
		// 准备连接服务器的会话信息 
		Properties props = new Properties(); 
		props.setProperty("mail.store.protocol", mailConfig.getProtocol()); 
		props.setProperty("mail.imap.host", mailConfig.getHost()); 
		props.setProperty("mail.imap.port", mailConfig.getPort()); 

		// 创建Session实例对象 
		Session session = Session.getInstance(props); 

		// 创建IMAP协议的Store对象 
		Store store ;
		store = session.getStore(mailConfig.getProtocol());
		// 连接邮件服务器 
		store.connect(mailConfig.getUsername(),mailConfig.getPassword()); 

		return store; 

	}



	/**
	 * 邮件转换
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public static Mail mesTansToMail(Message mes) throws MessagingException, UnsupportedEncodingException {
		Mail mail = new Mail();

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

		//邮件号
		mail.setMsgnum(mes.getMessageNumber());

		//邮件标志
		String flag ="";
		Flags flags = mes.getFlags();
		int messageNumber = mes.getMessageNumber();
		System.out.println(messageNumber);
		if(BeanUtils.isNotEmpty(flags)) {
			String string = mes.getFlags().toString();
			if(string.length()>0) {
				flag = string.substring(1, string.length());
			}
		}

		mail.setFlag(flag);

		return mail; 

	}

}