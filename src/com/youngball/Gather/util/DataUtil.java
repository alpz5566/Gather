package com.youngball.Gather.util;

import java.security.MessageDigest;

/**
 * 数据工具类
 * @author lpz
 *
 */
public class DataUtil {
	/**
	 * 采用MD5加密
	 * @param src
	 * @return
	 */
	public static String md5(String src){
		try {
			StringBuffer buffer = new StringBuffer();
			char[] chars= {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] data = md.digest(src.getBytes());
			for(byte b : data){
				//前四位
				buffer.append(chars[(b >> 4) & 0x0F]);
				//后四位
				buffer.append(chars[b & 0x0F]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
}
