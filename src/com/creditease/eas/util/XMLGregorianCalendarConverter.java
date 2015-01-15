//package com.creditease.eas.util;
//import java.util.Calendar;
//import javax.xml.datatype.XMLGregorianCalendar;
//import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
//
///**
// * XMLGregorianCalendar 和 Calendar 之间的转换器，适用于 Apache Commons BeanUtils.copyProperties 方法。<br>
// * 使用示例：
// * <pre>
// * import org.apache.commons.beanutils.*;
// * import javax.xml.datatype.XMLGregorianCalendar;
// * import java.util.Canlendar;
// *  
// *  
// * // 当需要转换为 XMLGregorianCalendar 类型时使用
// * ConvertUtils.register(new XMLGregorianCalendarConverter(), XMLGregorianCalendar.class);
// * // 当需要转换为 Calendar 类型时使用
// * ConvertUtils.register(new XMLGregorianCalendarConverter(), Calendar.class);
// * BeanUtils.copyProperties(destObj, srcObj);
// * </pre>
// * <p>注意：java.util.Calendar 的时区 (ZONE_OFFSET) 是以毫秒为单位的值。</p>
// * 
// * @author Stephen WJJ
// *
// */
//public class XMLGregorianCalendarConverter implements Converter {
//
//    public Object convert(Class arg0, Object arg1) {
//        if(arg1 instanceof XMLGregorianCalendar){
//            return this.convert(arg0, (XMLGregorianCalendar)arg1);
//        }else{
//            return this.convert(arg0, (Calendar)arg1);
//        }
//    }
//    
//    public Calendar convert(Class calendarType, XMLGregorianCalendar xmlCalendar){
//        return xmlCalendar.toGregorianCalendar();
//    }
//    
//    public XMLGregorianCalendar convert(Class xmlCalendarType, Calendar calendar){
//        XMLGregorianCalendar cal = new XMLGregorianCalendarImpl();
//        cal.setYear(calendar.get(Calendar.YEAR));
//        cal.setMonth(calendar.get(Calendar.MONTH) +1);
//        cal.setDay(calendar.get(Calendar.DAY_OF_MONTH));
//        cal.setHour(calendar.get(Calendar.HOUR_OF_DAY));
//        cal.setMinute(calendar.get(Calendar.MINUTE));
//        cal.setSecond(calendar.get(Calendar.SECOND));
//        cal.setMillisecond(calendar.get(Calendar.MILLISECOND));
//        cal.setTimezone(calendar.get(Calendar.ZONE_OFFSET) / 60000 );
//        return cal;
//    }
//
//}