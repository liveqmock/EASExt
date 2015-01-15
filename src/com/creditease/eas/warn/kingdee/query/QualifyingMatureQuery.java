package com.creditease.eas.warn.kingdee.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.warn.kingdee.dao.PersonMapper;
/**
 * 需求变化后的第一个版本  v2
 * created at 2013-1-4 下午02:07:43 by ygq
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class QualifyingMatureQuery extends BaseMyBatisDao{
	//定义一个职位的缓存变量，减少对数据库的操作
//	public static List<Map<String,String>>  list = null;
//	public static List<Map<String,String>>  listIndirect = null;//间接
//	public static List<Map<String,String>>  listOrg = null;
	//静态块，初始化组织的职位的信息
//	static{
//		list = queryImmediateSuperiorPositionInfo();
//		if(list != null){//如果直接上级不为空，才初始化间接上级的信息
//			listIndirect = queryIndirectSuperiorPositionInfo();
//			listOrg = queryResponsePersonOrg();//部门负责人
//		}
//	}
	/**
	 * 描述：获取直接上级的职位信息
	 * 2013-3-20 下午12:40:01 by ygq
	 * @version
	 * @return
	 * @throws Exception
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
	 * 描述：获取间接上级的职位信息
	 * 2013-3-20 下午12:40:01 by ygq
	 * @version
	 * @return
	 * @throws Exception
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
	 * 描述：获取部门负责人对应的部门编码
	 * 2013-3-20 下午12:40:01 by ygq
	 * @version
	 * @return
	 * @throws Exception
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
	/**
	 * 
	 * 描述：查询直接上级信息
	 * 2013-1-4 下午04:27:47 by ygq
	 * @version
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryImmQualifyPersonsInfo(List<String> numlist) throws Exception{
		List<Map<String,String>>  listMp = queryImmediateSuperiorPositionInfo(numlist);//查询直接上级
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
	 * 
	 * 描述：查询直接下属的信息
	 * 2013-1-4 下午04:27:47 by ygq
	 * @version
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryImmediatePersonInfoInfo(List<String> numlist) throws Exception{
		List<Map<String,String>> list = queryImmediateSuperiorPositionInfo(numlist);
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		//直接上级人员,直接上级下的人
		Map<String,List<Map<String,Object>>> superiorPerson = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			if(list != null && list.size()>0){
				for(int i=0;i<list.size();i++){
					String keyString = list.get(i).get("POSITION");//获得对应的职位key
					List<Map<String,Object>> listPersons = mapper.selectImmediatePersonInfoByParentPositionId(keyString);
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
	 * 
	 * 描述：查询直接间接上级的信息
	 * 2013-1-4 下午04:27:47 by ygq
	 * @version
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
	 * 
	 * 描述：查询间接上级对应的下属的信息
	 * 2013-1-4 下午04:27:47 by ygq
	 * @version
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryIndirectPersonInfoByParentPositionId(List<String> numlist) throws Exception{
		List<Map<String,String>>  listIndirect = queryIndirectSuperiorPositionInfo(numlist);//查询间接上级的职位信息
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		//直接上级人员,直接上级下的人
		Map<String,List<Map<String,Object>>> indirectPerson = new HashMap<String,List<Map<String,Object>>>();
		//有重复的人员信息怎么办？
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			if(listIndirect != null && listIndirect.size()>0){
				System.out.println("间接上级对应的人员的信息");
				//存取直接上级下的人员的相关信息
				for(int i=0;i<listIndirect.size();i++){
					String keyString = listIndirect.get(i).get("POSITION");//获得对应的职位key
					List<Map<String,Object>> listPersons = mapper.selectIndirectPersonInfoByParentPositionId(keyString);
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
	 * 
	 * 描述：查询部门负责人的信息
	 * 2013-1-4 下午04:27:47 by ygq
	 * @version
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryResponsePersonInfo(List<String> numlist) throws Exception{
		List<Map<String,String>> listOrg = queryResponsePersonOrg(numlist);//部门负责人
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		//直接上级人员,直接上级下的人
		Map<String,List<Map<String,Object>>> responsePerson = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			if(listOrg != null && listOrg.size()>0){
				System.out.println("部门负责人的信息");
				//存取直接上级下的人员的相关信息
				for(int i=0;i<listOrg.size();i++){
					String keyString = listOrg.get(i).get("ORGNUMBER");//获得对应的职位key
					List<Map<String,Object>> listPersons = mapper.selectResponsePersonInfo(keyString);
//					for(int j=0;j<listPersons.size();j++){
//						Map<String,Object> mp = listPersons.get(j);
//						System.out.println(keyString + "\t负责人的人员信息\t" + mp.get("PERSONID"));
//					}
					responsePerson.put(keyString, listPersons);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return responsePerson;
	}
	/**
	 * 
	 * 描述：查询部门负责人下的转正员工的信息
	 * 2013-1-4 下午04:27:47 by ygq
	 * @version
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryUnderResponsePersonInfo(List<String> numlist) throws Exception{
		List<Map<String,String>> listOrg = queryResponsePersonOrg(numlist);//部门负责人
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		//直接上级人员,直接上级下的人
		Map<String,List<Map<String,Object>>> indirectPerson = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			if(listOrg != null && listOrg.size()>0){
				System.out.println("部门负责人对应的转正员工的信息");
				//存取直接上级下的人员的相关信息
				for(int i=0;i<listOrg.size();i++){
					String keyString = listOrg.get(i).get("ORGNUMBER");//获得对应的职位key
					String orgNumber = StringUtil.addLikeOperBoth(keyString);
					List<Map<String,Object>> listPersons = mapper.selectUnderResponsePersonInfo(orgNumber);
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
	 * 描述：测试直接上级
	 * 2013-3-20 下午03:32:15 by ygq
	 * @version
	 * @throws Exception
	 */
	public static void testZhiJieShangJi() throws Exception{
		//发送邮件的步骤是什么？
//		Map<String,List<Map<String,Object>>> mp = queryQualifyPersonsInfo(list);//查询直接上级的信息
		System.out.println("遍历直接上级");
//		for(Entry<String,List<Map<String,Object>>> entry :mp.entrySet()){
//			String key = entry.getKey();
//			System.out.println("key\t" + key);
//			List<Map<String,Object>> mpp = entry.getValue();
//			System.out.println(Arrays.toString(mpp.toArray()));
//		}
		//查询直接上级下的人员的信息
//		Map<String,List<Map<String,Object>>> mpImmPersons = queryImmediatePersonInfoInfo();//获取直接上级下的人员的信息
//		System.out.println("遍历直接下级");
//		for(Entry<String,List<Map<String,Object>>> entry :mpImmPersons.entrySet()){
//			//这里面对应的是收件人的下属
//			List<Map<String,Object>> mpImmPerson = entry.getValue();
//			//将这些信息转换
//			//这里面对应的是收件人 的信息
//			List<Map<String,Object>> mpPersons = mp.get(entry.getKey());
//			//将这些信息对应的邮件整理出来
//			System.out.println("size1::::::::" + mpImmPerson.size());
//		}
	}
	/**
	 * 间接上级
	 * 2013-3-20 下午03:29:25 by ygq
	 * @version
	 * @throws Exception
	 */
	public static void testJianJieShangJi() throws Exception{
//		Map<String,List<Map<String,Object>>> mp = queryQualifyPersonsInfo(listIndirect);
//		System.out.println("遍历map");
//		for(Entry<String,List<Map<String,Object>>> entry :mp.entrySet()){
//			String key = entry.getKey();
//			System.out.println("key\t" + key);
//			List<Map<String,Object>> mpp = entry.getValue();
//			System.out.println(Arrays.toString(mpp.toArray()));
//		}
//		Map<String,List<Map<String,Object>>> mp2 = queryIndirectPersonInfoByParentPositionId();//间接上级下的转正的员工
//		System.out.println("转正的员工");
//		for(Entry<String,List<Map<String,Object>>> entry :mp2.entrySet()){
//			String key = entry.getKey();
//			System.out.println("key\t" + key);
//			List<Map<String,Object>> mpp = entry.getValue();
//			System.out.println(Arrays.toString(mpp.toArray()));
//		}
	}
	/**
	 * 描述：查询部门负责人
	 * 2013-3-20 下午03:29:25 by ygq
	 * @version
	 * @throws Exception
	 */
	public static void testFuZeRen() throws Exception{
//		Map<String,List<Map<String,Object>>> mp = queryResponsePersonInfo();//查询部门负责人
//		System.out.println("遍历map");
//		for(Entry<String,List<Map<String,Object>>> entry :mp.entrySet()){
//			String key = entry.getKey();
//			System.out.println("key\t" + key);
//			List<Map<String,Object>> mpp = entry.getValue();
//			System.out.println(Arrays.toString(mpp.toArray()));
//		}
		List<String> numlist = null;
		//部门负责人下的转正的员工
		Map<String,List<Map<String,Object>>> mp2 = queryUnderResponsePersonInfo(numlist);//查询部门负责人下的转正的员工
		System.out.println("转正的员工");
		for(Entry<String,List<Map<String,Object>>> entry :mp2.entrySet()){
			String key = entry.getKey();
			System.out.println("key\t" + key);
//			List<Map<String,Object>> mpp = entry.getValue();
//			System.out.println(mpp.size());
		}
	}
	public static void main(String[] args) throws Exception{
		testZhiJieShangJi();
//		testJianJieShangJi();
//		testFuZeRen();
		List<String> numlist = null;
		queryIndirectPersonInfoByParentPositionId(numlist);
	}
}
