package com.example.demo.protal.mail.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.common.utils.BeanUtils;
import com.example.demo.protal.mail.model.Mail;
import com.example.demo.protal.mail.service.MailService;
import com.example.demo.protal.sysUser.model.SysUser;

@Controller
@RequestMapping("/mail")
public class MailController {

	
	@Autowired
    private MailService mailService;
	
	/**
	 * 邮箱首页
	 * @return
	 */
	@RequestMapping("/mailbox")
	public String mailbox(Model model,Integer page,HttpServletRequest request) {
		int size;
		if(BeanUtils.isEmpty(page)) {
			page = 1;
		}
		size = 5;
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		Map<String, Object> mailMap = mailService.getAllMail(page,size,sysUser);
		model.addAllAttributes(mailMap);
		return "mail/mailbox";
	}
	
	
	/**
	 * 邮件详情
	 * @return
	 */
	@RequestMapping("/detail/{msgnum}")
	public String detail(Model model,@PathVariable(value = "msgnum") Integer msgnum) {
		
		Mail mail = mailService.getMailDetail(msgnum);
		model.addAttribute("mail", mail);
		return "mail/mail_detail";
	}
}
