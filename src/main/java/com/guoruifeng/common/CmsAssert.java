/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CmsAssert.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.common 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月15日
 * @version: V1.0   
 */
package com.guoruifeng.common;

/** 
 * @ClassName: CmsAssert 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月15日 
 */
public class CmsAssert {
	
	public static void AssertTrue(boolean express,String msg){
		if(!express)
			throw new CmcException(msg);
	}
	/**
	 * 
	 * @Title: AssertTrueHtml 
	 * @Description: TODO
	 * @param express
	 * @param msg
	 * @return: void
	 */
	public static void AssertTrueHtml(boolean express, String msg) {
		// TODO Auto-generated method stub
		if(!express)
			throw new CmcExceptionHtml(msg);
	}
}
