package com.creditease.eas.webservices.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.webservices.kingdee.dao.ItoumiMapper;

public class ItoumiMapperQuery extends BaseMyBatisDao{
	/**
	 * @author guoxu
	 * @return 入职员工信息
	 * @param 日期map
	 */
	public List<HashMap<String, Object>> enrollEmployeeQuery(Map params) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = getSession();
			ItoumiMapper empinfo=session.getMapper(ItoumiMapper.class);
			list = empinfo.enrollEmployeeQuery(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		
		return list;
	}
	
	/**
	 * @author guoxu
	 * @return 离职员工信息
	 * @param 日期map
	 */
	public List<HashMap<String, Object>> resignEmployeeQuery (Map params) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = getSession();
			ItoumiMapper empinfo=session.getMapper(ItoumiMapper.class);
			list = empinfo.resignEmployeeQuery(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * @author guoxu
	 * @return 异动员工信息
	 * @param 日期map
	 */
	public List<HashMap<String, Object>> fluctuationEmployeeQuery (Map params) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = getSession();
			ItoumiMapper empinfo=session.getMapper(ItoumiMapper.class);
			list = empinfo.fluctuationEmployeeQuery(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * @author guoxu
	 * @return 更新员工信息
	 * @param 日期map
	 */
	public List<HashMap<String, Object>> validateEmployeeQuery (Map params) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = getSession();
			ItoumiMapper empinfo=session.getMapper(ItoumiMapper.class);
			//传过来的参数判空
			if(null == params.get("empName") || "".equals(params.get("empName"))){
				return errorMsg();
			}
			if(null == params.get("empNO") || "".equals(params.get("empNO"))){
				return errorMsg();
			}
			if(null == params.get("typeValue") || "".equals(params.get("typeValue"))){
				return errorMsg();
			}
			if(null == params.get("typeKey") || "".equals(params.get("typeKey"))){
				return errorMsg();
			}
			
			
			list = empinfo.validateEmployeeQuery(params);
			
			//邮箱不存在直接返回false
			if("Email".equals(params.get("typeKey")) && 0 == list.size()){
				return valifalse(list);
			}
			//如果员工验证不存在，再验证是否证件类型传错了
			Map<String, String> typeQuery = empinfo.validateCardTypeQuery(params);
			if(0 == list.size() && null != typeQuery){
				//身份证匹配不对，护照号存在
				if("idCardNO".equals(params.get("typeKey")) && !StringUtil.isNull(typeQuery.get("PASSPORTNO"))
						&& StringUtil.isNull(typeQuery.get("IDCARDNO"))){
					return valitypeKey("passportNO");
				}
				//护照匹配不对，身份证存在
				if("passportNO".equals(params.get("typeKey")) && !StringUtil.isNull(typeQuery.get("IDCARDNO"))
						&& StringUtil.isNull(typeQuery.get("PASSPORTNO"))){
					return valitypeKey("idCardNO");
				}
				//身份证匹配不对，通行证存在
				if("idCardNO".equals(params.get("typeKey")) && !StringUtil.isNull(typeQuery.get("TXZ"))
						 && StringUtil.isNull(typeQuery.get("IDCARDNO")) ){
					return valitypeKey("txz");
				}
				//通行证匹配不对，身份证存在
				if("txz".equals(params.get("typeKey")) && !StringUtil.isNull(typeQuery.get("IDCARDNO"))
						&& StringUtil.isNull(typeQuery.get("TXZ"))){
					return valitypeKey("idCardNO");
				}
				//护照匹配不对，通行证存在
				if("passportNO".equals(params.get("typeKey")) && !StringUtil.isNull(typeQuery.get("TXZ"))
						&& StringUtil.isNull(typeQuery.get("PASSPORTNO")) ){
					return valitypeKey("txz");
				}
				//通行证匹配不对，护照存在
				if("txz".equals(params.get("typeKey")) && !StringUtil.isNull(typeQuery.get("PASSPORTNO"))
						&& StringUtil.isNull(typeQuery.get("TXZ")) ){
					return valitypeKey("passportNO");
				}
				
			}
			//两次查询都为空返回false
			if(0 == list.size() && null == typeQuery){
				return valifalse(list);
			}
			//上面验证都通过，list是空还没有返回
			if(0 == list.size()){
				return valifalse(list);
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
	 * @return  证件类型匹配，证件号码输入错误，直接返回false
	 * @date 2014-7-16上午11:23:03
	 * @author GUOXU
	 */    
	private List<HashMap<String, Object>> valifalse(
			List<HashMap<String, Object>> list) {
		HashMap<String, Object> valiFailed = new HashMap<String, Object>();
		valiFailed.put("HASEMP", "false");
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
		checkresult.put("TYPEKEY", typeKey);
		checkresult.put("HASEMP", "false");
		list.add(checkresult);
		return list;
	}

	/**  
	 * @return  返回验证无此人
	 * @date 2014-7-15下午03:24:45
	 * @author GUOXU
	 */    
	private List<HashMap<String, Object>> errorMsg() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> errormap = new HashMap<String, Object>();
		errormap.put("HASEMP", "false");
		errormap.put("TYPEKEY", "error");
		list.add(errormap);
		return list;
	}

}
