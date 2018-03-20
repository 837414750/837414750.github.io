package com.cn.common.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * RedisConn:redis连接信息配置类
 * @Project Name:spring-boot-demo 
 * @File Name:RedisConn.java 
 * @Package Name:com.example.myproject.sysconf.redis
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午12:36:54
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConn {

	private String host;

	private int port;

	private int timeout;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return "RedisConn [host=" + host + ", port=" + port + ", timeout=" + timeout + "]";
	}

}
