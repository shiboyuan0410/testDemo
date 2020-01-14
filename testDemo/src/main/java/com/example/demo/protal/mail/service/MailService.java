package com.example.demo.protal.mail.service;

import java.util.Map;

import com.example.demo.protal.mail.model.Mail;

public interface MailService {

	Map<String, Object> getAllMail(int page, int size);

	int getMailNum();

	Mail getMailDetail(int msgnum);

}
