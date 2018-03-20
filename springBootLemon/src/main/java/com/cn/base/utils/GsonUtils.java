package com.cn.base.utils;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * 
 * GsonUtils:Gson封装工具类
 * @Project Name:spring-boot-demo 
 * @File Name:GsonUtils.java 
 * @Package Name:com.example.myproject.utils
 * @Creator:GUO CHUN
 * @Date:2017年12月12日下午5:10:54
 */
@Component
public class GsonUtils {
	
	private static Gson gson = null;
	
	/**
	 * 初始化gson对象
	 */
	static {
		if(gson == null) {
			gson = new Gson();
		}
	}
	
	public GsonUtils() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * gsonString:(对象转Gson).
	 * @param obj
	 * @return 
	 * @return :String 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月12日 下午4:53:11
	 */
	public String gsonString(Object obj) {
		String gsonString = null;
		if(gson != null) {
			gsonString = gson.toJson(obj);
		}
		return gsonString;
	}
	
	/**
	 * 
	 * gsonToBean:(Gson转对象).
	 * @param gsonString
	 * @param cls
	 * @return 
	 * @return :T 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月12日 下午4:55:27
	 */
	public <T>T gsonToBean(String gsonString,Class<T> cls){
		T t = null;
		if(gson != null) {
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}
	
	/**
	 * 
	 * gsonToList:(Gson转List).
	 * @param gsonString
	 * @param cls
	 * @return 
	 * @return :List<T> 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月12日 下午5:02:25
	 */
	@SuppressWarnings("serial")
	public <T>List<T> gsonToList(String gsonString,Class<T> cls){
		List<T> list = null;
		if(gson != null) {
			list = gson.fromJson(gsonString, new TypeToken<List<T>>() {}.getType());
		}
		return list;
	}
	
	/**
	 * 
	 * gsonToListMaps:(Gson转成List中有Map的).
	 * @param gsonString
	 * @return 
	 * @return :List<Map<String,T>> 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月12日 下午5:07:04
	 */
	@SuppressWarnings("serial")
	public <T>List<Map<String,T>> gsonToListMaps(String gsonString){
		List<Map<String,T>> list = null;
		if(gson != null) {
			list = gson.fromJson(gsonString, new TypeToken<List<Map<String,T>>>() {}.getType());
		}
		return list;
	}
	
	/**
	 * 
	 * GsonToMaps:(Gson转Map).
	 * @param gsonString
	 * @return 
	 * @return :Map<String,T> 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月12日 下午5:10:07
	 */
	@SuppressWarnings("serial")
	public <T>Map<String,T> GsonToMaps(String gsonString){
		Map<String,T> map = null;
		if(gson != null) {
			map = gson.fromJson(gsonString, new TypeToken<Map<String,T>>() {
			}.getType());
		}
		return map;
	}

}
