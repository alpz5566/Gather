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
}
