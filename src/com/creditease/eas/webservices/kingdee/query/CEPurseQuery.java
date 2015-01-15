/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package com.creditease.eas.webservices.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.webservices.kingdee.dao.CEPurseMapper;

public class CEPurseQuery extends BaseMyBatisDao {
	/**  
	 * @param params
	 * @return  
	 * @date 2014-7-25下午02:59:20
	 * @author GUOXU
	 */    
	public List<HashMap<String, Object>> validateEmployeeQuery(Map params) {
		List<HashMap<String, Object>> list = null;
		List<HashMap<String, Object>> rslist = new ArrayList<HashMap<String,Object>>();
		SqlSession session = null;
		try {
			session = getSession();
			CEPurseMapper empinfo=session.getMapper(CEPurseMapper.class);
			if(null == params.get("idCardNo") || "".equals(params.get("idCardNo"))){
				return valifalse(rslist);
			}
			list = empinfo.validateEmployeeQuery(params);
			
			//验证失败 身份证不存在
			if(list.size()==0){
				return valifalse(rslist); 
			}
			
			if(list.size()>0){
				HashMap<String, Object> map = list.get(0);
				//判断姓名是否相符
				if(!map.get("EMPNAME").equals(params.get("empName"))){
					return valitypeKey("empName");
				}
				//邮箱是否相符
				if(!map.get("EMAIL").equals(params.get("email"))){
					return valitypeKey("email");
				}
				//以上都相符验证成功
				return valitrue(rslist);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**  
	 * @param list
	 * @return  验证错误
	 * @date 2014-7-29下午01:34:52
	 * @author GUOXU
	 */    
	private List<HashMap<String, Object>> valifalse(List<HashMap<String, Object>> list) {
		HashMap<String, Object> valiFailed = new HashMap<String, Object>();
		valiFailed.put("veriResults", "false");
		list.add(valiFailed);
		return list;
	}
	
	/**  
	 * @param list
	 * @return 验证通过
	 * @date 2014-7-29下午01:34:52
	 * @author GUOXU
	 */    
	private List<HashMap<String, Object>> valitrue(List<HashMap<String, Object>> list) {
		HashMap<String, Object> valiFailed = new HashMap<String, Object>();
		valiFailed.put("veriResults", "true");
		list.add(valiFailed);
		return list;
	}
	
	/**  
	 * @param typeKey
	 * @return 验证错误，证件类型不匹配 
	 * @date 2014-7-15下午03:25:11
	 * @author GUOXU
	 */    
	private List<HashMap<String, Object>> valitypeKey(String typeKey) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> checkresult = new HashMap<String, Object>();
		checkresult.put("typeKey", typeKey);
		checkresult.put("veriResults", "false");
		list.add(checkresult);
		return list;
	}
}
