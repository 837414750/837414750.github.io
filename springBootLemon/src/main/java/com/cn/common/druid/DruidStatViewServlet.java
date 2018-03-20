/**
 * Copyright (c) 2018,http://www.365wuliu.com/  All Rights Reserved.
 */
package com.cn.common.druid;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties.StatViewServlet;

/** 
 * DruidStatFilter:druid数据源状态监控.
 * @Project Name:springBootLemon 
 * @File Name:DruidStatFilter.java 
 * @Package Name:com.cn.common.druid
 * @Creator:LU YONG JUN
 * @Date:2018年3月8日下午5:19:05 
 */
@WebServlet(urlPatterns = "/druid/*",
	initParams = {
    // IP白名单 (没有配置或者为空，则允许所有访问)
    //@WebInitParam(name = "allow", value = "192.168.1.72,127.0.0.1"),
    // IP黑名单 (存在共同时，deny优先于allow)
    @WebInitParam(name = "deny", value = ""),
    // 用户名
    @WebInitParam(name = "loginUsername", value = "admin"),
    // 密码
    @WebInitParam(name = "loginPassword", value = "admin"),
    // 禁用HTML页面上的“Reset All”功能
    @WebInitParam(name = "resetEnable", value = "false")
}
)
public class DruidStatViewServlet extends StatViewServlet{

}
