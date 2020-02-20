/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryServiceImpl.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.service.impl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.guoruifeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoruifeng.beans.Category;
import com.guoruifeng.dao.CategoryMapper;
import com.guoruifeng.service.CategoryService;

/** 
 * @ClassName: CategoryServiceImpl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryMapper categoryMapper;

	/* (non Javadoc) 
	 * @Title: listByChannelId
	 * @Description: TODO
	 * @param chnId
	 * @return 
	 * @see com.guoruifeng.service.CategoryService#listByChannelId(int) 
	 */
	@Override
	public List<Category> listByChannelId(int chnId) {
		
		return categoryMapper.listByChannelId(chnId);
	}
}
