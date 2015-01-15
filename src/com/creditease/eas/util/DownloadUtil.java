
package com.creditease.eas.util;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * 导出文件的公用的包
 * @Title:UploadUtil.java
 * @Package com.creditease.eas.util
 * created at 2014-7-7 上午11:47:50 by ygq
 * @author ygq
 * @version 1.0
 */
public class DownloadUtil {
	/**
	 * 获得导出用的输出流对象
	* @Title: getOutputStream
	*created at 2014-7-7 上午11:49:10 by ygq  
	* @param response
	* @param title
	* @return
	* @throws Exception
	* @return OutputStream
	 */
	public static OutputStream getOutputStream(HttpServletResponse response,String title) throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		response.setContentType("application/vnd.ms-excel");
		String fireFox = request.getParameter("fireFox");
		String fileName = "";
		//对字符进行编码
		if(null != fireFox && fireFox.equals("1")){
			//如果是火狐浏览器，则使用这种转码方式
			fileName = new String((title + ".xlsx").getBytes(), "iso8859-1");
		}else{
			//如果其他浏览器,则使用这种转码方式
			fileName = URLEncoder.encode(title + ".xlsx", "UTF-8");
		}
		String header = "attachment; filename=" + fileName;
		response.setHeader("Content-Disposition", header);
		OutputStream out = response.getOutputStream();
		return out;
	}
	
	/**
	 * 
	 * 描述：获得导出用的输出流对象，文件扩展名为.xls
	 * 2014-7-15 上午10:44:48 by zhangxin
	 * @version
	 * @param response
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static OutputStream getOutputStreamlow(HttpServletResponse response,String title) throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		response.setContentType("application/vnd.ms-excel");
		String fireFox = request.getParameter("fireFox");
		String fileName = "";
		//对字符进行编码
		if(null != fireFox && fireFox.equals("1")){
			//如果是火狐浏览器，则使用这种转码方式
			fileName = new String((title + ".xls").getBytes(), "iso8859-1");
		}else{
			//如果其他浏览器,则使用这种转码方式
			fileName = URLEncoder.encode(title + ".xls", "UTF-8");
		}
		String header = "attachment; filename=" + fileName;
		response.setHeader("Content-Disposition", header);
		OutputStream out = response.getOutputStream();
		return out;
	}
}
