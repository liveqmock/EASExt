package com.creditease.eas.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.sql.CLOB;
import oracle.sql.TIMESTAMP;

/**
 * 字符串工具类
 */
public class Utils {
	/**
	 * 汉字转换
	 * 
	 * @param value
	 *            String
	 * @return String
	 */
	public static String toGBK(String value) {
		try {
			if (value != null) {
				return new String(value.getBytes("iso-8859-1"), "GBK");
			} else {
				return null;
			}
		} catch (Exception e) {
			return value;
		}
	}

	public static String toISO(String value) {
		try {
			if (value != null) {
				return new String(value.getBytes("GBK"), "iso-8859-1");
			} else {
				return "";
			}
		} catch (Exception e) {
			return value;
		}
	}

	/**
	 * 在最新记录后显示new图标
	 * 
	 * @param time
	 * @param newImgPath
	 * @param interval
	 * @return
	 */
	public static String showNewImg(String time, String newImgPath, int interval) {
		String imgPath = "";
		String curTime = getNowDate();
		if (time == null || time.length() < 9)
			return imgPath;
		if (curTime.contains(time.subSequence(0, 7))) {
			String date = time.substring(time.lastIndexOf("-") + 1, time
					.lastIndexOf("-") + 3);
			String curDate = curTime.substring(curTime.lastIndexOf("-") + 1);
			int date1 = Integer.parseInt(date);
			int date2 = Integer.parseInt(curDate);
			if (date2 - date1 < interval) {
				imgPath = "<img src = \"" + newImgPath + "\" />";
			}
		}
		return imgPath;
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static String getStr(Object str, int beginIndex, int endIndex) {
		if (str == null || str.toString().trim().toLowerCase().equals("null")
				|| str.toString().trim().equals(""))
			return "";
		if (beginIndex < 0 || beginIndex > str.toString().length())
			return str.toString();
		if (str.toString().length() < endIndex)
			return str.toString().substring(beginIndex);
		return str.toString().substring(beginIndex, endIndex);
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getStr(Object str, int len) {
		if (str == null || str.toString().trim().toLowerCase().equals("null")
				|| str.toString().trim().equals(""))
			return "";
		if (len < 0 || len > str.toString().length())
			return str.toString();

		return str.toString().substring(0, len) + "...";

	}

	/**
	 * 取得当前年份字符串
	 * 
	 * @return
	 */
	public static String getNowYear() {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy");
		return clsFormat.format(new java.util.Date());
	}

	/**
	 * 取得当前月份字符串
	 * 
	 * @return
	 */
	public static String getNowMonth() {
		SimpleDateFormat clsFormat = new SimpleDateFormat("MM");
		return clsFormat.format(new java.util.Date());
	}

	/**
	 * 取得当前日字符串
	 * 
	 * @return
	 */
	public static String getNowDay() {
		SimpleDateFormat clsFormat = new SimpleDateFormat("dd");
		return clsFormat.format(new java.util.Date());
	}

	/**
	 * 取得当前日期字符串
	 * 
	 * @return
	 */
	public static String getNowDate() {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd");
		return clsFormat.format(new java.util.Date());
	}

	/**
	 * 取得当前时间字符串
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return clsFormat.format(new java.util.Date());
	}
	
	public static String getNowcurrTime() {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyyMMddHHmm");
		return clsFormat.format(new java.util.Date());
	}

	/**
	 * 转换时间为日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd");
		return clsFormat.format(date);
	}

	/**
	 * 转换时间为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortTime(Date date) {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return clsFormat.format(date);
	}

	public static String getTime(Date date) {
		if(date!=null){
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return clsFormat.format(date);
		}else{
			return "";
		}
	}

	/**
	 * 取得当日的短日期
	 * 
	 * @return
	 */
	public static Date getDate() {
		Date CurrTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strMyDate = formatter.format(CurrTime);
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = new Date();
		try {
			date1 = clsFormat.parse(strMyDate);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return date1;
	}
	public static Date getLongDate() {
		Date CurrTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strMyDate = formatter.format(CurrTime);
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = new Date();
		try {
			date1 = clsFormat.parse(strMyDate);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return date1;
	}
	/**
	 * 转换为日期
	 * 
	 * @param obj
	 * @return
	 */
	public static Date toDate(Object obj) {
		if (obj == null || isNull(obj.toString()))
			return null;
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = new Date();
		try {
			date1 = clsFormat.parse(obj.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}
	
	public static String toDate2(Object obj) {
		if (obj == null || isNull(obj.toString()))
			return null;
		 DateFormat sdf = new SimpleDateFormat(
	                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 Date date;
		 String dfstr = null;
		try {
			date = sdf.parse(obj.toString());
			dfstr = df.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return dfstr; 
	    }
	
	
	
	public static Date toDateymdhms(Object obj) {
		if (obj == null || isNull(obj.toString()))
			return null;
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = new Date();
		try {
			date1 = clsFormat.parse(obj.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}
	/**
	 * 转换为时间
	 * 
	 * @param obj
	 * @return
	 */
	public static Date toTime(Object obj) {
		if (obj == null || isNull(obj.toString()))
			return null;
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = new Date();
		try {
			date1 = clsFormat.parse(obj.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}

	/**
	 * 截取字符串到整形数组
	 * 
	 * @param vals
	 * @return
	 */
	public static Integer[] getIntArray(String vals) {
		if (strTrim(vals).equals(""))
			return null;
		String[] audits = vals.split(",");
		Integer[] intAudits = new Integer[audits.length];
		for (int i = 0; i < audits.length; i++) {
			intAudits[i] = Utils.getInt(audits[i]);
		}
		return intAudits;
	}

	/**
	 * 转化字符串数组到整形数组
	 */
	public static Integer[] getIntArray(String[] vals) {
		if (vals == null)
			return null;
		Integer[] intAudits = new Integer[vals.length];
		for (int i = 0; i < vals.length; i++) {
			intAudits[i] = Utils.getInt(vals[i]);
		}
		return intAudits;
	}

	/**
	 * 将数组转换成字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrayToStr(String[] arr) {
		if (arr == null || arr.length < 1)
			return null;
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i != 0)
				temp.append(",");
			temp.append(arr[i]);
		}
		return temp.toString();
	}

	/**
	 * 将数组转换成字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrayToStr(Object[] arr) {

		if (arr == null || arr.length < 1)
			return null;
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i != 0)
				temp.append(",");
			temp.append(arr[i]);
		}
		return temp.toString();
	}

	/**
	 * 将集合转换成用逗号分割的字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String arrayToStr(ArrayList list) {

		if (list == null || list.size() < 1)
			return null;
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0)
				temp.append(",");
			temp.append(list.get(i));
		}
		return temp.toString();
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param strValue
	 * @return
	 */
	public static boolean isNull(String strValue) {

		if ((strValue == null)
				|| (strValue.trim().equalsIgnoreCase("null") || (strValue
						.trim().equals("")))) {
			return true;
		}

		return false;
	}

	/**
	 * 比较字符串是否相同
	 * 
	 * @param strData
	 * @param strData2
	 * @return
	 */
	public static boolean isEqual(String strData, String strData2) {
		if (strData == null)
			return false;
		if (!strData.equals(strData2)) {
			return false;
		}
		return true;
	}

	/**
	 * 整数转化为字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String intToStr(int value) {
		Integer integer = new Integer(value);
		return integer.toString();
	}

	/**
	 * 把src对象复制到tar对象，但只复制src对象中不为空的属性值
	 * 
	 * @param tar
	 * @param src
	 * @author lenglinyong
	 */
	public static void copy(Object tar, Object src) {
		Method[] srcMs = src.getClass().getMethods();
		Method[] tarMs = tar.getClass().getMethods();
		try {
			for (int i = 0; i < srcMs.length; i++) {
				String mName = srcMs[i].getName();
				Class reType = srcMs[i].getReturnType();
				if (mName.contains("get")) { // 得到三轮车的get方法
					Object val = srcMs[i].invoke(src, null);
					for (int j = 0; j < tarMs.length; j++) {
						String tmName = tarMs[j].getName();

						if (tmName.contains("set")
								&& tmName.contains(mName.substring(3))
								&& val != null) {
							String ptype = tarMs[j].getParameterTypes()[0]
									.getName();
							if (ptype.contains("Integer"))
								tarMs[j].invoke(tar,
										new Object[] { new Integer(val
												.toString()) });
							if (ptype.contains("Long"))
								tarMs[j].invoke(tar, new Object[] { new Long(
										val.toString()) });
							if (ptype.contains("int"))
								tarMs[j].invoke(tar, new Object[] { val });
							if (ptype.contains("String"))
								tarMs[j].invoke(tar, new Object[] { val
										.toString() });
							if (ptype.contains("Float"))
								tarMs[j].invoke(tar, new Object[] { new Float(
										val.toString()) });
							if (ptype.contains("Double"))
								tarMs[j].invoke(tar, new Object[] { new Double(
										val.toString()) });
							if(ptype.contains("Date")){
								String s = Utils.getDate((Date)val);
								tarMs[j].invoke(tar,Utils.toDate(s.toString()));
							}
								
								
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把空字符串转换为字符""
	 * 
	 * @author lenglinyong
	 */
	public static String strTrim(Object str) {
		if (str == null || str.toString().trim().toLowerCase().equals("null")
				|| str.toString().trim().equals(""))
			return "";
		return str.toString().trim();
	}

	/**
	 * 把数字字符串转换成Integer
	 * 
	 * @author
	 */
	public static Integer getInt(Object obj) {
		if (obj == null || obj.toString().trim().toLowerCase().equals("null")
				|| obj.toString().trim().equals(""))
			return new Integer(0);
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(obj.toString().trim());
		if (isNum.matches()) {
			return new Integer(obj.toString());
		} else {
			return new Integer(0);
		}

	}

	/**
	 * 把对象转换成Long
	 * 
	 * @author
	 */
	public static Long getLong(Object obj) {
		if (obj == null || obj.toString().trim().toLowerCase().equals("null")
				|| obj.toString().trim().equals(""))
			return new Long(0l);
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(obj.toString().trim());
		if (isNum.matches()) {
			return new Long(obj.toString());
		} else {
			return new Long(0l);
		}

	}

	/**
	 * 把数字字符串转换成Float
	 * 
	 * @author lenglinyong
	 */
	public static Float getFloat(Object obj) {
		if (obj == null || obj.toString().trim().toLowerCase().equals("null")
				|| obj.toString().trim().equals(""))
			return new Float(0.0f);
		if (Pattern.compile("^\\-?\\d+\\.?\\d*$")
				.matcher(obj.toString().trim()).find()) {
			return new Float(obj.toString());
		} else {
			return new Float(0.0f);
		}

	}

	/**
	 * 把数字字符串转换成Double
	 * 
	 * @author lenglinyong
	 */
	public static Double getDouble(Object obj) {
		if (obj == null || obj.toString().trim().toLowerCase().equals("null")
				|| obj.toString().trim().equals(""))
			return new Double(0.0);
		obj = obj.toString().replace("%", "");
		return new Double(obj.toString());
	}

	/**
	 * 四舍五入
	 */
	public static String getDouble(Object obj, int len) {
		String fomat = "#.0";
		if (len == 0)
			fomat = "#";
		if (len == 1)
			fomat = "#.0";
		if (len == 2)
			fomat = "#.00";
		if (len == 3 || len > 3)
			fomat = "#.0000";
		DecimalFormat df = new DecimalFormat(fomat);
		if (getDouble(obj).doubleValue() == 0) {
			return "0.00";
		}
		return df.format(obj);
	}

	/**
	 * 四舍五入
	 */
	public static String getDoubleto(Object obj) {
		String fomat = "0.0000";;
		
		DecimalFormat df = new DecimalFormat(fomat);
		if (getDouble(obj).doubleValue() == 0) {
			return "0.0000";
		}
		return df.format(obj);

	}
	
	/**
	 * 四舍五入
	 */
	public static String getFloat(Object obj, int len) {
		String fomat = "#.0";
		if (len == 0)
			fomat = "#";
		if (len == 1)
			fomat = "#.0";
		if (len == 2)
			fomat = "#.00";
		if (len == 3 || len > 3)
			fomat = "#.0000";
		DecimalFormat df = new DecimalFormat(fomat);
		if (getFloat(obj).floatValue() == 0) {
			return "0.00";
		}
		return df.format(obj);
	}

	/**
	 * 小数位取整
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer fToInt(Object obj) {
		if (obj == null || obj.toString().trim().toLowerCase().equals("null")
				|| obj.toString().trim().equals(""))
			return new Integer(0);
		return new Integer(obj.toString().substring(0,
				obj.toString().indexOf(".")));
	}
	/**
	 * 
	 * 描述：保留小数
	 * 2014-4-1 下午05:31:23 by sunxiaofeng
	 * @version
	 * @param len保留小数个数
	 * @param strDouble
	 * @return
	 */
	public static String getBigDecimal(Integer len,Double strDouble){
		  BigDecimal  bd  =  new  BigDecimal(strDouble);  
	      bd  =  bd.setScale(len,BigDecimal.ROUND_HALF_UP);
		  return  bd.toString();
	  }
	/**
	 * 把空字符串转换为字符""
	 * 
	 * @param strData
	 * @return
	 */
	public static String replaceNull(String strData) {
		if (strData == null || strData.equals("null")) {
			return "";
		}
		return strData.trim();
	}

	/**
	 * 过滤HTML标签截取
	 * 
	 * @param input
	 * @param len
	 * @return
	 */
	public static String htmlFilter(String input, int len) {

		input = input.replaceAll("<[^>]*>", "");
		return input.length() > len ? input.substring(0, len) + "..." : input;
	}

	/**
	 * 过滤HTML标签
	 * 
	 * @param input
	 * @param len
	 * @return
	 */
	public static String delHtml(String str) {
		String regEx = "<[^>]*>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 * @param i
	 * @return
	 */
	public static String subS(String str, int i) {
		String subStr = "";
		if (str.length() > i)
			subStr = str.substring(0, i) + "...";
		else
			subStr = str;
		return subStr;
	}

	/**
	 * 截取字符串,带title
	 * 
	 * @param str
	 * @param i
	 * @return
	 */
	public static String subS2T(String str, int i) {
		String subStr = "";
		if (str.length() > i) {
			subStr = str.substring(0, i) + "...";
			subStr = "<span title='" + str + "'>" + subStr + "</span>";
		} else
			subStr = str;
		return subStr;
	}

	/**
	 * 字符串是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	// public static Object parseObj(Object goal,Object src){
	// if(src == null || goal == null) return null;
	// Utils.copy(goal, src);
	// return goal;
	// }

	public static String getCurrentDate() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(date);
	}

	public static String getCurrentTime() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return simple.format(date);
	}

	public static String getTimeStr() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(date);
	}

	/**
	 * 取得当前时间
	 * 
	 * @return
	 */
	public static Date getTime() {
		Date CurrTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strMyDate = formatter.format(CurrTime);
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = new Date();
		try {
			date1 = clsFormat.parse(strMyDate);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return date1;
	}

	/**
	 * 取得当前时间
	 * 
	 * @return
	 */
	// 时间格式 2005-4-21 16:16:34
	public static String getCurrHHMMSS(Date date) {
		String hhmmss = new String();
		try {
			String datastr = date.toString();
			if (datastr.indexOf(":") != -1 && datastr.lastIndexOf(":") != -1)
				hhmmss = datastr.substring(datastr.indexOf(":") - 2, datastr
						.lastIndexOf(":") + 3);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return hhmmss;
	}

	public static Date getTimeByStr(String strMyDate) {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = new Date();
		try {
			date1 = clsFormat.parse(strMyDate);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return date1;
	}

	// 判断时间date1是否在时间date2之前
	// 时间格式 2005-4-21 16:16:34
	public static boolean isDateBefore(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	// 判断当前时间是否在时间date2之前
	// 时间格式 2005-4-21 16:16:34
	public static boolean isDateBefore(Date date2) {
		try {
			Date date1 = new Date();
			return date1.before(date2);
		} catch (Exception e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	// 判断当前时间是否在时间date2之后
	// 时间格式 2005-4-21 16:16:34
	public static boolean isDateAfter(Date date2) {
		try {
			Date date1 = new Date();
			return date1.after(date2);
		} catch (Exception e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	/**
	 * 设置为几天后的时间
	 * 
	 * @param day
	 *            多少天
	 * @return
	 */
	public static Date getDateAfter(int day) {
		Date date1 = new Date();
		try {
			Calendar calendar = Calendar.getInstance();
			date1 = calendar.getTime();
			int day1 = calendar.get(Calendar.DAY_OF_YEAR);
			calendar.set(Calendar.DAY_OF_YEAR, day1 + day);
			date1 = calendar.getTime();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return date1;
	}

	/**
	 * 设置为几天前的时间
	 * 
	 * @param day
	 *            多少天
	 * @return
	 */
	public static Date getDateBefore(int day) {
		Date date1 = new Date();
		try {
			Calendar calendar = Calendar.getInstance();
			date1 = calendar.getTime();
			int day1 = calendar.get(Calendar.DAY_OF_YEAR);
			calendar.set(Calendar.DAY_OF_YEAR, day1 - day);
			date1 = calendar.getTime();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return date1;
	}

	// 两日期相差天数
	public static int diffdates(Date date1, Date date2) {
		int result = 0;
		Calendar gc1 = Calendar.getInstance();
		Calendar gc2 = Calendar.getInstance();

		gc1.setTime(date1);
		gc2.setTime(date2);
		result = getDays(gc1, gc2);

		return result;
	}

	public static int getDays(Calendar g1, Calendar g2) {
		int elapsed = 0;
		Calendar gc1, gc2;

		if (g2.after(g1)) {
			gc2 = (Calendar) g2.clone();
			gc1 = (Calendar) g1.clone();
		} else {
			gc2 = (Calendar) g1.clone();
			gc1 = (Calendar) g2.clone();
		}

		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);

		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);

		while (gc1.before(gc2)) {
			gc1.add(Calendar.DATE, 1);
			elapsed++;
		}
		return elapsed;
	}

	/**
	 * 截取字符串长度为两行
	 * 
	 * @param str
	 *            要截取的字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String subStr(String str, int length) {
		String newstr = "";
		int tmplength = 0;
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 0 && c <= 255)
				j = 1;
			else
				j = 2;
			tmplength += j;
			if (tmplength <= length) {
				newstr += c;
				if (tmplength == length / 2) {
					newstr += "<br>";
				}
			}
		}

		return newstr;
	}

	/**
	 * 截取字符串长度为一行
	 * 
	 * @param str
	 *            要截取的字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String subRowsStr(String str, int length) {
		String newstr = "";
		int tmplength = 0;
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 0 && c <= 255)
				j = 1;
			else
				j = 2;
			tmplength += j;
			if (tmplength <= length) {
				newstr += c;
			}
		}

		return newstr;
	}

	/**
	 * 自动生成编号
	 * 
	 * @param preno
	 *            前缀
	 * @param aftno
	 *            后缀编号增长数
	 * @param num
	 *            后缀长度
	 * @return
	 */
	public static String getStrno(String preno, String aftno, int num) {
		if (aftno.length() == 0) {
			for (int i = 0; i < num; i++) {
				aftno += "0";
			}
		}
		long laftno = Long.parseLong(aftno) + 1;

		String saftno = String.valueOf(laftno);
		String tmpno = saftno;
		if (saftno.length() < num) {
			for (int i = 0; i < num - saftno.length(); i++) {
				tmpno = "0" + tmpno;
			}
		}

		return preno + tmpno;
	}

	/**
	 * 
	 * @param olddate
	 *            日期
	 * @param day
	 *            相对天数，为正数表示之后，为负数表示之前
	 * @return 指定日期字符串n天之前或者之后的日期    (某某日期之后)
	 */
	public static Date getBeforeAfterDate(Date olddate, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH);
		int Day = cal.get(Calendar.DAY_OF_MONTH);

		int NewDay = Day + day;

		cal.set(Calendar.YEAR, Year);
		cal.set(Calendar.MONTH, Month);
		cal.set(Calendar.DAY_OF_MONTH, NewDay);

		return new java.sql.Date(cal.getTimeInMillis());
	}
	/**
	 * 
	 * 描述：某某日期之前
	 * 2013-7-29 下午05:15:29 by Administrator
	 * @version
	 * @param olddate
	 * @param day
	 * @return
	 */
	public static Date getBeforeDate(Date olddate, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH);
		int Day = cal.get(Calendar.DAY_OF_MONTH);

		int NewDay = Day - day;

		cal.set(Calendar.YEAR, Year);
		cal.set(Calendar.MONTH, Month);
		cal.set(Calendar.DAY_OF_MONTH, NewDay);

		return new java.sql.Date(cal.getTimeInMillis());
	}

	/**
	 * 指定日期字符串之前或者之后的日期
	 * 
	 * @param olddate
	 *            原始日期
	 * @param year
	 *            偏移量 年
	 * @param month
	 *            偏移量 月
	 * @param day
	 *            偏移量 日
	 * @return 新日期
	 */
	public static Date getBeforeAfterDate(Date olddate, int year, int month,
			int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH);
		int Day = cal.get(Calendar.DAY_OF_MONTH);

		int NewYear = Year + year;
		int NewMonth = Month + month;
		int NewDay = Day + day;

		cal.set(Calendar.YEAR, NewYear);
		cal.set(Calendar.MONTH, NewMonth);
		cal.set(Calendar.DAY_OF_MONTH, NewDay);

		return new java.sql.Date(cal.getTimeInMillis());
	}

	/**
	 * 根据年月得到月天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaynumByYearMonth(int year, int month) {
		int daynum = 0;

		int[] arr = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			arr[1] = 29;
		}
		daynum = arr[month - 1];

		return daynum;
	}

	/**
	 * 文件的上传
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), 1024);
				out = new BufferedOutputStream(new FileOutputStream(dst), 1024);
				byte[] buffer = new byte[1024];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getDiffHour(Date begin, Date end) {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		long hour1 = between / 3600;
		return hour1;

	}

	/**
	 * 去掉字符串中重复的值，如字符串“1,2,3,4,2,1,5”，转化为“1,2,3,4,5”
	 * 
	 * @param str
	 *            要处理的字符串 格式如“1,2,3,4,2,1,5”
	 * @return
	 */
	public static String delRepetStr(String str) {
		String result =",";
		String[] s = str.split(",");
		if(s.length>0){
			for(int i=0; i<s.length; i++){
			     if(result.indexOf(","+s[i]+",") == -1 ) result = result + s[i] + ",";
			}
			result = result.substring(1,result.length()-1);
		}
		return result;
	}

	public static void main(String[] args) {
		
	//	System.out.println("--------" + Utils.getCnTime());
		//System.out.println(toDate2("Mon Oct 21 20:27:28 CST 2013"));
			//System.out.println(getFloat(0.3333333333,2));
			  BigDecimal  bd  =  new  BigDecimal("0.33533333");  
			      bd  =  bd.setScale(2,BigDecimal.ROUND_HALF_UP);
			      System.out.println(bd);


	}

	/**
	 * 获得字符串截止到非数字的数字字符串，如“1245h78” 返回“1245”
	 * 
	 * @param str
	 * @return
	 */
	public static String getNumberEndNoChar(String str) {
		String tmp = "";
		if (str.length() > 0) {
			for (int i = 0; i < str.length(); i++) {
				int k = str.charAt(i);
				if (k <= 57 && k >= 48) {
					tmp += str.charAt(i);
				} else {

				}
			}
		}
		return tmp;
	}

	/**
	 * 去掉字符串中的空格
	 * 
	 * @param str
	 * @return String
	 */
	public static String toTrim(String str) {
		StringBuilder sb = new StringBuilder();
		char c = ' ';
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != c) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	/**
	 * 取得当前时间,格式（yyyy年MM月dd日HH时mm分）
	 * 
	 * @return
	 */
	public static String getCnTime() {
		Date CurrTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		String strMyDate = formatter.format(CurrTime);
		return strMyDate;
	}
	
	public static String getCnTimeStr(String strdate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
		Date str = toDate(strdate);
		String strMyDate = formatter.format(str);
		return strMyDate;
	}
	
	public static String getCnTimeFormat(Date dates) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
		String strMyDate = formatter.format(dates);
		return strMyDate;
	}

	/**
	 * 将字符串添加字符，达到需要的个数 ，如 “2,3,4,5”需要存在5个“,” --> "2,3,4,5,,"
	 * 
	 * @param str
	 *            字符串
	 * @param c
	 *            添加的字符
	 * @param charnum
	 *            字符串存在字符数
	 * @return
	 */
	public static String changeStrChar(String str, String c, int charnum) {
		int num = (str.split(c)).length;
		if (num >= charnum)
			return str;
		else {
			for (int i = 0; i < charnum - num; i++) {
				str += c;
			}
		}
		return str;
	}

	/**
	 * 随机生产指定长度的数字字符串
	 * 
	 * @param len
	 *            长度
	 * @return 数字字符串
	 */
	public static String randomStr(int len) {
		String str = "";
		for (int i = 0; i < len; i++) {
			str += (int) (Math.random() * 10);
		}
		return str;
	}

	/**
	 * 根据身份证取出生日期
	 * 
	 * @param cardNumber
	 * @return
	 */
	public static String getBirthStrByCard(String cardNumber) {
		String card = cardNumber.trim();
		String year = "";
		String month = "";
		String day = "";
		if (card.length() == 18) {
			year = card.substring(6, 10);
			month = card.substring(10, 12);
			day = card.substring(12, 14);
		} else if (card.length() == 15) {
			year = card.substring(6, 8);
			month = card.substring(8, 10);
			day = card.substring(10, 12);
			year = "19" + year;
		}
		if (month.length() == 1)
			month = "0" + month;
		if (day.length() == 1)
			day = "0" + day;
		return year + "-" + month + "-" + day;
	}

	/**
	 * 根据身份证取出生日期
	 * 
	 * @param cardNumber
	 * @return
	 */
	public static Date getBirthDateByCard(String cardNumber) {
		return Utils.toDate(getBirthStrByCard(cardNumber));
	}

	/**
	 * 判断是否闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		boolean isleap = false;
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			isleap = true;
		}
		return isleap;
	}
	
	/**
	 * 只能是数字和字母
	 * @param s
	 * @return
	 */
	public static boolean isNumOrChr(String s){
		Pattern pattern = Pattern.compile("[0-9A-Za-z]*");
		Matcher matcher = pattern.matcher(s);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 只能是数字
	 * @param s
	 * @return
	 */
	public static boolean isNum(String s){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher matcher = pattern.matcher(s);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 将字符串指定的连接字符串去掉， 如字符串“1,2,3,4,5” ，要去掉内容的字符串为“2,4” ，结果应显示“1,3,5”
	 * @param str
	 * @param substr
	 * @param chr
	 * @return
	 */
	public static String delCharInStr(String str, String substr, String c){
		if(Utils.strTrim(str).equals("")) return "";
		str = c + str + c;
		String[] substrs = substr.split(c);
		for(int i=0; i<substrs.length; i++){
			String nstr = substrs[i];
			if(str.indexOf(c+nstr+c)>=0){
				str = str.replace(c+nstr+c, c);
			}
		}
		if(str.length()>2*c.length()) str = str.substring(c.length(), str.length()-c.length());
		
		return str;
	}
	
	/**
	 * 将字符串指定的连接字符串去掉， 如字符串“1,2,3,4,5” ，要去掉内容的字符串为“2,4” ，结果应显示“1,3,5”
	 * @param str
	 * @param substr
	 * @return
	 */
	public static String delCharInStr(String str, String substr){
		return Utils.delCharInStr(str,substr,",");
	}
	//获取前月的第一天
	public static String lastmonth(String strdate,int month) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		//获取前月的第一天
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        try {
			cal_1.setTime(format.parse(strdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("日期格式错误");
		}
//        cal_1.add(Calendar.MONTH, -1);
        cal_1.add(Calendar.MONTH, month);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
	}
	public static String lastDay(Date strdate,int day){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        try {
			cal_1.setTime(strdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        cal_1.add(Calendar.MONTH, -1);
        cal_1.add(Calendar.DATE, day);
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
	}

	public static String formatTimestampToString(Object  object){
	 	if(object == null){
	 		return null;
	 	}
	 	try{
		   Timestamp tt = ((TIMESTAMP) object).timestampValue();
		   Date date = new Date(tt.getTime());
		   SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		   return sd.format(date);
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		return null;
	 	}
	}
	
	/**
	 * 
	 * 描述：处理分页查询的结果集中数据有oracle.sql.timestmap类型的数据
	 * 2013-8-14 下午05:19:51 by caoyong
	 * @version
	 * @param list
	 * @param format
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map> convertTimeOfList2String(List<Map> list,String format){
		for(Map map:list){
			for(Object key:map.keySet()){
				Object object = map.get(key);
				if(object instanceof TIMESTAMP){
					map.put(key, DateUtil.formatTimestampToString2((TIMESTAMP)object, format));
				}
			}
		}
		return list;
	}
	/**
	 * 描述：处理返回map的结果集对象中有oracle.sql.clob类型的数据
	 * 2013-12-26 下午02:04:39 by caoyong
	 * @version
	 * @param map 结果集对象
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public static Map convertClob2String(Map map) throws SQLException{
			for(Object key:map.keySet()){
				Object object = map.get(key);
				if(object instanceof CLOB){
					map.put(key, object != null ? ((CLOB)object).getSubString(1,(int)((CLOB)object).length()) : null);
				}
			}
		return map;
	}
	/**
	 * 
	 * 描述：设置表名，字段名，字段值参数
	 * 2013-10-7 上午10:06:41 by caoyong
	 * @version
	 * @param tableName
	 * @param columnName
	 * @param columnValue
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map setParams(String tableName,String columnName,String columnValue) throws Exception{
		Map map = HashMap.class.newInstance();
		map.put("tableName", tableName);
		map.put("columnName", columnName);
		map.put("columnValue", columnValue);
		return map;
	}
	/**
	 * 
	 * 描述：日期字条字符串格式统一转换成yyyy-mm-dd格式
	 * 2013-12-11 上午10:10:09 by caoyong
	 * @version
	 * @param target 目标字符串
	 * @return target替换后的结果字条串
	 */
	public static String checkDateStr(String target){
		if(target.contains(".")) target = target.replace(".", "-");
		if(target.contains("/")) target = target.replace("/", "-");
		if(target.contains("\\")) target = target.replace("\\", "-");
		if(target.contains("~")) target = target.replace("~", "-");
		return target;
	}
}
