package com.creditease.eas.warn.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.warn.kingdee.dao.PersonMapper;
/**
 * 为实现HR生日预警所需数据访问对象类
 * @author lining
 * @since 2014-5-26
 */
public class BirthdayQuery extends BaseMyBatisDao{
	/**
	 * 合并所有的直接上级对应生日下属集合和间接上级对应生日下属集合的信息
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Map<String,List<Map<String,Object>>>> mergeAllPersonInfo(List<String> numlist) throws Exception{
		//用来存取所有的（直接/间接）上级 与其对应下属的人员的信息
		Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos = new HashMap<String,Map<String,List<Map<String,Object>>>>();
		//向集合中添加直接上级及其对应下属的数据
		addImmInfo(mpAllPersonInfos,numlist);
		//向集合中添加间接接上级及其对应下属的数据
		addIndirectInfo(mpAllPersonInfos,numlist);
		return mpAllPersonInfos;
	}
	/**
	 * 添加直接上级及其对应下属
	 * @author lining
	 * @since 2014-5-26
	 * @param mpAllPersonInfos
	 * @param numlist
	 * @throws Exception
	 */
	public static void addImmInfo(Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos,List<String> numlist) throws Exception{
		
		//查询直接接上级的信息
		Map<String,List<Map<String,Object>>> mp = queryImmQualifyPersonsInfo(numlist);
		//查询直接上级对应下属的信息
		Map<String,List<Map<String,Object>>> mpImmPersons = queryImmediatePersonInfoInfo(numlist);
		if( null != mp && mp.size()>0 && mpImmPersons != null && mpImmPersons.size()>0){
			for(Entry<String, List<Map<String, Object>>> entry : mp.entrySet()){
				//对应的是职位的id
				List<Map<String,Object>> mpPersons = entry.getValue();
				if(null != mpPersons && mpPersons.size()>0){
					for(int i=0;i<mpPersons.size();i++){
						Map<String,Object> mpUp = mpPersons.get(i);
						String personID = StringUtil.objToString2(mpUp.get("PERSONID"));
						if(null != personID){
							//验证是否已经存在该上级信息
							Object validKey = mpAllPersonInfos.get(personID);
							//如果还没存在，则直接存该上级的信息然后对应存储其下属信息
							if(null == validKey){
								//存储直接上级人员ID及其对应的数据（包含上级本人信息及其对应下属信息列表）
								Map<String,List<Map<String,Object>>> mapTemp = new HashMap<String,List<Map<String,Object>>>();
								//存储直接上级数据
								List<Map<String,Object>> listUpTemp = new ArrayList<Map<String,Object>>();
								listUpTemp.add(mpUp);
								mapTemp.put("PERSONINFO",listUpTemp);
								//查找直接下属的信息
								List<Map<String,Object>> listImmDownTemp  = mpImmPersons.get(entry.getKey());
								mapTemp.put("immDownPerson",listImmDownTemp);
								//将查询出来的信息存放到大map中
								mpAllPersonInfos.put(personID, mapTemp);
							//已经存在相应上级人员的信息，可以直接添加其直接下属的信息
							}else{
								//因为根据是否是主职过滤过，所以不存在重复的下级
								Map<String,List<Map<String,Object>>> mapTemp = mpAllPersonInfos.get(personID);
								List<Map<String,Object>> listImmDownTemp = mapTemp.get("immDownPerson");//获取直接下属的信息
								//查找直接下级的信息:这个是需要过滤掉重复的人员的信息
								List<Map<String,Object>> listInfo = mpImmPersons.get(entry.getKey());
								for(int j=0;j<listInfo.size();j++){
									Map<String,Object> map = listInfo.get(j);
									boolean va = false;
									for(int k=0;k<listImmDownTemp.size();k++){
										//已经重复的人员就不保存
										if(map.get("PERSONID").equals(listImmDownTemp.get(k).get("PERSONID"))){
											va = true;
										}
									}
									//如果没有意见存在的，就将人员信息加上
									if(!va){
										listImmDownTemp.add(map);
									}
								}
								//存储直接下属信息
								mapTemp.put("immDownPerson",listImmDownTemp);
								//将查询出来的信息存放到大map中
								mpAllPersonInfos.put(personID, mapTemp);//存放了直接上级的信息
							}
						}
					}
				}
			}
		}
		System.out.println("遍历直接上级有关的信息结束");
	}
	/**
	 * 添加间接接上级及其对应下属(需要判断人员的信息是否已经存在了)
	 * @author lining
	 * @since 2014-5-26
	 * @param mpAllPersonInfos
	 * @param numlist
	 * @throws Exception
	 */
	public static void addIndirectInfo(Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos,List<String> numlist) throws Exception{
		//查询间接上级的信息
		Map<String,List<Map<String,Object>>> mp = queryIndirectQualifyPersonsInfo(numlist);
		//查询间接下属的信息
		Map<String,List<Map<String,Object>>> mpIndirectPersons = queryIndirectPersonInfoByParentPositionId(numlist);
		if(mp != null && mpIndirectPersons != null){
			for(Entry<String, List<Map<String, Object>>> entry : mp.entrySet()){
				List<Map<String,Object>> mpPersons = entry.getValue();//直接上级:一般情况下只有一个直接上级
				if(null != mpPersons && mpPersons.isEmpty()==false){
					for(int i=0;i<mpPersons.size();i++){
						Map<String,Object> mpUp = mpPersons.get(i);//直接上级的信息
						String personID = StringUtil.objToString2(mpUp.get("PERSONID"));
						if(null != personID){
							Object validKey = mpAllPersonInfos.get(personID);//验证是否已经存在该人员信息了
							if(null == validKey){//如果还没存在，首次加载，则直接存放人员的信息
								//直接存到List<Map 中
								Map<String,List<Map<String,Object>>> mapTemp = new HashMap<String,List<Map<String,Object>>>();
								//存取直接上级
								List<Map<String,Object>> listUpTemp = new ArrayList<Map<String,Object>>();
								listUpTemp.add(mpUp);
								mapTemp.put("PERSONINFO",listUpTemp);//存取直接上级
								//查找直接下级的信息
								List<Map<String,Object>> listImmDownTemp  = mpIndirectPersons.get(entry.getKey());
								mapTemp.put("indirDownPerson",listImmDownTemp);//存取间接下级
								//将查询出来的信息存放到大map中
								mpAllPersonInfos.put(personID, mapTemp);//存放了直接上级的信息
							}else{//已经存在相应的人员的信息了，可以直接添加直接下属的信息
								//因为根据是否是主职过滤过，所以不存在重复的下级(还是会存在重复的下级人员的
								Map<String,List<Map<String,Object>>> mapTemp = mpAllPersonInfos.get(personID);
								List<Map<String,Object>> listImmDownTemp = mapTemp.get("indirDownPerson");//获取直接下属的信息
								if(listImmDownTemp == null){
									listImmDownTemp = new ArrayList<Map<String,Object>>();//可能刚开始只有直接下级的信息，还没有间接下级的信息
								}else{
									List<Map<String,Object>> listInfo = mpIndirectPersons.get(entry.getKey());
									for(int j=0;j<listInfo.size();j++){
										Map<String,Object> map = listInfo.get(j);
										boolean va = false;
										for(int k=0;k<listImmDownTemp.size();k++){
											if(map.get("PERSONID").equals(listImmDownTemp.get(k).get("PERSONID"))){//已经重复的人员就不保存了
												va = true;
											}
										}
										if(!va){
											listImmDownTemp.add(map);
										}
									}
								}
								//查找间接下级的信息
								mapTemp.put("indirDownPerson",listImmDownTemp);//存取间接下级
								//将查询出来的信息存放到大map中
								mpAllPersonInfos.put(personID, mapTemp);//存放了
							}
						}
					}
				}
			}
		}
		System.out.println("遍历间接上级有关的信息结束!");
	}
	/**
	 * 获取部门负责人对应的过生日的员工信息
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 */
	public static Map<String, List<Map<String, Object>>> queryUnderResponsePersonInfo(
			List<String> numlist) {
		List<Map<String,String>> listOrg = queryResponsePersonOrg(numlist);
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		Map<String,List<Map<String,Object>>> indirectPerson = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			if(listOrg != null && listOrg.size()>0){
//				System.out.println("部门负责人对应的生日员工的信息");
				for(int i=0;i<listOrg.size();i++){
					String keyString = listOrg.get(i).get("ORGNUMBER");
					String orgNumber = StringUtil.addLikeOperBoth(keyString);
					List<Map<String,Object>> listPersons = mapper.selectUnderResponsePersonInfoOnBirthdayWarn(orgNumber);
					System.out.println("listPersons\t" + listPersons.size());
					if(null != listPersons){
						indirectPerson.put(keyString, listPersons);
					}
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return indirectPerson;
	}
	/**
	 * 查询直接上级信息
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryImmQualifyPersonsInfo(List<String> numlist) throws Exception{
		List<Map<String,String>>  listMp = queryImmediateSuperiorPositionInfo(numlist);//查询直接上级
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		Map<String,List<Map<String,Object>>> mapImmediateSuperior = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			//查询直接上级的职位编码
			if(listMp != null && listMp.size()>0){
				//根据直接上级的职位的 编码，存取直接上级的人员的相关的信息
				for(int i=0;i<listMp.size();i++){
					String keyString = listMp.get(i).get("POSITION");//获得对应的职位key:这个职位信息，不会为空
					List<Map<String,Object>> listImmediateSuperiorPersons = mapper.selectSuperiorPerson(keyString);
					mapImmediateSuperior.put(keyString, listImmediateSuperiorPersons);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return mapImmediateSuperior;
	}
	/**
	 * 查询过生日的直接下属的信息
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryImmediatePersonInfoInfo(List<String> numlist) throws Exception{
		//获取直接上级的职位信息
		List<Map<String,String>> list = queryImmediateSuperiorPositionInfo(numlist);
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		//获取直接上级对应的过生日的下属信息
		Map<String,List<Map<String,Object>>> superiorPerson = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			if(list != null && list.size()>0){
				for(int i=0;i<list.size();i++){
					String keyString = list.get(i).get("POSITION");
					List<Map<String,Object>> listPersons = mapper.selectImmediatePersonInfoByParentPositionIdOnBirthdayWarn(keyString);
					superiorPerson.put(keyString, listPersons);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return superiorPerson;
	}
	/**
	 * 获取直接上级的职位信息
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 */
	public static List<Map<String,String>> queryImmediateSuperiorPositionInfo(List<String> numlist){
		List<Map<String,String>>  list = null;
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			//查询直接上级的职位编码
			 list = mapper.selectImmediateSuperiorPositions(numlist);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 查询直接间接上级的信息
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryIndirectQualifyPersonsInfo(List<String> numlist) throws Exception{
		List<Map<String,String>>  listMp = queryIndirectSuperiorPositionInfo(numlist);//查询间接上级的职位信息
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		//直接上级人员,直接上级下的人
		Map<String,List<Map<String,Object>>> mapImmediateSuperior = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			//查询直接上级的职位编码
			if(listMp != null && listMp.size()>0){
				//根据直接上级的职位的 编码，存取直接上级的人员的相关的信息
				for(int i=0;i<listMp.size();i++){
					String keyString = listMp.get(i).get("POSITION");//获得对应的职位key:这个职位信息，不会为空
					List<Map<String,Object>> listImmediateSuperiorPersons = mapper.selectSuperiorPerson(keyString);
					mapImmediateSuperior.put(keyString, listImmediateSuperiorPersons);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return mapImmediateSuperior;
	}
	/**
	 * 查询间接上级对应的过生日的下属的信息
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryIndirectPersonInfoByParentPositionId(List<String> numlist) throws Exception{
		//查询间接上级的职位信息
		List<Map<String,String>>  listIndirect = queryIndirectSuperiorPositionInfo(numlist);
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		//封装职位对应的过生日的间接下属
		Map<String,List<Map<String,Object>>> indirectPerson = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			if(listIndirect != null && listIndirect.size()>0){
				System.out.println("间接上级对应的人员的信息");
				//存取直接上级下的人员的相关信息
				for(int i=0;i<listIndirect.size();i++){
					String keyString = listIndirect.get(i).get("POSITION");//获得对应的职位key
					List<Map<String,Object>> listPersons = mapper.selectIndirectPersonInfoByParentPositionIdOnBirthdayWarn(keyString);
					indirectPerson.put(keyString, listPersons);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return indirectPerson;
	}
	/**
	 * 获取间接上级的职位信息
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 */
	public static List<Map<String,String>> queryIndirectSuperiorPositionInfo(List<String> numlist){
		List<Map<String,String>>  list = null;
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			//查询直接上级的职位编码
			 list = mapper.selectIndirectSuperiorPositions(numlist);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取部门负责人对应的部门编码
	 * @author lining
	 * @since 2014-5-26
	 * @param numlist
	 * @return
	 */
	public static List<Map<String,String>> queryResponsePersonOrg(List<String> numlist){
		List<Map<String,String>>  list = null;
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			//获取部门负责人的职位编码
			 list = mapper.selectResponsePersonOrg(numlist);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
}
