/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Collect.java 
 * @Prject: -cms
 * @Package: com.guoruifeng.beans 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日
 * @version: V1.0   
 */
package com.guoruifeng.beans;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

/** 
 * @ClassName: Collect 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日 
 */
public class Collect {
	
	 private int	id;
	 
	 private int	userId;
	 
	 @Length(min=2,max=128)
	 private String url;
	 
	 @Length(min=2,max=30)
	 private String name;
	 
	 private Date created;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/* (non Javadoc) 
	 * @Title: toString
	 * @Description: TODO
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Collect [id=" + id + ", userId=" + userId + ", url=" + url
				+ ", name=" + name + ", created=" + created + "]";
	}

	 
}
