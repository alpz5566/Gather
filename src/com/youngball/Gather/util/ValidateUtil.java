package com.youngball.Gather.util;

import java.util.Collection;

public class ValidateUtil {
	/**
	 * 判断字符串有效性
	 * @param str
	 * @return
	 */
	public static boolean isValid(String str){
		if(str == null || "".equals(str.trim())){
			return false; 
		}
		return true;
	}
	
	public static boolean isvalidate(Collection col){
		if(col == null || col.isEmpty()){
			return false;
		}
		return true;
	}
}
