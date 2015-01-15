package com.creditease.eas.institutional.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.eas.institutional.bean.LogBaserecordIns;
import com.creditease.eas.institutional.kingdee.query.EASPersonQuery;
import com.creditease.eas.institutional.service.WSPersonInfoService;
import com.creditease.eas.institutional.util.FormatConst;
import com.creditease.eas.institutional.util.StringUtil;
import com.creditease.webservice.ArrayOfXsdAnyType;
import com.creditease.webservice.EASWebService;
import com.creditease.webservice.EASWebServiceService;
import com.creditease.webservice.dto.UserInfoDTO;
/**
 * 人员数据推送服务实现类
 * @author lining
 *
 */
@Service("personInfoService")
public class WSPersonInfoServiceImpl implements WSPersonInfoService {

	private static EASWebService SERVICE;
	
	@Resource
	private EASPersonQuery personQuery;
	
	static {
		EASWebServiceService ws = new EASWebServiceService();
		SERVICE = ws.getEASWebService();
	}
	
	@Override
	public LogBaserecordIns addPersonToRP(Map<String, Object> paramMap)
			throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(1L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(1L);
		List<UserInfoDTO> addUsers = personQuery.selectAddPerson(paramMap);
		if(0 == addUsers.size() || null == addUsers){
			loger.setCount(0);//修改了参数类型，待验证
		}else{
			ArrayOfXsdAnyType dto = new ArrayOfXsdAnyType();
			dto.getItem().addAll(addUsers);
			int reult = SERVICE.addUsers(dto);
			loger.setCount(reult);//修改了参数类型，待验证
		}
		return loger;
	}

	@Override
	public LogBaserecordIns removePersonFromRP(Map<String, Object> paramMap)
			throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(1L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(2L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
		List<UserInfoDTO> invalidUsers = personQuery.selectInvalidPerson(paramMap);
		if(0 == invalidUsers.size() || null == invalidUsers){
			loger.setCount(0);//修改了参数类型，待验证
		}else{
			ArrayOfXsdAnyType dto = new ArrayOfXsdAnyType();
			dto.getItem().addAll(invalidUsers);
			Integer result = SERVICE.deleteUsers(dto);
			loger.setCount(result);
		}
		return loger;
	}

	@Override
	public LogBaserecordIns updatePersonToRP(Map<String, Object> paramMap)
			throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(1L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(3L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
		Integer count = 0;
		List<UserInfoDTO> updateUsers = personQuery.selectUpdatePerson(paramMap);
		if(0 != updateUsers.size() || null != updateUsers){
			ArrayOfXsdAnyType dto = new ArrayOfXsdAnyType();
			dto.getItem().addAll(updateUsers);
			Integer result = SERVICE.updateUsers(dto);
			count += result;
		}
		updateUsers = personQuery.selectAlterPerson(paramMap); 
		if(0 != updateUsers.size() || null != updateUsers){
			ArrayOfXsdAnyType dto = new ArrayOfXsdAnyType();
			dto.getItem().addAll(updateUsers);
			Integer result = SERVICE.updateUsers(dto);
			count += result;
		}
		loger.setCount(count);
		return loger;
	}

	@Override
	public LogBaserecordIns initPersonToRP(Map<String,Object> paramMap) throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(1L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(0L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
		List<UserInfoDTO> allUsers = personQuery.selectAllPerson(paramMap);
		System.out.println(allUsers.get(0).getNewId()+"--->"+allUsers.get(0).getGrade());
		if(0 == allUsers.size() || null == allUsers){
			loger.setCount(0);//修改了参数类型，待验证
		}else{
			ArrayOfXsdAnyType dto = new ArrayOfXsdAnyType();
			dto.getItem().addAll(allUsers);
			int result = SERVICE.addUsers(dto);
			loger.setCount(result);
		}
		return loger;
	}

//	@Override
//	public LogBaserecordIns sendMailToPerson(Map<String, Object> paramMap)
//			throws Exception {
//		LogBaserecordIns loger = new LogBaserecordIns();
//		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
//		loger.setTypeid(1L);
//		loger.setFuncName("EASPushInfoToRP");
//		loger.setOperatetype(5L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作、5：其它操作
//		List<UserInfoDTO> allUsers = personQuery.selectAllPerson(paramMap);
//		if(0 == allUsers.size() || null == allUsers){
//			loger.setCount(0);//修改了参数类型，待验证
//		}else{
//			ArrayOfXsdAnyType dto = new ArrayOfXsdAnyType();
//			dto.getItem().addAll(allUsers);
//			if(SERVICE.sendMail(dto)){
//				loger.setCount(allUsers.size());
//			}
//		}
//		return loger;
//	}

	@Override
	public LogBaserecordIns addPersonAvocationToRP(Map<String, Object> paramMap)
			throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(1L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(5L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作、5：其它操作
		List<UserInfoDTO> allUsers = personQuery.selectPersonAvocation(paramMap);
		if(0 == allUsers.size() || null == allUsers){
			loger.setCount(0);//修改了参数类型，待验证
		}else{
			Integer count = 0;
			for(UserInfoDTO user:allUsers){
				if(SERVICE.addGroupForAuthority(user.getLevelOneOrg(), user.getNewId())){
					count++;
				}
			}
			loger.setCount(count);
		}
		return loger;
	}

}
