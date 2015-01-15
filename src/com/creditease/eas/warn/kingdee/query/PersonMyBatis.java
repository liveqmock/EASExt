//package com.creditease.eas.warn.kingdee.query;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.session.SqlSession;
//
//import com.creditease.eas.util.BaseMyBatisDao;
//import com.creditease.eas.util.Utils;
//import com.creditease.eas.warn.dao.WaringDetailMapper;
//import com.creditease.eas.warn.kingdee.dao.PersonMapper;
//public class PersonMyBatis extends BaseMyBatisDao{
////	public static void testMybiats() throws Exception{
////		SqlSession session = null;
////		try {
////			session = getSession();
////			PersonMapper mapper = session.getMapper(PersonMapper.class); 
////			int count = mapper.getTotalCounts();
////			System.out.println("count::::::::" + count);
////			session.commit();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}finally{
////			closeSession(session);
////		}
////	}
//	/**
//	 * 查询明细信息
//	 */
//	public static int selectWaringdetail() throws Exception{
//		int result = 0;
//		SqlSession session = null;
//		try {
//			session = getSession();
//			WaringDetailMapper mapper = session.getMapper(WaringDetailMapper.class); 
//			Map map = new HashMap();
//			String strs=Utils.getNowMonth()+Utils.getNowDay();
//			map.put("time", strs);
//			result = mapper.selectWaringdetail(map);
//			System.err.println("result: "+result);
//			session.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return result;
//	}
//	/**
//	 * 
//	 * 描述：
//	 * 2012-12-24 下午11:31:40 by ygq
//	 * @version
//	 * @return
//	 * @throws Exception
//	 */
//	public static void testMybatisWaringDetail() throws Exception{
//		SqlSession session = null;
//		try {
//			session = getSession();
//			PersonMapper mapper = session.getMapper(PersonMapper.class); 
//			List<Map<String,Object>> list = mapper.testQuery();
//			for(int i=0;i<list.size();i++){
//				Map<String,Object> map = list.get(i);
//				System.out.println(map.get("ID"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//	}
//	public static void main(String[] args)  throws Exception{
//		//testMybiats();
//		testMybatisWaringDetail();
//	}
//}
