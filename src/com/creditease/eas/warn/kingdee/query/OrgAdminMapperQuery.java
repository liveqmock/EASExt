package com.creditease.eas.warn.kingdee.query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.OrgAdmin;
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
public class OrgAdminMapperQuery extends BaseMyBatisDao  {
	
	/**
	 * 
	 * 描述：查询所有行政组织
	 * 2013-4-2 下午04:45:35 by guominggao
	 * @version
	 * @return
	 */
	public static List<Map<String,Object>>  selectAllOrgAdmin(){
		SqlSession session = null;
		List<Map<String,Object>>  list = null;
		List<OrgAdmin> listorg = new ArrayList<OrgAdmin>();
		
		try{
			session = getSession();
			OrgAdminMapper orgadmin = session.getMapper(OrgAdminMapper.class);
		    list =  orgadmin.selectAllPerson();
//		    for (int i = 0; i < list.size(); i++) {
//		    	OrgAdmin orgamdin = new OrgAdmin();
//		    	if(null!=list.get(i)){
//		    		Map<String,Object> mapadmin =    list.get(i);  
//					orgamdin.setFid(mapadmin.get("FID").toString());
//					orgamdin.setFname(mapadmin.get("FNAME").toString());
//					orgamdin.setFparentid(mapadmin.get("FPARENTID").toString());
//					listorg.add(orgamdin);
//		    	}
		    	
				
//			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		
		return list;
		
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
//				System.out.println(map.get("FNAME").toString()+" : "+map.get("FNUMBER").toString());
				org.put(map.get("FNUMBER").toString(), map.get("FNAME").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		
		return org;
		
	}
	
	public static OrgAdminMapper getOrgAdminMapper(){
		SqlSession session = null;
		OrgAdminMapper orgadminMapper = null;
		session = getSession();
		try{
			orgadminMapper = session.getMapper(OrgAdminMapper.class);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			closeSession(session);
		}
		return orgadminMapper;
	}
	
	
	public static Pagination queryPage(Pagination page) {
//		OrgAdminMapper oorgAdminMapper = getOrgAdminMapper();
		SqlSession session = null;
		OrgAdminMapper oorgAdminMapper = null;
		
		try{
			session = getSession();
			oorgAdminMapper = session.getMapper(OrgAdminMapper.class);
			HttpServletRequest request= ServletActionContext.getRequest();
			int currentPage = PageUtil.strToPage(request.getParameter("page"));
			int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
			//查询总行数的方法
			int totalCounts = oorgAdminMapper.getTotalCounts();
			System.out.println("totalcount: "+totalCounts);
			page = new Pagination(currentPage,pageSize,totalCounts); 
			//查询数据集的方法
			Map map2 = PageUtil.getMap(page);
			List list = oorgAdminMapper.queryPage(map2);
			page.setRows(list);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return page;
	}
	
	/**
	 * 
	 * 描述：
	 * 2013-4-2 下午03:58:20 by guominggao
	 * @version
	 * @param args
	 */
	public static void main(String[] args) {  
//        List<Map<String,Object>>  listadmin = OrgAdminMapperQuery.selectAllOrgAdmin();
//        System.out.println(listadmin);
//        for (int i = 0; i < listadmin.size(); i++) {
//			 Map<String, Object> admin = listadmin.get(i);
//			System.out.println(admin.get("FNAME"));
//			
//		}
 
		Map<String,Object> org = OrgAdminMapperQuery.getAllOrgAdmin();
		for(Entry<String,Object> it : org.entrySet()){
			System.out.println(it.getKey()+" : " +it.getValue());
		}
	}

	
}
