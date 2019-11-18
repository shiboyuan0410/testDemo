package com.example.demo.sysUser.service;

import java.util.List;

import com.example.demo.common.model.ReturnResult;
import com.example.demo.sysUser.model.SysUser;

public interface SysUserService {

	List<SysUser> getAll();

	ReturnResult save(SysUser sysUser);

	void delById(Long id);

	SysUser getById(Long id);

	int checkUser(SysUser sysUser);

}
