/**
 * 
 */
package com.creditease.eas.hr.kingdee.query.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.hr.kingdee.query.PersonnelInterfaceQuery;
import com.creditease.eas.util.StringUtil;

/**
 * @Title:TestPersonnelInterface.java
 * @Package com.creditease.eas.hr.kingdee.query.test
 * created at 2014-6-9 下午04:15:32 by ygq
 * @author ygq
 * @version 1.0
 */
public class TestPersonnelInterface {
	/***
	 * 根据组织ID 类型ID获取人员编码，类型名称
	* @Title: getPersonnelInterfaceByOrgId
	*created at 2014-6-9 下午01:33:52 by ygq  
	* @param fid
	* @param ftypeid
	* @return
	* @return List<HashMap<String,Object>>
	 */
	public static void getPersonnelInterfaceByOrgId(){
		String fid = "9AOHzOJ/SZCOpK7Afx8sssccMznrtQ=fff";
		//String fid = "rlgAAAAAC8TM567U";//HR组织id
		String ftypeid = "rlgAAAADBXPx7ELq";//查询对应的人员类型的id
		PersonnelInterfaceQuery query = new PersonnelInterfaceQuery();
		List<HashMap<String,Object>> listInfo = query.getPersonnelInterfaceByOrgId(fid, ftypeid);
		String json = "";
		if(null != listInfo && !listInfo.isEmpty()){
			json = StringUtil.convertListToGson(listInfo);
		}
		System.out.println("result\t" + json);
	}
	/***
	 * 查询接口人类别
	* @Title: getPersonnelInterfaceTypeById
	*created at 2014-6-9 下午05:20:27 by ygq  
	* @return void
	 */
	public static void getPersonnelInterfaceTypeById(){
		PersonnelInterfaceQuery query = new PersonnelInterfaceQuery();
		List<Map<String,Object>> list = query.getPersonnelInterfaceTypeById("rlgAAAADBXXx65ELq");
		System.out.println("result\t" + StringUtil.convertListToGson(list));
	}
	/***
	 * 根据人员编码，接口人类型ID获取接口人的基本信息
	* @Title: getPersonnelInterfaceByOrgId
	*created at 2014-6-9 下午01:33:52 by ygq  
	* @param fid
	* @param ftypeid
	* @return
	* @return List<HashMap<String,Object>>
	 */
	public static void getPersonnelInterfaceByPerId(){
		String fnumber = "2013080500302";//传递组织id
		String ftypeid = "rlgAAAADBXXx7ELq";//查询对应的人员类型的id
		PersonnelInterfaceQuery query = new PersonnelInterfaceQuery();
		List<HashMap<String,Object>> listInfo = query.getPersonnelInterfaceByPerId(fnumber, ftypeid);
		String json = "";
		if(null != listInfo && !listInfo.isEmpty()){
			json = StringUtil.convertListToGson(listInfo);
		}
		System.out.println("result\t" + json);
	}
	public static void main(String[] args){
//		getPersonnelInterfaceByOrgId();
		//根据组织ID 类型ID获取人员编码，类型名称
//		getPersonnelInterfaceByPerId();
		//查询接口人类别
		getPersonnelInterfaceTypeById();
		
	}
}
