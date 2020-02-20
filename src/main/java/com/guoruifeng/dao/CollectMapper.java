/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CollectMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日
 * @version: V1.0   
 */
package com.guoruifeng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.guoruifeng.beans.Collect;

/** 
 * @ClassName: CollectMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日 
 */
public interface CollectMapper {

	/** 
	 * @Title: list 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: List<Collect>
	 */
	@Select("SELECT * FROM cms_collect "
			+ " WHERE userId=#{userId} "
			+ " ORDER BY created DESC")
	List<Collect> list(int userId);

	/** 
	 * @Title: add 
	 * @Description: TODO
	 * @param collect
	 * @return
	 * @return: int
	 */
	@Insert("INSERT INTO cms_collect (userId,url,name,created) "
			+ " VALUES(#{userId},#{url},#{name},now())")
	int add(Collect collect);

	/** 
	 * @Title: delete 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: int
	 */
	@Delete("DELETE  FROM cms_collect WHERE id=#{value} ")
	int delete(int id);

	/** 
	 * @Title: get 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Collect
	 */
	@Select("SELECT * FROM cms_collect WHERE id=#{value} ")
	Collect get(int id);

	/** 
	 * @Title: update 
	 * @Description: TODO
	 * @param collect
	 * @return
	 * @return: int
	 */
	@Update("UPDATE cms_collect set url=#{url},name=#{name} "
			+ "	WHERE id=#{id}")
	int update(Collect collect);
	
}
