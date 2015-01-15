package com.creditease.eas.warn.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.creditease.eas.util.StringUtil;
/**
 * 将所有的人员的信息合并(这是最新的一个版本的尝试
 * 	由于数据量过大，所以准备使用旧版本进行重新尝试查下
 * )
 * @QualifyingMatureMergeQuery.java
 * created at 2013-3-30 下午08:03:50 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class QualifyingMatureMergeQuery {
	/**
	 * 描述：添加直接下属
	 * 2013-3-30 下午09:39:09 by ygq
	 * @version
	 * @param mpAllPersonInfos
	 * @throws Exception
	 */
	public static void addImmInfo(Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos,List<String> numlist) throws Exception{
		
		Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryImmQualifyPersonsInfo(numlist);//查询间接上级的信息
		//查询直接上级下的人员的信息
		Map<String,List<Map<String,Object>>> mpImmPersons = QualifyingMatureQuery.queryImmediatePersonInfoInfo(numlist);//查询直接下属的信息
		//有的人没有直接上级的信息，这样的该怎么办呢？这样的信息就不往里面存
//		System.out.println("mpImm:::::::" + mp);
//		System.out.println("mpImmPersons:::::::" + mpImmPersons);
		if(mp != null && mp.size()>0 && mpImmPersons != null && mpImmPersons.size()>0){
			for(Entry<String, List<Map<String, Object>>> entry : mp.entrySet()){
//				System.out.println("key::::" + entry.getKey());//对应的是职位的id
				List<Map<String,Object>> mpPersons = entry.getValue();//直接上级:一般情况下只有一个直接上级
				if(null != mpPersons && mpPersons.size()>0){
					for(int i=0;i<mpPersons.size();i++){
						Map<String,Object> mpUp = mpPersons.get(i);
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
								List<Map<String,Object>> listImmDownTemp  = mpImmPersons.get(entry.getKey());
								mapTemp.put("immDownPerson",listImmDownTemp);//存取直接上级
								//将查询出来的信息存放到大map中
								mpAllPersonInfos.put(personID, mapTemp);//存放了直接上级的信息
							}else{//已经存在相应的人员的信息了，可以直接添加直接下属的信息
								//因为根据是否是主职过滤过，所以不存在重复的下级
								Map<String,List<Map<String,Object>>> mapTemp = mpAllPersonInfos.get(personID);
								List<Map<String,Object>> listImmDownTemp = mapTemp.get("immDownPerson");//获取直接下属的信息
								//查找直接下级的信息:这个是需要过滤掉重复的人员的信息
								List<Map<String,Object>> listInfo = mpImmPersons.get(entry.getKey());
								for(int j=0;j<listInfo.size();j++){
									Map<String,Object> map = listInfo.get(j);
									boolean va = false;
									for(int k=0;k<listImmDownTemp.size();k++){
										if(map.get("PERSONID").equals(listImmDownTemp.get(k).get("PERSONID"))){//已经重复的人员就不保存了
											va = true;
										}
									}
									//如果没有意见存在的，就将人员信息加上
									if(!va){
										listImmDownTemp.add(map);
									}
								}
								mapTemp.put("immDownPerson",listImmDownTemp);//存取直接上级
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
	 * 描述：添加间接下属(需要判断人员的信息是否已经存在了)
	 * 2013-3-30 下午09:39:09 by ygq
	 * @version
	 * @param mpAllPersonInfos
	 * @throws Exception
	 */
	public static void addIndirectInfo(Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos,List<String> numlist) throws Exception{
		Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryIndirectQualifyPersonsInfo(numlist);//查询间接上级的信息
		//查询直接上级下的人员的信息
		Map<String,List<Map<String,Object>>> mpIndirectPersons = QualifyingMatureQuery.queryIndirectPersonInfoByParentPositionId(numlist);//查询间接下属的信息
//		System.out.println(mpIndirectPersons);
		//有的人没有直接上级的信息，这样的该怎么办呢？这样的信息就不往里面存
//		System.out.println("遍历间接上级有关的信息开始");
//		System.out.println("in:::::::" + mp);
//		System.out.println("mpIndirectPersons:::::::" + mpIndirectPersons);
		if(mp != null && mpIndirectPersons != null){
			for(Entry<String, List<Map<String, Object>>> entry : mp.entrySet()){
				List<Map<String,Object>> mpPersons = entry.getValue();//直接上级:一般情况下只有一个直接上级
//				System.out.println("value\t" + mpPersons);
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
	 * 描述：合并所有的直接上级和间接上级的信息
	 * 2013-3-30 下午08:04:33 by ygq
	 * @version
	 */
	public static Map<String,Map<String,List<Map<String,Object>>>> mergeAllPersonInfo(List<String> numlist) throws Exception{
		//用来存取所有的信息 1. 所有的直接上级 的人员的信息，所有的下属的信息。
		//依据：1:人员的id
		Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos = new HashMap<String,Map<String,List<Map<String,Object>>>>();
//		System.out.println("直接上级-----");
		addImmInfo(mpAllPersonInfos,numlist);
		//间接上级
//		System.out.println("间接上级-----");
		addIndirectInfo(mpAllPersonInfos,numlist);
		return mpAllPersonInfos;
	}
	/**
	 * 描述：遍历大map
	 * 2013-3-30 下午08:39:57 by ygq
	 * @version
	 * @param mpAllPersonInfos
	 */
	public static void iteratorBigMap(Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos){
		for(Entry<String,Map<String,List<Map<String,Object>>>> mpp: mpAllPersonInfos.entrySet()){
			Map<String,List<Map<String,Object>>> mpPerson = mpp.getValue();
			List<Map<String,Object>> listUpTemp = mpPerson.get("PERSONINFO");
			System.out.println("personInfo" + listUpTemp);
//			System.out.println("immDownPerson\t" + mpPerson.get("immDownPerson"));
			List<Map<String,Object>> indirDownPerson = mpPerson.get("indirDownPerson");
			System.out.println("indirDownPerson\t" + indirDownPerson);
		}
	}
	public static void main(String[] args) throws Exception{
//		long start = System.currentTimeMillis();
//		System.out.println("begin");
//		Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos =  mergeAllPersonInfo();
//		iteratorBigMap(mpAllPersonInfos);
//		System.out.println("end");
//		long end = System.currentTimeMillis();
//		System.out.println("end-start\t" + (end-start));
	}
}
