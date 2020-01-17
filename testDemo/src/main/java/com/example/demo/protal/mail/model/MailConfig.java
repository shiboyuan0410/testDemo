package com.example.demo.protal.mail.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.protal.sysUser.model.SysUser;

import lombok.Data;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

	private String host;
	private String username;
	private String password;
	private String protocol;
	private String port;
	
	
	public MailConfig getMailConfig() {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		MailConfig mailConfig = new MailConfig();
		
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		String email = sysUser.getEmail();
		String emailLicense = sysUser.getEmailLicense();
		
		mailConfig.setHost(host);
		mailConfig.setUsername(email);
		mailConfig.setPassword(emailLicense);
		mailConfig.setProtocol(protocol);
		mailConfig.setPort(port);
		
		return mailConfig;
	}
	
}
