/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Comment.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.beans 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日
 * @version: V1.0   
 */
package com.guoruifeng.beans;

import java.util.Date;

/** 
 * @ClassName: Comment 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日 
 */
public class Comment {
	private int id;
	private int articleId;
	private int userId;
	private String content;
	private Date created;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
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
		return "Comment [id=" + id + ", articleId=" + articleId + ", userId="
				+ userId + ", content=" + content + ", created=" + created
				+ "]";
	}
	
}
