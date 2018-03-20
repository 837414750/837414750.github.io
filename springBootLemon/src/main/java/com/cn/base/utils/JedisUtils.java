package com.cn.base.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * JedisUtils:redis连接JedisPool工具类
 * @Project Name:spring-boot-demo 
 * @File Name:JedisUtils.java 
 * @Package Name:com.example.myproject.utils
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午12:43:05
 */
@Component
public class JedisUtils {

	private Logger logger = Logger.getLogger(JedisUtils.class);

	@Autowired
	private JedisPool jedisPool;

	/**
	 * 
	 * getResource:(获得Jedis资源).
	 * @return
	 * @return :Jedis
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午2:34:13
	 */
	public Jedis getResource() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			logger.error("get Jedis Resource error:",e);
		}
		return jedis;
	}
	
	/**
	 * 
	 * recycleJedis:(回收Jedis资源).
	 * @param jedis 
	 * @return :void 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 下午8:25:22
	 */
	public void recycleJedis(Jedis jedis) {
		try {
			if(jedis != null) {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error("recycle Jedis Resource error:",e);
		}
	}
	
	

	/**
	 * 
	 * set:(Jedis写入缓存).
	 * 
	 * @param key
	 * @param value
	 * @return :void
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:25:38
	 */
	public void set(final String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
			logger.info("Jedis set success - " + key + ",value:" + value);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
	}

	/**
	 * 
	 * set:(Jedis写入缓存-key和value都是以byte数组方式).
	 * 
	 * @param key
	 * @param value
	 * @return :void
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:26:44
	 */
	public void set(final byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
			logger.info("Jedis set success - " + key + ",value:" + value);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
	}

	/**
	 * 
	 * set:(写入缓存,设置过期时间).
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 * @return :void
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:34:58
	 */
	public void set(final String key, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
			jedis.expire(key, seconds);
			logger.info("Jedis set success - " + key + ",value:" + value + ",seconds:" + seconds);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
	}

	/**
	 * 
	 * set:(写入缓存,设置过期时间-key和value都是以byte数组方式).
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 * @return :void
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:36:28
	 */
	public void set(final byte[] key, byte[] value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
			jedis.expire(key, seconds);
			logger.info("Jedis set success for byte array seconds:" + seconds);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
	}

	/**
	 * 
	 * get:(读取缓存).
	 * 
	 * @param key
	 * @return
	 * @return :String
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:54:57
	 */
	public String get(final String key) {
		Jedis jedis = null;
		String result = "";
		try {
			jedis = getResource();
			result = jedis.get(key);
			logger.info("Jedis get success - " + key);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
		return result;
	}

	/**
	 * 
	 * get:(读取缓存,key和返回值都是byte数组).
	 * 
	 * @param key
	 * @return
	 * @return :byte[]
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:55:49
	 */
	public byte[] get(final byte[] key) {
		Jedis jedis = null;
		byte[] result = null;
		try {
			jedis = getResource();
			result = jedis.get(key);
			logger.info("Jedis get success - " + key);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
		return result;
	}

	/**
	 * 
	 * remove:(删除对应的value).
	 * 
	 * @param key
	 * @return :void
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:46:35
	 */
	public void remove(final String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.del(key);
			logger.info("Jedis remove success - " + key);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
	}

	/**
	 * 
	 * remove:(删除对应的value,key为byte数组).
	 * 
	 * @param key
	 * @return :void
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:46:56
	 */
	public void remove(final byte[] key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.del(key);
			logger.info("Jedis remove success - " + key);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
	}

	/**
	 * 
	 * exists:(判断缓存中是否有对应的value).
	 * 
	 * @param key
	 * @return
	 * @return :boolean
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:39:47
	 */
	public boolean exists(final String key) {
		Jedis jedis = null;
		boolean ifExists = false;
		try {
			jedis = getResource();
			ifExists = jedis.exists(key);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
		return ifExists;
	}

	/**
	 * 
	 * exists:(判断缓存中是否有对应的value,key为byte数组).
	 * 
	 * @param key
	 * @return
	 * @return :boolean
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:40:15
	 */
	public boolean exists(final byte[] key) {
		Jedis jedis = null;
		boolean ifExists = false;
		try {
			jedis = getResource();
			ifExists = jedis.exists(key);
		} catch (Exception e) {
			logger.error("Jedis set error:", e);
		} finally {
			recycleJedis(jedis);
		}
		return ifExists;
	}

	/**
	 * 
	 * remove:(批量删除对应value).
	 * 
	 * @param keys
	 * @return :void
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:50:48
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 
	 * remove:(批量删除对应value,key是byte数组).
	 * 
	 * @param keys
	 * @return :void
	 * @Creator:GUO CHUN
	 * @Date:2017年12月9日 上午11:51:42
	 */
	public void remove(final byte[]... keys) {
		for (byte[] key : keys) {
			remove(key);
		}
	}
	
}
