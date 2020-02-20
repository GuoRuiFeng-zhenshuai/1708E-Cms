/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: AdminController.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.controller 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日
 * @version: V1.0   
 */
package com.guoruifeng.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.guoruifeng.beans.Article;
import com.guoruifeng.beans.User;
import com.guoruifeng.common.CmsAssert;
import com.guoruifeng.common.MsgResult;
import com.guoruifeng.service.ArticleService;
import com.guoruifeng.service.UserService;

/** 
 * @ClassName: AdminController 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService  articleService;
	
	//前台访问页面
	/*@RequestMapping("indexs")
	public String indexs() {
		return "index";
	}*/
	
	//后台访问页面
	@RequestMapping("index")
	public String index() {
		return "amdin/index";
	}
	/**
	 * 
	 * @Title: articles 
	 * @Description: TODO
	 * @param request
	 * @param status  -1 全部  0 待审核  1 审核通过  2 审核未通过
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(HttpServletRequest request,
			@RequestParam(defaultValue="-1") int status,
			@RequestParam(defaultValue="1") Integer page) {
		
		PageInfo<Article> articlePage =  articleService.getPageList(status,page);
		request.setAttribute("pageInfo", articlePage);
		request.setAttribute("status", status);
		
		return "amdin/article/list";
	}
	
	
	
	
	@RequestMapping("users")
	public String users(HttpServletRequest request,
			@RequestParam(defaultValue="") String name,
			@RequestParam(defaultValue="1") Integer page) {
		
		PageInfo<User> userPage =  userService.getPageList(name,page);
		request.setAttribute("info", userPage);
		request.setAttribute("name", name);
		
		return "amdin/user/list";
	}
	
	/**
	 * 用户解禁或封禁用户
	 * @param userId
	 * @param status
	 * @return
	 */
	@RequestMapping("lockuser")
	@ResponseBody
	public MsgResult lock(Integer userId,int status) {
		
		/**
		 * 
		 */
		if(status != 0 && status!=1) {
			return new MsgResult(2,"参数无效",null);
		}
		
		/**
		 * 
		 */
		User user  = userService.getUserById(userId);
		
		/**
		 * 
		 */
		if(user == null) {
			return new MsgResult(2,"该用户不存在",null);
		}
		
		/**
		 * 
		 */
		if(user.getLocked()==status) {
			return new MsgResult(2,"无需做该操作",null);
		}
		
		int result = userService.updateStatus(userId,status);
		if(result>0) {
			return new MsgResult(1,"恭喜您，处理成功",null);
		}else{
			return new MsgResult(2,"非常抱歉，处理失败，请与管理员联系！",null);
		}
		
	}
	
	@RequestMapping("getArticle")
	@ResponseBody
	public MsgResult getArticle(int id) {
		Article article = articleService.getDetailById(id);
		CmsAssert.AssertTrue(article!=null, "文章不存在");
		return new MsgResult(1,"获取成功",article);
	}
	
	@RequestMapping("applyArticle")
	@ResponseBody
	public MsgResult applyArticle(int id,int status) {
		Article article = articleService.checkExist(id);
		CmsAssert.AssertTrue(article!=null, "该文已经不存在");
		int result = articleService.apply( id,status);
		if(result>0) {
			return new MsgResult(1,"处理成功",null);
		}else {
			return new MsgResult(2,"处理失败",null);
		}
	}
	
	/**
	 * 设置热门与否
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("setArticleHot")
	@ResponseBody
	public MsgResult setArticleHot(int id,int status) {
		Article article = articleService.checkExist(id);
		CmsAssert.AssertTrue(article!=null, "该文已经不存在");
		int result = articleService.setHot( id,status);
		if(result>0) {
			return new MsgResult(1,"处理成功",null);
		}else {
			return new MsgResult(2,"处理失败",null);
		}
	}
	
	
	
}
