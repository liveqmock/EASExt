package com.creditease.eas.quartz.ws;

import org.springframework.beans.factory.annotation.Autowired;

import com.creditease.eas.hr.webService.IEmpChangeSendService;
import com.creditease.eas.hr.webService.IOrganizationChangeSendService;
import com.creditease.eas.hr.webService.IPositionChangeSendService;
import com.creditease.eas.warn.bean.ConfigInfo;
import com.creditease.eas.warn.service.ConfigService;
/***
 * 推送接口：
 *   1.推送失败之后，时间暂停5分钟，更改为了2分钟
 *   2.调用方法由原来的三个方法，更改为了现在的1个方法
 * @OAWebServiceQuartzJob.java
 * created at 2013-6-22 上午10:09:59 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class OAWebServiceQuartzJob {
	//引用推送的几个接口
	public void testMethod(){
		System.out.println("测试成功了哎...亲");
	}
	@Autowired
	private IOrganizationChangeSendService iOrganizationChangeSendService;
	@Autowired
	private IPositionChangeSendService iPositionChangeSendService;
	@Autowired
	private IEmpChangeSendService iEmpChangeSendService;
	@Autowired
	private ConfigService configServiceImpl;
	/**
	 * 描述：组织信息推送的接口
	 * 2013-4-21 上午10:12:25 by ygq
	 * 
	 * @version
	 */
	public void queryOrganizationChangeFromHRToOA(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(7);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			iOrganizationChangeSendService.queryOrganizationChangeFromHRToOA();
		}
	}
	/**
	 * 描述：职位信息推送的接口
	 * 2013-4-21 上午10:13:28 by ygq
	 * @version
	 */
	public void queryPositionInfoChangeFromHRToOA(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(8);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			iPositionChangeSendService.queryPositionInfoChangeFromHRToOA();
		}
	}
	/**
	 * 描述：人员信息推送的接口
	 * 2013-4-21 上午10:11:09 by ygq
	 * @version
	 */
	public void queryEmpInfoChangeFromHRToSMPAndOA(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(9);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			iEmpChangeSendService.queryEmpInfoChangeFromHRToSMPAndOA();
		}
	}
	/**
	 * 描述：总的组织推送的方法
	 * 2013-4-21 上午10:11:09 by ygq
	 * @version
	 */
	public void queryOrganizationChangeFromHRToOAAll(){
		try{
			queryOrganizationChangeFromHRToOA();
			System.out.println("组织信息推送完成，开始推送职位的信息");
			queryPositionInfoChangeFromHRToOA();
			System.out.println("职位信息推送完成，开始推送人员的信息");
			queryEmpInfoChangeFromHRToSMPAndOA();
			System.out.println("人员信息推送完成!");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
