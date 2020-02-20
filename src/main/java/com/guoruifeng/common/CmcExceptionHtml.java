/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CmcExceptionHtml.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.common 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月19日
 * @version: V1.0   
 */
package com.guoruifeng.common;

/** 
 * @ClassName: CmcExceptionHtml 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月19日 
 */
public class CmcExceptionHtml extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 9071941577156183402L;
	
	
	public CmcExceptionHtml(String msg) {
		super(msg);
	}

	
}
