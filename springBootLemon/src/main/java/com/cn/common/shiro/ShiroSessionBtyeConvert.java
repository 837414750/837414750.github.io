package com.cn.common.shiro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.stereotype.Component;

/**
 * 
 * ShiroSessionBtyeConvert:ShiroSession格式转换类
 * @Project Name:spring-boot-demo 
 * @File Name:ShiroSessionBtyeConvert.java 
 * @Package Name:com.example.myproject.sysconf.shiro
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午12:40:19
 */
@Component
public class ShiroSessionBtyeConvert {

	private Logger logger = Logger.getLogger(ShiroSessionBtyeConvert.class);

	/**
	 * 
	 * sessionToByte:(将ShiroSession转换成Byte数组).
	 * 
	 * @param session
	 * @return
	 * @return :byte[]
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 下午6:48:15
	 */
	public byte[] sessionToByte(Session session) {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] bytes = null;
		try {
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(session);
			bytes = bo.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("ShiroSession To Byte Error", e);
		}
		return bytes;
	}

	/**
	 * 
	 * byteToSession:(将byte数组转换成ShiroSession).
	 * @param bytes
	 * @return 
	 * @return :Session 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 下午6:50:51
	 */
	public Session byteToSession(byte[] bytes) {
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		ObjectInputStream in;
		SimpleSession session = null;
		try {
			in = new ObjectInputStream(bi);
			session = (SimpleSession) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("Byte To ShiroSession ClassNotFoundException", e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Byte To ShiroSession IOException", e);
		}
		return session;
	}

}
