/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: IndexController.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.controller 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.guoruifeng.controller;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.guoruifeng.beans.Article;
import com.guoruifeng.beans.Category;
import com.guoruifeng.beans.Channel;
import com.guoruifeng.beans.Link;
import com.guoruifeng.common.ConstantClass;
import com.guoruifeng.common.HLUtils;
import com.guoruifeng.dao.ArticleResp;
import com.guoruifeng.service.ArticleService;
import com.guoruifeng.service.CategoryService;
import com.guoruifeng.service.ChannelService;
import com.guoruifeng.service.LinkService;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @作者: ZJ
 * @时间: 2019年11月14日
 */
@Controller
public class IndexController {
	/**
	 * 注入频道
	 */
	@Autowired
	ChannelService channelService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ArticleService articleService;

	@Autowired
	LinkService linkService;

	@Autowired
	ArticleResp articleResp;

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	RedisTemplate r;

	
	/**
	 * 
	 * @Title: channel
	 * @Description: 根据id查询文章内容
	 * @param request
	 * @param chnId
	 * @param categoryId
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("channel")
	public String channel(HttpServletRequest request, @RequestParam(defaultValue = "1") int chnId,
			@RequestParam(defaultValue = "0") int categoryId, @RequestParam(defaultValue = "1") int page) {

		// 回传参数数值
		request.setAttribute("chnId", chnId);
		request.setAttribute("categoryId", categoryId);

		// 获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);

		// 获取这个频道下所以的分类
		List<Category> categories = categoryService.listByChannelId(chnId);
		request.setAttribute("categories", categories);

		// 文章分页
		PageInfo<Article> articles = articleService.listByCat(chnId, categoryId, page);
		request.setAttribute("articles", articles);
		// 跳转到用户页面
		return "channelindex";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "index", "/" })
	public String index(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {

		// 获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);

		PageInfo<Article> hotList = articleService.hotList(page);
		//实现守夜人们1文章第一页记录Redis缓存功能，有效期5分钟
		r.opsForValue().set("redis_hotList", "", 5, TimeUnit.MINUTES);
		
		List<Article> newArticles = articleService.getNewArticles(5);

		// 获取最新图片文章
		List<Article> imgArticles = articleService.getImgArticles(10);

		// 友情链接
		PageInfo<Link> info = linkService.list(1);
		List<Link> linkList = info.getList();
		
		request.setAttribute("hotList", hotList);
		request.setAttribute("newArticles", newArticles);
		request.setAttribute("imgArticles", imgArticles);
		request.setAttribute("linkList", linkList);

		return "index";
	}
	@GetMapping("search")
	public String gl(HttpServletRequest request,String key,@RequestParam(defaultValue="1")int page ){
		PageInfo<?> findByHighLight = HLUtils.findByHighLight(elasticsearchTemplate, Article.class, page, 5, new String []{"title"}, "id", key);
			
				List<Article> list = (List<Article>) findByHighLight.getList();
				PageInfo<Article> pageinfo=new PageInfo<Article>(list);
				request.setAttribute("key", key);
				request.setAttribute("hotList",pageinfo );
		return "index";
		
	}
}
