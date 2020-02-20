/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinkController.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.controller 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日
 * @version: V1.0   
 */
package com.guoruifeng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.guoruifeng.StringUtils;
import com.guoruifeng.beans.Link;
import com.guoruifeng.service.LinkService;

/** 
 * @ClassName: LinkController 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日  
 */
@Controller
@RequestMapping("link")
public class LinkController {
	@Autowired
	LinkService linkService;
	
	/**
	 * 
	 * @Title: list 
	 * @Description: 友情链接管理页面
	 * @param request
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request,@RequestParam(defaultValue="1")int page) {
		PageInfo info = linkService.list(page);
		request.setAttribute("info", info);
		return "amdin/link/list";
	}
	/**
	 * 
	 * @Title: add 
	 * @Description: 跳转到友情链接添加页面，用get请求
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("add")
	public String add(HttpServletRequest request) {
		request.setAttribute("link", new Link());
		return "amdin/link/add";
	}
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 用form表单标签实现添加
	 * @param request
	 * @param link
	 * @param result
	 * @return
	 * @return: String
	 */
	@PostMapping("add")
	public String add(HttpServletRequest request,
			@Valid @ModelAttribute("link")Link link,BindingResult result) {
		
		if(!StringUtils.isHttpUrl(link.getUrl())) {
			result.rejectValue("url", "", "不是合法的url");
		}
		
		//如果有错，返回到添加页面
		if(result.hasErrors()) {
			return "amdin/link/add";
		}
		linkService.add(link);
		//如果添加成功，返回到列表页面
		return "redirect:list";
		
	}
	/**
	 * 
	 * @Title: delete 
	 * @Description: 删除友情链接
	 * @param id
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("delete")
	@ResponseBody
	public boolean delete(int id) {
		try {
			linkService.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 * @Title: add 
	 * @Description:跳转到修改页面
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("update")
	public String add(HttpServletRequest request,int id) {
		request.setAttribute("link", linkService.get(id));
		return "amdin/link/update";	 
	}
	/**
	 * 
	 * @Title: update 
	 * @Description: 管理员修改友情链接
	 * @param request
	 * @param link
	 * @param result
	 * @return
	 * @return: String
	 */
	@PostMapping("update")
	public String update(HttpServletRequest request,
			@Valid  @ModelAttribute("link") Link link,
			BindingResult result) {
			
		if(!StringUtils.isHttpUrl(link.getUrl())) {
			result.rejectValue("url", "", "不是合法的url");
		}
		
		// 有错误 还在原来的页面
		if(result.hasErrors()) {
			//request.setAttribute("link", link);
			return "amdin/link/update";	
		}
		
		linkService.update(link);
		
		// 没有错误跳转到列表页面
		return "redirect:list";
	}
	
	
}
