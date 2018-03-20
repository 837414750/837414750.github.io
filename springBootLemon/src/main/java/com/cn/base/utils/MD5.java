/**
 * Copyright (c) 2016,http://www.365wuliu.com/  All Rights Reserved.
 */
package com.cn.base.utils;

import java.security.MessageDigest;

/**
 * MD5:md5加解密工具类
 * 
 * @Project Name:cargo
 * @File Name:MD5.java
 * @Package Name:com.cargo.utils
 * @Creator:ZHU CHEN
 * @Date:2016年4月14日下午5:42:43
 */
public class MD5 {
	// 加密
	public static String toMD5(String plainText) {
		if (null == plainText) {
			plainText = "";
		}
		String MD5Str = "";
		try {
			// JDK 6 支持以下6种消息摘要算法，不区分大小写
			// md5,sha(sha-1),md2,sha-256,sha-384,sha-512
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("UTF-8"));
			byte b[] = md.digest();

			int i;

			StringBuilder builder = new StringBuilder(32);
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					builder.append("0");
				builder.append(Integer.toHexString(i));
			}
			MD5Str = builder.toString();
			// LogUtil.println("result: " + buf.toString());// 32位的加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MD5Str;
	}

}
