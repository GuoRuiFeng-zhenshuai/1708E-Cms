/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleController.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.controller 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.guoruifeng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.guoruifeng.HLUtils;
import com.guoruifeng.beans.Article;
import com.guoruifeng.beans.Category;
import com.guoruifeng.beans.Comment;
import com.guoruifeng.beans.Image;
import com.guoruifeng.beans.TypeEnum;
import com.guoruifeng.common.CmsAssert;
import com.guoruifeng.common.ConstantClass;
import com.guoruifeng.common.MsgResult;
import com.guoruifeng.dao.ArticleMapper;
import com.guoruifeng.service.ArticleService;
import com.guoruifeng.service.CategoryService;

/** 
 * @ClassName: ArticleController 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */

@RequestMapping("article")
@Controller
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CategoryService catService; 
	

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	

	
	@RequestMapping("showdetail")
	public String showDetail(HttpServletRequest request,Integer id) {
		
		Article article = articleService.getById(id); 
		CmsAssert.AssertTrueHtml(article!=null, "文章不存在");
		int a=article.getHits();
		article.setHits(a+1);
		//这个是修改了mysql数据库
		int result = articleService.updates(article);
		//=================发kafka============================
		// 由于你不知道要修改的文章id,因此我们把修改的文章对象,也发过去
		
		String jsonString = JSON.toJSONString(article);
		
		
		kafkaTemplate.send("articles", "update=" + jsonString);
	
		//redisTemplate.opsForValue().set(key, "",5, TimeUnit.MINUTES);
	
		request.setAttribute("article",article);
		if(article.getArticleType()==TypeEnum.HTML) {
			return "article/detail";
		}else {
			//获取json转换器
			Gson gson = new Gson();
			//文章内容转换成集合对象
			List<Image> imgs = gson.fromJson(article.getContent(), List.class);
			article.setImgList(imgs);
			System.out.println("article "+article);
			return "article/detailimg";
			
		}
		
		
	}
	/**
	 * 
	 * @Title: getCategoryByChannel 
	 * @Description: 获取频道信息
	 * @param chnId
	 * @return
	 * @return: MsgResult
	 */
	@RequestMapping("getCategoryByChannel")
	@ResponseBody
	public MsgResult getCategoryByChannel(int chnId) {
		//List<Category> categories =  
		List<Category> categories = catService.listByChannelId(chnId);
		return new MsgResult(1, "",  categories);
		
	}
	/**
	 * 
	 * @Title: commentlist 
	 * @Description: 获取该用户的所有评论
	 * @param request
	 * @param id
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("commentlist")
	//@ResponseBody
	public String commentlist(HttpServletRequest request, int id,
			@RequestParam(defaultValue="1") int page) {
		
		PageInfo<Comment> pageComment =  articleService.commentlist(id,page);
		request.setAttribute("pageComment", pageComment);
		return "article/comments";
		//return new MsgResult(1,"获取成功",pageComment);
		
	}
}
