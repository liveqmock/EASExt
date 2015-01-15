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
public class RootFilter implements Filter{
	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setAttribute("rootFilter", req.getRequestURL());
		arg2.doFilter(req,resp);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
