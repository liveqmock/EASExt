package com.creditease.eas.institutional.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.eas.institutional.bean.LogBaserecordIns;
import com.creditease.eas.institutional.kingdee.query.EASOrgQuery;
import com.creditease.eas.institutional.service.WSOrgInfoService;
import com.creditease.eas.institutional.util.FormatConst;
import com.creditease.eas.institutional.util.StringUtil;
import com.creditease.webservice.ArrayOfXsdAnyType;
import com.creditease.webservice.EASWebService;
import com.creditease.webservice.EASWebServiceService;
import com.creditease.webservice.dto.GroupInfoDTO;
/**
 * 组织数据推送服务实现类
 * @author lining
 *
 */
@Service("orgInfoService")
public class WSOrgInfoServiceImpl implements WSOrgInfoService {

	private static EASWebService SERVICE;
	
	@Resource
	private EASOrgQuery orgQuery;
	
	static {
		EASWebServiceService ws = new EASWebServiceService();
		SERVICE = ws.getEASWebService();
	}
	
	@Override
	public LogBaserecordIns addOrgToRP(Map<String, Object> paramMap)
			throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(2L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(1L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
		List<GroupInfoDTO> addOrgs = orgQuery.selectAddOrg(paramMap);
		if(0 == addOrgs.size() || null == addOrgs){
			loger.setCount(0);//修改了参数类型，待验证
		}else{
			ArrayOfXsdAnyType dto = new ArrayOfXsdAnyType();
			dto.getItem().addAll(addOrgs);
			Integer result = SERVICE.addManyGroup(dto);
			loger.setCount(result);//修改了参数类型，待验证
		}
		return loger;
	}

	@Override
	public LogBaserecordIns initOrgToRP(Map<String,Object> paramMap) throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(2L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(0L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
		List<GroupInfoDTO> addOrgs = orgQuery.selectAllOrg(paramMap);
		if(0 == addOrgs.size() || null == addOrgs){
			loger.setCount(0);//修改了参数类型，待验证
		}else{
			ArrayOfXsdAnyType dto = new ArrayOfXsdAnyType();
			dto.getItem().addAll(addOrgs);
			Integer result = SERVICE.addManyGroup(dto);
			loger.setCount(result);//修改了参数类型，待验证
		}
		return loger;
	}

	@Override
	public LogBaserecordIns removeOrgFromRP(Map<String, Object> paramMap)
			throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(2L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(2L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
		List<GroupInfoDTO> invalidOrgs = orgQuery.selectInvalidOrg(paramMap);
		if(0 == invalidOrgs.size() || null == invalidOrgs){
			loger.setCount(0);//修改了参数类型，待验证
		}else{
			Integer count = 0;
			for(GroupInfoDTO org:invalidOrgs){
				if(SERVICE.deleteConfirmGroup(org.getId())){
					count++;
				}
			}
			loger.setCount(count);//修改了参数类型，待验证
		}
		return loger;
	}

	@Override
	public LogBaserecordIns updateOrgToRP(Map<String, Object> paramMap)
			throws Exception {
		LogBaserecordIns loger = new LogBaserecordIns();
		loger.setId(Long.valueOf(StringUtil.dateToStr(new Date(),FormatConst.TIMETOID_FORMAT)));
		loger.setTypeid(2L);
		loger.setFuncName("EASPushInfoToRP");
		loger.setOperatetype(3L);//0:初始化、1：增加操作、2：删除操作、3：修改操作、4：查询操作
		List<GroupInfoDTO> updateOrgs = orgQuery.selectUpdateOrg(paramMap);
		if(0 == updateOrgs.size() || null == updateOrgs){
			loger.setCount(0);//修改了参数类型，待验证
		}else{
			Integer count = 0;
			for(GroupInfoDTO org:updateOrgs){
				if(SERVICE.updateGroup(org.getId(), org.getGroupName())){
					count++;
				}
			}
			loger.setCount(count);//修改了参数类型，待验证
		}
		return loger;
	}

}
