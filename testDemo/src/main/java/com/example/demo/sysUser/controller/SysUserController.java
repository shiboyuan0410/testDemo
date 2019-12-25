package com.example.demo.sysUser.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.model.ReturnResult;
import com.example.demo.common.utils.BeanUtils;
import com.example.demo.sysUser.model.SysUser;
import com.example.demo.sysUser.service.SysUserService;

/**
 * 用户
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

	
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 获取所有的用户
	 * @return
	 */
	@RequestMapping("/all")
	public String getAllUsers(Model model) {
		List<SysUser> sysUserList = sysUserService.getAll();
		model.addAttribute("sysUserList", sysUserList);
		return "user/userList";
	}
	
	
	
	/**
	 * 跳转到注册用户页面
	 * @return
	 */
	@RequestMapping("/register")
	public String register() {
		return "user/userRegister";
	}
	
	
	/**
	 * 用户编辑页面
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit() {
		return "user/userEdit";
	}
	
	
	
	/**
	 * 添加用户
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ReturnResult save(SysUser sysUser) {
		return sysUserService.save(sysUser);
	}
	
	
	/**
	 * 用户登录验证
	 * @return
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public ReturnResult checkUser(SysUser sysUser,HttpServletRequest request) {
		
		int checkUser = sysUserService.checkUser(sysUser);
		if(checkUser == 1) {
			
			HttpSession session=request.getSession();//获取session并将sysUser存入session对象
			session.setAttribute("sysUser", sysUser);

			return ReturnResult.success();
		}else {
			return ReturnResult.fail(-1, "fail","账号或密码不正确!");
		}
		
	}
	
	/*@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate_oracle;
	
	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate_mysql;
	
	@RequestMapping("/cs")
	@ResponseBody
	public int cs() {
		List<Map<String, Object>> queryForList = jdbcTemplate_oracle.queryForList("select * from sys_user");
		System.out.println("oracle -----" + queryForList.size());
		return queryForList.size();
	}*/
}
