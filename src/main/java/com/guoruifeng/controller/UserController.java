/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserController.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.controller 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月12日
 * @version: V1.0   
 */
package com.guoruifeng.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.guoruifeng.beans.Article;
import com.guoruifeng.beans.Channel;
import com.guoruifeng.beans.Collect;
import com.guoruifeng.beans.Image;
import com.guoruifeng.beans.TypeEnum;
import com.guoruifeng.beans.User;
import com.guoruifeng.common.CmsAssert;
import com.guoruifeng.common.ConstantClass;
import com.guoruifeng.common.MsgResult;
import com.guoruifeng.dao.ArticleResp;
import com.guoruifeng.service.ArticleService;
import com.guoruifeng.service.ChannelService;
import com.guoruifeng.service.CollectService;
import com.guoruifeng.service.UserService;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @作者: ZJ
 * @时间: 2019年11月12日
 */
@Controller
@RequestMapping("user")
public class UserController {
	// 用Logger打印语句，企业经常用这个方法，不用syso
	Logger log = Logger.getLogger(UserController.class);

	@Value("${upload.path}")
	String updloadPath;

	@Autowired
	UserService userService;

	@Autowired
	ArticleService articleService;

	@Autowired
	ChannelService channelService;

	@Autowired
	CollectService collectService;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	private SimpleDateFormat dateFormat;

	/**
	 * 
	 * @Title: tet
	 * @Description: 文章收藏功能
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping("favorite")
	public MsgResult favorite(HttpServletRequest request, int id) {
		CmsAssert.AssertTrue(id > 0, "id不能为负值");
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(loginUser != null, "您未登录，请先登录账户");
		int result = articleService.faverite(loginUser.getId(), id);
		CmsAssert.AssertTrue(result > 0, "收藏成功");
		return new MsgResult(1, "恭喜您，收藏成功", null);
	}

	/**
	 * 
	 * @Title: myfavorite
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@RequestMapping("myfavorite")
	public String myfavorite(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		PageInfo<Article> pageInfo = articleService.myfavoriteById(page, loginUser.getId());
		request.setAttribute("pageInfo", pageInfo);

		return "user/myfavorite";

	}

	/**
	 * 
	 * @Title: delArticle
	 * @Description: 取消收藏功能
	 * @param request
	 * @param id
	 * @return
	 * @return: MsgResult
	 */
	@RequestMapping("delFavorite")
	@ResponseBody
	public MsgResult delFavorite(HttpServletRequest request, int id) {

		CmsAssert.AssertTrue(id > 0, "文章id必须大于0");
		Article article = articleService.checkExist(id);
		CmsAssert.AssertTrue(article != null, "该文章不存在");

		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		/*
		 * CmsAssert.AssertTrue( loginUser.getRole()==ConstantClass.USER_ROLE_ADMIN ||
		 * loginUser.getId()==article.getUserId(), "只有该用户才能取消收藏该文章");
		 */

		int result = articleService.delFavorite(id);
		CmsAssert.AssertTrue(result > 0, "文章收藏失败");
		return new MsgResult(1, "取消收藏成功", null);

	}

	// httppxxxx://user/hello
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String tet(HttpServletRequest request) {
		request.setAttribute("info", "hello");
		return "user/test";
	}

	/**
	 * 跳转到注册页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(HttpServletRequest request) {
		return "user/register";
	}

	/**
	 * 处理用户提交的注册的数据
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, User user) {

		int result = userService.register(user);
		CmsAssert.AssertTrue(result > 0, "用户注册失败，请稍后再试");

		return "redirect:/user/login";
	}

	/**
	 * 跳转到登录页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "user/login";
	}

	/**
	 * 处理用户提交的登录的数据
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, User user) {

		User loginUser = userService.login(user);
		// 用户存在 登录成功
		if (loginUser != null) {
			request.getSession().setAttribute(ConstantClass.USER_KEY, loginUser);

			return loginUser.getRole() == ConstantClass.USER_ROLE_ADMIN ? "redirect:/admin/index"
					: "redirect:/user/home";
		} else {
			request.setAttribute("errorMsg", "用户名或密码错误！！");
			request.setAttribute("user", user);
			return "user/login";
		}

	}

	/**
	 * 
	 * @Title: logout
	 * @Description: 退出登录
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstantClass.USER_KEY);
		return "redirect:/";
	}

	@RequestMapping("checkname")
	@ResponseBody
	public boolean checkname(String username) {
		return null == userService.findByName(username);
	}

	/**
	 * 登录管理员后台操作页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request) {
		return "/user/home";
	}

	/**
	 * 
	 * @Title: updateArticle
	 * @Description: 根据id修改用户发表的文章
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */

	@GetMapping("updateArticle")
	public String updateArticle(HttpServletRequest request, int id) {
		// 为什么修改之后,立马就能再页面看到?
		// redisTemplate.delete("hot_articles");

		// 获取文章的详情 用于回显
		Article article = articleService.getDetailById(id);
		request.setAttribute("article", article);
		request.setAttribute("content1", htmlspecialchars(article.getContent()));

		System.out.println(" 将要修改文章 " + article);

		// 获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);

		return "article/update";
	}

	/**
	 * 
	 * @param request
	 * @param article
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@PostMapping("updateArticle")
	@ResponseBody
	public MsgResult updateArticle(HttpServletRequest request, MultipartFile file, Article article)
			throws IllegalStateException, IOException {
		// 文章id 是否存在

		// 用户是否有权限修改

		if (!file.isEmpty()) {
			String picUrl = processFile(file);
			article.setPicture(picUrl);
		}
			//这个是修改了mysql数据库
		int result = articleService.update(article);
		//=================发kafka============================
		// 由于你不知道要修改的文章id,因此我们把修改的文章对象,也发过去
		
		String jsonString = JSON.toJSONString(article);
		
		kafkaTemplate.send("articles", "update=" + jsonString);
		
		if (result > 0) {
			// 成功
			return new MsgResult(1, "", null);
		} else {
			// 失败则给出错误提示信息
			return new MsgResult(2, "对不起，您修改失败！请稍后再试", null);
		}

	}

	/**
	 * 
	 * @Title: postArticle
	 * @Description: 进入发表文章的界面
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("postArticle")
	public String postArticle(HttpServletRequest request) {

		// 获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
		return "article/publish";
	}

	/**
	 * 上传文件的规则 文件扩展名不能改变 保存到某个路径下边 要求子目录 子目录 每天一个子目录
	 */

	/**
	 * 
	 * @Title: postArticle
	 * @Description:
	 * 
	 * 				上传文件的规则 文件扩展名不能改变 保存到某个路径下边 要求子目录 子目录 每天一个子目录
	 * @param request
	 * @param file
	 * @param article
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @return: MsgResult
	 */
	@PostMapping("postArticle")
	@ResponseBody
	public MsgResult postArticle(HttpServletRequest request, MultipartFile file, Article article)
			throws IllegalStateException, IOException {

		if (!file.isEmpty()) {
			String fileUrl = processFile(file);
			article.setPicture(fileUrl);
		}
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		article.setUserId(loginUser.getId());

		int result = articleService.add(article);
		String jsonString = JSON.toJSONString(article);
		//通知卡夫卡,让次逻辑(redis)进行新增
		kafkaTemplate.send("articles","add="+jsonString);
		
		if (result > 0) {
			return new MsgResult(1, "处理成功", null);
		} else {
			return new MsgResult(2, "添加失败，请稍后再试试", null);
		}
	}

	/**
	 * 
	 * @param file
	 * @return 保存文件的相对路径
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {

		log.info("updloadPath is " + updloadPath);

		// 1 求扩展名 "xxx.jpg"
		String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		String fileNamePre = UUID.randomUUID().toString();
		// 计算出新的文件名称
		String fileName = fileNamePre + suffixName;

		dateFormat = new SimpleDateFormat("yyyyMMdd");
		String path = dateFormat.format(new Date());
		File pathFile = new File(updloadPath + "/" + path);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		// 最终的新的文件名称
		String newFileName = updloadPath + "/" + path + "/" + fileName;
		file.transferTo(new File(newFileName));

		return path + "/" + fileName;
	}

	/**
	 * 获取文章列表
	 * 
	 * @return
	 */
	@RequestMapping("myarticles")
	public String myarticles(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {

		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);

		PageInfo<Article> pageInfo = articleService.listByUser(page, loginUser.getId());
		request.setAttribute("pageInfo", pageInfo);
		return "user/myarticles";
	}

	@RequestMapping("delArticle")
	@ResponseBody
	public MsgResult delArticle(HttpServletRequest request, int id) {

		CmsAssert.AssertTrue(id > 0, "文章id必须大于0");
		Article article = articleService.checkExist(id);
		CmsAssert.AssertTrue(article != null, "该文章不存在");

		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(
				loginUser.getRole() == ConstantClass.USER_ROLE_ADMIN || loginUser.getId() == article.getUserId(),
				"只有管理员和文章的作者能删除文章");

		int result = articleService.delete(id);
		//通知kafka让次逻辑(redis) 进行删除
		kafkaTemplate.send("articles" ,"del="+id);
		CmsAssert.AssertTrue(result > 0, "文章删除失败");
		return new MsgResult(1, "删除成功", null);

	}

	@GetMapping("postImg")
	public String postImg(HttpServletRequest request) {
		// 获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
		return "article/postimg";
	}

	/**
	 * 
	 * @Title: postImg
	 * @Description: 上传图片，把图片转为json数据类型，存入到list集合中
	 * @param request
	 * @return
	 * @return: MsgResult
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	
	@Autowired
	ArticleResp articleResp;
	@RequestMapping(value = "postImg", method = RequestMethod.POST)
	@ResponseBody
	public MsgResult postImg(HttpServletRequest request, Article article, MultipartFile file[], String desc[])
			throws IllegalStateException, IOException {
		// 获取登录用户对象，判断用户是否登录
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		// 创建List集合
		List<Image> list = new ArrayList<Image>();
		// 遍历每个上传的图片，并存入list集合中
		for (int i = 0; i < desc.length && i < file.length; i++) {
			String url = processFile(file[i]);
			Image image = new Image();
			image.setDesc(desc[i]);
			image.setUrl(url);
			// 存入到list集合中
			list.add(image);
		}

		// 获取gson转换器，转为json数据类型
		Gson gson = new Gson();
		// 设置作者
		article.setUserId(loginUser.getId());
		article.setContent(gson.toJson(list));
		// 设置文章类型 图片类型
		article.setArticleType(TypeEnum.IMG);
		// 添加到数据库
		int add = articleService.add(article);
		if (add > 0) {
			articleResp.save(article);
			return new MsgResult(1, "发布成功", null);
		} else {
			return new MsgResult(2, "发布失败", null);
		}

	}

	/**
	 * 
	 * @Title: collect
	 * @Description: 把文章添加到我的收藏夹中,收藏的名字和地址。收藏表中cms_collect
	 * @param request
	 * @param collect
	 * @return
	 * @return: MsgResult
	 */
	@RequestMapping("collect")
	@ResponseBody
	public MsgResult collect(HttpServletRequest request, Collect collect) {

		// CmsAssert.AssertTrue(id>0, "id 不合法");
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(loginUser != null, "亲，您尚未登录！！");

		if (collect.getName().length() > 20) {
			collect.setName(collect.getName().substring(0, 20) + "...");
		}
		collect.setUserId(loginUser.getId());
		int result = collectService.add(collect);

		CmsAssert.AssertTrue(result > 0, "很遗憾，加入收藏失败！！");
		return new MsgResult(1, "恭喜，收藏成功", null);
	}

	/**
	 * 
	 * @Title: comment
	 * @Description: 评论
	 * @param request
	 * @param id
	 * @param content
	 * @return
	 * @return: MsgResult
	 */
	@RequestMapping("comment")
	@ResponseBody
	public MsgResult comment(HttpServletRequest request, int id, String content) {

		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(loginUser != null, "亲，您尚未登录");

		int result = articleService.comment(loginUser.getId(), id, content);
		CmsAssert.AssertTrue(result > 0, "亲，评论失败了！！");
		return new MsgResult(1, "评论成功", "");
	}

	private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

}
