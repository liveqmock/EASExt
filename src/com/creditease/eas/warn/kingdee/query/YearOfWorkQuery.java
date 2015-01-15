///**
// * 
// */
//package com.creditease.eas.warn.kingdee.query;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.apache.ibatis.session.SqlSession;
//
//import com.creditease.eas.util.BaseMyBatisDao;
//import com.creditease.eas.util.StringUtil;
//import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;
//import com.creditease.eas.warn.vo.QueryData;
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
//public class YearOfWorkQuery extends BaseMyBatisDao{
//
//	/**
//	 * 
//	 * 描述：经理以下人员信息
//	 * 2013-1-15 下午05:38:10 by xjw
//	 * @version
//	 * @return
//	 */
//	public static List<Map<String,Object>> getAllByYear(){
//		SqlSession session = null;
//		List<Map<String,Object>> list=null;
//		try {
//			session = getSession();
//			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//			//司龄满整年度人员（即司龄满1年、2年及以上人员）
//			list = mapper.getYearOfWork();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
//	
//	public static List<Map<String,Object>> getAllByYear3(){
//		SqlSession session = null;
//		List<Map<String,Object>> list=null;
//		try {
//			session = getSession();
//			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//			//司龄满整年度人员（即司龄满1年、2年及以上人员）
//			list = mapper.getYearOfWork();
//			Map<String, String> tmp = new HashMap<String, String>();
//			for(int i=0;i<list.size();i++){
//				Map<String, Object> map = list.get(i);
//				tmp = getPersonInfoById(map.get("POSITIONID").toString(),tmp);
//			}
//			Iterator it = tmp.entrySet().iterator();
//			while (it.hasNext()) {
//	           Entry entry = (Entry) it.next();
//	           // entry.getKey() 返回与此项对应的键
//	           // entry.getValue() 返回与此项对应的值
//	           System.out.println(entry.getKey()+" : "+entry.getValue());
//			}
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
//	public static QueryData getPersonsById(String strid) throws Exception{
//		QueryData datass= new QueryData();
//		String namestrs = null;
//		String emailstrs = null;
//		SqlSession session = null;
//		session = getSession();
//		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//		List<Map<String,Object>> highlist = mapper.selectPersonInfoByPositionId(strid);
//		if(highlist!=null && highlist.size()>0){
//			for (int a = 0; a < highlist.size(); a++) {
//				Map<String,Object> highmap = highlist.get(a);
//				Object cfmail = highmap.get("CFEMAIL")==null?null:highmap.get("CFEMAIL");//企业邮箱
//				if(null!=cfmail){
//					namestrs = StringUtil.objToString( highmap.get("PERSONNAME") )+",";
//					emailstrs = cfmail + ",";
//					List<Map<String,Object>> levellist = mapper.selectPersonInfoByPositionId(highmap.get("POSITIONID").toString());
//					if(levellist!=null && levellist.size()>0){
//						for (int i = 0; i < levellist.size(); i++) {
//							Map<String,Object> levelmap = levellist.get(i);
//							Object cfmail2 = levelmap.get("CFEMAIL")==null?null:levelmap.get("CFEMAIL");//企业邮箱
//							if(null!= cfmail2){
//								namestrs += StringUtil.objToString( levelmap.get("PERSONNAME") ) ;
//								emailstrs += cfmail2 + ",";
//							}else{
//								cfmail2 = levelmap.get("EMAIL")==null?null:levelmap.get("EMAIL");//取个人邮箱
//								if(null != cfmail2){
//									namestrs += StringUtil.objToString( levelmap.get("PERSONNAME") ) ;
//									emailstrs += cfmail2 + ",";
//								}
//							}
//						}
//					}
//				}else{
//					cfmail = highmap.get("EMAIL")==null?null:highmap.get("EMAIL");//取个人邮箱
//					if(null != cfmail){
//						namestrs = StringUtil.objToString( highmap.get("PERSONNAME") )+",";
//						emailstrs = cfmail + ",";
//						List<Map<String,Object>> levellist = mapper.selectPersonInfoByPositionId(highmap.get("POSITIONID").toString());
//						if(levellist!=null && levellist.size()>0){
//							for (int i = 0; i < levellist.size(); i++) {
//								Map<String,Object> levelmap = levellist.get(i);
//								Object cfmail2 = levelmap.get("CFEMAIL")==null?null:levelmap.get("CFEMAIL");//企业邮箱
//								if(null!= cfmail2){
//									namestrs += StringUtil.objToString( levelmap.get("PERSONNAME") ) ;
//									emailstrs += cfmail2 + ",";
//								}else{
//									cfmail2 = levelmap.get("EMAIL")==null?null:levelmap.get("EMAIL");//取个人邮箱
//									if(null != cfmail2){
//										namestrs += StringUtil.objToString( levelmap.get("PERSONNAME") ) ;
//										emailstrs += cfmail2 + ",";
//									}
//								}
//							}
//						}
//						
//					}
//				}
//			}
//		}
//		System.err.println("用户："+namestrs+" 邮箱："+emailstrs);
//		if(null!=namestrs){
//			String[] names=namestrs.split(",");
//			datass.setNamess(names);	
//		}
//		if(null!=emailstrs){
//			String[] emails=emailstrs.split(",");
//			datass.setEmailss(emails);	
//		}
//		
//		return datass;
//		
//	}
//	
//	
//	public static Map<String, String> getPersonInfoById(String strid,Map<String, String> map) throws Exception{
//		SqlSession session = null;
//		session = getSession();
//		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//		List<Map<String,Object>> highlist = mapper.selectPersonInfoByPositionId(strid);
//		if(highlist!=null && highlist.size()>0){
//			for (int a = 0; a < highlist.size(); a++) {
//				Map<String,Object> highmap = highlist.get(a);
//				Object cfmail = highmap.get("CFEMAIL")==null?null:highmap.get("CFEMAIL");//企业邮箱
//				if(null!=cfmail){
//					String highid = StringUtil.objToString( highmap.get("FID") );
//					map.put(highid, strid);
//					List<Map<String,Object>> levellist = mapper.selectPersonInfoByPositionId(highmap.get("POSITIONID").toString());
//					if(highlist!=null && highlist.size()>0){
//						for (int i = 0; i < levellist.size(); i++) {
//							Map<String,Object> levelmap = levellist.get(i);
//							String levelid = StringUtil.objToString( levelmap.get("FID") );
//							map.put(levelid, strid);
//						}
//					}
//				}
//			}
//		}
//		return map;
//	}
//	
//	/**
//	 * 
//	 * 描述：经理以上级别
//	 * 2013-1-15 下午05:34:39 by xjw
//	 * @version
//	 * @return
//	 */
//	public static List<Map<String,Object>> getAllByYear2(){
//		SqlSession session = null;
//		List<Map<String,Object>> list=null;
//		try {
//			session = getSession();
//			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//			 list = mapper.getYearOfWork2();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
//	/**
//	 * 
//	 * 描述：查询部门负责人信息
//	 * 2013-1-15 下午06:04:20 by xjw
//	 * @version
//	 * @param strid
//	 * @return
//	 * @throws Exception
//	 */
//	public  static Map<String, Object> getDepartLeader(String strid)throws Exception{
//		SqlSession session = null;
//		session = getSession();
//		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//		 Map<String, Object> hrmap = mapper.selectResponsePersonInfo(strid);
//		return hrmap;
//	}
//	/**
//	 * 
//	 * 描述：查询CEO相关信息
//	 * 2013-1-15 下午06:07:06 by xjw
//	 * @version
//	 * @return
//	 * @throws Exception
//	 */
//	public static Map<String, Object> getCeo() throws Exception{
//		SqlSession session = null;
//		session = getSession();
//		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//		Map<String, Object> ceomap = mapper.getCeo();
//		return ceomap;
//	}
//	/**
//	 *  
//	 * 描述：查询人力资源与行政部总监相关信息
//	 * 2013-1-15 下午06:07:43 by xjw
//	 * @version
//	 * @return
//	 * @throws Exception
//	 */
//	public static Map<String, Object> getHr() throws Exception{
//		SqlSession session = null;
//		session = getSession();
//		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//		Map<String, Object> hrmap = mapper.getHr();
//		//System.err.println(hrmap.get("NAME").toString());
//		return hrmap;
//	}
//	public static void main(String[] args) throws Exception {
//		getAllByYear3();
//		//Map<String,List<Map<String,Object>>> map = queryQualifyPersonsInfo();
//		
////		getHr();
////		getPersonsById("ArWjtfJ5QWiUAgr/JOOdZXSuYS4=");
//		//getEmailById("ArWjtfJ5QWiUAgr/JOOdZXSuYS4=");
//	}
//}
