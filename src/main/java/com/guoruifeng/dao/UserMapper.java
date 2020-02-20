/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日
 * @version: V1.0   
 */
package com.guoruifeng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.guoruifeng.beans.User;

/** 
 * @ClassName: UserMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
public interface UserMapper {


	List<User> list(String name);


	User getById(Integer userId);


	@Update("UPDATE cms_user SET locked=${status} WHERE id=${userId}")
	int updateStatus(@Param("userId")Integer userId, @Param("status")int status);

	@Select("SELECT * FROM cms_user WHERE username = #{value} limit 1")
	User findByUserName(String username);

	//用户/管理注册
	int add(User user);

}
