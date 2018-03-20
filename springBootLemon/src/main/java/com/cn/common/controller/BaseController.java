/**
 * Copyright (c) 2016,http://www.365wuliu.com/  All Rights Reserved.
 */
package com.cn.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.common.shiro.ShiroUser;


/**
 * 
 * BaseController:基类controller,用继承的方式使用 Project Name:cargo File
 * Name:BaseController.java Package Name:com.cargo.common.controller
 * 
 * @Creator:ZHU CHEN
 * @Date:2016年4月14日上午11:58:08
 */
public class BaseController {

	/**
	 * 
	 * currentRequest:获取当前request
	 * 
	 * @return HttpServletRequest
	 * @Creator:ZHU CHEN
	 * @Date:2016年4月14日 上午11:57:11
	 */
	protected HttpServletRequest currentRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) requestAttributes).getRequest();
	}

	/**
	 * 
	 * getRootPath:获取当前项目的根路径
	 * 
	 * @param request
	 * @return String
	 * @Creator:ZHU CHEN
	 * @Date:2016年4月14日 上午11:57:40
	 */
	protected String getRootPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
	}

	/**
	 * 
	 * curretUser:获取当前登录用户，如果是其他客户端使用，则不起作用，需要通过参数控制
	 * 
	 * @return
	 * @return :ShiroUser
	 * @Creator:ZHU CHEN
	 * @Date:2016年4月18日 上午9:44:18
	 */
	protected ShiroUser curretUser() {
		Subject subject = SecurityUtils.getSubject();
		Object obj = subject.getPrincipal();
		if (obj == null) {
			return null;
		} else {
			return (ShiroUser) obj;
		}
	}
}
