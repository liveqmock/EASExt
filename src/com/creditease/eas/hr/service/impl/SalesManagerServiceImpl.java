package com.creditease.eas.hr.service.impl;
import java.util.List;
import java.util.Map;

import com.creditease.eas.hr.kingdee.query.SalesManagerQuery;
import com.creditease.eas.hr.service.ISalesManagerService;
import com.creditease.eas.util.StringUtil;
/***
 * 销管系统相关的接口的service
 * @author ygq
 * @version 1.0 2013/12/27 10:23
 */
public class SalesManagerServiceImpl implements ISalesManagerService{
	/**********************提供接口查询 的Query类********************************/
	SalesManagerQuery salesManagerQuery = new SalesManagerQuery();
//	@Override
//	public String orgAddQuery(Map<String, Object> map) {
//		List list =  salesManagerQuery.personAddQuery(map);
//		String json = StringUtil.convertListToGson(list);
//		return json;
//	}
//	@Override
//	public String orgAddQuery(SalesManagerBean sManagerBean) {
//		String json = "good\t" + sManagerBean.getFname() + "\t" + sManagerBean.getFnumber() + StringUtil.dateToString(sManagerBean.getBeginDate());
//		String json = StringUtil.convertListToGson(list);
//		return json;
//	}
	@Override
	public String orgAddQuery(String jsonParam) {
		Map mapParam = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.orgAddQuery(mapParam);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String orgUpdateQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.orgUpdateQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String rankQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.rankQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personAddContractQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personAddContractQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personAddDegreeQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personAddDegreeQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personAddPositionQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personAddPositionQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personAddQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personAddQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personUpdateContractQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personUpdateContractQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personUpdateDegreeQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personUpdateDegreeQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personUpdatePositionQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personUpdatePositionQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personUpdateQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personUpdateQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personLeaveQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personLeaveQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personTransformQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personTransformQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String personUnusualActionQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.personUnusualActionQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
//	@Override
//	public String salaryPersonQuery(String jsonParam) {
//		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
//		if(null == paramMap){
//			paramMap = new HashMap<String,Object>();
//		}
//		List list =  salesManagerQuery.salaryPersonQuery(paramMap);
//		String jsonRet = StringUtil.convertListToGson(list);
//		return jsonRet;
//	}
//	@Override
//	public String salaryUpdateQuery(String jsonParam) {
//		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
//		List list =  salesManagerQuery.salaryUpdateQuery(paramMap);
//		String jsonRet = StringUtil.convertListToGson(list);
//		return jsonRet;
//	}
	
	@Override
	public String paidStaffQuery(String jsonParam) {
		Map paramMap = StringUtil.convertJsonToMap(jsonParam);//参数
		List list =  salesManagerQuery.paidStaffQuery(paramMap);
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}
	@Override
	public String paymentQuery() {
		List list =  salesManagerQuery.paymentQuery();
		String jsonRet = StringUtil.convertListToGson(list);
		return jsonRet;
	}

//	public static void main(String[] args) {
//		SalesManagerServiceImpl ss = new SalesManagerServiceImpl();
//		String jsonParam =  "{'beginTime':'2014-04-01', 'endTime':'2014-05-28'}";
////		String json = null;
//		String result = ss.rankQuery(jsonParam);
//		System.out.println(result);
//	}
	
}
	