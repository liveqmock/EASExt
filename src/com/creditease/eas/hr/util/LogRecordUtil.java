package com.creditease.eas.hr.util;

import java.util.List;

import com.creditease.eas.hr.bean.BaseLogRecord;
import com.creditease.eas.hr.bean.Wsorganization;
import com.creditease.eas.hr.bean.Wspersoninfo;
import com.creditease.eas.hr.bean.Wspositioninfo;
import com.creditease.eas.hr.dao.BaseLogRecordMapper;
import com.creditease.eas.hr.dao.WsorganizationMapper;
import com.creditease.eas.hr.dao.WspersoninfoMapper;
import com.creditease.eas.hr.dao.WspositioninfoMapper;
public class LogRecordUtil {
	/***
	 * 
	 * 描述：日志记录功能
	 * 2013-1-23 下午06:46:15 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo：异常信息可能只取前1000字的信息
	 * @throws Exception
	 */
	public static void addLogBaseLogRecord(String funcName,int count, int ifsuccess,String exceptioninfo,Long typeid,BaseLogRecordMapper baseLogRecordMapper) throws Exception 
	{
		try{
			BaseLogRecord baseLogRecord = new BaseLogRecord();
			baseLogRecord.setFuncName(funcName);
			baseLogRecord.setCount(count);//第几次
			baseLogRecord.setIfsuccess(ifsuccess);//是否成功
			baseLogRecord.setExceptioninfo(exceptioninfo);
			baseLogRecord.setTypeid(typeid);
			//获取信息
			//insertOrganization
			baseLogRecordMapper.insert(baseLogRecord);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	/***
	 * 
	 * 描述：添加日志记录
	 * 2013-1-23 下午06:46:15 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo：异常信息可能只取前1000字的信息
	 * @throws Exception
	 */
	public static long addLogBaseLogRecord(BaseLogRecord baseLogRecord,BaseLogRecordMapper baseLogRecordMapper) throws Exception{
		long result = 0;
		try{
			//获取信息
			result = baseLogRecordMapper.insert(baseLogRecord);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	/***
	 * 
	 * 描述：更新日志记录
	 * 2013-1-23 下午06:46:15 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo：异常信息可能只取前1000字的信息
	 * @throws Exception
	 */
	public static long updateLogBaseLogRecord(BaseLogRecord baseLogRecord,BaseLogRecordMapper baseLogRecordMapper) throws Exception{
		long result = 0;
		try{
			//获取信息
			result = baseLogRecordMapper.updateByPrimaryKeySelective(baseLogRecord);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	/**
	 * 描述：添加组织相关的信息
	 * 2013-2-25 下午03:37:01 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo
	 * @param baseLogRecordMapper
	 * @throws Exception
	 */
	public static int addOrganization(Wsorganization wsorganization,WsorganizationMapper wsorganizationMapper) throws Exception 
	{
		int result = 0;
		try{
			//获取信息
			result = wsorganizationMapper.insert(wsorganization);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	/**
	 * 描述：更新组织相关的信息
	 * 2013-2-25 下午03:37:01 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo
	 * @param baseLogRecordMapper
	 * @throws Exception
	 */
	public static void addOrganizationAll(List<Wsorganization> list,WsorganizationMapper wsorganizationMapper){
		if(list == null || list.size()==0){
			return;
		}
		try{
			//获取信息 
			for(int i=0;i<list.size();i++){
				Wsorganization wsorganization = list.get(i);
				wsorganizationMapper.insert(wsorganization);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 描述：添加人员相关的信息
	 * 2013-2-25 下午03:37:01 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo
	 * @param baseLogRecordMapper
	 * @throws Exception
	 */
	public static int addPersonInfo(Wspersoninfo wspersoninfo,WspersoninfoMapper wspersoninfoMapper) throws Exception 
	{
		int result = 0;
		try{
			//获取信息
			result = wspersoninfoMapper.insert(wspersoninfo);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	/**
	 * 描述：记录人员相关的日志信息
	 * 2013-2-25 下午03:37:01 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo
	 * @param baseLogRecordMapper
	 * @throws Exception
	 */
	public static void addPersonInfoAll(List<Wspersoninfo> list,WspersoninfoMapper wspersoninfoMapper){
		if(list == null || list.size()==0){
			return;
		}
		try{
			//获取信息 
			for(int i=0;i<list.size();i++){
				Wspersoninfo wspersoninfo = list.get(i);
				wspersoninfoMapper.insert(wspersoninfo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 描述：添加人员相关的信息
	 * 2013-2-25 下午03:37:01 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo
	 * @param baseLogRecordMapper
	 * @throws Exception
	 */
	public static int addPositionInfo(Wspositioninfo wspersoninfo,WspositioninfoMapper wspositioninfoMapper) throws Exception 
	{
		int result = 0;
		try{
			//获取信息
			result = wspositioninfoMapper.insert(wspersoninfo);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	/**
	 * 描述：记录人员相关的日志信息
	 * 2013-2-25 下午03:37:01 by ygq
	 * @version
	 * @param funcName
	 * @param count
	 * @param ifsuccess
	 * @param exceptioninfo
	 * @param baseLogRecordMapper
	 * @throws Exception
	 */
	public static void addPositionInfoAll(List<Wspositioninfo> list,WspositioninfoMapper wspositioninfoMapper){
		if(list == null || list.size()==0){
			return;
		}
		try{
			//获取信息 
			for(int i=0;i<list.size();i++){
				Wspositioninfo wspersoninfo = list.get(i);
				wspositioninfoMapper.insert(wspersoninfo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
