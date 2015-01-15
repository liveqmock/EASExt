package com.creditease.eas.finance.query;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.finance.bean.AsstBean;
import com.creditease.eas.finance.kingdee.dao.EASAsstProgramWSMapper;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
/**
 * AMS接口主要的接口
 * @EASfinancialInfoCsspQuery.java
 * created at 2013-6-5 下午05:25:59 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class EASfinancialInfoCsspQuery extends BaseMyBatisDao{
	
	/**
	 * 
	 * 描述：查询客商分类信息
	 * 2013-6-5 下午05:04:51 by Administrator
	 * @version
	 * @return
	 */
	public static List<AsstBean> csspgroupQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectCsspgroupQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：根据客商分类ID查询客户信息
	 * 2013-6-5 下午08:37:30 by Administrator
	 * @version
	 * @param fid
	 * @return
	 */
//	public static List<AsstBean> customerQueryByFnumber(String fid){
//		SqlSession session = null;
//		List<AsstBean> list = null;
//		try {
//			session = getSession();
//			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
//			list = mapper.selectCustomerQueryByFnumber(fid);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
	/**
	 * 
	 * 描述：客户信息接口
	 * 2013-6-7 下午05:13:56 by Administrator
	 * @version
	 * @return
	 */
	public static List<AsstBean> customerQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectCustomerQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：查询客户信息  分页
	 * 2013-6-6 下午02:24:06 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
//	public static List<AsstBean> customerQueryPageByFnumber(Map map){
//		SqlSession session = null;
//		List<AsstBean> list = null;
//		try {
//			session = getSession();
//			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
//			Map mapParameter = new HashMap();
//			mapParameter.put("min", map.get("min"));
//			mapParameter.put("max", map.get("max"));
//			list = mapper.selectCusPageQuery(mapParameter);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
	/**
	 * 
	 * 描述：根据客商分类ID查询供应商信息
	 * 2013-6-5 下午08:41:22 by Administrator
	 * @version
	 * @param fid
	 * @return
	 */
	public static List<AsstBean> supplierQueryByFnumber(String fid){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectSupplierQueryByFnumber(fid);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：客户分类信息+客户信息接口
	 * 2013-6-7 下午05:15:30 by Administrator
	 * @version
	 * @return
	 */
//	public List<AsstBean> csspQuery(String beginTime){
//		Map map = new HashMap();
//		Date date = StringUtil.strToDate(beginTime,"yyyy-MM-dd HH:mm:ss");
//		map.put("beginTime", date);
//		map.put("fsid", "00000000-0000-0000-0000-000000000002BC122A7F");  //客商分类  客户信息
//		List<AsstBean> list = new ArrayList<AsstBean>();
//		List<AsstBean> custlist = new ArrayList<AsstBean>();
//		list = EASfinancialInfoCsspQuery.csspgroupQuery(map);
//		custlist = EASfinancialInfoCsspQuery.customerQuery(map);
//		list.addAll(custlist);
//		return list;
//	}
	/**
	 * 
	 * 描述：所有供应商信息接口
	 * 2013-6-8 上午10:35:03 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<AsstBean> supplierQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectSupplierQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：所有组织信息的查询
	 * 2013-6-8 下午07:14:50 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<AsstBean> orgAdminQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectOrgAdminQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：所有职位信息的查询 
	 * 2013-6-8 下午07:15:25 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<AsstBean> positionQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectPositionQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：所有人员信息的查询
	 * 2013-6-8 下午07:15:41 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<AsstBean> personQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectPersonQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	//客商信息总页数  	左侧菜单树
//	public static Integer csspCount(){
//		SqlSession session = null;
//		int i = 0;
//		try {
//			session = getSession();
//			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
//			i = mapper.csspCount();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return i;
//	}
	
	//客商信息 分页 客户
//	public static List<AsstBean> pageCsspQueryTest(Map map){
//		SqlSession session = null;
//		List<AsstBean> list = null;
//		try {
//			session = getSession();
//			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
//			Map mapParameter = new HashMap();
//			mapParameter.put("min", map.get("min"));
//			mapParameter.put("max", map.get("max"));
//			mapParameter.put("beginTime", map.get("beginTime"));
//			
//			int csspCount = mapper.csspCount(mapParameter);//客商信息总页数  	左侧菜单树
////			int custCount = mapper.custCount();//客户信息总页数   	右侧数据
////			int sum = csspCount+custCount;//总页数 
//			int max = Integer.parseInt(map.get("max").toString());
//			int min = Integer.parseInt(map.get("min").toString());
//			int pageSize = max - min + 1;
//			//差多少数据
//			int rem = pageSize - csspCount;
//			System.out.println("pageSize:"+pageSize+" rem: "+rem);
//			if(csspCount>min){
//				list = mapper.pageCsspQuery(mapParameter);
//				for (AsstBean css : list) {
//					System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//							+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//				}
//				mapParameter.put("min", 1);
//				mapParameter.put("max", rem);
//				
//				List<AsstBean> list2 = mapper.selectCusPageQuery(mapParameter);
//				list.addAll(list2);
//				System.err.println("-----------------------");
//				for (AsstBean css : list2) {
//					System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//							+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//				}
//			}else{
//				mapParameter.put("min", min - csspCount);
//				mapParameter.put("max", max - csspCount);
//				List<AsstBean> list2 = mapper.selectCusPageQuery(mapParameter);
//				for (AsstBean css : list2) {
//					System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//							+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//				}
//			}
//			
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
//	
//	//客商信息 分页 供应商
//	public static List<AsstBean> pageSupueryTest(Map map){
//		SqlSession session = null;
//		List<AsstBean> list = null;
//		try {
//			session = getSession();
//			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
//			Map mapParameter = new HashMap();
//			mapParameter.put("min", map.get("min"));
//			mapParameter.put("max", map.get("max"));
//			mapParameter.put("beginTime", map.get("beginTime"));
//			
//			int csspCount = mapper.csspCount(mapParameter);//客商信息总页数  	左侧菜单树
////			int custCount = mapper.custCount();//客户信息总页数   	右侧数据
////			int sum = csspCount+custCount;//总页数 
//			int max = Integer.parseInt(map.get("max").toString());
//			int min = Integer.parseInt(map.get("min").toString());
//			int pageSize = max - min + 1;
//			//差多少数据
//			int rem = pageSize - csspCount;
//			System.out.println("pageSize:"+pageSize+" rem: "+rem);
//			if(csspCount>min){
//				list = mapper.pageCsspQuery(mapParameter);
//				for (AsstBean css : list) {
//					System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//							+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//				}
//				mapParameter.put("min", 1);
//				mapParameter.put("max", rem);
//				
//				List<AsstBean> list2 = mapper.selectSupPageQuery(mapParameter);
//				list.addAll(list2);
//				System.err.println("-----------------------");
//				for (AsstBean css : list2) {
//					System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//							+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//				}
//			}else{
//				mapParameter.put("min", min - csspCount);
//				mapParameter.put("max", max - csspCount);
//				List<AsstBean> list2 = mapper.selectSupPageQuery(mapParameter);
//				for (AsstBean css : list2) {
//					System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//							+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//				}
//			}
//			
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
	/**
	 * 
	 * 描述：客商信息 分页 
	 * 2013-6-9 下午04:55:39 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<AsstBean> pageCsspQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			Map mapParameter = new HashMap();
			mapParameter.put("min", map.get("min"));
			mapParameter.put("max", map.get("max"));
			list = mapper.pageCsspQuery(mapParameter);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：公司信息的查询
	 * 2013-6-18 下午06:19:27 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<AsstBean> companyQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectCompanyQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：成本中心信息的查询 
	 * 2013-6-19 上午11:08:24 by Administrator
	 * 2013-12-4 20:00：经查在辅助核算项中在用
	 * @version
	 * @param map
	 * @return
	 */
	public static List<AsstBean> costCenterQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASAsstProgramWSMapper mapper = session.getMapper(EASAsstProgramWSMapper.class);
			list = mapper.selectCostCenterQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	public static void main(String[] args) {
//		Date date = StringUtil.strToDate("2011-11-12 12:10:10", "yyyy-MM-dd HH:mm:ss");  
		Date date = StringUtil.strToDate("2013-1-15 13:57:06", "yyyy-MM-dd HH:mm:ss");  
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginTime", date);
//		map.put("fsid", "00000000-0000-0000-0000-000000000002BC122A7F");  //客商分类  客户信息
//		map.put("fsid", "00000000-0000-0000-0000-000000000001BC122A7F");  //客商分类  供应商信息
//		List<AsstBean> list = customerQuery(map);   //客户信息
//		List<AsstBean> list = supplierQuery(map);   //供应商信息
//		List<AsstBean> list = csspgroupQuery(map);	//客商分类信息
//		for (AsstBean css : list) {
//			System.out.println(css.getFname()+":"+css.getFnumber()+" "+css.getCompanyname());
//		}
//		System.out.println(list.size());
		
		//----------------------------
//		List<AsstBean> list = customerQueryByFnumber("IZnzSaiJQwWSZgwYsV80snolaaI=");
//		for (AsstBean ass : list) {
//			System.out.println(ass.getFnumber()+":"+ass.getFname());
//		}
		
//		List<AsstBean> list = supplierQueryByFnumber("FzYn9V12TSqgblbicjukeHolaaI=");
//		for (AsstBean ass : list) {
//			System.out.println(ass.getFnumber()+":"+ass.getFname());
//		}
		
//		Map map = new HashMap<String, String>();
//		map.put("min", 20);
//		map.put("max", 40);
//		List<AsstBean> list = customerQueryPageByFnumber(map);
//		for (AsstBean ass : list) {
//			System.out.println(ass.getFnumber()+":"+ass.getFname());
//		}
		
		//-------------------------------
		
//		map.put("beginTime", null);
////		List<AsstBean> list = orgAdminQuery(map);	//所有组织信息的查询
////		List<AsstBean> list = positionQuery(map);	//所有职位信息的查询
//		List<AsstBean> list = personQuery(map);		//所有人员信息的查询 
		map.put("min", 1);
		map.put("max", 60);
//		int count = Integer map.get("min")
//		List<AsstBean> list = costCenterQuery(map);
//		List<AsstBean> list = pageCsspQueryTest(map);	//客商信息 分页 客户
		
//		List<AsstBean> list = pageSupueryTest(map);	//客商信息 分页 供应商
//		for (AsstBean css : list) {
//			System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//					+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//			System.out.println(css.getFname()+":"+css.getFnumber()+":parent\t"+css.getFtreeParentNumber() + "\t" + css.getIstree());
			//+":"
				//	+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//		}
//		System.out.println(list.size());
	}
}
