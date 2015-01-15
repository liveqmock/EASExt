package com.creditease.eas.hr.kingdee.query;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.kingdee.dao.FixedAssetsMapper;
import com.creditease.eas.hr.kingdee.dao.PersonnelInterfaceMapper;
import com.creditease.eas.util.BaseMyBatisDao;

public class FixedAssetsQuery extends BaseMyBatisDao{
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> getPersonnelInterfaceByOrgId(String fid){
		List<HashMap<String, Object>> pmap = getParentId(fid);
		List<HashMap<String,Object>> forPoserlist=null;
		SqlSession session=null;
		try {
			
			session=getSession();
			FixedAssetsMapper mapper=session.getMapper(FixedAssetsMapper.class);
			forPoserlist=mapper.getFixedAssetsByOrgId(fid);
			if(forPoserlist.isEmpty()){
				for(HashMap<String, Object> list:pmap){
					//System.out.println(list.get("FID"));
					forPoserlist=mapper.getFixedAssetsByOrgId(list.get("FID").toString());
					if(!forPoserlist.isEmpty()){
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
		if(forPoserlist==null){
			forPoserlist = new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("FNAME_L2", "");
			forPoserlist.add(map);
		}
		
		return forPoserlist;
		
	}
	
	protected List<HashMap<String,Object>> getParentId(String fid){
		List<HashMap<String,Object>> pid = null;
		SqlSession session=null;
		try {
			
			session=getSession();
			FixedAssetsMapper mapper=session.getMapper(FixedAssetsMapper.class);
			pid=mapper.getParentId(fid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		
		return pid;
	}
	public List<HashMap<String,Object>> getPersonnelInterfaceByPerId(String fnumber){
//		List<HashMap<String, Object>> pmap =null;
		List<HashMap<String,Object>> forPoserlist=null;
		SqlSession session=null;
		try {
			session=getSession();
			FixedAssetsMapper mapper=session.getMapper(FixedAssetsMapper.class);
			forPoserlist=mapper.getFixedAssetsByCode(fnumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		
		return forPoserlist;
		
	}
	

}
