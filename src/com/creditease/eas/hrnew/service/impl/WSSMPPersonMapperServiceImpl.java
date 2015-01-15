package com.creditease.eas.hrnew.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.hr.util.MessageChangeDateUtil;
import com.creditease.eas.hrnew.bean.BaseLogRecordSMP;
import com.creditease.eas.hrnew.dao.BaseLogRecordSMPMapper;
import com.creditease.eas.hrnew.kingdee.query.WSSMPPersonMapperQuery;
import com.creditease.eas.hrnew.service.IWSSMPPersonMapperService;
import com.creditease.eas.warn.bean.ConfigInfo;
import com.creditease.eas.warn.service.ConfigService;
import com.creditease.smp.manager.EASWebservice;
import com.creditease.smp.manager.dto.EASEmployeeDTO;
import com.creditease.smp.manager.dto.EASOrganizeDTO;
import com.creditease.smp.manager.dto.EASPositionDTO;
import com.creditease.smp.manager.dto.EASTransferDTO;
@Service("wSSMPPersonMapperService")
public class WSSMPPersonMapperServiceImpl implements IWSSMPPersonMapperService {
	@Autowired
	private BaseLogRecordSMPMapper baseLogRecordSMPMapper ;
	@Autowired
	private ConfigService configServiceImpl;
//	@Autowired
//	private EASWebservice serviceClass ;

	
	/**
	 *  异动信息
	 */
	public Integer queryFluctuation() {
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(10);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println("配置信息中未增加异动的信息(10)或未初始化，请先配置.");
			return null;
		}
		if(configInfo.getConfigvalue()!=0){
			return null;
		}
		
		Integer typeid = 1;
//		得到日志最大时间
		Map<String, Object> map = getMaxDate(typeid);
		
		List<EASTransferDTO> fluctuation = WSSMPPersonMapperQuery.queryFluctuation(map);

		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("fluctuation____________"+fluctuation.size());
		if(fluctuation.equals(null)||fluctuation.size()==0){
			System.out.println("____没有数据更新，不调用接口");
			tempint = 1;
		}
		else{
			//改成服务器调用
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		    tempint = easWebservice.updateEASTransfers(fluctuation); 
		}
		
		
		
//		Integer tempint = easwebservice.updateEASTransfers(fluctuation);
		try {
			Long record = regularInfoCondition(tempint,typeid);
			if(record==0L){
				System.out.println("异动信息参数错误，日志记录未成功");
			}
		} catch (Exception ex) {
			ex.printStackTrace();		
		}
		return tempint; 
	}

	/**
	 * 组织信息
	 */
	public Integer orgAdminQuery() {
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(11);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println("配置信息中未增加组织信息(11)或未初始化，请先配置.");
			return null;
		}
		if(configInfo.getConfigvalue()!=0){
			return null;
		}
		
		Integer typeid = 2;
//		得到日志最大时间
		Map<String, Object> map = getMaxDate(typeid);
		List<EASOrganizeDTO> organize = WSSMPPersonMapperQuery.orgAdminQuery(map);

		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("organize____________"+organize.size());
		if(organize.equals(null)||organize.size()==0){
			System.out.println("____没有数据更新，不调用接口");
			tempint = 1;
		}
		else{
			//改成服务器调用
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
			
			tempint = easWebservice.updateEASOrganizes(organize);
		
		}
		try {
			Long record = regularInfoCondition(tempint,typeid);
			if(record==0L){
				System.out.println("组织信息参数错误，日志记录未成功");
			}
		} catch (Exception ex) {
			ex.printStackTrace();		
		}
		return tempint; 
	}

	/**
	 * 职位信息
	 */
	public Integer orgPositionQuery() {
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(12);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println("配置信息中未增加职位信息(12)或未初始化，请先配置.");
			return null;
		}
		if(configInfo.getConfigvalue()!=0){
			return null;
		}
		
		Integer typeid = 3;
//		得到日志最大时间
		Map<String, Object> map = getMaxDate(typeid);
		List<EASPositionDTO> position = WSSMPPersonMapperQuery.orgPositionQuery(map);

		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("position____________"+position.size());
		if(position.equals(null)||position.size()==0){
			System.out.println("____没有数据更新，不调用接口");
			tempint = 1;
		}
		else{
			//改成服务器调用
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		
			tempint = easWebservice.updateEASPositions(position);
		}
		try {
			Long record = regularInfoCondition(tempint,typeid);
			if(record==0L){
				System.out.println("职位信息参数错误，日志记录未成功");
			}
		} catch (Exception ex) {
			ex.printStackTrace();		
		}
		return tempint; 
	}




	/**
	 * 人员信息
	 */
	public Integer queryPerson() {
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(13);//获取期配置信息
		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
			System.out.println("配置信息中未增加人员信息(13)或未初始化，请先配置.");
			return null;
		}
		if(configInfo.getConfigvalue()!=0){
			return null;
		} 
		
		Integer typeid = 4;
//		得到日志最大时间
		Map<String, Object> map = getMaxDate(typeid);
		
		List<EASEmployeeDTO> employee = WSSMPPersonMapperQuery.queryPerson(map);

		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("employee____________"+employee.size());
		if(employee.equals(null)||employee.size()==0){
			System.out.println("____没有数据更新，不调用接口");
			tempint = 1;
		}
		else{
			//改成服务器调用
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
			
			tempint = easWebservice.updateEASEmployees(employee);
		}
		try {
			Long record = regularInfoCondition(tempint,typeid);
			if(record==0L){
				System.out.println("人员信息参数错误，日志记录未成功");
			}
		} catch (Exception ex) {
			ex.printStackTrace();		
		}
		return tempint;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IWSSMPPersonMapperService im = (IWSSMPPersonMapperService)context.getBean("wSSMPPersonMapperService");
	

		
		//1.异动
//		Integer temp = im.queryFluctuation();
//		System.out.println("__"+temp);
		
		//2.组织信息
		Integer temp = im.orgAdminQuery();
		System.out.println("__"+temp);
		
		//3.职位信息
//		Integer temp = im.orgPositionQuery();
//		System.out.println("__"+temp);
		
		//4.职员
//		Integer temp = im.queryPerson();
//		System.out.println("__"+temp);
	}

	/**
	 *   得到日志最大时间
	 * @param typeid
	 * @return
	 */
	private Map<String, Object> getMaxDate(Integer typeid) {
		Date dt = MessageChangeDateUtil.completeDate(new Date());
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("typeid", typeid);//类型
		map.put("endTime",dt);//获取当前时间
		Map<String,Object>  maxDate = baseLogRecordSMPMapper.selectLastMaxRecordTime(map);
		System.out.println("maxDate::::::::" + maxDate);
		Date beginTime = null;
		if(maxDate== null){
//			beginTime = MessageChangeDateUtil.getBeginDate(null);//开始时间
		}else{
			beginTime = MessageChangeDateUtil.getBeginDate(maxDate.get("RECDATE"));//开始时间
		}
		map.put("beginTime",beginTime);
		return map;
	}

	// 信息的处理
	private Long regularInfoCondition(Integer tempint,Integer typeid) throws Exception{
		Long  baseLogId = 0L;
		if(tempint ==null){
			System.out.println("验证进入异常块了");
		}else if(tempint.equals(0)){
			System.out.println("验证未通过:内容里有异常信息! ");
			BaseLogRecordSMP baseLogRecord = new BaseLogRecordSMP();
			if(null == typeid){
				return 0L;
			}
			else if(typeid == 1){
				baseLogRecord.setFuncName("异动信息");		
				baseLogRecord.setTypeid(1L);  //类型为异动信息
			}
			else if(typeid == 2){
				baseLogRecord.setFuncName("组织机构信息");		
				baseLogRecord.setTypeid(2L);  //类型为异动信息
			}
			else if(typeid == 3){
				baseLogRecord.setFuncName("职位信息");		
				baseLogRecord.setTypeid(3L);  //类型为异动信息
			}
			else if(typeid == 4){
				baseLogRecord.setFuncName("人员信息");		
				baseLogRecord.setTypeid(4L);  //类型为异动信息
			}
			
			baseLogRecord.setCount(1);
			baseLogRecord.setIfsuccess(0);//失败
			baseLogRecord.setExceptioninfo("推送数量失败");
			baseLogRecord.setRecdate(new Date());//记录日期
			baseLogRecordSMPMapper.insert(baseLogRecord);
			baseLogId = baseLogRecord.getId();
		}else if(tempint.equals(1)){
			System.out.println("正常,日志记录,BaseLogRecordSMPMapper,成功!");
			BaseLogRecordSMP baseLogRecord = new BaseLogRecordSMP();
			if(null == typeid){
				return 0L;
			}
			else if(typeid == 1){
				baseLogRecord.setFuncName("异动信息");		
				baseLogRecord.setTypeid(1L);  //类型为异动信息
			}
			else if(typeid == 2){
				baseLogRecord.setFuncName("组织机构信息");		
				baseLogRecord.setTypeid(2L);  //类型为异动信息
			}
			else if(typeid == 3){
				baseLogRecord.setFuncName("职位信息");		
				baseLogRecord.setTypeid(3L);  //类型为异动信息
			}
			else if(typeid == 4){
				baseLogRecord.setFuncName("人员信息");		
				baseLogRecord.setTypeid(4L);  //类型为异动信息
			}
			baseLogRecord.setCount(1);//第几次
			baseLogRecord.setIfsuccess(1);//是否成功
			baseLogRecord.setExceptioninfo(null);
			baseLogRecord.setRecdate(new Date());//记录日期
			baseLogRecordSMPMapper.insert(baseLogRecord);
			baseLogId = baseLogRecord.getId();
		}
		return baseLogId;
	}
}
