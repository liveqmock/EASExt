package com.creditease.eas.hr.webService.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import organizationwebservice.IOrganizationChangeServiceService;
import organizationwebservice.OrganizationService;

import com.creditease.eas.hr.bean.BaseLogRecord;
import com.creditease.eas.hr.bean.Wsorganization;
import com.creditease.eas.hr.dao.BaseLogRecordMapper;
import com.creditease.eas.hr.dao.WsorganizationMapper;
import com.creditease.eas.hr.kingdee.query.MessageChangeQuery;
import com.creditease.eas.hr.kingdee.query.MessageSendHandlerQuery;
import com.creditease.eas.hr.util.CommonUtil;
import com.creditease.eas.hr.util.LogRecordUtil;
import com.creditease.eas.hr.util.MessageChangeDateUtil;
import com.creditease.eas.hr.util.XmlResolve;
import com.creditease.eas.hr.webService.IOrganizationChangeSendService;
import com.creditease.eas.util.StringUtil;
@Service("organizationChangeSendService")
public class OrganizationChangeSendServiceImpl implements IOrganizationChangeSendService{
	private static final Logger logger = Logger.getLogger(OrganizationChangeSendServiceImpl.class);
	@Autowired
	private BaseLogRecordMapper baseLogRecordMapper;
	@Autowired
	private WsorganizationMapper wsorganizationMapper;
	/**
	 * 描述：组织
	 * 需要增加日志信息
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public void queryOrganizationChangeFromHRToOA(){
		Long baseLogId = 0L;
		//baseLogId = 67L;
		//信息的获取，首先从上次的日志中取出最大的，这个类型的日志的时间
		Date dt = MessageChangeDateUtil.completeDate(new Date());
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("typeid", 2L);//组织的信息
		map.put("endTime",dt);//获取当前时间
		Map<String,Object>  maxDate = baseLogRecordMapper.selectLastMaxRecordTime(map);
		Date beginTime = null;//真实库：335条
		if(maxDate== null){
			beginTime = MessageChangeDateUtil.getBeginDate(null);//开始时间
		}else{
			beginTime = MessageChangeDateUtil.getBeginDate(maxDate.get("RECDATE"));//开始时间
		}
		System.out.println("beginDate\t" + beginTime);
//		String strTime = "2013-3-2 0:05:03";//暂且使用该条假数据:364
//		String strTime = "2013-8-15 15:05:03";//暂且使用该条假数据:364
//		beginTime = StringUtil.strToDate(strTime);
		System.out.println("beginTime:::::::" + beginTime);
		MessageChangeQuery msgQuery = new MessageChangeQuery();
		//根据上次的最大的时间，查询出来对应的变动的信息
		String xmlString = msgQuery.selectChangeOrgInfo(beginTime);
		xmlString = xmlString.replaceAll("&", "");//替换dc
		System.out.println("xmlString\t" + xmlString);
		try{
			System.out.println("正常情况下信息的推送!");
			baseLogId = regularOrgCondition(xmlString);
		}catch(Exception ex){
			ex.printStackTrace();			
			System.out.println("出现了异常信息,过5分钟之后重新发送消息!");
			try{
				Thread.sleep(2*60*1000);
			}catch(Exception e){
				e.printStackTrace();
				//logger.info(e.getMessage());
			}
			System.out.println("五分钟之后，再次发送信息");
			try {
				secondOrgSendCondition(xmlString,baseLogId);//第二次发送的，成功或者是失败了
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
	}
	//正常情况下的信息的处理
	private Long regularOrgCondition(String xmlString) throws Exception{
		Long  baseLogId = 0L;
		//推送相关的信息
		IOrganizationChangeServiceService org = new IOrganizationChangeServiceService();
		OrganizationService service =  org.getOrganizationServicePort();
		String xmlStr = service.queryOrganizationChangeFromHRToOA(xmlString);
		System.out.println("show:::::::" + xmlStr);
		if(XmlResolve.messageHeadValidate(xmlStr) ==null){//解析xml Body中的内容
			System.out.println("验证进入异常块了");
		}else if(XmlResolve.messageHeadValidate(xmlStr).equals("301")){//301 的状态也需要更改成成功的状态
			//往baseLog表中添加一条信息
			//往组织对应的表中添加异常的信息
			//读取异常代码
			//不是4个零
			//取内容哪些没有通过（记录下来)
			System.out.println("验证未通过:内容里有异常信息!这个时候需要将验证未通过的信息记录下来......... ");
			BaseLogRecord baseLogRecord = new BaseLogRecord();
			baseLogRecord.setFuncName("组织信息变动");
			baseLogRecord.setCount(1);//第几次
			baseLogRecord.setIfsuccess(1);//成功
			baseLogRecord.setExceptioninfo(null);
			baseLogRecord.setTypeid(2L);
			baseLogRecord.setRecdate(new Date());//记录日期
			//"组织信息变动",1,2,null,2L
			LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
			baseLogId = baseLogRecord.getId();//添加完信息后，id自动被保存在baseLogRecord对象里了
			//提取xmlStr中的异常的信息，组织相关的信息，这些信息是从xml的内容中解析出来的
			List<Wsorganization> listOrg = CommonUtil.xmlStringToWsorganization(xmlString,xmlStr,baseLogId);
			LogRecordUtil.addOrganizationAll(listOrg, wsorganizationMapper);	
		}else if(XmlResolve.messageHeadValidate(xmlStr).equals("302")){
			System.out.println("推送的数量为0!");
			BaseLogRecord baseLogRecord = new BaseLogRecord();
			baseLogRecord.setFuncName("组织信息变动");
			baseLogRecord.setCount(1);//第几次
			baseLogRecord.setIfsuccess(1);//是否成功
			baseLogRecord.setExceptioninfo(null);
			baseLogRecord.setTypeid(2L);
			baseLogRecord.setRecdate(new Date());//记录日期
			baseLogRecord.setExceptcode(302L);
			baseLogRecord.setExceptioninfo("推送的数量为0");
			LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
			baseLogId = baseLogRecord.getId();
		}else if(XmlResolve.messageHeadValidate(xmlStr).equals("303")){
			System.out.println("推送的数量超标!");
			BaseLogRecord baseLogRecord = new BaseLogRecord();
			baseLogRecord.setFuncName("组织信息变动");
			baseLogRecord.setCount(1);//第几次
			baseLogRecord.setIfsuccess(1);//是否成功
			baseLogRecord.setExceptioninfo(null);
			baseLogRecord.setTypeid(2L);
			baseLogRecord.setRecdate(new Date());//记录日期
			baseLogRecord.setExceptcode(303L);
			baseLogRecord.setExceptioninfo("推送的数量超标");
			LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
			baseLogId = baseLogRecord.getId();
		}else if(XmlResolve.messageHeadValidate(xmlStr).equals("00000")){
			System.out.println("正常,日志记录,BaseLogRecordMapper,成功!");
			BaseLogRecord baseLogRecord = new BaseLogRecord();
			baseLogRecord.setFuncName("组织信息变动");
			baseLogRecord.setCount(1);//第几次
			baseLogRecord.setIfsuccess(1);//是否成功
			baseLogRecord.setExceptioninfo(null);
			baseLogRecord.setTypeid(2L);
			baseLogRecord.setRecdate(new Date());//记录日期
			LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
			baseLogId = baseLogRecord.getId();
		}
		return baseLogId;
	}
	/**
	 * 
	 * 描述：第二次推送下的信息的处理:这次是数据的更新
	 * 2013-2-25 下午06:12:03 by ygq
	 * @version
	 * @param xmlString
	 * @param baseLogId
	 * @param listInfo
	 * @throws Exception
	 */
	private void secondOrgSendCondition(String xmlString,Long baseLogId){//
		try{
			//推送相关的信息
			IOrganizationChangeServiceService org = new IOrganizationChangeServiceService();
			OrganizationService service =  org.getOrganizationServicePort();
			String xmlStr = service.queryOrganizationChangeFromHRToOA(xmlString);
			System.out.println("result::::::" + xmlStr);
			//解析xml Body中的内容
			if(XmlResolve.messageHeadValidate(xmlStr) ==null){//解析xml Body中的内容
				System.out.println("验证进入异常块了");
			}else if(XmlResolve.messageHeadValidate(xmlStr).equals("301")){//一会再加
				BaseLogRecord baseLogRecord = new BaseLogRecord();
				baseLogRecord.setId(baseLogId);
				baseLogRecord.setFuncName("组织信息变动");
				baseLogRecord.setCount(2);//第几次
				baseLogRecord.setIfsuccess(1);//成功
				baseLogRecord.setExceptioninfo(null);
				baseLogRecord.setTypeid(2L);
				baseLogRecord.setRecdate(new Date());//记录日期
				baseLogRecord.setUpdatedate(new Date());//记录日期
				//"组织信息变动",1,2,null,2L
				LogRecordUtil.updateLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);//更新基本日志记录的信息
				//xml Body中的内容
				//XmlResolve.readMessageBody(xmlStr);
				//提取xmlStr中的异常的信息，组织相关的信息，这些信息是从xml的内容中解析出来的
				List<Wsorganization> listOrg = CommonUtil.xmlStringToWsorganization(xmlString,xmlStr,baseLogId);
				LogRecordUtil.addOrganizationAll(listOrg, wsorganizationMapper);	
			}else if(XmlResolve.messageHeadValidate(xmlStr).equals("302")){
				System.out.println("推送的数量为0!");
				BaseLogRecord baseLogRecord = new BaseLogRecord();
				baseLogRecord.setFuncName("组织信息变动");
				baseLogRecord.setCount(1);//第几次
				baseLogRecord.setIfsuccess(1);//是否成功
				baseLogRecord.setExceptioninfo(null);
				baseLogRecord.setTypeid(2L);
				baseLogRecord.setRecdate(new Date());//记录日期
				baseLogRecord.setExceptcode(302L);
				baseLogRecord.setExceptioninfo("推送的数量为0");
				LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
				baseLogId = baseLogRecord.getId();
			}else if(XmlResolve.messageHeadValidate(xmlStr).equals("303")){
				System.out.println("推送的数量超标!");
				BaseLogRecord baseLogRecord = new BaseLogRecord();
				baseLogRecord.setFuncName("组织信息变动");
				baseLogRecord.setCount(1);//第几次
				baseLogRecord.setIfsuccess(1);//是否成功
				baseLogRecord.setExceptioninfo(null);
				baseLogRecord.setTypeid(2L);
				baseLogRecord.setRecdate(new Date());//记录日期
				baseLogRecord.setExceptcode(303L);
				baseLogRecord.setExceptioninfo("推送的数量超标");
				LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
				baseLogId = baseLogRecord.getId();
			}else if(XmlResolve.messageHeadValidate(xmlStr).equals("00000")){
				//正常,日志记录,BaseLogRecordMapper,成功
				BaseLogRecord baseLogRecord = new BaseLogRecord();
				baseLogRecord.setId(baseLogId);
				baseLogRecord.setFuncName("组织信息变动");
				baseLogRecord.setCount(2);//第几次
				baseLogRecord.setIfsuccess(1);//是否成功
				baseLogRecord.setExceptioninfo(null);
				baseLogRecord.setTypeid(2L);
				baseLogRecord.setRecdate(new Date());//记录日期
				baseLogRecord.setUpdatedate(new Date());//记录日期
				LogRecordUtil.updateLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
			}
		}catch(Exception ex){//
			System.out.println("再次推送失败了,将那些推送失败的信息保存下来!");
			List<Wsorganization> list = CommonUtil.xmlStringToWsorganization(xmlString, baseLogId);
//			for(int i=0;i<list.size();i++){
//				Wsorganization ws = list.get(i);
//				System.out.println(ws.getPushtime() +"\t" + ws.getRecdate() + "\t" + ws.getIscostcenter());
//			}
			LogRecordUtil.addOrganizationAll(list, wsorganizationMapper);
			System.out.println("第二次失败后，保存失败信息完成!");
		}
	}
	/**
	 * 描述：组织
	 * 需要增加日志信息
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public void queryOrganizationChangeFromHRToOAHand(String begin,String end,String fnumber){
		Long  baseLogId = 0L;
		Date dt = StringUtil.strToDate(end,"yyyy-MM-dd HH:mm:ss");//结束日期
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("typeid", 2L);//组织的信息
		map.put("endTime",dt);//获取当前时间
		Date beginTime = StringUtil.strToDate(begin,"yyyy-MM-dd HH:mm:ss");//开始日期
		beginTime = MessageChangeDateUtil.completeDate(beginTime);
		//根据上次的最大的时间，查询出来对应的变动的信息
		String xmlString = MessageSendHandlerQuery.selectChangeOrgInfoHandler(beginTime,dt,fnumber);
		xmlString = xmlString.replaceAll("&", "");
		System.out.println("xmlString\t" + xmlString);
		try{
			System.out.println("正常情况下信息的推送!");
			baseLogId = regularOrgCondition(xmlString);
		}catch(Exception ex){
			ex.printStackTrace();			
			System.out.println("出现了异常信息,过5分钟之后重新发送消息!");
			try{
				Thread.sleep(2*60*1000);
			}catch(Exception e){
				e.printStackTrace();
				//logger.info(e.getMessage());
			}
			System.out.println("五分钟之后，再次发送信息");
			try {
				secondOrgSendCondition(xmlString,baseLogId);//第二次发送的，成功或者是失败了
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
	}
}
