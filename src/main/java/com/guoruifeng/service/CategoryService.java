/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryService.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.service 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.guoruifeng.service;

import java.util.List;

import com.guoruifeng.beans.Category;

/** 
 * @ClassName: CategoryService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface CategoryService {

	/** 
	 * @Title: listByChannelId 
	 * @Description: TODO
	 * @param chnId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> listByChannelId(int chnId);

}
