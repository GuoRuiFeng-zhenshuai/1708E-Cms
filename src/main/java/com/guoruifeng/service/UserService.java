/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserService.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日
 * @version: V1.0   
 */
package com.guoruifeng.service;

import com.github.pagehelper.PageInfo;
import com.guoruifeng.beans.User;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
public interface UserService {

	/** 
	 * @Title: getPageList 
	 * @Description: TODO
	 * @param name
	 * @param page
	 * @return
	 * @return: PageInfo<User>
	 */
	PageInfo<User> getPageList(String name, Integer page);

	/** 
	 * @Title: getUserById 
	 * @Description: 根据id获取用户信息
	 * @param userId
	 * @return
	 * @return: User
	 */
	User getUserById(Integer userId);

	/** 
	 * @Title: updateStatus 
	 * @Description: 修改用户的状态 也就是解禁和封禁
	 * @param userId
	 * @param status
	 * @return
	 * @return: int
	 */
	int updateStatus(Integer userId, int status);

	/** 
	 * @Title: register 
	 * @Description: 注册用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int register(User user);

	/** 
	 * @Title: 登录 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user);

	/** 
	 * @Title: findByName 
	 * @Description: 根据用户名查找用户
	 * @param username
	 * @return
	 * @return: Object
	 */
	User findByName(String username);

}
