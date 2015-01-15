package com.creditease.eas.compliance.action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
@Controller
@Scope("prototype")
public class FormRepeatAction extends BaseAction{
	/**
	 * 验证表单重复提交问题
	 */
	public void testRepeat(){
		String repeat = request.getParameter("repeat");
//		HttpSession session = request.getSession();
//		System.out.println("struts.token\t" + session.getAttribute("struts.token"));
		System.out.println("struts\t" + request.getParameter("struts.token"));
		System.out.println("good\t" + repeat);
//		struts	56OLXKP3GSBLC9E8JOUAQE7QGX9EHPM1
//		good	abcddd
	}
}
