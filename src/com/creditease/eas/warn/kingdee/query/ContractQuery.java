package com.creditease.eas.warn.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.warn.kingdee.dao.ContractMapper;
/**
 * 查询人员信息和上级部门的人员的相关信息的代码
 * @QualifyingMatureQuery001.java
 * created at 2013-1-4 下午02:07:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ContractQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：查询合同到期的人员的名单
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static void selectContractInfo(){
		SqlSession session = null;
		try {
			session = getSession();
			ContractMapper mapper = session.getMapper(ContractMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,String>> list = mapper.selectContractInfo();
			//将信息放入到List中
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	/**
	 * 
	 * 描述：只查询职位的相关的信息
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static void testPositionsOnly(){
		SqlSession session = null;
		try {
			session = getSession();
			ContractMapper mapper = session.getMapper(ContractMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,String>> list = mapper.selectPositions();
			//将信息放入到List中
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	/**
	 * 
	 * 描述：查询相关人员的信息
	 * 2013-1-4 下午04:27:47 by ygq
	 * @version
	 * @throws Exception
	 */
	public static Map<String,List<Map<String,Object>>> queryQualifyPersonsInfo() throws Exception{
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		Map<String,List<Map<String,Object>>> map = new HashMap<String,List<Map<String,Object>>>();
		try {
			session = getSession();
			ContractMapper mapper = session.getMapper(ContractMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,String>> list = mapper.selectPositions();
			//将信息放入到List中
			List<String> mapkey = new ArrayList<String>();
				if(list != null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,String> mp = list.get(i);
					for(Entry<String,String> it : mp.entrySet()){
						mapkey.add(it.getValue());
					}
				}
				//存取人员的相关的信息
				for(int i=0;i<mapkey.size();i++){
					String keyString = mapkey.get(i);
					List<Map<String,Object>> listPersons = mapper.selectContractPersonInfoByPositionId(keyString);
					map.put(keyString, listPersons);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return map;
	}
	public static void testMethod() throws Exception{
		SqlSession session = null;
		try {
			session = getSession();
			ContractMapper mapper = session.getMapper(ContractMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,Object>> list = mapper.test();
			//将信息放入到List中
			System.out.println(list.size());
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	public static void main(String[] args) throws Exception{
		//selectContractInfo();
//		testPositionsOnly();
		testMethod();
//		Map<String,List<Map<String,Object>>> map = ContractQuery.queryQualifyPersonsInfo();//查询出来的是人员的信息
//		for(Entry<String,List<Map<String,Object>>> entry : map.entrySet()){
//			String keyId = entry.getKey();
//			List<Map<String,Object>> list = CommonPersonInfoSerachQuery.queryHighPersonByPositionId(keyId);
//			System.out.println(keyId + "\t" + list);
//		}
//		System.out.println("合同到期预警发送邮件成功!!!" );
		//queryQualifyPersonsInfo();
	}
}
