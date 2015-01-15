package com.creditease.eas.warn.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope(value="prototype")
public class UploadLogAction extends ActionSupport {
	/**
	 * 
	 * 描述：tomcat日志
	 * 2013-3-8 下午05:04:59 by xjw
	 * @version
	 */
	public void tomcatlog(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		MailSenderInfo mailInfo = new MailSenderInfo();
        String filelog = mailInfo.getProperties().getProperty("tomcatlog");
        String mytext = request.getParameter("mytext");
        filelog += mytext;
        System.out.println("filelog: "+filelog);
        download(filelog,response);
	}
	/**
	 * 
	 * 描述：log日志
	 * 2013-3-8 下午05:04:34 by xjw
	 * @version
	 */
	public void test(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		MailSenderInfo mailInfo = new MailSenderInfo();
        String filelog = mailInfo.getProperties().getProperty("filelog");
        String currentdate = Utils.getCurrentDate();
        String times = request.getParameter("mydate");
       
        if(times.equals(currentdate))
        {
        	filelog = mailInfo.getProperties().getProperty("filelog");
        }else{
        	 filelog += times+".log";
        }
        System.out.println("filelog: "+filelog);
        download(filelog,response);
	}
	
	public HttpServletResponse download(String filelog, HttpServletResponse response) {
		try {
				// path是指欲下载的文件的路径。
				File file = new File(filelog);
				if(!file.exists()){
					String data = "没有日志记录";
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write(data);
	            }else{
	            	// 取得文件名。
	    			String filename = file.getName();
	    			// 取得文件的后缀名。
	//    			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
	//    			System.out.println("ext: "+ext);
	    			// 以流的形式下载文件。
	    			InputStream fis = new BufferedInputStream(new FileInputStream(filelog));
	    			byte[] buffer = new byte[fis.available()];
	    			fis.read(buffer);
	    			fis.close();
	    			// 清空response
	    			response.reset();
	    			// 设置response的Header
	    			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
	    			response.addHeader("Content-Length", "" + file.length());
	    			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	    			response.setContentType("application/octet-stream");
	    			toClient.write(buffer);
	    			toClient.flush();
	    			toClient.close();
	            }
			
			} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}
	
	
}
