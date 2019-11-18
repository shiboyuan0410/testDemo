package com.example.demo.mail.service;

import java.util.Map;

public interface MailService {

	Map<String, Object> getAllMail(int page, int size);

	int getMailNum();

}
