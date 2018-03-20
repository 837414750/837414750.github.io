/**
 * Copyright (c) 2016,http://www.365wuliu.com/  All Rights Reserved.
 */
package com.cn.base.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

/** 
 * BeanUtil:一句话描述该类的作用
 * @Project Name:fob_services 
 * @File Name:BeanUtil.java 
 * @Package Name:com.cargo.sysconfig.utils
 * @Creator:DONG CHEN ZHI
 * @Date:2016年5月9日下午5:20:54 
 */
public class BeanUtil {
	/**
	 * 
	 * copyObj:对象复制，objA和objB字段名称需要和类型需要一致
	 * @param objA
	 * @param objB
	 * @throws Exception 
	 * @return :void 
	 * @Creator:DONG CHEN ZHI
	 * @Date:2016年5月9日 下午5:21:21
	 */
	public static void copyObj(Object objA,Object objB)throws Exception{
		//获得对象A的字段列表
		List<String> filListA = reBuildFil(objA);
		
		//获得对象B的字段列表
		List<String> filListB = reBuildFil(objB);
		
		for (int i = 0; i < filListA.size(); i++) {
			String filNameA = filListA.get(i);
			Object value = PropertyUtils.getProperty(objA, filNameA);
			if(CheckUtil.checkObj(value)){
				for (int j = 0; j < filListB.size(); j++) {
					String filNameB = filListB.get(j);
					if(filNameA.equals(filNameB)){
						if(value != null){
							PropertyUtils.setProperty(objB, filNameB, value);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * copyObjCanNull:(复制对象允许有空值).
	 * @param objA
	 * @param objB
	 * @throws Exception 
	 * @return :void 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月16日 下午1:07:47
	 */
	public static void copyObjCanNull(Object objA,Object objB)throws Exception{
		//获得对象A的字段列表
		List<String> filListA = reBuildFil(objA);
		
		//获得对象B的字段列表
		List<String> filListB = reBuildFil(objB);
		
		for (int i = 0; i < filListA.size(); i++) {
			String filNameA = filListA.get(i);
			Object value = PropertyUtils.getProperty(objA, filNameA);
			for (int j = 0; j < filListB.size(); j++) {
				String filNameB = filListB.get(j);
				if(filNameA.equals(filNameB)){
					PropertyUtils.setProperty(objB, filNameB, value);
				}
			}
		}
	}
	
	/**
	 * 
	 * copyObj:(对象复制，objA和objB字段名称需要和类型需要一致，如果有需要跳过的字段传入passFilList列表).
	 * @param objA
	 * @param objB
	 * @param passFilList
	 * @throws Exception 
	 * @return :void 
	 * @Creator:GUO CHUN
	 * @Date:2016年5月30日 下午2:08:01
	 */
	public static void copyObj(Object objA,Object objB,List<String> passFilList)throws Exception{
		//获得对象A的字段列表
		List<String> filListA = reBuildFil(objA);
		
		//获得对象B的字段列表
		List<String> filListB = reBuildFil(objB);
		
		for (int i = 0; i < filListA.size(); i++) {
			String filNameA = filListA.get(i);
			//如果passFilList(自定义跳过赋值列表)不为空且objA的字段名称在该列表中则跳过赋值
			if(CheckUtil.checkObj(passFilList)){
				if(passFilList.contains(filNameA)){
					continue;
				}
			}
			Object value = PropertyUtils.getProperty(objA, filNameA);
			if(CheckUtil.checkObj(value)){
				for (int j = 0; j < filListB.size(); j++) {
					String filNameB = filListB.get(j);
					if(filNameA.equals(filNameB)){
						if(value != null){
							PropertyUtils.setProperty(objB, filNameB, value);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * copyObjCanNull:(复制对象允许空值，包含passFilList).
	 * @param objA
	 * @param objB
	 * @param passFilList
	 * @throws Exception 
	 * @return :void 
	 * @Creator:GUO CHUN
	 * @Date:2017年12月16日 下午1:08:19
	 */
	public static void copyObjCanNull(Object objA,Object objB,List<String> passFilList)throws Exception{
		//获得对象A的字段列表
		List<String> filListA = reBuildFil(objA);
		
		//获得对象B的字段列表
		List<String> filListB = reBuildFil(objB);
		
		for (int i = 0; i < filListA.size(); i++) {
			String filNameA = filListA.get(i);
			if(CheckUtil.checkObj(passFilList)){
				if(passFilList.contains(filNameA)){
					continue;
				}
			}
			Object value = PropertyUtils.getProperty(objA, filNameA);
			for (int j = 0; j < filListB.size(); j++) {
				String filNameB = filListB.get(j);
				if(filNameA.equals(filNameB)){
					PropertyUtils.setProperty(objB, filNameB, value);
				}
			}
		}
	}
	
	/**
	 * 获得对象的字段列表
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private static List<String> reBuildFil(Object obj)throws Exception{
		List<String> filList = new ArrayList<String>();
		//获得类
		Class<?> cls = obj.getClass();
		//获得类中的所有字段
		Field[] fil = cls.getDeclaredFields();
		//遍历类中的所有字段加入到字段List中
		for (int i = 0; i < fil.length; i++) {
			filList.add(fil[i].getName());
		}
		
		//获得被继承的父类
		Class<?> supCls = cls.getSuperclass();
		//获得被继承的父类中的所有字段
		Field[] supFil = supCls.getDeclaredFields();
		//遍历被继承的父类中的所有字段加入到字段List中
		for (int i = 0; i < supFil.length; i++) {
			filList.add(supFil[i].getName());
		}
		return filList;
	}
	
}
