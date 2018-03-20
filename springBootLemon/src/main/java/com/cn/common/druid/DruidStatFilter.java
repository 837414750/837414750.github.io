/**
 * Copyright (c) 2018,http://www.365wuliu.com/  All Rights Reserved.
 */
package com.cn.common.druid;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties.WebStatFilter;

/** 
 * DruidStatFilter:druid过滤器.
 * @Project Name:springBootLemon 
 * @File Name:DruidStatFilter.java 
 * @Package Name:com.cn.common.druid
 * @Creator:LU YONG JUN
 * @Date:2018年3月8日下午5:26:30 
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
 initParams = {
     // 忽略资源
     @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
 }
)
public class DruidStatFilter extends WebStatFilter{

}
