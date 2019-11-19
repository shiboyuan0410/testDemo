package com.example.demo.mail.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
	
}
