package com.example.demo.protal.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.protal.mail.service.MailService;
import com.example.demo.protal.sysUser.model.SysUser;

/**
 * 登录
 * @author Administrator
 *
 */
@Controller
public class LoginController {

	@Autowired
    private MailService mailService;
	
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	
	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		int mailNum  = mailService.getMailNum();
		
		model.addAttribute("mailNum", mailNum);
		return "admin/index";
	}
	
}
