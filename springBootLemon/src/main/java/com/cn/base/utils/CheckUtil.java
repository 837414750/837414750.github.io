package com.cn.base.utils;

import java.util.List;
import java.util.Set;

public class CheckUtil {
	
	/**
	 * 验证列表是否为空
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static boolean checkObj(List<?> list)throws Exception{
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证列表是否为空
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static boolean checkObj(Set<?> set)throws Exception{
		if(set != null && set.size() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证字符串是否为空
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static boolean checkObj(String s)throws Exception{
		if(s != null && s.trim().length() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static boolean checkObj(Object obj) {
		if(obj != null){
			return true;
		}
		return false;
	}

}
