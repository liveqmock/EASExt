//package com.creditease.eas.hr.webService.impl;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.jws.WebService;
//
//import oracle.sql.TIMESTAMP;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.creditease.eas.hr.bean.BaseLogRecord;
//import com.creditease.eas.hr.bean.Wsorganization;
//import com.creditease.eas.hr.dao.BaseLogRecordMapper;
//import com.creditease.eas.hr.dao.WsorganizationMapper;
//import com.creditease.eas.hr.kingdee.query.MessageChangeQuery;
//import com.creditease.eas.hr.kingdee.query.MessageSendQuery;
//import com.creditease.eas.hr.service.BaseLogRecordService;
//import com.creditease.eas.hr.util.LogRecordUtil;
//import com.creditease.eas.hr.util.MessageChangeDateUtil;
//import com.creditease.eas.hr.util.XmlResolve;
//import com.creditease.eas.hr.webService.IOrganizationChangeSendService;
//import com.creditease.eas.hr.webService.IMessageSendService;
//import com.creditease.eas.hr.webService.esb.IOrganizationChangeService;
//import com.creditease.eas.hr.webService.esb.OrganizationChangeServiceImpl;
//import com.creditease.eas.util.DateUtil;
//import com.creditease.eas.util.StringUtil;
//public class OrganizationChangeSendServiceImplV1 implements IOrganizationChangeSendService{
//	@Autowired
//	private BaseLogRecordMapper baseLogRecordMapper;
//	@Autowired
//	private WsorganizationMapper wsorganizationMapper;
//	
////	@Autowired
////	private BaseLogRecordMapper baseLogRecordMapper;
////	@Autowired
////	private BaseLogRecordMapper baseLogRecordMapper;
//
////	@Override
////	public void addLog() {
////		List<Map<String,Object>>  show = baseLogRecordMapper.selectLogBase();
////		System.out.println(show);
////	}
//	//:如果传code,则返回对应的，如果传null，则传所有的
//	//怎么去记录日志信息呢?
//	/**
//	 * 
//	 * 描述：组织
//	 * 需要增加日志信息
//	 * 2013-1-21 下午01:44:35 by ygq
//	 * @version
//	 * @param code
//	 * @return
//	 */
//	public void queryOrganizationChangeFromHRToOA(){
//		//System.out.println(show);
//		//1.推送信息，10秒中如果没有响应，这边会抛一个异常，
////		如果第一次推送失败，我在baseLog这张表里面记录信息
////		隔5分钟之后，我再次推送信息,如果再次失败，就将发送的人员的信息记录下来
////		第二次如果成功了，就更新BaseHistoryRecord表里面的 count，并将ifSucess设置成成功状态
////   如果推送的信息有返回的情况，就往baseLog 这张表里面记录 1 条信息, ifSucess设置成失败状态
////  然后解析esb那边返回的数据,将数据信息插入到相对应的日志表中
////	    private Date recdate;
////	    private Long typeid;
////		public void add(String funcName,String operateType,String exceptioninfo,String ifsuccess,Integer count,List list){
////			if(count ==1){//第一次：添加信息
////				BaseLogRecord baseLogRecord  = new BaseLogRecord();//第一步：肯定是添加信息
////				//baseLogRecordMapper.insert(baseLogRecord);
////				
////			}else{//第二次：修改信息 
////				//首先需要将创建的信息查询出来，并且缓存进来
////				//baseLogRecordMapper.update(baseLogRecord);
////				//根据操作类型：判断需要添加哪些
////				//
////				if(){
////					
////				}
////			}
////			//1：
////		}
//		String xmlString = "";
//		try{
//			regularCondition(xmlString);
//		}catch(Exception ex){
//			ex.printStackTrace();
//			//出现了异常信息,过5分钟之后重新发送消息
//			try{
//				Thread.sleep(5*60*1000);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			//五分钟中之后，再次发送信息
//			//不管信息是否成功了，都发送信息
//			IOrganizationChangeService i = new OrganizationChangeServiceImpl();
//			String str = i.QueryOrganizationChangeFromHRToOA(xmlString);
//			System.out.println("result::::::;" + str);
//			//这次的信息是：将那些失败的信息记录下来
//			try{
//				//成功 :日志更新
//			}catch(Exception e){
//				ex.printStackTrace();
//				//失败 :日志更新
//
//			}
//		}
//	}
//	//正常情况下的信息的处理
//	public void regularCondition(String xmlString) throws Exception{
//		//首先从上次的日志中取出最大的，这个类型的日志的时间
//		Map<String,Object> map  = new HashMap<String,Object>();
//		map.put("typeid", "3");//组织的信息
//		Object maxDate = baseLogRecordMapper.selectLastMaxRecordTime(map);
//		System.out.println(maxDate);
//		Date beginTime = MessageChangeDateUtil.getBeginDate(maxDate);//开始时间
//		//根据上次的最大的时间，查询出来对应的变动的信息
//		xmlString = MessageChangeQuery.selectChangeOrgInfo(beginTime);
//		System.out.println("xmlString:::::::::::" + xmlString);
//		//推送相关的信息
//		IOrganizationChangeService organizationChangeService = new OrganizationChangeServiceImpl();
//		String xmlStr = organizationChangeService.QueryOrganizationChangeFromHRToOA(xmlString);
//		System.out.println("result::::::" + xmlStr);
//		//解析xml Body中的内容
//		if(XmlResolve.messageHeadValidate(xmlStr) == false){//内容里有异常信息
//			//xml Body中的内容
//			XmlResolve.readMessageBody(xmlStr);
//			//往baseLog表中添加一条信息
//			//往组织对应的表中添加异常的信息
//			//读取异常代码
//			//不是4个零
//			//取内容哪些没有通过（记录下来)
//			BaseLogRecord baseLogRecord = new BaseLogRecord();
//			baseLogRecord.setFuncName("组织信息变动");
//			baseLogRecord.setCount(1);//第几次
//			baseLogRecord.setIfsuccess(2);//是否成功
//			baseLogRecord.setExceptioninfo(null);
//			baseLogRecord.setTypeid(2L);
//			baseLogRecord.setRecdate(new Date());//记录日期
//			//"组织信息变动",1,2,null,2L
//			LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
//			//添加组织相关的信息
//			Wsorganization wsorganization = new Wsorganization();
//			wsorganization.setDisplayname("中华人民共和国");
//			//组织相关的信息，这些信息是从xml的内容中解析出来的
//			LogRecordUtil.addOrganization(wsorganization, wsorganizationMapper);	
//		}else{
//			//正常,日志记录,BaseLogRecordMapper,成功
//			BaseLogRecord baseLogRecord = new BaseLogRecord();
//			baseLogRecord.setFuncName("组织信息变动");
//			baseLogRecord.setCount(1);//第几次
//			baseLogRecord.setIfsuccess(1);//是否成功
//			baseLogRecord.setExceptioninfo(null);
//			baseLogRecord.setTypeid(2L);
//			LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
//		}
//	}
//	//第二次推送下的信息的处理
//	public void secondSendCondition(String xmlString) throws Exception{
//		//首先从上次的日志中取出最大的，这个类型的日志的时间
//		Map<String,Object> map  = new HashMap<String,Object>();
//		map.put("typeid", "3");//组织的信息
//		Object maxDate = baseLogRecordMapper.selectLastMaxRecordTime(map);
//		System.out.println(maxDate);
//		Date beginTime = MessageChangeDateUtil.getBeginDate(maxDate);//开始时间
//		//根据上次的最大的时间，查询出来对应的变动的信息
//		xmlString = MessageChangeQuery.selectChangeOrgInfo(beginTime);
//		System.out.println("xmlString:::::::::::" + xmlString);
//		//推送相关的信息
//		IOrganizationChangeService organizationChangeService = new OrganizationChangeServiceImpl();
//		String xmlStr = organizationChangeService.QueryOrganizationChangeFromHRToOA(xmlString);
//		System.out.println("result::::::" + xmlStr);
//		//解析xml Body中的内容
//		if(XmlResolve.messageHeadValidate(xmlStr) == false){//内容里有异常信息
//			//xml Body中的内容
//			XmlResolve.readMessageBody(xmlStr);
//			//往baseLog表中添加一条信息
//			//往组织对应的表中添加异常的信息
//			//读取异常代码
//			//不是4个零
//			//取内容哪些没有通过（记录下来)
//			BaseLogRecord baseLogRecord = new BaseLogRecord();
//			baseLogRecord.setFuncName("组织信息变动");
//			baseLogRecord.setCount(1);//第几次
//			baseLogRecord.setIfsuccess(2);//是否成功
//			baseLogRecord.setExceptioninfo(null);
//			baseLogRecord.setTypeid(2L);
//			baseLogRecord.setRecdate(new Date());//记录日期
//			//"组织信息变动",1,2,null,2L
//			LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
//			//添加组织相关的信息
//			Wsorganization wsorganization = new Wsorganization();
//			wsorganization.setDisplayname("中华人民共和国");
//			//组织相关的信息，这些信息是从xml的内容中解析出来的
//			LogRecordUtil.addOrganization(wsorganization, wsorganizationMapper);	
//		}else{
//			//正常,日志记录,BaseLogRecordMapper,成功
//			BaseLogRecord baseLogRecord = new BaseLogRecord();
//			baseLogRecord.setFuncName("组织信息变动");
//			baseLogRecord.setCount(1);//第几次
//			baseLogRecord.setIfsuccess(1);//是否成功
//			baseLogRecord.setExceptioninfo(null);
//			baseLogRecord.setTypeid(2L);
//			LogRecordUtil.addLogBaseLogRecord(baseLogRecord,baseLogRecordMapper);
//		}
//	}
//}
