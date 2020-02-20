/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.guoruifeng.dao;

import java.util.List;

import com.guoruifeng.beans.Category;

/** 
 * @ClassName: CategoryMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface CategoryMapper {

	/** 
	 * @Title: listByChannelId 
	 * @Description: 获取分类
	 * @param chnId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> listByChannelId(int chnId);
	
}
