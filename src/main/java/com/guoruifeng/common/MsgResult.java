/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: MsgResult.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.common 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日
 * @version: V1.0   
 */
package com.guoruifeng.common;

import java.io.Serializable;

/** 
 * @ClassName: MsgResult 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月13日 
 */
public class MsgResult implements Serializable{
	
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -1831217997251921541L;
	int result;
	String errorMsg;
	Object data;
	
	public MsgResult() {
		
	}
	
	public MsgResult(int result, String errorMsg, Object data) {
		super();
		this.result = result;
		this.errorMsg = errorMsg;
		this.data = data;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getData() {
		return data;
	}
}
