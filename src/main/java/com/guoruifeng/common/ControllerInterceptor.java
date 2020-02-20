/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ControllerInterceptor.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.common 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月19日
 * @version: V1.0   
 */
package com.guoruifeng.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: ControllerInterceptor 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月19日 
 */
@ControllerAdvice
public class ControllerInterceptor {

	@ExceptionHandler(CmcException.class)
	@ResponseBody
	public MsgResult  interceptorException(CmcException excetion) {
		System.out.println(" 错误是 "  + excetion);
		return new MsgResult(100,excetion.getMessage(),null);
	}	
	
	@ExceptionHandler(CmcExceptionHtml.class)
	public ModelAndView  interceptorExceptionHtml(CmcExceptionHtml excetion) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");// 跳转到jsp
		modelAndView.addObject("errorInfo", excetion.getMessage());
		return modelAndView;
		
		
	}
}
