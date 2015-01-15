package com.creditease.eas.hr.kingdee.query.test;

import java.util.List;
import java.util.Map;

import com.creditease.eas.hr.kingdee.query.BasePersonInfoQuery;
import com.creditease.eas.util.StringUtil;

public class TestBasePersonInfo {
	
	public static void testGetPersonInfoByNumber(){
		BasePersonInfoQuery bq = new BasePersonInfoQuery();
		Map map = bq.getPersonInfoByNumber("201210290003");
		System.out.println(map);
	}
	/***
	 * 根据参数查询人员的基本信息
	* @Title: testGetPersonInfoByNumber
	*created at 2014-6-15 下午02:28:02 by ygq  
	* @param json
	* @return void
	 */
	public static void testQueryPersonInfoByParams(String json){
		Map map = StringUtil.convertJsonToMap(json);
		BasePersonInfoQuery bq = new BasePersonInfoQuery();
		List<Map<String,Object>> list = bq.queryPersonInfoByParams(map);
		System.out.println(list);
	}
	public static void main(String[] args) {
//		String json = "{'fid':'rlgAAAAAOsGA733t','fname':'方以涵','fnumber':'201210290005','femail':'yihanfang@creditease.cn'}";
//		testQueryPersonInfoByParams(json);
		List listnull = null;
		String str = StringUtil.convertListToGson(listnull);
		System.out.println(str);
	}
}
