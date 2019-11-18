package com.example.demo.mail.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.mail.service.MailService;

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
	public String mailbox(Model model) {
		
		int page = 1;
		int size = 5;
		
		Map<String, Object> mailMap = mailService.getAllMail(page,size);
		model.addAllAttributes(mailMap);
		return "mail/mailbox";
	}
	
	
	/**
	 * 邮件详情
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail() {
		
		return "mail/mail_detail";
	}
}
