package com.creditease.eas.hrnew.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
 
import com.creditease.eas.hrnew.service.IWSSMPPersonMapperHandService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.StringUtil;
 

@Controller
@Scope("prototype")
public class MessageChangeSMPSendAction extends BaseAction {

	@Autowired
	private IWSSMPPersonMapperHandService iwssmppersonMapperHandServiceImpl;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 * 描述： 测试异动信息推送
	 * 2013-8-5 下午03:15:45 by guominggao
	 * @version
	 * @return
	 */
	public String organizationChangeSend(){
		System.out.println("__________________手动推送异动信息");
		Integer number, tempint;  //推送数量,调用返回值
		
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime"); 
		Map result = iwssmppersonMapperHandServiceImpl.queryFluctuation(beginTime, endTime);
		System.out.println("接口返回结果__"+result); 
		
		if(null==result.get("tempint")){
			System.out.println("________SMP异动接口返回为空");
			return "sendSMPMessageTest";
		}
		if(null==result.get("number")){
			number = 0;
		}else{
			number = (Integer) result.get("number");
		} 
		tempint = (Integer) result.get("tempint");  
		request.setAttribute("number",number );
		if(tempint.equals(1)){
			request.setAttribute("number", number);
			request.setAttribute("type", "异动");
			return "sendsuccess";
		}else if(tempint.equals(0)){
			request.setAttribute("result", "异动接口调用不成功,信息推送失败!");
		}else{
			request.setAttribute("result", "异动接口调用返回值错误,信息推送失败!");
		}
		return "sendFailure";
	}
	
	/**
	 * 
	 * 描述：测试组织信息推送
	 * 2013-8-12 下午02:39:08 by guominggao
	 * @version
	 * @return
	 */
	public String orgAdminChangeSend(){
		System.out.println("__________________手动推送组织信息");
		Integer number, tempint;  //推送数量,调用返回值
		
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime"); 
		Map result = iwssmppersonMapperHandServiceImpl.orgAdminQuery(beginTime, endTime);
		System.out.println("接口返回结果__"+result); 

		if(null==result.get("tempint")){
			System.out.println("________SMP组织接口返回为空");
			return "sendSMPMessageTest";
		}
		if(null==result.get("number")){
			number = 0;
		}else{
			number = (Integer) result.get("number");
		} 
		tempint = (Integer) result.get("tempint");  
		request.setAttribute("number",number );
		if(tempint.equals(1)){
			request.setAttribute("number", number);
			request.setAttribute("type", "组织");
			return "sendsuccess";
		}else if(tempint.equals(0)){
			request.setAttribute("result", "组织接口调用不成功,信息推送失败!");
		}else{
			request.setAttribute("result", "组织接口调用返回值错误,信息推送失败!");
		}
		return "sendFailure";
	}
	
	/**
	 * 
	 * 描述： 测试职位信息推送
	 * 2013-8-12 下午02:38:13 by guominggao
	 * @version
	 * @return
	 */
	public String orgPositionChangeSend(){
		System.out.println("__________________手动推送职位信息");
		Integer number, tempint;  //推送数量,调用返回值
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime"); 
		Map result = iwssmppersonMapperHandServiceImpl.orgPositionQuery(beginTime, endTime);
		System.out.println("接口返回结果__"+result); 

		if(null==result.get("tempint")){
			System.out.println("________SMP职位接口返回为空");
			return "sendSMPMessageTest";
		}
		if(null==result.get("number")){
			number = 0;
		}else{
			number = (Integer) result.get("number");
		} 
		tempint = (Integer) result.get("tempint");  
		request.setAttribute("number",number );
		if(tempint.equals(1)){
			request.setAttribute("number", number);
			request.setAttribute("type", "职位");
			return "sendsuccess";
		}else if(tempint.equals(0)){
			request.setAttribute("result", "职位接口调用不成功,信息推送失败!");
		}else{
			request.setAttribute("result", "职位接口调用返回值错误,信息推送失败!");
		}
		return "sendFailure";
	}
	
	/**
	 * 
	 * 描述： 测试人员信息推送
	 * 2013-8-12 下午02:39:41 by guominggao
	 * @version
	 * @return
	 */
	public String PersonChangeSend(){
		System.out.println("__________________手动推送人员信息");
		Integer number, tempint;  //推送数量,调用返回值
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime"); 
		Map result = iwssmppersonMapperHandServiceImpl.queryPerson(beginTime, endTime);
		System.out.println("接口返回结果__"+result); 

		if(null==result.get("tempint")){
			System.out.println("________SMP人员接口返回为空");
			return "sendSMPMessageTest";
		}
		if(null==result.get("number")){
			number = 0;
		}else{
			number = (Integer) result.get("number");
		} 
		tempint = (Integer) result.get("tempint");  
		request.setAttribute("number",number );
		if(tempint.equals(1)){
			request.setAttribute("number", number);
			request.setAttribute("type", "人员");
			return "sendsuccess";
		}else if(tempint.equals(0)){
			request.setAttribute("result", "人员接口调用不成功,信息推送失败!");
		}else{
			request.setAttribute("result", "人员接口调用返回值错误,信息推送失败!");
		}
		return "sendFailure";
	}
	
}
