/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.guoruifeng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.guoruifeng.beans.Channel;

/** 
 * @ClassName: ChannelMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface ChannelMapper {

	/** 
	 * @Title: list 
	 * @Description: TODO
	 * @return
	 * @return: List<Channel>
	 */
	
	@Select("SELECT * FROM cms_channel ORDER BY id")
	List<Channel> list();

}
