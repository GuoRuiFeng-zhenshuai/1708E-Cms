/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CollectController.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.controller 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日
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
import com.guoruifeng.beans.Collect;
import com.guoruifeng.beans.User;
import com.guoruifeng.common.ConstantClass;
import com.guoruifeng.common.MsgResult;
import com.guoruifeng.service.CollectService;

/** 
 * @ClassName: CollectController 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日 
 * 
 * 我的收藏
 */
@Controller
@RequestMapping("collect")
public class CollectController {
	
	@Autowired
	CollectService collectService;
	
	/**
	 * 
	 * @Title: list 
	 * @Description: 我的收藏夹展示
	 * @param request
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request, 
			@RequestParam(defaultValue="1") int page) {
		
		User loginUser=(User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		
		
		PageInfo info = collectService.list(loginUser.getId(), page);
		request.setAttribute("info", info);
		return "user/collect/list";
		
		
	}
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 跳转到添加收藏页面
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("add")
	public String add(HttpServletRequest request) {
		request.setAttribute("collect", new Collect());
		return "user/collect/add";	 
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 跳转到修改收藏页面
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("update")
	public String update(HttpServletRequest request,int id) {
		request.setAttribute("collect", collectService.get(id));
		return "user/collect/update";	 
	}
	
	/**
	 * 
	 * @Title: delete 
	 * @Description: 删除收藏
	 * @param request
	 * @param id
	 * @return
	 * @return: MsgResult
	 */
	@RequestMapping("delete")
	@ResponseBody
	public MsgResult delete(HttpServletRequest request,int id) {
		int result = collectService.delete(id);
		if(result<1)
			return new MsgResult(2,"删除失败",null);
		
		return new MsgResult(1,"删除失败",null);
		
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改收藏
	 * @param request
	 * @param collect
	 * @param result
	 * @return
	 * @return: String
	 */
	@PostMapping("update")
	public String update(HttpServletRequest request,
			@Valid  @ModelAttribute("collect") Collect collect,
			BindingResult result
			) {
		
		if(!StringUtils.isHttpUrl(collect.getUrl())) {
			result.rejectValue("url", "不是合法的url", "不是合法的url");
		}
		
		// 有错误 还在原来的页面
		if(result.hasErrors()) {
			//request.setAttribute("collect", collect);
			return "user/collect/update";	
		}
		
		collectService.update(collect);
		
		// 没有错误跳转到列表页面
		return "redirect:list";
	}
	
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 添加收藏
	 * @param request
	 * @param collect
	 * @param result
	 * @return
	 * @return: String
	 */
	@PostMapping("add")
	public String add(HttpServletRequest request,
			@Valid  @ModelAttribute("collect") Collect collect,
			BindingResult result
			) {
		
		if(!StringUtils.isHttpUrl(collect.getUrl())) {
			result.rejectValue("url", "不是合法的url", "不是合法的url");
		}
		
		// 有错误 还在原来的页面
		if(result.hasErrors()) {
			
			request.setAttribute("collect", collect);
			return "user/collect/add";	
		}
		
		User loginUser=(User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		collect.setUserId(loginUser.getId());
		collectService.add(collect);
		
		// 没有错误跳转到列表页面
		return "redirect:list";
	}
}
