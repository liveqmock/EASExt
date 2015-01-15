package com.creditease.eas.warn.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.Utils;
import com.creditease.eas.warn.dao.WaringDetailMapper;
import com.creditease.eas.warn.kingdee.dao.ContractMapper;
 
public class ContractRenewalQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：合同续签人员名单
	 * 2013-2-1 下午02:44:11 by xjw
	 * @version
	 * @throws Exception
	 */
	public  static List<Map<String,Object>> queryContract() throws Exception{
		
			SqlSession session = null;
		/***************************遍历mapKey*******************************/
			session = getSession();
			ContractMapper mapper = session.getMapper(ContractMapper.class); 
			List<Map<String,Object>> list = mapper.selectRenewalList();
//			System.out.println(list.size());
//			for (int i = 0; i < list.size(); i++) {
//				Map<String,Object> mp = list.get(i);
//				System.err.println(mp.get("PNAME").toString()+"  "+mp.get("LONGNUMBER").toString());
//			}
			
			return list;
	}
	
public  static List<Map<String,Object>> getDeptList(List<String> numlist) throws Exception{
		
		SqlSession session = null;
	/***************************遍历mapKey*******************************/
		session = getSession();
		ContractMapper mapper = session.getMapper(ContractMapper.class); 
		List<Map<String,Object>> list = mapper.selectDeptList(numlist);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> mp = list.get(i);
			System.err.println(mp.get("NAME").toString()+"  "+mp.get("LONGNUMBER").toString());
			System.out.println("部门名称："+mp.get("ORGNAME").toString());
		}
		
		return list;
} 
	
	public  static List<Map<String,Object>> queryContractById(String strid) throws Exception{
		
		SqlSession session = null;
	/***************************遍历mapKey*******************************/
		session = getSession();
		ContractMapper mapper = session.getMapper(ContractMapper.class); 
		List<Map<String,Object>> list = mapper.selectRenewalListById(strid);
		System.out.println("ccccc: "+list.size());
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> mp = list.get(i);
			System.err.println(mp.get("PNAME").toString());
		}
		
		return list;
}
	/**
	 * 
	 * 描述：测试
	 * 2013-3-8 上午10:22:19 by xjw
	 * @version
	 */
	public  void sendRenewal(){
		try{
			Map<String, List<Map<String,Object>>> datamap = test();
			for(Entry<String,List<Map<String,Object>>> it : datamap.entrySet()){
				System.out.println(it.getKey());
			}
		}catch(Exception ex){
			ex.getMessage();
		}
	}
	/**
	 * 
	 * 描述：根据根部门对应接口人编码归类对应的人员信息集合、并以相应的接口人邮箱作为主键
	 * 2013-3-8 上午10:19:24 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Map<String,Object>>> test() throws Exception{
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		session = getSession();
		WaringDetailMapper waringdetailMapper = session.getMapper(WaringDetailMapper.class);
//		Map<String, String> port = new HashMap<String, String>();
//		Map<String, Map<String,Object>> port2 = new HashMap<String, Map<String,Object>>();
		List<Map<String,Object>> list = ContractRenewalQuery.queryContract();
		List<Map<String,Object>> list2 = waringdetailMapper.getAllPort();
		
		List<Map<String,Object>> data =new ArrayList<Map<String,Object>>();
		Map<String, List<Map<String,Object>>> datamap = new HashMap<String, List<Map<String,Object>>>();
		
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> mp = list.get(i);
			if(mp.get("LONGNUMBER")!=null){
//				System.err.println(mp.get("LONGNUMBER").toString());
				String[] strs = mp.get("LONGNUMBER").toString().split("!");
				if(strs!=null && strs.length>1){
//					System.out.println(strs[1]+"  " + strs[2]);
					
					if(list2!=null){
						for (int j = 0; j < list2.size(); j++) {
							Map<String,Object> mp2 = list2.get(j);
							if(mp2.get("FNUMBER")!=null){
								String email = Utils.strTrim(mp2.get("FPERSONEMAIL"));
								String fnumber = Utils.strTrim(mp2.get("FNUMBER"));
								if(strs[1].equals(fnumber)){
//									System.out.println("fnumber: "+fnumber+"  email:"+email);
//									port2.put(email, mp);
//									port.put(email, fnumber);
									data.add(mp);
									datamap.put(email, data);
								}else{
									if(strs[2].equals(fnumber)){
//										System.out.println("fnumber: "+fnumber+"  email:"+email);
//										port.put(email, fnumber);
										data.add(mp);
										datamap.put(email, data);
									}
								}
//								data.add(mp);
//								datamap.put(email, data);
							}
						}
					}
					System.out.println("-----------------------");
				}
			}
		}
		
//		List<Map<String,Object>> list2 = waringdetailMapper.getAllPort();
//		System.out.println(list2.size());
//		if(list2!=null){
//			for (int j = 0; j < list2.size(); j++) {
//				Map<String,Object> mp2 = list2.get(j);
//				if(mp2.get("FNUMBER")!=null){
//					String fnumber = Utils.strTrim(mp2.get("FNUMBER"));
//					System.out.println("fnumber: "+fnumber);
//				}
//			}
//		}
		return datamap;
	}
	
	/**
	 * 
	 * 描述：查询部门负责人
	 * 2013-6-6 下午05:58:55 by Administrator
	 * @version
	 * @param pnumber
	 * @return
	 */
	public static  Map<String,Object> getDeptPerson(String pnumber){
		SqlSession session = null;
		Map<String,Object> org = null;
		try {
			session = getSession();
			ContractMapper mapper = session.getMapper(ContractMapper.class);
			org = mapper.getOrgByFnumber(pnumber);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return org;
	}
	/**
	 * 
	 * 描述：直接上级、隔级上级
	 * 2013-6-6 下午06:01:06 by Administrator
	 * @version
	 * @param pnumber
	 * @return
	 */
	public static  Map<String,Object> getYearPerson(String pnumber){
		SqlSession session = null;
		Map<String,Object> org = null;
		try {
			session = getSession();
			ContractMapper mapper = session.getMapper(ContractMapper.class);
			org = mapper.getPersonByFnumber(pnumber);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return org;
	}
	
	public static void main(String[] args) throws Exception{
//		List<Map<String,Object>> list = queryContract();
//		List<Map<String,Object>> list = queryContractById("!11");
		
//		getDeptList();
		
		Map<String,Object> map = getYearPerson("201211110311");
		System.out.println(map.get("NAME").toString());
	}
}
