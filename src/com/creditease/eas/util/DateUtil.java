package com.creditease.eas.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import oracle.sql.TIMESTAMP;

public class DateUtil {
	/**
	 * 转换时间为字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date) {
		if(date == null){
			return null;
		}
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd");
		return clsFormat.format(date);
	}
	/**
	 * 转换时间为字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date,String format) {
		if(date == null){
			return null;
		}
		if(null == format || "".equals(format)){
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat clsFormat = new SimpleDateFormat(format);
		return clsFormat.format(date);
	}
	/**
	 * 
	 * 描述：将Timestamp转换成字符串
	 * 2013-1-4 下午05:49:41 by ygq
	 * @version
	 * @param date
	 * @return
	 */
	public static String formatTimestampToString(TIMESTAMP  timestamp){
		 	if(timestamp == null){
		 		return "";
		 	}
		 	try{
			   Timestamp tt = timestamp.timestampValue();
			   Date date = new Date(tt.getTime());
			   SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			   return sd.format(date);
		 	}catch(Exception e){
		 		e.printStackTrace();
		 		return "";
		 	}
	}
	/**
	 * 
	 * 描述：将Timestamp转换成字符串
	 * 2013-1-4 下午05:49:41 by ygq
	 * @version
	 * @param date
	 * @return
	 */
	public static String formatTimestampToString2(TIMESTAMP  timestamp,String format){
		 	if(timestamp == null){
		 		return null;
		 	}
		 	try{
			   Timestamp tt = timestamp.timestampValue();
			   Date date = new Date(tt.getTime());
			   SimpleDateFormat sd = new SimpleDateFormat(format);
			   return sd.format(date);
		 	}catch(Exception e){
		 		e.printStackTrace();
		 		return null;
		 	}
	}
	/**
	 * 
	 * 描述：将Timestamp转换成字符串
	 * 2013-1-4 下午05:49:41 by ygq
	 * @version
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatTimestampToString(Timestamp date,String format){
		if(date == null){
			return null;
		}
		if(null == format || "".equals(format)){
			format = "yyyy-MM-dd";
		}
		 SimpleDateFormat df = new SimpleDateFormat("format");//定义格式，不显示毫秒
		 Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
		 String str = df.format(now);
		 return str;
	}
	public static final String DATE_SEPARATOR ="-/";
	/**作日期分析之用*/
	static StringTokenizer sToken;
	/**
     *给定两个时间相差的月数
     */
	 public static int monthsBetween(GregorianCalendar pFormer,GregorianCalendar pLatter){
	        GregorianCalendar vFormer = pFormer,vLatter = pLatter;
	        boolean vPositive = true;
	        if( pFormer.before(pLatter) ){
	            vFormer = pFormer;
	            vLatter = pLatter;
	        }else{
	            vFormer = pLatter;
	            vLatter = pFormer;
	            vPositive = false;
	        }
	        int vCounter = 0;
	        while(vFormer.get(vFormer.YEAR) != vLatter.get(vLatter.YEAR) ||
	             vFormer.get(vFormer.MONTH) != vLatter.get(vLatter.MONTH)){
	            vFormer.add(Calendar.MONTH, 1);
	            vCounter++;
	        }
	        if( vPositive)
	            return vCounter;
	        else
	            return -vCounter;
	 }
	 /**将字符串格式的日期转换为Calender**/
    public static GregorianCalendar parse2Cal(String pDateStr){
        sToken = new StringTokenizer(pDateStr,DATE_SEPARATOR);
        int vYear = Integer.parseInt(sToken.nextToken());
        //GregorianCalendar的月份是从0开始算起的，变态！！
        int vMonth = Integer.parseInt(sToken.nextToken())-1;
        int vDayOfMonth = Integer.parseInt(sToken.nextToken());
        return new GregorianCalendar(vYear,vMonth,vDayOfMonth);
    }
    /**给本月和给定月份的差**/
    public static int monthsBetween(String pFurtherMonth){
    	if(pFurtherMonth ==null || "".equals(pFurtherMonth)){
    		return 0;
    	}
        GregorianCalendar vToday = new GregorianCalendar();
        GregorianCalendar vFurtherMonth = parse2Cal(pFurtherMonth);
        return monthsBetween(vFurtherMonth,vToday);
    }
    
    /**
     * 
     * 描述：时间加减数月函数
     * 2013-8-20 上午11:10:03 by caoyong
     * @version
     * @param date 传入时间参数
     * @param months 加减月数
     * @return 返回加减数月后的日期
     */
    public static Date getDateAfterMonth(Date date,int months){
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		date = calendar.getTime();
		return date;
    }
    /***
     * 将Date 转换成XMLGregorianCalendar
     * @param date
     * @return  XMLGregorianCalendar
     * @version 1.0 2013/12/28 18:08
     */
    public XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {

             e.printStackTrace();
        }
        return gc;
    }
    /***
     * 将Date 转换成XMLGregorianCalendar
     * @param date
     * @return  XMLGregorianCalendar
     * @version 1.0 2013/12/28 18:08
     */
     public  Date convertToDate(XMLGregorianCalendar cal) throws Exception{
         GregorianCalendar ca = cal.toGregorianCalendar();
         return ca.getTime();
     }
     /***
      * 计算两个日期之间相差了多少个月，包含对天数的计算
     * @Title: monthsBetweenIncludeDays
     *created at 2014-6-29 上午10:57:27 by ygq  
     * @param pattern
     * @param strDateBegin
     * @param strDateEnd
     * @return
     * @return int
      */
     public static int monthsBetweenIncludeDays(Date dateBegin,Date dateEnd){
    	int result = 0;
    	//如果有为空的，则返回0
    	if(dateBegin == null || dateEnd == null){
    		return 0;
    	}
    	try{
	 		//开始日期
	 		Calendar cbegin = Calendar.getInstance();
	 		cbegin.setTime(dateBegin);//转换日期
	 		int yearBegin = cbegin.get(Calendar.YEAR);
	 		int monthBegin = cbegin.get(Calendar.MONTH);
	 		int dayBegin = cbegin.get(Calendar.DAY_OF_MONTH);
	 		//结束日期
	 		Calendar cend = Calendar.getInstance();
	 		cend.setTime(dateEnd);//转换日期
	 		int yearEnd = cend.get(Calendar.YEAR);
	 		int monthEnd = cend.get(Calendar.MARCH);
	 		int dayEnd = cend.get(Calendar.DAY_OF_MONTH);
	 		
	 		//月份相同，结束日期的天数大于等于开始日期的天数时的计算方法
	 		int result1 = monthEnd - monthBegin;
	 		//月份相同，结束日期的天数小于等于开始日期的天数时的计算方法
	 		int result2 = monthEnd - monthBegin -1;
	 		if (yearBegin == yearEnd) {
	 			if(dayEnd >= dayBegin){
	 				result = result1;// 两个日期相差几个月，即月份差
	 			}else{
	 				result = result2;
	 			}
	 		} else {
	 			if(dayEnd >= dayBegin){
	 				result = 12*(yearEnd - yearBegin) + result1;// 两个日期相差几个月，即月份差
	 			}else{
	 				result = 12*(yearEnd - yearBegin) + result2;// 两个日期相差几个月，即月份差
	 			}
	 		}
    	}catch(Exception e){
    		e.printStackTrace();
    		result = 0;
    	}
    	return result;
     }
}
