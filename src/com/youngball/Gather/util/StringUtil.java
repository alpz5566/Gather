package com.youngball.Gather.util;

/**
 * String 工具类
 * @author lpz
 *
 */
public class StringUtil {
	
	/**
	 * 字符串拆分成数组
	 */
	public static String[] strArr(String str,String tag){
		if(ValidateUtil.isValid(str)){
			return str.split(tag);
		}
		return null;
	}

	/**
	 * 数组转换成字符串,逗号分割
	 * @param value
	 * @return
	 */
	public static String arr2Str(String[] value) {
		String temp = "" ;
		if(ValidateUtil.isValid(value)){
			for(String s : value){
				temp = temp + s + ",";
			}
			return temp.substring(0,temp.length()-1);
		}
		return null;
	}
}
