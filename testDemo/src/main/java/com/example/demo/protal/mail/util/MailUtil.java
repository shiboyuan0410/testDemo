package com.example.demo.protal.mail.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.example.demo.common.utils.BeanUtils;
import com.example.demo.common.utils.UniqueIdUtils;
import com.example.demo.protal.mail.model.Mail;
import com.example.demo.protal.mail.model.MailConfig;

public class MailUtil {


	private static String fileUrl = "F:\\mailFile\\";


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
	 * @throws Exception 
	 */
	public static Mail mesTansToMail(Message mes) throws Exception {
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

		if(BeanUtils.isNotEmpty(flags)) {
			String string = mes.getFlags().toString();
			if(string.length()>0) {
				flag = string.substring(1, string.length());
			}
		}

		//是否包含附件
		boolean isContainerAttachment = isContainAttachment((Part) mes);  
		List<String> imgsList = new ArrayList<String>();
		Map<String,String> fileMap = new HashMap<String,String>();
		if (isContainerAttachment) {
			saveAttachment((Part) mes, fileUrl +mes.getMessageNumber()+ "_" + mes.getSubject() +"\\",imgsList,fileMap); //保存附件  
		}
		if(fileMap.size()>0) {
			mail.setIsAttachment(true);
		}else {
			mail.setIsAttachment(false);
		}
		
		//附件的个数
		mail.setAttachmentNums(fileMap.size());
		//附件
		mail.setAttachments(fileMap);

		//文件内容
		StringBuffer bodyText = new StringBuffer();// 存放邮件内容
		String contentType = mes.getContentType();
		if (contentType.startsWith("text/plain")) {
			getMailContent((Part) mes,bodyText,true);
		} else {
			getMailContent((Part) mes,bodyText,false);
		}

		//对于图片cid的替换
		String replaceLocalPathByImgCid = replaceLocalPathByImgCid(bodyText.toString(),imgsList);

		//System.out.println(bodyText);
		mail.setContent(replaceLocalPathByImgCid);


		mail.setFlag(flag);

		return mail; 

	}

	/**
	 * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
	 * @param bodytext 
	 */
	public static void getMailContent(Part part, StringBuffer bodyText,boolean plainFlag) throws Exception {
		//如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
		boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;  
		if (part.isMimeType("text/html") && !isContainTextAttach && plainFlag == false) {  
			bodyText.append(MimeUtility.decodeText(part.getContent().toString()));  
		} else if(part.isMimeType("text/plain") && !isContainTextAttach && plainFlag){
			bodyText.append(part.getContent().toString());  
			plainFlag = false;
		} else if (part.isMimeType("message/rfc822")) {   
			getMailContent((Part)part.getContent(),bodyText,plainFlag);  
		} else if (part.isMimeType("multipart/*")) {  
			Multipart multipart = (Multipart) part.getContent();  
			int partCount = multipart.getCount();  
			for (int i = 0; i < partCount; i++) {  
				BodyPart bodyPart = multipart.getBodyPart(i);  
				getMailContent(bodyPart,bodyText,plainFlag);  
			}  
		}  

	}

	/** 
	 * 判断邮件中是否包含附件 
	 * @param msg 邮件内容 
	 * @return 邮件中存在附件返回true，不存在返回false 
	 * @throws MessagingException 
	 * @throws IOException 
	 */  
	public static boolean isContainAttachment(Part part) throws MessagingException, IOException {  
		boolean flag = false;  
		if (part.isMimeType("multipart/*")) {  
			MimeMultipart multipart = (MimeMultipart) part.getContent();  
			int partCount = multipart.getCount();  
			for (int i = 0; i < partCount; i++) {  
				BodyPart bodyPart = multipart.getBodyPart(i);  
				String disp = bodyPart.getDisposition();  
				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {  
					flag = true;  
				} else if (bodyPart.isMimeType("multipart/*")) {  
					flag = isContainAttachment(bodyPart);  
				} else {  
					String contentType = bodyPart.getContentType();  
					if (contentType.indexOf("application") != -1) {  
						flag = true;  
					}    
					if (contentType.indexOf("name") != -1) {  
						flag = true;  
					}   
				}  

				if (flag) break;  
			}  
		} else if (part.isMimeType("message/rfc822")) {  
			flag = isContainAttachment((Part)part.getContent());  
		}  
		return flag;  
	}  


	/**  
	 * 保存附件  
	 * @param part 邮件中多个组合体中的其中一个组合体  
	 * @param destDir  附件保存目录  
	 * @param fileMap 
	 * @param imgsMap 
	 * @throws UnsupportedEncodingException  
	 * @throws MessagingException  
	 * @throws FileNotFoundException  
	 * @throws IOException  
	 */  
	public static void saveAttachment(Part part, String destDir, List<String> imgsList, Map<String, String> fileMap) throws UnsupportedEncodingException, MessagingException,FileNotFoundException, IOException {  
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();    //复杂体邮件  
			//复杂体邮件包含多个邮件体  
			int partCount = multipart.getCount();  
			for (int i = 0; i < partCount; i++) {  
				//获得复杂体邮件中其中一个邮件体  
				BodyPart bodyPart = multipart.getBodyPart(i);  
				//某一个邮件体也有可能是由多个邮件体组成的复杂体  
				String disp = bodyPart.getDisposition();  

				/**disp
				 * ATTACHMENT<附件>
				 * INLINE<嵌入>
				 */

				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {  
					InputStream is = bodyPart.getInputStream();

					saveFile(is, destDir, decodeText(bodyPart.getFileName()),bodyPart,imgsList,fileMap);  
				} else if (bodyPart.isMimeType("multipart/*")) {  
					saveAttachment(bodyPart,destDir,imgsList,fileMap);  
				} else {  
					String contentType = bodyPart.getContentType();  
					if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {  
						saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()),bodyPart,imgsList,fileMap);  
					}  
				}  
			}  
		} else if (part.isMimeType("message/rfc822")) {  
			saveAttachment((Part) part.getContent(),destDir,imgsList,fileMap);  
		}  
	}  

	/**  
	 * 读取输入流中的数据保存至指定目录  
	 * @param is 输入流  
	 * @param fileName 文件名  
	 * @param destDir 文件存储目录  
	 * @param fileMap 
	 * @param strings 
	 * @param string 
	 * @throws FileNotFoundException  
	 * @throws IOException  
	 */  
	private static  void saveFile(InputStream is, String destDir, String fileName , BodyPart bodyPart, List<String> imgsList, Map<String, String> fileMap)  
			throws FileNotFoundException, IOException {  

		if(BeanUtils.isNotEmpty(fileName)) {

			
			
			String[] headers;
			try {
				
				/**
				 * 获取cid 用于替换内嵌图片
				 */
				headers = bodyPart.getHeader("Content-id");
				String cidraw = null, cid = null;
				if (headers != null && headers.length > 0) {
					cidraw = headers[0];

					if (cidraw.startsWith("<") && cidraw.endsWith(">")) {
						cid = "cid:" + cidraw.substring(1, cidraw.length() - 1);
						imgsList.add(cid);
					} else {
						cid = "cid:" + cidraw;
						imgsList.add(cid);
					}
				}
				

				
				String imgExt = "";
				//如果是图片 需要判断格式 并上传到图片服务器
				String contentType = bodyPart.getContentType();
				if(contentType.contains("image")) {
					String[] contents= contentType.split(";");
					if(contents.length > 0) {
						String content = contents[0];
						String[] c= content.split("/");
						if(c.length > 0) {
							imgExt = "." + c[1];
						}
					}

				}

				
				/*
				 * 对于真附件的处理
				 */
				String disp = bodyPart.getDisposition();  

				/**disp
				 * ATTACHMENT<附件>
				 * INLINE<嵌入>
				 */

				if(disp != null && disp.equalsIgnoreCase(Part.ATTACHMENT)) {
					fileMap.put(fileName, destDir + fileName + imgExt);
				}
				

				/**
				 * 判断文件是否存在
				 */
				File file = new File(destDir + fileName + imgExt);
				if(!file.exists()) {//不存在 重新下载文件

					//先得到文件的上级目录，并创建上级目录，在创建文件
					file.getParentFile().mkdir();
					try {
						//创建文件
						BufferedInputStream bis = new BufferedInputStream(is);  
						BufferedOutputStream bos = new BufferedOutputStream(  
								new FileOutputStream(new File(destDir + fileName + imgExt)));  
						int len = -1;  
						while ((len = bis.read()) != -1) {  
							bos.write(len);  
							bos.flush();  
						}  
						bos.close();  
						bis.close();  


					} catch (IOException e) {
						e.printStackTrace();
					}


				}


			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}



	}  

	/** 
	 * 文本解码 
	 * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本 
	 * @return 解码后的文本 
	 * @throws UnsupportedEncodingException 
	 */  
	public static  String decodeText(String encodeText) throws UnsupportedEncodingException {  
		if (encodeText == null || "".equals(encodeText)) {  
			return "";  
		} else {  
			return MimeUtility.decodeText(encodeText);  
		}  
	}  


	/**
	 * 图片替换
	 * @param content
	 * @param subject 
	 * @param mes 
	 * @param mes 
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	public static String replaceLocalPathByImgCid(String content, List<String> imgsList) {
		//src="cid:Base64Image1"

		//file://D://TEST//Base64Image1
		//浏览器不支持打开本地图片 所以临时以nginx作为图片服务器


		String newCont = content ;
		if(imgsList.size() > 0) {
			for (String img : imgsList) {

				String[] split = img.split("\\:");
				System.out.println(split[1]);
				newCont = newCont.replace(img,"http://localhost:8089/"+split[1]+".png").toString();

			}
		}

		return newCont;
	}

}