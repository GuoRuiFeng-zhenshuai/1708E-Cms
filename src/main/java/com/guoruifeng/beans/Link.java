/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Link.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.beans 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日
 * @version: V1.0   
 */
package com.guoruifeng.beans;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

/** 
 * @ClassName: Link 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日   
 */
public class Link {
	
	private Integer id;
	//网址
	@Length(max=32,min=5,message="长度超出范围")
	@URL
	private String url;
	//网址名
	@Length(max=10,min=2,message="网名长度为2-8位")
	private String name;
	//创建时间
	private Date created;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	
}
