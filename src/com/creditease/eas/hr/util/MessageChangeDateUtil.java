package com.creditease.eas.hr.util;

import java.util.Date;

import oracle.sql.TIMESTAMP;

import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.StringUtil;

public class MessageChangeDateUtil {
	/***
	 * 描述：获取当前时间的整点时间
	 * 比如现在是3:59 
	 * 取 3点
	 * 2013-2-21 下午03:20:31 by ygq
	 * @version
	 * @return
	 */
	public static Date completeDate(Date date){
		String newDate = DateUtil.formatDateToString(date,"yyyy-MM-dd HH:00:00");
		Date dt = StringUtil.strToDate(newDate,"yyyy-MM-dd HH:00:00");
		return dt;
	}
	/***
	 * 描述：获取当前时间的整点时间
	 * 比如现在是3:59 
	 * 取 3点
	 * 2013-2-21 下午03:20:31 by ygq
	 * @version
	 * @return
	 */
	public static Date completeDateEnd(Date date){
		date.setHours(23);
		String newDate = DateUtil.formatDateToString(date,"yyyy-MM-dd HH:59:59");
		System.out.println("newDate\t" + newDate);
		Date dt = StringUtil.strToDate(newDate,"yyyy-MM-dd HH:mm:ss");
		return dt;
	}
	/**
	 * 描述：获取日志查询的开始的时间
	 * 2013-2-25 上午11:27:28 by ygq
	 * @version
	 * @param maxDate
	 * @return
	 */
	public static Date getBeginDate(Object maxDate){
		Date beginTime = null;
		if(maxDate == null || "".equals(maxDate)){//获取当前时间对应的整点的时间
			//当前时间减少1个小时
			Date date = new Date();
			date.setHours(date.getHours()-1);//
			beginTime = MessageChangeDateUtil.completeDate(date);
		}else{
			String tempDateStr = DateUtil.formatTimestampToString2((TIMESTAMP)maxDate, "yyyy-MM-dd HH:mm:ss");
			Date tempDate  = StringUtil.strToDate(tempDateStr);//这个时间也要获取到整点的时间吗？
			beginTime = completeDate(tempDate);//转换成整点的时间
		}
		return beginTime;
	}
}
