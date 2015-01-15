package com.creditease.eas.quartz.ws;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import oracle.sql.TIMESTAMP;

import com.creditease.eas.institutional.bean.LogBaserecordIns;
import com.creditease.eas.institutional.dao.LogBaserecordInsMapper;
import com.creditease.eas.institutional.service.WSOrgInfoService;
import com.creditease.eas.institutional.service.WSPersonInfoService;
import com.creditease.eas.institutional.util.FormatConst;
import com.creditease.eas.institutional.util.StringUtil;
import com.creditease.eas.warn.bean.ConfigInfo;
import com.creditease.eas.warn.service.ConfigService;

public class RPWebServiceQuartzJob {
	
	@Resource
	private WSOrgInfoService orgInfoService;
	@Resource
	private WSPersonInfoService personInfoService;
	@Resource
	private LogBaserecordInsMapper logBaserecordInsMapper;
	@Resource
	private ConfigService configServiceImpl;
	
	/**
	 * 推送新增组织
	 */
	public void pushAddOrg(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns syncLoger = null;
				try{
					System.out.println("新增组织数据开始推送……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("typeId", 2L);
					paramMap.put("operateType", 1L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
					//获取最后一次成功推送组织数据的日期
					Map<String,Object> ts = logBaserecordInsMapper.selectLastMaxRecordTime(paramMap);
					//生成本次数据推送的日期参数
					String beginTime =StringUtil.timestampToStr((TIMESTAMP)ts.get("RECDATE"),FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.clear();
					paramMap.put("beginTime", beginTime);
					//新增数据推送
					syncLoger = orgInfoService.addOrgToRP(paramMap);
					syncLoger.setRecdate(new Date());
					syncLoger.setIfsuccess(1);
					System.out.println("新增组织数据推送成功。");
				}catch(Exception e){
					syncLoger = new LogBaserecordIns();
					syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					syncLoger.setTypeid(2L);
					syncLoger.setFuncName("EASPushInfoToRP");
					syncLoger.setOperatetype(1L);
					syncLoger.setRecdate(new Date());
					syncLoger.setIfsuccess(0);
					System.out.println("新增组织数据推送失败。");
					e.printStackTrace();
				}finally{
					//记录日志数据
					logBaserecordInsMapper.insert(syncLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}
	/**
	 * 推送更新组织
	 */
	public void pushUpdateOrg(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns syncLoger = null;
				try{
					System.out.println("更新组织数据开始推送……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("typeId", 2L);
					paramMap.put("operateType", 3L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
					//获取最后一次成功推送组织数据的日期
					Map<String,Object> ts = logBaserecordInsMapper.selectLastMaxRecordTime(paramMap);
					//生成本次数据推送的日期参数
					String beginTime =StringUtil.timestampToStr((TIMESTAMP)ts.get("RECDATE"),FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.clear();
					paramMap.put("beginTime", beginTime);
					//新增数据推送
					syncLoger = orgInfoService.updateOrgToRP(paramMap);
					syncLoger.setRecdate(new Date());
					syncLoger.setIfsuccess(1);
					System.out.println("更新组织数据推送成功。");
				}catch(Exception e){
					syncLoger = new LogBaserecordIns();
					syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					syncLoger.setTypeid(2L);
					syncLoger.setFuncName("EASPushInfoToRP");
					syncLoger.setOperatetype(3L);
					syncLoger.setRecdate(new Date());
					syncLoger.setIfsuccess(0);
					System.out.println("更新组织数据推送失败。");
					e.printStackTrace();
				}finally{
					//记录日志数据
					logBaserecordInsMapper.insert(syncLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}
	/**
	 * 删除无效组织
	 */
	public void deleteInvalidOrg(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns syncLoger = null;
				try{
					System.out.println("删除无效组织数据开始……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("typeId", 2L);
					paramMap.put("operateType", 2L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
					//获取最后一次成功推送组织数据的日期
					Map<String,Object> ts = logBaserecordInsMapper.selectLastMaxRecordTime(paramMap);
					//生成本次数据推送的日期参数
					String beginTime =StringUtil.timestampToStr((TIMESTAMP)ts.get("RECDATE"),FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.clear();
					paramMap.put("beginTime", beginTime);
					//删除无效数据
					syncLoger = orgInfoService.removeOrgFromRP(paramMap);
					syncLoger.setRecdate(new Date());
					syncLoger.setIfsuccess(1);
					System.out.println("删除无效组织数据成功。");
				}catch(Exception e){
					syncLoger = new LogBaserecordIns();
					syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					syncLoger.setTypeid(2L);
					syncLoger.setFuncName("EASPushInfoToRP");
					syncLoger.setOperatetype(2L);
					syncLoger.setRecdate(new Date());
					syncLoger.setIfsuccess(0);
					System.out.println("删除无效组织数据失败。");
					e.printStackTrace();
				}finally{
					//记录日志数据
					logBaserecordInsMapper.insert(syncLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}
	/**
	 * 推送新增人员
	 */
	public void pushAddPerson(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns syncLoger = null;
				Date endDate = null;
				Date beginDate = null;
				try{
					System.out.println("新增人员数据开始推送……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("typeId", 1L);
					paramMap.put("operateType", 1L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
					//获取最后一次成功推送人员数据的日期
					Map<String,Object> sucTime = logBaserecordInsMapper.selectLastMaxRecordTime(paramMap);
					//生成本次数据推送的日期参数
					paramMap.clear();
					Calendar bc = Calendar.getInstance();
					bc.setTime(((TIMESTAMP)sucTime.get("RECDATE")).timestampValue());
					beginDate = bc.getTime();
					String beginTime = StringUtil.dateToStr(beginDate, FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.put("beginTime", beginTime);
					Calendar ec = Calendar.getInstance();
					ec.setTime(new Date());
					ec.add(Calendar.DATE, -2);
					endDate = ec.getTime();
					String endTime = StringUtil.dateToStr(endDate, FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.put("endTime", endTime);
					if(beginDate.compareTo(endDate) < 0){ //当前时间推迟两天后作为推送结束时间需要在开始推送时间之后
						//新增数据推送
						syncLoger = personInfoService.addPersonToRP(paramMap);
						syncLoger.setRecdate(endDate);
					}else{
						syncLoger = new LogBaserecordIns();
						syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
						syncLoger.setTypeid(1L);
						syncLoger.setFuncName("EASPushInfoToRP");
						syncLoger.setOperatetype(1L);
						syncLoger.setRecdate(beginDate);
					}
					syncLoger.setIfsuccess(1);
					System.out.println("新增人员数据推送成功。");
				}catch(Exception e){
					syncLoger = new LogBaserecordIns();
					syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					syncLoger.setTypeid(1L);
					syncLoger.setFuncName("EASPushInfoToRP");
					syncLoger.setOperatetype(1L);
					syncLoger.setRecdate(endDate);
					syncLoger.setIfsuccess(0);
					System.out.println("新增人员数据推送失败。");
					e.printStackTrace();
				}finally{
					//记录日志数据
					logBaserecordInsMapper.insert(syncLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}

	/**
	 * 推送更新人员
	 */
	public void pushUpdatePerson(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns syncLoger = null;
				Date endDate = null;
				Date beginDate = null;
				try{
					System.out.println("更新人员数据开始推送……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("typeId", 1L);
					paramMap.put("operateType", 3L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
					//获取最后一次成功推送人员数据的日期
					Map<String,Object> sucTime = logBaserecordInsMapper.selectLastMaxRecordTime(paramMap);
					//生成本次数据推送的日期参数
					paramMap.clear();
					Calendar bc = Calendar.getInstance();
					bc.setTime(((TIMESTAMP)sucTime.get("RECDATE")).timestampValue());
					beginDate = bc.getTime();
					String beginTime = StringUtil.dateToStr(beginDate, FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.put("beginTime", beginTime);
					Calendar ec = Calendar.getInstance();
					ec.setTime(new Date());
					ec.add(Calendar.DATE, -2);
					endDate = ec.getTime();
					String endTime = StringUtil.dateToStr(endDate, FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.put("endTime", endTime);
					if(beginDate.compareTo(endDate) < 0){ //当前时间推迟两天后作为推送结束时间需要在开始推送时间之后
						//新增数据推送
						syncLoger = personInfoService.updatePersonToRP(paramMap);
						syncLoger.setRecdate(endDate);
					}else{
						syncLoger = new LogBaserecordIns();
						syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
						syncLoger.setTypeid(1L);
						syncLoger.setFuncName("EASPushInfoToRP");
						syncLoger.setOperatetype(3L);
						syncLoger.setRecdate(beginDate);
					}
					syncLoger.setIfsuccess(1);
					System.out.println("更新人员数据开始成功。");
				}catch(Exception e){
					syncLoger = new LogBaserecordIns();
					syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					syncLoger.setTypeid(1L);
					syncLoger.setFuncName("EASPushInfoToRP");
					syncLoger.setOperatetype(3L);
					syncLoger.setRecdate(endDate);
					syncLoger.setIfsuccess(0);
					System.out.println("更新人员数据开始失败。");
					e.printStackTrace();
				}finally{
					//记录日志数据
					logBaserecordInsMapper.insert(syncLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}
	/**
	 * 删除无效人员
	 */
	public void deleteInvalidPerson(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(14);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println(configInfo.getConfigname()+"尚未完成或该服务未初始化，请先完成该服务的配置与初始化.");
		}else{
			if(configInfo.getConfigvalue()==0){
				LogBaserecordIns syncLoger = null;
				Date endDate = null;
				Date beginDate = null;
				try{
					System.out.println("删除无效人员数据开始……");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("typeId", 1L);
					paramMap.put("operateType", 2L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
					//获取最后一次成功推送人员数据的日期
					Map<String,Object> sucTime = logBaserecordInsMapper.selectLastMaxRecordTime(paramMap);
					//生成本次数据推送的日期参数
					paramMap.clear();
					Calendar bc = Calendar.getInstance();
					bc.setTime(((TIMESTAMP)sucTime.get("RECDATE")).timestampValue());
					beginDate = bc.getTime();
					String beginTime = StringUtil.dateToStr(beginDate, FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.put("beginTime", beginTime);
					Calendar ec = Calendar.getInstance();
					ec.setTime(new Date());
					ec.add(Calendar.DATE, -2);
					endDate = ec.getTime();
					String endTime = StringUtil.dateToStr(endDate, FormatConst.LONG_TIMESTAMP_FORMAT);
					paramMap.put("endTime", endTime);
					if(beginDate.compareTo(endDate) < 0){ //当前时间推迟两天后作为推送结束时间需要在开始推送时间之后
						//新增数据推送
						syncLoger = personInfoService.removePersonFromRP(paramMap);
						syncLoger.setRecdate(endDate);
					}else{
						syncLoger = new LogBaserecordIns();
						syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
						syncLoger.setTypeid(1L);
						syncLoger.setFuncName("EASPushInfoToRP");
						syncLoger.setOperatetype(2L);
						syncLoger.setRecdate(beginDate);
					}
					syncLoger.setIfsuccess(1);
					System.out.println("删除无效人员数据成功。");
				}catch(Exception e){
					syncLoger = new LogBaserecordIns();
					syncLoger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
					syncLoger.setTypeid(1L);
					syncLoger.setFuncName("EASPushInfoToRP");
					syncLoger.setOperatetype(2L);
					syncLoger.setRecdate(endDate);
					syncLoger.setIfsuccess(0);
					System.out.println("删除无效人员数据失败");
					e.printStackTrace();
				}finally{
					//记录日志数据
					logBaserecordInsMapper.insert(syncLoger);
				}
			}else{
				System.out.println(configInfo.getConfigname()+"服务处于关闭状态！");
			}
		}
	}
	
}
