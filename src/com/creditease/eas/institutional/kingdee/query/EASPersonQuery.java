package com.creditease.eas.institutional.kingdee.query;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.creditease.eas.institutional.helper.PersonHelper;
import com.creditease.eas.institutional.kingdee.dao.IEASPersonDao;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.webservice.dto.UserInfoDTO;

/**
 * EAS人员信息查询
 * @author lining
 *
 */
@Repository("personQuery")
public class EASPersonQuery extends BaseMyBatisDao {

	/**
	 * 查询所有需要的人员信息
	 * @return 人员信息集合
	 * @throws Exception
	 */
	public List<UserInfoDTO> selectAllPerson(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<UserInfoDTO> personList = null;
		try { 
			session = getSession();
			IEASPersonDao personDao = session.getMapper(IEASPersonDao.class);
			personList = PersonHelper.personMerge(personDao.selectAllPerson(paramMap));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return personList;
	}
	/**
	 * 查询新增人员信息
	 * @param paramMap 查询条件参数
	 * @return
	 * @throws Exception
	 */
	public List<UserInfoDTO> selectAddPerson(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<UserInfoDTO> personList = null;
		try { 
			session = getSession();
			IEASPersonDao personDao = session.getMapper(IEASPersonDao.class);  
			personList = PersonHelper.personMerge(personDao.selectAddPerson(paramMap));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return personList;
	}
	/**
	 * 查询信息更新人员信息
	 * @param paramMap 查询条件参数
	 * @return
	 * @throws Exception
	 */
	public List<UserInfoDTO> selectUpdatePerson(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<UserInfoDTO> personList = null;
		try { 
			session = getSession();
			IEASPersonDao personDao = session.getMapper(IEASPersonDao.class);  
			personList = PersonHelper.personMerge(personDao.selectUpdatePerson(paramMap));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return personList;
	}
	/**
	 * 查询信息无效（离职、退休、不在职）人员信息
	 * @param paramMap 查询条件参数
	 * @return
	 * @throws Exception
	 */
	public List<UserInfoDTO> selectInvalidPerson(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<UserInfoDTO> personList = null;
		try { 
			session = getSession();
			IEASPersonDao personDao = session.getMapper(IEASPersonDao.class);  
			personList = PersonHelper.personMerge(personDao.selectInvalidPerson(paramMap));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return personList;
	}
	/**
	 * 查询异动（所属部门的调整——添加新部门，删除旧部门记录）人员信息
	 * @param paramMap 查询条件参数
	 * @return
	 * @throws Exception
	 */
	public List<UserInfoDTO> selectAlterPerson(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<UserInfoDTO> personList = null;
		try { 
			session = getSession();
			IEASPersonDao personDao = session.getMapper(IEASPersonDao.class);  
			personList = PersonHelper.personMerge(personDao.selectAlterPerson(paramMap));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return personList;
	}
	/**
	 * 查询人员兼职数据
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<UserInfoDTO> selectPersonAvocation(Map<String,Object> paramMap) throws Exception{
		SqlSession session = null; 
		List<UserInfoDTO> personList = null;
		try { 
			session = getSession();
			IEASPersonDao personDao = session.getMapper(IEASPersonDao.class);  
			personList = PersonHelper.personMerge(personDao.selectPersonAvocation(paramMap));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return personList;
	}
	
//	public static void main(String[] args) {
//		EASPersonQuery personQuery = new EASPersonQuery();
//		try {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("beginTime", "2013-12-1");
//			paramMap.put("endTime", "2013-12-11");
////			paramMap.put("beginNumber", "201312010001");
////			paramMap.put("endNumber", "201312010001");
////			List<UserInfoDTO> resultList = personQuery.selectAllPerson(paramMap);
////			List<UserInfoDTO> resultList = personQuery.selectAddPerson(paramMap);
//			List<UserInfoDTO> resultList = personQuery.selectUpdatePerson(paramMap);
////			List<UserInfoDTO> resultList = personQuery.selectInvalidPerson(paramMap);
//			System.out.println(resultList.size());
//			for(int i = 0; i < resultList.size(); i++){
//				System.out.println(resultList.get(i).getNewId()+"------>"+resultList.get(i).getLevelOneOrg().getItem().get(0)+"------>"+resultList.get(i).getGrade());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
