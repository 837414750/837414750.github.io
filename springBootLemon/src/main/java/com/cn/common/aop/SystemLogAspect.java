/**
 * Copyright (c) 2018,http://www.365wuliu.com/  All Rights Reserved.
 */
package com.cn.common.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.base.utils.GsonUtils;

/** 
 * SystemLogAspect:监控
 * @Project Name:springBootLemon 
 * @File Name:SystemLogAspect.java 
 * @Package Name:com.cn.common.aop
 * @Creator:LU YONG JUN
 * @Date:2018年3月8日下午5:31:05 
 */
@Aspect
@Component
@Order(1)
public class SystemLogAspect {
	private static Logger logger = Logger.getLogger(SystemLogAspect.class);
	
	@Autowired
	private GsonUtils gsonUtils;
	/**
	 * 
	 * serviceAspect:(指定切入点位置). 
	 * @return :void 
	 * @Creator:LU YONG JUN
	 * @Date:2018年3月8日 下午5:36:48
	 */
	@Pointcut("execution(* com.cn..service.*(..))")
	public void serviceAspect() {
	}
	
	@Before("serviceAspect()")
	public void doBefore(JoinPoint joinPoint) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		
		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}
	
	 @AfterReturning(returning = "ret", pointcut = "serviceAspect()")  
	 public void doAfterReturning(Object ret){  
		// 处理完请求，返回内容
//		logger.info("RESPONSE : " + ret);
//		logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
	 } 
	
	 @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	 public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			// 读取session中的用户
			// 获取请求ip
			String ip = request.getRemoteAddr();
			// 获取用户请求方法的参数并序列化为JSON格式字符串
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					params += gsonUtils.gsonString(joinPoint.getArgs()[i]) + ";";
				}
			}
			try {
				/* ========控制台输出========= */
				logger.error("=====异常通知开始=====");
				StringBuffer str = new StringBuffer();
				str.append("\r\n");
				str.append("请求IP:" + ip);
				str.append("\r\n");
				str.append("请求路径:" + request.getRequestURL());
				str.append("\r\n");
				str.append("异常的service方法:"
						+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
				str.append("\r\n");
				str.append("异常service方法的参数:" + params);
				str.append("\r\n");
				str.append("异常信息:" + e.toString());

				logger.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-" + str);
				logger.error("异常详细信息", e);
				logger.error("=====异常通知结束=====");
				/*if (PropertiesUtil.getValue("debug").equals("true") || e instanceof CommonException) {

				} else {
				MailSender.send(PropertiesUtil.getValue("logfrom") + "services主服务捕获到异常了",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "<br/>"
								+ str.toString().replaceAll("\r\n", "<br/>"));
				}	*/		
			} catch (Exception ex) {
				// 记录本地异常日志
				logger.error("==异常通知异常==");
				logger.error("异常信息:{}" + ex.getMessage());
			}
		}
}
