package com.creditease.eas.institutional.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.sql.TIMESTAMP;

public class StringUtil {
	/**
	 * 将时间转化为指定格式的字符串
	 * @param date 传入时间
	 * @param format 传入格式
	 * @return 格式化后的字符串
	 * @author lining
	 * @since 2014-01-21
	 */
	public static String dateToStr(Date date,String format){
		String str = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(date==null){
			return null;
		}
		str = sdf.format(date);
		return str;
	}
	/**
	 * 描述：将Timestamp转换成字符串
	 * 2014-2-27 by lining
	 * @param timestamp
	 * @param Format
	 * @return
	 */
	public static String timestampToStr(TIMESTAMP  timestamp,String Format){
		 	if(timestamp == null){
		 		return "";
		 	}
		 	try{
			   Timestamp tt = timestamp.timestampValue();
			   Date date = new Date(tt.getTime());
			   SimpleDateFormat sd = new SimpleDateFormat(Format);
			   return sd.format(date);
		 	}catch(Exception e){
		 		e.printStackTrace();
		 		return "";
		 	}
	}
}
