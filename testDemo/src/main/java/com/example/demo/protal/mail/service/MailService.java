package com.example.demo.protal.mail.service;

import java.util.Map;

import com.example.demo.protal.mail.model.Mail;
import com.example.demo.protal.sysUser.model.SysUser;

public interface MailService {

	Map<String, Object> getAllMail(int page, int size, SysUser sysUser);

	int getMailNum();

	Mail getMailDetail(int msgnum);

}
