package com.creditease.eas.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import com.creditease.eas.util.DateUtil;
public class DateTest {
	/**
     *程序中主要的日期分隔符为"-"和"/"，且日期序列为“年/月/日”型，其内容缺一不可
     * 例如:09/02/02或2009-02-02
     **/
	public static final String DATE_SEPARATOR ="-/";
	/**作日期分析之用*/
	static StringTokenizer sToken;
	/**
     *给定两个时间相差的月数
     */
    //本月和未来一个月的月份差
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
        GregorianCalendar vToday = new GregorianCalendar();
        GregorianCalendar vFurtherMonth = parse2Cal(pFurtherMonth);
        return monthsBetween(vFurtherMonth,vToday);
    }
	public static void main(String[] args) {
		int num = DateUtil.monthsBetween("2012-05-03");
		System.out.println(num);
	}
}
