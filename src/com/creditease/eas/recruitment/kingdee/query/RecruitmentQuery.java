package com.creditease.eas.recruitment.kingdee.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.recruitment.bean.RecOrgAdmin;
import com.creditease.eas.recruitment.bean.RecOrgPosition;
import com.creditease.eas.recruitment.bean.RecPersonInfo;
import com.creditease.eas.recruitment.kingdee.dao.RecruitmentMapper;
import com.creditease.eas.util.BaseMyBatisDao;

/**
 * 招聘系统需要的接口的信息
 * @RecruitmentQuery.java
 * created at 2013-7-31 下午04:47:15 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class RecruitmentQuery extends BaseMyBatisDao{
	/**
	 * 描述：查询组织的信息
	 * 2013-7-31 下午04:48:49 by ygq
	 * @version
	 * @return
	 */
	public static List<RecOrgAdmin> findOrgAdmin(Map map){
		List<RecOrgAdmin> list = null;
		SqlSession session = null;
		try {
		    session = getSession();
			RecruitmentMapper mapper = session.getMapper(RecruitmentMapper.class);
			list =  mapper.selectOrgAdminInfo(map);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 描述：查询职位的信息
	 * 2013-7-31 下午04:48:49 by ygq
	 * @version
	 * @return
	 */
	public static List<RecOrgPosition> findOrgPositionInfo(Map map){
		List<RecOrgPosition> list = null;
		SqlSession session = null;
		try{
			session = getSession();
			RecruitmentMapper mapper = session.getMapper(RecruitmentMapper.class);
			list = mapper.selectOrgPositionInfo(map);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 描述：查询人员的信息
	 * 2013-7-31 下午04:48:49 by ygq
	 * @version
	 * @return
	 */
	public static List<RecPersonInfo> findPersonInfo(Map map){
		List<RecPersonInfo> list = null;
		SqlSession session = null;
		try{
		  session = getSession();
		  RecruitmentMapper mapper = session.getMapper(RecruitmentMapper.class);
		  list = mapper.selectPersonInfo(map);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("startRow", 1);
		map.put("endRow", 100);
		List<RecOrgAdmin> list = findOrgAdmin(map);
		for(int i=0;i<list.size();i++){
			RecOrgAdmin rec = list.get(i);
			System.out.println(rec.getDeptid() + "\t" + rec.getDeptname() + "\t" + rec.getParent_deptid() + "\t" + rec.getOrder());
		}
//		List<RecOrgPosition> listPosition = findOrgPositionInfo(map);
//		for(int i=0;i<listPosition.size();i++){
//			RecOrgPosition rec = listPosition.get(i);
//			System.out.println(rec.getPosID()+ "\t" + rec.getPosName() + "\t" + rec.getOrgID()+ "\t" + rec.getExt1());
//		} 
		
//		List<RecPersonInfo> listPersonInfo = findPersonInfo(map);
//		for(int i=0;i<listPersonInfo.size();i++){
//			RecPersonInfo rec = listPersonInfo.get(i);
//			System.out.println(rec.getUserID()+ "\t" + rec.getUserName() + "\t" + rec.getRealName() + "\t" + rec.getEmail());
//		}
		System.out.println("result");
	}
}
