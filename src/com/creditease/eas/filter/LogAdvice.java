package com.creditease.eas.filter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.compliance.bean.Log;
import com.creditease.eas.compliance.bean.UpdateInfo;
import com.creditease.eas.compliance.service.LogService;
import com.creditease.eas.util.Action;

@Aspect
public class LogAdvice {
	@Resource
	private LogService logService;
	
	private Object getFieldValue(Object targetObj,String fieldFullName){
		Object obj=targetObj;
		while(true){
			if(fieldFullName.indexOf(".")>-1){
				obj=getFieldValueAssist(obj,fieldFullName.substring(0,fieldFullName.indexOf(".")));
				fieldFullName=fieldFullName.substring(fieldFullName.indexOf(".")+1);
			}else{
				obj=getFieldValueAssist(obj,fieldFullName);
				break;
			}
		}
		return obj;
	}
	
	
	public Object getFieldValueAssist(Object targetObj,String fieldName){
		Class<?> targetClass=targetObj.getClass();
		Object obj=null;
		try {
			if(targetClass==HashMap.class){
				return ((HashMap)targetObj).get(fieldName);
			}
			Field field = targetClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			obj=field.get(targetObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	public String replaceMatch(JoinPoint point,String s){
		Object targetObj=point.getTarget();
		String regex = "\\$\\{(.+?)\\}";
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher=pattern.matcher(s);
		while (matcher.find()) {
			String str=getFieldValue(targetObj,matcher.group(1)).toString();
			s=s.replaceAll(Pattern.quote(matcher.group()), str);
		}
		return s;
	}
	
	
	@SuppressWarnings("unused")
	@AfterReturning(pointcut="execution(* com.creditease.eas.*.action.*.*(..)) && @annotation(action)",returning="result" )
	private void afterReturning(JoinPoint point,Object result,Action action) throws Throwable{
		String description=replaceMatch(point,action.description());
		
		Log l=new Log();
		User u=findCurrentUser();
		l.setOpId(u.getId().intValue());
		l.setOpName(u.getUsername());
		l.setOpcontent(description);
		l.setOpIp(ServletActionContext.getRequest().getRemoteAddr());
		l.setCreateId(u.getId().intValue());
		l.setCreateDate(new Date());
		
	
		
		System.out.println(description);
		
		String updateDescription=action.updateDescription();
		if(!updateDescription.equals("updateDescription")){
			Object targetObj=point.getTarget();
			Class<?> targetClass=targetObj.getClass();
			Field field = targetClass.getDeclaredField(updateDescription);
			field.setAccessible(true);
			Object fieldObj=field.get(targetObj);
			System.out.println(fieldObj.getClass());
			if(fieldObj.getClass()==ArrayList.class){
				if(fieldObj!=null){
					List<UpdateInfo> list=(List<UpdateInfo>)fieldObj;
					for (int i = 0; i < list.size(); i++) {
						UpdateInfo updateInfo=list.get(i);
						l.setUpdateField(updateInfo.getFieldName());
						l.setUpdateFielddescription(updateInfo.getFieldDescription());
						l.setTt1(updateInfo.getOldValue());
						l.setTt2(updateInfo.getNewValue());
						logService.insertLog(l);
					}
				}
			}
		}else{
			logService.insertLog(l);
		}
	}
	
	private User findCurrentUser(){
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		return user;
	}

	@SuppressWarnings("unused")
	@AfterThrowing(pointcut = "execution(* com.creditease.eas.compliance.action.CaseInfoAction.*(..))",throwing= "error")
	private void afterThrowing(JoinPoint point,Throwable error) {
		System.out.println(error);
	}
	
	/*private String getIpAddr(HttpServletRequest request) {   
	    String ip = request.getHeader( "x-forwarded-for" );   
	    if (ip == null || ip.length() == 0 || "unknown" .equalsIgnoreCase(ip)) {   
	        ip = request.getHeader("Proxy-Client-IP");   
	    }   
	    if (ip == null || ip.length() == 0 || "unknown" .equalsIgnoreCase(ip)) {   
	        ip = request.getHeader( "WL-Proxy-Client-IP" );   
	    }   
	    if (ip == null || ip.length() == 0 || "unknown" .equalsIgnoreCase(ip)) {   
	        ip = request.getRemoteAddr();   
	    }   
	    return ip;   
	}*/
}
