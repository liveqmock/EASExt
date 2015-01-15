//package com.creditease.eas.warn.kingdee.query;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.apache.ibatis.session.SqlSession;
//
//import com.creditease.eas.util.BaseMyBatisDao;
//import com.creditease.eas.warn.kingdee.dao.PersonMapper;
//
//public class QualifyingMatureQueryV2 extends BaseMyBatisDao{
//	/**
//	 * 
//	 * 描述：查询将要转正的人员的信息：
//	 *还需要查询的是人员 的直接负责人，间接负责人，部门负责人的相关的信息
//	 * 2012-12-24 下午11:31:40 by ygq
//	 * @version
//	 * @return
//	 * @throws Exception
//	 */
//	public static void testQualifyingMatureQuery() throws Exception{
//		SqlSession session = null;
//		try {
//			session = getSession();
//			PersonMapper mapper = session.getMapper(PersonMapper.class); 
//			List<Map<String,Object>> list = mapper.selectPersonInfo();
//			for(int i=0;i<list.size();i++){
//				Map<String,Object> map = list.get(i);
//				Object fname = map.get("FNAME");
//				Object positionname = map.get("POSITIONNAME"); 
//				Object formaldate = map.get("FORMALDATE"); 
//				Object fpersonnum = map.get("FPERSONNUM"); 
//				System.out.println(fname + "\t" + positionname  + "\t" +  formaldate  + "\t" +  fpersonnum);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//	}
//	/**
//	 * 
//	 * 描述：
//	 * 2012-12-26 下午11:04:58 by ygq
//	 * @version
//	 * @throws Exception
//	 */
////	public static void testQualifyPosition() throws Exception{
////		SqlSession session = null;
////		try {
////			session = getSession();
////			PersonMapper mapper = session.getMapper(PersonMapper.class); 
////			List<Map<String,Object>> list = mapper.selectPositions();
////			//System.out.println(list);
////			for(int i=0;i<list.size();i++){
////				Map<String,Object> map = list.get(i);
////				Object fname = map.get("POSITION");
////				System.out.println("fname:::::::::" +  fname);
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}finally{
////			closeSession(session);
////		}
////	}
//	/**
//	 * 统计有人和没人的时候的人员信息情况
//	 */
//	public static void testQualifyPositionTongji() throws Exception{
//		SqlSession session = null;
//		try {
//			session = getSession();
//			PersonMapper mapper = session.getMapper(PersonMapper.class); 
//			//需要转正的人的职位信息
//			List<Map<String,String>> list = mapper.selectPositionsAll();
//			//将信息放入到List中
//			List<String> mapkey = new ArrayList<String>();
//			for(int i=0;i<list.size();i++){
//				Map<String,String> mp = list.get(i);
//				for(Entry<String,String> it : mp.entrySet()){
//					mapkey.add(it.getValue());
//				}
//			}
//			/***************************遍历mapKey,存取部门领导的相关的信息：肯定要用另外一种方式存信息*******************************/
//			Map<String,List<Map<String,Object>>> map2 = new HashMap<String,List<Map<String,Object>>>();
//			for(int i=0;i<mapkey.size();i++){
//				String keyString = mapkey.get(i);
//				List<Map<String,Object>> listHighLevelPerson = mapper.selectHighLeverPersonByPositionIdAll(keyString);
//				map2.put(keyString, listHighLevelPerson);
//			}
//			/*******************************统计多个人的情况****************************************/
//			System.out.println("********************遍历上级部门人员信息*****************************");
//			//遍历Map2
//			for(Entry<String,List<Map<String,Object>>> mapit : map2.entrySet()){
//				String mapKey = mapit.getKey();
//				List<Map<String,Object>> mm = mapit.getValue();
//				  if(mm != null && mm.size()>1){
//						Map<String,Object> m = mm.get(0);
//						if(m != null){
//							System.out.print(mm.get(0).get("POSITIONNAME") + "\t");
//							for(int i=0;i<mm.size();i++){
//								Map<String,Object> mp =  mm.get(i);
//								System.out.print(mp.get("PERSONNAME") + "\t");
//							}
//						}
//						System.out.println();
//				  }
//		   }
//			System.out.println("-------------------------------");
//			/*******************************统计没有人的情况****************************************/
//			//遍历Map2
//			for(Entry<String,List<Map<String,Object>>> mapit : map2.entrySet()){
//				String mapKey = mapit.getKey();
//				//如果人员为空
//				List<Map<String,Object>> mm = mapit.getValue();
//				if(mm==null || mm.size()==0){
//					String positionName = mapper.queryPositionNameById(mapKey);
//					System.out.println(positionName);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//	}
//	public static void main(String[] args) throws Exception{
//		testQualifyPositionTongji();
//	}
//}
