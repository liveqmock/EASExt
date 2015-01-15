///**
// * 
// */
//package com.creditease.eas.warn.kingdee.query;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.session.SqlSession;
//
//import com.creditease.eas.util.BaseMyBatisDao;
//import com.creditease.eas.util.StringUtil;
//import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;
//
///**
// * 司龄查询相关信息
// * @YearOfWorkQuery.java
// * created at 2013-1-8 下午04:05:35 by xjw
// * 
// * @author xjw({@link authorEmail})
// * @version $Revision$</br>
// * update: $Date$
// */
//public class YearOfWorkQuery2 extends BaseMyBatisDao{
//
//	/**
//	 * 
//	 * 描述：经理以下人员信息
//	 * 2013-1-15 下午05:38:10 by xjw
//	 * @version
//	 * @return
//	 */
//
//
//	public static List<Map<String,Object>> getAllByYear(){
//		SqlSession session = null;
//		List<Map<String,Object>> list=null;
//		String strs = "";
//		List<String> ss = new ArrayList<String>();
//		try {
//			session = getSession();
//			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//			//司龄满整年度人员（即司龄满1年、2年及以上人员）
//			list = mapper.getYearOfWork();
////			Map<String, String> tmp = new HashMap<String, String>();
//			Map<String, List<String>> tmp = new HashMap<String, List<String>>();
//			for(int i=0;i<list.size();i++){
//				Map<String, Object> map = list.get(i);
//				String fid = map.get("FID").toString();
////				tmp = getPersonInfoById(map.get("POSITIONID").toString(),tmp,fid);
//				strs = getPersonInfoById(map.get("POSITIONID").toString());
//				if(!tmp.containsKey(strs)){
//					ss.add(fid);
//					tmp.put(strs, ss);
//				}else{
//					ss.add(fid);
//					tmp.put(strs, ss);
//					System.out.println("ccccccccccccc");
//				}
//			}
//			
////			Iterator it = tmp.entrySet().iterator();
////			while (it.hasNext()) {
////	           Entry entry = (Entry) it.next();
////	           // entry.getKey() 返回与此项对应的键
////	           // entry.getValue() 返回与此项对应的值
////	           System.out.println(entry.getKey()+" : "+entry.getValue());
////			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
//	/**
//	 * 
//	 * 描述：直接上级: 姓名和邮箱、隔级上级：姓名和邮箱
//	 * 2013-1-23 下午04:31:27 by xjw
//	 * @version
//	 * @param strid
//	 * @return
//	 * @throws Exception
//	 */
//
//	
//	public static String getPersonInfoById(String postid) throws Exception{
//		SqlSession session = null;
//		session = getSession();
//		String highid = "";
//		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//		List<Map<String,Object>> highlist = mapper.selectPersonInfoByPositionId(postid);
//		if(highlist!=null && highlist.size()>0){
//			for (int a = 0; a < highlist.size(); a++) {
//				System.out.println("highlist: "+highlist.size());
//				Map<String,Object> highmap = highlist.get(a);
//				Object cfmail = highmap.get("CFEMAIL")==null?null:highmap.get("CFEMAIL");//企业邮箱
//				if(null!=cfmail){
//					highid = StringUtil.objToString( highmap.get("FID") );
//				}
//			}
//		}
//		return highid;
//	}
//	
//
//
////	
////	public static Map<String, String> getPersonInfoById(String postid,Map<String, String> map,String fid) throws Exception{
////		SqlSession session = null;
////		session = getSession();
////		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
////		List<Map<String,Object>> highlist = mapper.selectPersonInfoByPositionId(postid);
////		if(highlist!=null && highlist.size()>0){
////			for (int a = 0; a < highlist.size(); a++) {
////				System.out.println("highlist: "+highlist.size());
////				Map<String,Object> highmap = highlist.get(a);
////				Object cfmail = highmap.get("CFEMAIL")==null?null:highmap.get("CFEMAIL");//企业邮箱
////				if(null!=cfmail){
////					String highid = StringUtil.objToString( highmap.get("FID") );
//////					if(!map.containsKey(highid)){
//////						map.put(highid, fid);
//////					}else{
//////						map.put(highid, fid);
//////						System.out.println("收到多条信息1:"+highid+" : "+fid);
//////					}
////					map.put(highid, fid);
////					List<Map<String,Object>> levellist = mapper.selectPersonInfoByPositionId(highmap.get("POSITIONID").toString());
////					if(highlist!=null && highlist.size()>0){
////						for (int i = 0; i < levellist.size(); i++) {
////							Map<String,Object> levelmap = levellist.get(i);
////							String levelid = StringUtil.objToString( levelmap.get("FID") );
////							map.put(levelid, fid);
////						}
////					}
////				}
////			}
////		}
////		return map;
////	}
////	
////	public static Map<String, Integer> getPersonInfoById2(String orgid,Map<String, Integer> map,String fid) throws Exception{
////		SqlSession session = null;
////		session = getSession();
////		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
////		List<Map<String,Object>> highlist = mapper.selectPersonInfoByPositionId(orgid);
////		if(highlist!=null && highlist.size()>0){
////			for (int a = 0; a < highlist.size(); a++) {
////				Map<String,Object> highmap = highlist.get(a);
////				Object cfmail = highmap.get("CFEMAIL")==null?null:highmap.get("CFEMAIL");//企业邮箱
////				if(null!=cfmail){
////					String highid = StringUtil.objToString( highmap.get("FID") );
////					map.put(highid, 1);
////					if(!map.containsKey(highid)){
////						map.put(highid, fid);
////					}else{
////						map.put(highid, fid);
////						System.out.println("收到多条信息:"+highid+" : "+fid);
////					}
////					List<Map<String,Object>> levellist = mapper.selectPersonInfoByPositionId(highmap.get("POSITIONID").toString());
////					if(highlist!=null && highlist.size()>0){
////						for (int i = 0; i < levellist.size(); i++) {
////							Map<String,Object> levelmap = levellist.get(i);
////							String levelid = StringUtil.objToString( levelmap.get("FID") );
////							if(!map.containsKey(levelid)){
////								map.put(levelid, fid);
////							}else{
////								map.put(levelid, fid);
////								System.out.println("收到多条信息:"+highid+" : "+fid);
////							}
////						}
////					}
////				}
////			}
////		}
////		return map;
////	}
//	
//	public static void main(String[] args) throws Exception {
//		getAllByYear();
//	}
//}
