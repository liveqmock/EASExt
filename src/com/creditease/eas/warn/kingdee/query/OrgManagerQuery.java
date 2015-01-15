package com.creditease.eas.warn.kingdee.query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.warn.bean.OrgAdmin;
import com.creditease.eas.warn.dao.OrgAdminChMapper;
import com.creditease.eas.warn.kingdee.dao.OrgAdminMapper;
/**
 * 
 * @OrgAdminMapperQuery.java
 * created at 2013-4-2 下午03:44:52 by guominggao
 * 
 * @author guominggao({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class OrgManagerQuery extends BaseMyBatisDao  {
	
	
	public static String  getOrgNameByFnumber(String fnumber){
		SqlSession session = null;
		try{
			session = getSession();
			OrgAdminMapper orgadmin = session.getMapper(OrgAdminMapper.class);
			return orgadmin.getOrgNameByFnumber(fnumber);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	
	/**
	 * 
	 * 描述：获取所有一级部门
	 * 2013-4-23 上午10:05:17 by xjw
	 * @version
	 * @return
	 */
	public static Map<String,Object>  getAllOrgAdmin(){
		SqlSession session = null;
		List<Map<String,Object>>  list = null; 
		Map<String,Object> org = new HashMap<String, Object>();
		try{
			session = getSession();
			OrgAdminMapper orgadmin = session.getMapper(OrgAdminMapper.class);
		    list =  orgadmin.selectAllOrg();
			for(Map<String,Object> map : list){
				org.put(map.get("FNUMBER").toString(), map.get("FNAME").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		
		return org;
		
	}
	
	//获取所有一级部门
	public static Map<String,String>  getAllOrgManager(){
		SqlSession session = null;
		List<Map<String,Object>>  list = null; 
		Map<String,String> org = new HashMap<String, String>();
		try{
			session = getSession();
			OrgAdminMapper orgadmin = session.getMapper(OrgAdminMapper.class);
		    list =  orgadmin.selectAllOrgManager();  //获取所有一级部门 
			for(Map<String,Object> map : list){
				org.put(map.get("FNUMBER").toString(), map.get("FNAME").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		
		return org;
		
	}
	
	//根据人员编码查询人员部门编码相关信息 
	public static Map<String,Object> queryPersonInfoByFnumber(String fnumber){
		SqlSession session = null;
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			session = getSession();
			OrgAdminMapper orgadmin = session.getMapper(OrgAdminMapper.class);
			map =  orgadmin.selectPersonInfoByFnumber(fnumber);  //获取所有一级部门 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		
		return map;
		
	}
	
	public static void main(String[] args) {  
//		Map<String,Object> org = OrgManagerQuery.getAllOrgAdmin();
//		for(Entry<String,Object> it : org.entrySet()){
//			System.out.println(it.getKey()+" : " +it.getValue());
//		}
		
		Map<String,Object> map = queryPersonInfoByFnumber("201211110311");
		System.out.println(map.get("ORGNAME")+":"+map.get("NAME"));
	}

	 
}
