package com.creditease.eas.hr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.creditease.eas.hr.dao.BaseLogRecordMapper;
import com.creditease.eas.hr.service.BaseLogRecordService;
@Service("baseLogRecordService")
public class BaseLogRecordServiceImpl implements BaseLogRecordService{
	@Autowired
	private BaseLogRecordMapper baseLogRecordMapper;
	@Override
	public void addLog() {
//		List<Map<String,Object>>  show = baseLogRecordMapper.selectLogBase();
//		System.out.println(show);
	}
//	public static void main(String[] args) {
//		BaseLogRecordService baseLogRecordService = new BaseLogRecordServiceImpl();
//		baseLogRecordService.addLog();
//	}
}
//private String funcName;
//private Long operatetype;
//private String exceptioninfo;
//
//private Short ifsuccess;
//private Short count;
//1.	推送信息，10秒中如果没有响应，这边会抛一个异常，
//如果第一次推送失败，我在baseLog这张表里面记录信息
//隔5分钟之后，我再次推送信息,如果再次失败，就将发送的人员的信息记录下来
//第二次如果成功了，就更新BaseHistoryRecord表里面的 count，并将ifSucess设置成成功状态

//private Date recdate;
//private Long typeid;
//public void add(String funcName,String operateType,String exceptioninfo,String ifsuccess,Integer count,List list){
//	if(count ==1){//第一次：添加信息
//		BaseLogRecord baseLogRecord  = new BaseLogRecord();//第一步：肯定是添加信息
//		//baseLogRecordMapper.insert(baseLogRecord);
//		
//	}else{//第二次：修改信息 
//		//首先需要将创建的信息查询出来，并且缓存进来
//		//baseLogRecordMapper.update(baseLogRecord);
//		//根据操作类型：判断需要添加哪些
//		//
//		if(){
//			
//		}
//	}
//	//1：
//}
//
//public void insertLogInfo(BaseLogRecordService){
//	BaseLogRecordMapper
//}
