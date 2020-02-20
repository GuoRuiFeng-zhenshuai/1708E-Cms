/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinkService.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日
 * @version: V1.0   
 */
package com.guoruifeng.service;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.guoruifeng.beans.Link;

/** 
 * @ClassName: LinkService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日  
 */
public interface LinkService {

	/** 
	 * @Title: list 
	 * @Description: 列表+分页
	 * @param page
	 * @return
	 * @return: PageInfo
	 */
	PageInfo list(int page);

	/** 
	 * @Title: add 
	 * @Description: 添加
	 * @param link
	 * @return: void
	 */
	void add(Link link);

	/** 
	 * @Title: delete 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	void delete(int id);
	
	/** 
	 * @Title: get 
	 * @Description: 根据id获取要修改的友情链接
	 * @param id
	 * @return
	 * @return: Object
	 */
	Link get(int id);


	/** 
	 * @Title: update 
	 * @Description: 修改友情链接
	 * @param link
	 * @return: void
	 */
	int update(Link link);

	
}
