package com.example.demo.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.protal.sysUser.model.SysUser;

public class SessionUtils {



	private static HttpSession getSession(HttpServletRequest request){
		HttpSession sessoin = request.getSession();//这就是session的创建
		return sessoin;
	}


	/**
	 * 存储session信息
	 * @param o
	 * @return
	 */
	public static void setUserSession(SysUser sysUser) {
		
	}
}
