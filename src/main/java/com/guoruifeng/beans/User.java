/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: User.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.beans 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日
 * @version: V1.0   
 */
package com.guoruifeng.beans;

import java.io.Serializable;
import java.util.Date;

import com.guoruifeng.common.ConstantClass;


/** 
 * @ClassName: User 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
public class User implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6460832723994198125L;
	
	//id
	private Integer id;
	//用户名
	private String username;
	//密码
	private String password;
	//昵称没有用上
	private String nickname;
	//生日
	private Date birthday;
	// 性别
	private int gender;
	//是否被解禁
	private int  locked;
	//注册时间
	private Date createTime;
	private Date updateTime;
	//没有用上
	private String url            ;
	// 没有用上
	private int score          ;
	
	private int role=ConstantClass.USER_ROLE_GENERAL;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
