package com.creditease.eas.institutional.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.creditease.eas.institutional.bean.LogBaserecordIns;
import com.creditease.eas.institutional.dao.LogBaserecordInsMapper;
import com.creditease.eas.institutional.service.WSOrgInfoService;
import com.creditease.eas.institutional.service.WSPersonInfoService;
import com.creditease.eas.institutional.util.FormatConst;
import com.creditease.eas.institutional.util.StringUtil;
import com.creditease.eas.warn.bean.ConfigInfo;
import com.creditease.eas.warn.service.ConfigService;
/**
 * 数据推送操作类
 * @author lining
 *
 */
@Controller(value="handPushInfoAction")
public class HandPushInfoAction{
	@Resource
	private WSOrgInfoService orgInfoService;
	@Resource
	private WSPersonInfoService personInfoService;
	@Resource
	private LogBaserecordInsMapper logBaserecordInsMapper;
	@Resource
	private ConfigService configServiceImpl;

	//属性驱动模式
	private String begin;
	private String end;
	private String name;
	private String number;
	
	public void setConfigServiceImpl(ConfigService configServiceImpl) {
		this.configServiceImpl = configServiceImpl;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 手动向制度平台推送组织数据
	 */
	public void pushOrgInitInfo(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns initLoger = null;
				try{
					System.out.println("组织初始化数据开始推送……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("beginTime", begin);
					paramMap.put("endTime", end); 
					paramMap.put("orgName", name);
					initLoger = orgInfoService.initOrgToRP(paramMap);
					initLoger.setRecdate(new Date());
					initLoger.setIfsuccess(1);
					System.out.println("组织初始化数据推送成功。");
				}catch(Exception e){
					initLoger = new LogBaserecordIns();
					initLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					initLoger.setTypeid(2L);
					initLoger.setFuncName("EASPushInfoToRP");
					initLoger.setOperatetype(0L);
					initLoger.setRecdate(new Date());
					initLoger.setIfsuccess(0);
					System.out.println("组织初始化数据推送失败。");
					e.printStackTrace();
				}finally{
					logBaserecordInsMapper.insert(initLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}

	/**
	 * 手动向制度平台推送人员数据
	 */
	public void pushPersonInitInfo(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns initLoger = null;
				try{
					System.out.println("人员初始化数据开始推送……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("beginNumber", begin);
					paramMap.put("endNumber", end);
					paramMap.put("orgName", name);
					initLoger = personInfoService.initPersonToRP(paramMap);
					initLoger.setRecdate(new Date());
					initLoger.setIfsuccess(1);
					System.out.println("人员初始化数据推送成功。");
				}catch(Exception e){
					initLoger = new LogBaserecordIns();
					initLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					initLoger.setTypeid(1L);
					initLoger.setFuncName("EASPushInfoToRP");
					initLoger.setOperatetype(0L);
					initLoger.setRecdate(new Date());
					initLoger.setIfsuccess(0);
					System.out.println("人员初始化数据推送失败。");
					e.printStackTrace();
				}finally{
					logBaserecordInsMapper.insert(initLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}
//	/**
//	 * 手动向人员发送加入平台的提示邮件
//	 */
//	public void handSendMailToPerson(){
//		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
//		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
//			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
//		}else{
//			if(configInfo.getConfigvalue()==0){
//				LogBaserecordIns initLoger = null;
//				try{
//					System.out.println("邮件发送开始……");
//					Map<String,Object> paramMap = new HashMap<String,Object>();
//					paramMap.put("beginNumber", begin);
//					paramMap.put("endNumber", end);
//					paramMap.put("orgName", name);
//					initLoger = personInfoService.sendMailToPerson(paramMap);
//					initLoger.setRecdate(new Date());
//					initLoger.setIfsuccess(1);
//					System.out.println("邮件发送成功。");
//				}catch(Exception e){
//					initLoger = new LogBaserecordIns();
//					initLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
//					initLoger.setTypeid(1L);
//					initLoger.setFuncName("EASPushInfoToRP");
//					initLoger.setOperatetype(5L);
//					initLoger.setRecdate(new Date());
//					initLoger.setIfsuccess(0);
//					System.out.println("邮件发送失败。");
//					e.printStackTrace();
//				}finally{
//					logBaserecordInsMapper.insert(initLoger);
//				}
//			}else{
//				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
//			}
//		}
//	}
	/**
	 * 手动向制度平台推送已入平台的人员的兼职数据
	 */
	public void pushPersonAvocationInfo(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns initLoger = null;
				try{
					System.out.println("人员兼职数据推送开始……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("beginNumber", begin);
					paramMap.put("endNumber", end);
					paramMap.put("orgName", name);
					initLoger = personInfoService.addPersonAvocationToRP(paramMap);
					initLoger.setRecdate(new Date());
					initLoger.setIfsuccess(1);
					System.out.println("人员兼职数据推送成功。");
				}catch(Exception e){
					initLoger = new LogBaserecordIns();
					initLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					initLoger.setTypeid(1L);
					initLoger.setFuncName("EASPushInfoToRP");
					initLoger.setOperatetype(5L);
					initLoger.setRecdate(new Date());
					initLoger.setIfsuccess(0);
					System.out.println("人员兼职数据推送失败。");
					e.printStackTrace();
				}finally{
					logBaserecordInsMapper.insert(initLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}
}
