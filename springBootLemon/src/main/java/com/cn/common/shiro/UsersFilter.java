/**
 * Copyright (c) 2016,http://www.365wuliu.com/  All Rights Reserved.
 */
package com.cn.common.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import com.cn.base.utils.CheckUtil;


/**
 * 
 * UsersFilter:Shiro自定义拦截器类
 * @Project Name:spring-boot-demo 
 * @File Name:UsersFilter.java 
 * @Package Name:com.example.myproject.sysconf.shiro
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午12:41:25
 */
public class UsersFilter extends PathMatchingFilter {

	private Logger logger = Logger.getLogger(UsersFilter.class);

	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
		HttpServletRequest req = WebUtils.toHttp(request);
		HttpServletResponse res = WebUtils.toHttp(response);
		try {

			// Response设置跨域头
			res.addHeader("Access-Control-Allow-Origin", "*");

			// 获得前端传过来的apiKey
			String apiKey = req.getParameter("api_key");

//			logger.info("apiKey=====>>" + apiKey);

			// 序列化apiKey到ShiroSessionId
			Serializable sessionId = apiKey;

			// 通过ShiroSessionId构建ShiroSubject
			Subject requestSubject = new Subject.Builder().sessionId(sessionId).buildSubject();

//			logger.info("requestSubject 被认证状态=====>>" + requestSubject.isAuthenticated());

			if (!CheckUtil.checkObj(requestSubject)) {
				throw new Exception("ShiroSubject构建失败，SessionId:" + sessionId);
			}

			// 判断构建的ShiroSubject是否为已认证，如果是已认证的Subject则加入到Shiro的线程上下文中
			if (requestSubject.isAuthenticated()) {
				ThreadContext.bind(requestSubject);
			}

			return true;
		} catch (Exception e) {
			logger.error("ShiroUsersFilter error", e);
			return false;
		}
	}

}
