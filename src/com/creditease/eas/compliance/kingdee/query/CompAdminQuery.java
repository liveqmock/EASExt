package com.creditease.eas.compliance.kingdee.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.compliance.kingdee.dao.CompAdminMapping;
import com.creditease.eas.util.BaseMyBatisDao;
/**
 *公用方法，查询组织相关的信息
 * @RecruitmentQuery.java
 * created at 2013-7-31 下午04:47:15 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class CompAdminQuery extends BaseMyBatisDao{
	/**
	 * 描述：查询组织的信息
	 * 2013-7-31 下午04:48:49 by ygq
	 * @version
	 * @return
	 */
	public static List<Map<String,Object>> findOrgAdmin(Map map){
		SqlSession session = getSession();
		CompAdminMapping mapper = session.getMapper(CompAdminMapping.class);
		return mapper.selectOrgAdminInfo(map);
	}
	/**
	 * 描述：查询组织的信息
	 * 2014-1-15 上午09:54:09 by caoyong
	 * @version
	 * @param map
	 * @return
	 */
	public static List<Map<String,Object>> getOrgData(Map map){
		SqlSession session = getSession();
		CompAdminMapping mapper = session.getMapper(CompAdminMapping.class);
		return mapper.getOrgData(map);
	}
	/***
	 * 获得分页的数据的数量
	 * @param map
	 * @return
	 */
	public static int getTotalCountsByParams(Map map){
		SqlSession session = getSession();
		CompAdminMapping mapper = session.getMapper(CompAdminMapping.class);
		return mapper.getTotalCountsByParams(map);
	}
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("startRow", 1);
		map.put("endRow", 100);
		CompAdminQuery cq = new CompAdminQuery();
		int number = cq.getTotalCountsByParams(map);
		System.out.println("number\t" + number);
//		List list = cq.findOrgAdmin(map);
//		System.out.println(list.size());
		
	}
}
