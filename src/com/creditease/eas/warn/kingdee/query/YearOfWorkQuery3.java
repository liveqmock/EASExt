/**
 * 
 */
package com.creditease.eas.warn.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.sql.TIMESTAMP;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.DateUtil;
import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;
import com.creditease.eas.warn.vo.PersonData2;

/**
 * 司龄查询相关信息
 * @YearOfWorkQuery.java
 * created at 2013-1-8 下午04:05:35 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class YearOfWorkQuery3 extends BaseMyBatisDao{

	public static Map<String, Set<PersonData2>> getAllByYear2(List<String> numlist){
		Map<String, Set<PersonData2>> map = new HashMap<String, Set<PersonData2>>();
//		Map<String,Map<String, Set<Map<String,Object>>>> map2 = new HashMap<String,Map<String, Set<Map<String,Object>>>>();
		SqlSession session = null;
		List<Map<String,Object>> list = null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			//司龄满整年度人员（即司龄满1年、2年及以上人员）
			list = mapper.getWorkPerson(numlist);
			for (int a = 0; a < list.size(); a++) {
//				Set<Map<String,Object>> personlist = new HashSet<Map<String,Object>>();
				
				Map<String,Object> obj = list.get(a);	//人员信息
				
				String positionid = obj.get("POSITIONID").toString().trim();
				List<Map<String,Object>> highlist = mapper.selectPersonInfoByPositionId(positionid); //直接上级
				String poid2 = null;
				if(highlist!=null && highlist.size()>0){
					Set<PersonData2> personlist = new HashSet<PersonData2>();
					PersonData2 pd = new PersonData2();
					pd.setPid(obj.get("PERSONNUMBER").toString());
					pd.setName(obj.get("NAME").toString());
					pd.setPosition(obj.get("POSITION").toString());
					pd.setPosiqual((obj.get("POSIQUAL")==null)?"":obj.get("POSIQUAL").toString());
					pd.setPstatus((obj.get("PSTATUS")==null)?"":obj.get("PSTATUS").toString());
					pd.setEnterdate(DateUtil.formatTimestampToString((TIMESTAMP)obj.get("ENTERDATE")));
					pd.setYears(obj.get("YEARS").toString());
					pd.setTmp("zj");
					pd.setDisname(obj.get("DISNAME").toString());
					personlist.add(pd);
//					personlist.add(obj);
					for (int b = 0; b < highlist.size(); b++) {
						Map<String,Object> obj2 = highlist.get(b);
//						String fid2 = obj2.get("FID").toString();
						String fid2 = obj2.get("PERSONNUMBER").toString();
						poid2 = obj2.get("POSITIONID").toString();   //直接上级职位ID
						if(map.containsKey(fid2)){
							map.get(fid2).add(pd);
						}else{
							map.put(fid2, personlist);
						}
					}
					
					List<Map<String,Object>> levellist = mapper.selectPersonInfoByPositionId(poid2); //间接上级
					if(levellist!=null && levellist.size()>0){
						Set<PersonData2> personlist2 = new HashSet<PersonData2>();
						PersonData2 pd2 = new PersonData2();
						pd2.setPid(obj.get("PERSONNUMBER").toString());
						pd2.setName(obj.get("NAME").toString());
						pd2.setPosition(obj.get("POSITION").toString());
						pd2.setPosiqual((obj.get("POSIQUAL")==null)?"":obj.get("POSIQUAL").toString());
						pd2.setPstatus((obj.get("PSTATUS")==null)?"":obj.get("PSTATUS").toString());
						pd2.setEnterdate(DateUtil.formatTimestampToString((TIMESTAMP)obj.get("ENTERDATE")));
						pd2.setYears(obj.get("YEARS").toString());
						pd2.setTmp("jj");
						pd2.setDisname(obj.get("DISNAME").toString());
						
						personlist2.add(pd2);
//						System.out.println("间接上级");
						for (int c = 0; c < levellist.size(); c++) {
							Map<String,Object> obj3 = levellist.get(c);
//							String fid3 = obj3.get("FID").toString();
							String fid3 = obj3.get("PERSONNUMBER").toString();
							if (map.containsKey(fid3)) {
								map.get(fid3).add(pd2);
							}else{
								map.put(fid3, personlist2);
							}
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return map;
	}
	
	public static List<Map<String,Object>> getAllByYear(){
		SqlSession session = null;
		List<Map<String,Object>> list=null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			//司龄满整年度人员（即司龄满1年、2年及以上人员）
			list = mapper.getYearOfWork();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	public static Map<String, List<Map<String,Object>>> test(Map<String,Object> obj){
		Map<String, List<Map<String,Object>>> map = new HashMap<String, List<Map<String,Object>>>();
		SqlSession session = null;
		List<Map<String,Object>> personlist = new ArrayList<Map<String,Object>>();
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			obj.put("type", 1);			//0表示发送给本人信息
			personlist.add(obj);
			String positionid = obj.get("POSITIONID").toString();
			List<Map<String,Object>> highlist = mapper.selectPersonInfoByPositionId(positionid); //直接上级
			if(highlist!=null && highlist.size()>0){
				obj.put("type2", 2);			//1表示发送给直接上级
				for (int i = 0; i < highlist.size(); i++) {
					Map<String,Object> obj2 = highlist.get(i);
					String fid = obj2.get("FID").toString();
					if(map.containsKey(fid)){
						map.get(fid).add(obj);
					}else{
						map.put(fid, personlist);
					}
					List<Map<String,Object>> levellist = mapper.selectPersonInfoByPositionId(obj2.get("POSITIONID").toString()); //间接上级
					if(levellist!=null && levellist.size()>0){
						obj.put("type3", 3);			//3表示发送给间接上级
						for (int j = 0; j < levellist.size(); j++) {
							Map<String,Object> obj3 = levellist.get(j);
							String fid3 = obj3.get("FID").toString();
							if (map.containsKey(fid3)) {
								map.get(fid3).add(obj);
							}else{
								map.put(fid3, personlist);
							}
						}
					}
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return map;
	}
	/**
	 * 
	 * 描述：根据id获取上级职位
	 * 2013-4-1 下午08:27:59 by xjw
	 * @version
	 * @param positionid
	 * @return
	 */
	public static List<Map<String,Object>> getPersonInfoByPositionId(String positionid){
		SqlSession session = null;
		List<Map<String,Object>> list=null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			list = mapper.selectPersonInfoByPositionId(positionid);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	public static List<Map<String,Object>> getYearPerson(List<String> numlist){
		SqlSession session = null;
		List<Map<String,Object>> list=null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			//司龄满整年度人员（即司龄满1年、2年及以上人员）
			list = mapper.getWorkPerson(numlist);
//			System.out.println("size: "+list.size());
//			 if(list!=null && list.size()>0){
//				 for (int i = 0; i < list.size(); i++) {
//					 Map<String,Object> tmp = list.get(i);
//					 String positionid = tmp.get("POSITIONID").toString().trim();
//					 Map<String,Object> org = mapper.selectResponsePersonInfo(positionid);
//					 if(org!=null){
//						 System.out.println(org.get("PNAME").toString());;
//					 }
//				 } 
//			 }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	public static  Map<String,Object> getDeptPerson(String positionid){
		SqlSession session = null;
		Map<String,Object> org = null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			org = mapper.selectResponsePersonInfo(positionid);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return org;
	}
	
	public static  String getPersonByFid(String fid){
		SqlSession session = null;
//		Map<String,Object> org = null;
		String tmp = null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			Map<String,Object> org = mapper.getPersonByFid(fid);
			System.out.println(org.get("NAME").toString());
			tmp = org.get("POSITIONID").toString();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return tmp;
	} 
	public static void main(String[] args) throws Exception {
//		getAllByYear2();
//		getYearPerson();
//		getPersonByFid("rlgAAAAAOrWA733t");
		List<String> list = null;
		getAllByYear2(list);
	}
}
