package com.creditease.eas.hr.kingdee.query;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.kingdee.dao.PersonnelInterfaceMapper;
import com.creditease.eas.util.BaseMyBatisDao;

public class PersonnelInterfaceQuery extends BaseMyBatisDao{
	/***
	 * 根据组织ID 类型ID获取人员编码，类型名称
	* @Title: getPersonnelInterfaceByOrgId
	*created at 2014-6-9 下午01:33:52 by ygq  
	* @param fid
	* @param ftypeid
	* @return
	* @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> getPersonnelInterfaceByOrgId(String fid,String ftypeid){
		//获得组织的所有的父id
		List<HashMap<String,Object>> forPoserlist=null;//获得需要返回的信息
		//获得过滤之后的接口人信息，因为查询出来的都是符合条件的，所以只需要过滤第一个组织对应的信息
		List<HashMap<String,Object>> forPoserExcludeList = new ArrayList<HashMap<String,Object>>();
		SqlSession session=null;
		try {
			session=getSession();
			PersonnelInterfaceMapper mapper=session.getMapper(PersonnelInterfaceMapper.class);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fid", fid);
			map.put("ftypeid", ftypeid);
			
			
			forPoserlist = mapper.getPersonnelInterfaceByOrgId(map);//获得当前的组织的id
			
			if(null!=forPoserlist && !forPoserlist.isEmpty()){//找到了对应的接口人，则跳出循环，结束
				Object tempOrgInfo = forPoserlist.get(0).get("DEPTID");
				for(int i=0;i<forPoserlist.size();i++){
					HashMap<String,Object> hp = forPoserlist.get(i);
					Object deptid = hp.get("DEPTID");//查询接口人
					if(tempOrgInfo.equals(deptid)){//如果usercode不为空
						forPoserExcludeList.add(hp);
					}else{
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		//查询到顶级，但是没有查询到接口人，则返回空的数据
		return forPoserExcludeList;
	}
	/***
	 * 杨高权添加注释：根据当前组织的id，查询它所有的父id
	* @Title: getParentId
	*created at 2014-6-9 下午03:35:26 by ygq  
	* @param fid
	* @return
	* @return List<HashMap<String,Object>>
	 */
	protected List<HashMap<String,Object>> getParentId(String fid){
		List<HashMap<String,Object>> pids = null;
		SqlSession session=null;
		try {
			session=getSession();
			PersonnelInterfaceMapper mapper=session.getMapper(PersonnelInterfaceMapper.class);
			
			pids = mapper.getParentId(fid);//查询它所有的父id
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		
		return pids;
	}
	/***
	 * 杨高权添加注释：根据接口人对应的编码和接口人的类别id，查询对应的接口人信息
	* @Title: getPersonnelInterfaceByPerId
	*created at 2014-6-9 下午03:37:04 by ygq  
	* @param fnumber
	* @param ftypeid
	* @return
	* @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> getPersonnelInterfaceByPerId(String fnumber,String ftypeid){
		List<HashMap<String,Object>> forPoserlist=null;
		SqlSession session=null;
		try {
			session=getSession();
			PersonnelInterfaceMapper mapper=session.getMapper(PersonnelInterfaceMapper.class);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fnumber", fnumber);
			map.put("ftypeid", ftypeid);
			forPoserlist=mapper.getPersonnelInterfaceByPerId(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		
		return forPoserlist;
		
	}
	/***
	 * 根据接口人类别的id，查询对应的接口人类别的信息
	* @Title: getPersonnelInterfaceTypeByPerId
	*created at 2014-6-9 下午04:01:08 by ygq  
	* @param fnumber
	* @param ftypeid
	* @return
	* @return List<HashMap<String,Object>>
	 */
	public List<Map<String,Object>> getPersonnelInterfaceTypeById(String fid){
		List<Map<String,Object>> forPoserlist=null;
		SqlSession session=null;
		try {
			session=getSession();
			PersonnelInterfaceMapper mapper=session.getMapper(PersonnelInterfaceMapper.class);
			forPoserlist = mapper.getPersonnelInterfaceTypeById(fid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return forPoserlist;
	}

}
