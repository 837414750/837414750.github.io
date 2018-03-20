package com.cn.common.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * JedisConfig:redis连接池JedisPool配置类
 * @Project Name:spring-boot-demo 
 * @File Name:JedisConfig.java 
 * @Package Name:com.example.myproject.sysconf.redis
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午12:35:26
 */
@Configuration
@EnableAutoConfiguration
public class JedisConfig {
	
	private Logger logger = Logger.getLogger(JedisConfig.class);

	@Autowired
	private RedisConn redisConn;
	
	@Bean
	public JedisPoolConfig getJedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}
	
	@Bean
	public JedisPool getJedisPool() {
		JedisPoolConfig congfig = getJedisConfig();
		JedisPool pool = new JedisPool(congfig,redisConn.getHost(),redisConn.getPort(),redisConn.getTimeout());
		logger.info("init JedisPool……！");
		return pool;
	}
}
