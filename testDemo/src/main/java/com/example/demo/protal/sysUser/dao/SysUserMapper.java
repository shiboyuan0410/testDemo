package com.example.demo.protal.sysUser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.common.dao.BaseMapper;
import com.example.demo.protal.sysUser.model.SysUser;


@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>{

	List<SysUser> getAll();
	
	int add(SysUser sysUser);
	
	int delById(Long id);

	SysUser getById(Long id);

	int checkUser(SysUser sysUser);
	
}
