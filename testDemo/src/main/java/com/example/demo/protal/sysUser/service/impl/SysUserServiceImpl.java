package com.example.demo.protal.sysUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.example.demo.common.utils.EncryptUtils;
import org.springframework.stereotype.Service;

import com.example.demo.common.model.ReturnResult;
import com.example.demo.common.utils.BeanUtils;
import com.example.demo.common.utils.UniqueIdUtils;
import com.example.demo.protal.sysUser.dao.SysUserMapper;
import com.example.demo.protal.sysUser.model.SysUser;
import com.example.demo.protal.sysUser.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;
	
	@Override
	public List<SysUser> getAll() {
		List<SysUser> selectAll = sysUserMapper.getAll();
		return selectAll;
	}

	/**
	 * 保存用户
	 * @return 
	 */
	@Override
	public ReturnResult save(SysUser sysUser) {
		
		try {
			
			Long id = UniqueIdUtils.getId();
			sysUser.setId(id);

			String password = sysUser.getPassword();
			password = EncryptUtils.encryptBasedDes(password);
			sysUser.setPassword(password);


			int insert = sysUserMapper.add(sysUser);
			
			if(insert == 1) {
				return ReturnResult.success("注册成功!请重新登录");
			}else {
				return ReturnResult.fail(-1, "fail","注册失败!");
			}
			
		} catch (Exception e) {
			
		}
		return null;
		
	}

	
	/**
	 * 根据id查询用户
	 */
	@Override
	public SysUser getById(Long id) {
		SysUser sysUser = sysUserMapper.getById(id);
		return sysUser;
	}
	
	
	/**
	 * 删除用户
	 */
	@Override
	public void delById(Long id) {
		int delete = sysUserMapper.delById(id);
	}

	/**
	 * 判断用户是否存在
	 */
	@Override
	public SysUser checkUser(SysUser sysUser) {

		String password = sysUser.getPassword();
		password = EncryptUtils.encryptBasedDes(password);
		sysUser.setPassword(password);

		return sysUserMapper.checkUser(sysUser);
	}
	
}
