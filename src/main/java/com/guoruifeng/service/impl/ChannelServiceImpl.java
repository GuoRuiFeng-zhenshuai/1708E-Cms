/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelServiceImpl.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.service.impl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.guoruifeng.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoruifeng.beans.Channel;
import com.guoruifeng.dao.ChannelMapper;
import com.guoruifeng.service.ChannelService;

/** 
 * @ClassName: ChannelServiceImpl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	ChannelMapper channelMapper;
	/* (non Javadoc) 
	 * @Title: list
	 * @Description: TODO
	 * @return 
	 * @see com.guoruifeng.service.ChannelService#list() 
	 */
	@Override
	public List<Channel> list() {
		return channelMapper.list();
	}
	
}
