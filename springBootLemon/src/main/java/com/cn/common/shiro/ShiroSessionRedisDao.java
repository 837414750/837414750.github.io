package com.cn.common.shiro;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cn.base.utils.JedisUtils;


/**
 * 
 * ShiroSessionRedisDao:整合了redis的sessionDao
 * @Project Name:spring-boot-demo 
 * @File Name:ShiroSessionRedisDao.java 
 * @Package Name:com.example.myproject.sysconf.shiro
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午12:40:57
 */
@Component
public class ShiroSessionRedisDao extends EnterpriseCacheSessionDAO {
	
	private Logger logger = Logger.getLogger(ShiroSessionRedisDao.class);
	
	@Autowired
	private ShiroSessionBtyeConvert SSBConvert;
	
	@Autowired
	private JedisUtils jedisUtils;
	
	
	/**
	 * 创建session，保存到redis数据库
	 */
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.doCreate(session);
		try {
			jedisUtils.set(sessionId.toString().getBytes(), SSBConvert.sessionToByte(session));
		} catch (Exception e) {
			logger.error("===Shiro创建Session保存到redis错误===>>"+e);
		}
		return sessionId;
	}
	
	/**
	 * 首先判断缓存中有没有session，如果没有则从redis中获取session
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = super.doReadSession(sessionId);
		if(null == session) {
			byte[] bytes = jedisUtils.get(sessionId.toString().getBytes());
			if(bytes != null && bytes.length > 0) {
				session = SSBConvert.byteToSession(bytes);
			}
		}
		return session;
	}
	
	/**
	 * 更新session的最后一次访问时间
	 */
	@Override
	protected void doUpdate(Session session) {
		super.doUpdate(session);
		try {
			jedisUtils.set(session.getId().toString().getBytes(), SSBConvert.sessionToByte(session));
		} catch (Exception e) {
			logger.error("===Shiro更新Session到redis错误===>>"+e);
		}
	}
	
	/**
	 * 删除session
	 */
	@Override
	protected void doDelete(Session session) {
		super.doDelete(session);
		jedisUtils.remove(session.getId().toString().getBytes());
	}

}
