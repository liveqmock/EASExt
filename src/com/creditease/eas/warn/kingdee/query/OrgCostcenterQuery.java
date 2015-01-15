package com.creditease.eas.warn.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.OrgCostcenter;

public class OrgCostcenterQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：根据成本中心从eas中查找成本中心编号
	 * 2013-10-8 下午05:00:08 by sunxiaofeng
	 * @version
	 * @param orgCostcenter
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
    public static Pagination selectCostcenter(Pagination page) throws Exception{
    	SqlSession session = getSession();
    	HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map = HashMap.class.newInstance();
		map.put("fname_l2", request.getParameter("fname_l2"));
		//查询总行数的方法
		Integer totalCounts = (Integer)session.selectOne("com.creditease.eas.warn.kingdee.dao.OrgCostcenterMapper.selectCostcenterCount", map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List<OrgCostcenter> list = session.selectList("com.creditease.eas.warn.kingdee.dao.OrgCostcenterMapper.selectAllCostcenter",map1);
		page.setRows(list);
		return page;
    }
    /**
     * 
     * 描述：根据成本中心编号或的id
     * 2013-10-14 下午05:46:51 by sunxiaofeng
     * @version
     * @param fnumber
     * @return
     */
    public static String selectById(String fnumber){
    	SqlSession session = getSession();
    	String orgCostcenter=(String)session.selectOne("com.creditease.eas.warn.kingdee.dao.OrgCostcenterMapper.selectById", fnumber);
    	return orgCostcenter;
    }
/**
 * 
 * 描述：根据fid获得成本中心名称(多个对象)
 * 2013-10-14 下午05:47:51 by sunxiaofeng
 * @version
 * @param ext1
 * @return
 */
	public static List<OrgCostcenter> selectByFname(List<String> fidlist) {
		SqlSession session = getSession();
		List<OrgCostcenter> orgCostcenterList=null;
		if(fidlist.size()>0)
		 orgCostcenterList=session.selectList("com.creditease.eas.warn.kingdee.dao.OrgCostcenterMapper.selectByFname", fidlist);
		return orgCostcenterList;
		
	}
	/**
	 * 根据成本中心名称或的fid
	 * 描述：
	 * 2013-10-15 下午01:43:15 by sunxiaofeng
	 * @version
	 * @param parameter
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	
   public static List<String> selectIdByFname(String parameter) throws Exception{
	   SqlSession session = getSession();
	    Map map = HashMap.class.newInstance();
		map.put("fname_l2", parameter);
		List<String> list = session.selectList("com.creditease.eas.warn.kingdee.dao.OrgCostcenterMapper.selectIdByFname",parameter);
		return list;
    }
  /**
   * 
   * 描述：根据fid获得成本中心名称(一个对象)
   * 2013-10-16 上午10:28:48 by sunxiaofeng
   * @version
   * @param ext1
   * @return
   */
	public static OrgCostcenter selectByFname(String ext1) {
		SqlSession session = getSession();
		OrgCostcenter orgCostcenter =(OrgCostcenter)session.selectOne("com.creditease.eas.warn.kingdee.dao.OrgCostcenterMapper.selectFname",ext1);
		return orgCostcenter;
	}
	/**
	 * 
	 * 描述：根据成本中心编号获得fid(多个对象)
	 * 2013-10-17 下午02:35:17 by sunxiaofeng
	 * @version
	 * @param fnumberList
	 * @return
	 * @throws Exception
	 */
	public static List<OrgCostcenter> selectByIds(List<String> fnumberList) throws Exception{
		SqlSession session = getSession();
		List<OrgCostcenter> orgCostcenterList=null;
		if(fnumberList.size()>0)
		 orgCostcenterList=session.selectList("com.creditease.eas.warn.kingdee.dao.OrgCostcenterMapper.selectByIds", fnumberList);
		return orgCostcenterList;
	}
	
}
