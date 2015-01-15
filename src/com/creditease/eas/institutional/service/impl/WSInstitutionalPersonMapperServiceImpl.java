package com.creditease.eas.institutional.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.hr.util.MessageChangeDateUtil;
import com.creditease.eas.hrnew.bean.BaseLogRecordSMP;
import com.creditease.eas.hrnew.dao.BaseLogRecordSMPMapper;
import com.creditease.eas.institutional.kingdee.query.WSInstitutionalPersonMapperQuery;
import com.creditease.eas.institutional.service.IWSInstitutionalPersonMapperService;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.warn.service.ConfigService;
import com.creditease.webservice.ArrayOfXsdAnyType;
import com.creditease.webservice.EASWebService;
import com.creditease.webservice.dto.UserInfoDTO;
@Service("wSInstitutionalPersonMapperService")
public class WSInstitutionalPersonMapperServiceImpl implements IWSInstitutionalPersonMapperService {
	@Autowired
	private BaseLogRecordSMPMapper baseLogRecordSMPMapper ;
	@Autowired
	private ConfigService configServiceImpl;
//	@Autowired
//	private EASWebservice serviceClass ; 

	/**
	 * 人员信息
	 */
	public Integer queryPerson() {
//		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(13);//获取期配置信息
//		if(configInfo.equals(null)||null==configInfo.getConfigvalue()){
//			System.out.println("配置信息中未增加人员信息(13)或未初始化，请先配置.");
//			return null;
//		}
//		if(configInfo.getConfigvalue()!=0){
//			return null;
//		} 
		
		Integer typeid = 4;
//		得到日志最大时间
//		Map<String, Object> map = getMaxDate(typeid);
		Map<String,Object> map = new HashMap<String, Object>();;
		Date date = StringUtil.strToDate("2013-10-22 13:57:06", "yyyy-MM-dd HH:mm:ss");  //开始时间
//		Date endTime = StringUtil.strToDate("2013-1-1 ", "yyyy-MM-dd "); //结束时间 
		map.put("beginTime", date);
		
		List<UserInfoDTO> userInfoList = WSInstitutionalPersonMapperQuery.queryPerson(map);

		for (int i = 0; i < 10; i++) {
			 UserInfoDTO temp = userInfoList.get(i);
			System.out.println(i+"___"+temp.getOldId()+"___"+temp.getNewId()+"————"+temp.getEnabled()
					+temp.getLevelOneOrg()+"___"+temp.getGrade()+"————"+temp.getEmail()+"————"+temp.getName());
		}
		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("employee____________"+userInfoList.size());


		// 调用制度平台接口
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setServiceClass(EASWebService.class);
		factoryBean.setAddress("http://10.10.38.241:8080/alfresco/api/EASWebService"); 
//		factoryBean.setAddress("http://10.100.30.98:8080/alfresco/api/EASWebService"); 
		EASWebService service = (EASWebService) factoryBean.create();
		ArrayOfXsdAnyType arrayuser = new ArrayOfXsdAnyType();
		arrayuser.getItem().addAll(userInfoList);
		int temp = service.addUsers(arrayuser);
		
		System.out.println(temp);
		return tempint;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IWSInstitutionalPersonMapperService im = (IWSInstitutionalPersonMapperService)context.getBean("wSInstitutionalPersonMapperService");
		//4.职员
		Integer temp = im.queryPerson();
		System.out.println("__"+temp);
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
