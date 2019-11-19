package com.example.demo.mail.service;

import java.util.Map;

import com.example.demo.mail.model.Mail;

public interface MailService {

	Map<String, Object> getAllMail(int page, int size);

	int getMailNum();

	Mail getMailDetail(int msgnum);

}
