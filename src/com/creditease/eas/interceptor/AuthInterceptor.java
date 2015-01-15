package com.creditease.eas.interceptor;
//package com.creditease.eas.struts.interceptor;
//
//import java.util.Map;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionInvocation;
//import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
//
//public class AuthInterceptor extends AbstractInterceptor{
//
//	@Override
//	public String intercept(ActionInvocation actionInvocation) throws Exception {
//		// TODO Auto-generated method stub
//		ActionContext actionContext = actionInvocation.getInvocationContext();
//		Map  session = actionInvocation.getInvocationContext().getSession();
//		Map  auth = (Map) session.get("auth");
//		String actionName = actionInvocation.getProxy().getActionName();
//		String method  = actionInvocation.getProxy().getMethod();
//		System.out.println("AuthInterceptor.intercept()"+actionName+"\\\\\\\\\\\\"+method);
//		AuthInfo authInfo = (AuthInfo) auth.get(actionName);
//		if (authInfo==null) {
//			return "no_auth";
//		}else{
//			if (method.contains("add")) {
//				if ((Operator.CREATE&authInfo.getOperator())==0) {
//					
//					return "no_auth";
//				}else{
//					
//					return actionInvocation.invoke();
//				}
//			}else if(method.contains("update")){
//				
//				if ((Operator.UPDATE&authInfo.getOperator())==0) {
//					
//					return "no_auth";
//				}else{
//					
//					return actionInvocation.invoke();
//				}
//				
//			}else if(method.contains("delete")){
//				
//				if ((Operator.DELETE&authInfo.getOperator())==0) {
//					
//					return "no_auth";
//				}else{
//					
//					return actionInvocation.invoke();
//				}
//				
//			}else{
//				
//				if ((Operator.RETRIEVE&authInfo.getOperator())==0) {
//					
//					return "no_auth";
//				}else{
//					
//					return actionInvocation.invoke();
//				}
//				
//			}
//			
//		} 
//		
//	}
//
//}
