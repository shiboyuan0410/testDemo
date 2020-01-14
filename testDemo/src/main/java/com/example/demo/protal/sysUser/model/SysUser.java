package com.example.demo.protal.sysUser.model;


import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="SYS_USER")
public class SysUser {

	// userId
	@Id
	protected Long id;
	
	// 帐号
	protected String account;
	// 密码
	protected String password;
	
	// 姓名
	protected String fullname;
	
	
	// 邮箱
	protected String email;

	//地址
	protected String address;

}
