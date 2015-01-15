package com.creditease.eas.institutional.kingdee.query;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.creditease.eas.institutional.kingdee.dao.IEASOrgDao;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.webservice.dto.GroupInfoDTO;
/**
 * EAS组织信息查询
 * @author lining
 *
 */
@Repository("orgQuery")
public class EASOrgQuery extends BaseMyBatisDao{

	/**
	 * 获取所有行政组织信息
	 * @return
	 * @throws Exception
	 */
	public List<GroupInfoDTO> selectAllOrg(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<GroupInfoDTO> orgList = null;
		try { 
			session = getSession();
			IEASOrgDao orgDao = session.getMapper(IEASOrgDao.class);  
			orgList = orgDao.selectAllOrg();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return orgList;
	}
	/**
	 * 获取新增组织信息
	 * @param paramMap 查询条件参数（beginTime,endTime）
	 * @return
	 * @throws Exception
	 */
	public List<GroupInfoDTO> selectAddOrg(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<GroupInfoDTO> orgList = null;
		try { 
			session = getSession();
			IEASOrgDao orgDao = session.getMapper(IEASOrgDao.class);  
			orgList = orgDao.selectAddOrg(paramMap);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return orgList;
	}
	/**
	 * 获取所有更新组织信息
	 * @param paramMap 查询条件参数（beginTime,endTime）
	 * @return
	 * @throws Exception
	 */
	public List<GroupInfoDTO> selectUpdateOrg(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<GroupInfoDTO> orgList = null;
		try { 
			session = getSession();
			IEASOrgDao orgDao = session.getMapper(IEASOrgDao.class);  
			orgList = orgDao.selectUpdateOrg(paramMap);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return orgList;
	}
	/**
	 * 获取所有无效组织信息
	 * @param paramMap 查询条件参数（beginTime,endTime）
	 * @return
	 * @throws Exception
	 */
	public List<GroupInfoDTO> selectInvalidOrg(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<GroupInfoDTO> orgList = null;
		try { 
			session = getSession();
			IEASOrgDao orgDao = session.getMapper(IEASOrgDao.class);  
			orgList = orgDao.selectInvalidOrg(paramMap);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return orgList;
	}
//	public static void main(String[] args) {
//		EASOrgQuery orgQuery = new EASOrgQuery();
//		try {
////			Map<String, Object> paramMap = new HashMap<String, Object>();
////			paramMap.put("isInit", true);
////			paramMap.put("beginTime", "2013-11-25 ");
////			paramMap.put("endTime", "2013-11-27 ");
//			List<Map<String,Object>> orgList = orgQuery.selectAllOrg();
////			List<String> orgList = orgQuery.selectAddOrg(paramMap);
////			List<String> orgList = orgQuery.selectUpdateOrg(paramMap);
////			List<String> orgList = orgQuery.selectInvalidOrg(paramMap);
//			for(Map<String,Object> org:orgList){
//				System.out.println(org);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
