package com.creditease.eas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creditease.eas.admin.bean.User;

public class LoginFilter implements Filter{
	public void destroy(){
	}
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException{
    	HttpSession session=((HttpServletRequest)request).getSession();
    	((HttpServletResponse)response).setHeader("Pragma","No-cache");          
    	((HttpServletResponse)response).setHeader("Cache-Control","no-cache");   
    	((HttpServletResponse)response).setHeader("Cache-Control", "no-store");   
    	((HttpServletResponse)response).setDateHeader("Expires",0);
    	User user=(User)session.getAttribute("user");
    	String url=(((HttpServletRequest)request).getRequestURI());
    	//System.out.println("url\t" + url + "\t" + user);
    	//if(url.endsWith("login.jsp")||url.endsWith("login!checkLogin")||(url.indexOf("png")!=-1)||(url.indexOf("jpg")!=-1)||(url.indexOf("gif")!=-1)||(url.indexOf(".css")!=-1)||(url.indexOf("js")!=-1)){
    	if(url.endsWith("login.jsp")||url.endsWith("login!checkLogin")){
    		chain.doFilter(request, response);
    	}else{
			if(user==null){
				System.out.println(((HttpServletRequest)request).getContextPath());
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/login.jsp");
			}else{
				chain.doFilter(request, response);
			}
    	}
    }
    public void init(FilterConfig filterConfig)throws ServletException{
    }
}
