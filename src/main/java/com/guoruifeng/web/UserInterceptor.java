/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserInterceptor.java 
 * @Prject: -cms
 * @Package: com.guoruifeng.web 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月18日
 * @version: V1.0   
 */
package com.guoruifeng.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.guoruifeng.beans.User;
import com.guoruifeng.common.ConstantClass;

/** 
 * @ClassName: UserInterceptor 
 * @Description: 拦截器
 * @作者: ZJ 
 * @时间: 2019年11月18日 
 */
public class UserInterceptor implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User loginUser =  (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		
		// 用户没有登录
		if(loginUser==null) {
			response.sendRedirect("/user/login");
			return false;
		}
		
		/**
		 * 普通用户不能进入管理员页面
		 */
		if(request.getServletPath().contains("/admin/") 
				&& loginUser.getRole()==ConstantClass.USER_ROLE_GENERAL ) {
			request.setAttribute("errorMsg", "只有管理员才能访问这个页面");
			//response.sendRedirect("/user/login");
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		return true;
	}
}
