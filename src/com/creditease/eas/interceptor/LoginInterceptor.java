package com.creditease.eas.interceptor;

import java.util.Map;

import com.creditease.eas.admin.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		User user = (User) session.get("user");
		String str = "";
		if (user!=null) {
			str = invocation.invoke();
		}else{
			str ="login";
		}
		return str;
	}
	

}
