/**
 * 
 */
package com.creditease.eas.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import _208._203._207._111.ormrpc.services.EASLogin.EASLoginProxy;
import _208._203._207._111.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxy;
import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxyServiceLocator;
import client.WSContext;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.warn.bean.personContactMethod;
import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;

/**
 * @NameUtils.java
 * created at 2013-3-14 下午06:28:03 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class NameUtils  extends BaseMyBatisDao{

	/**
	 * 
	 * 描述：
	 * 2013-3-14 下午07:17:17 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public  static void getNoEmail(){
		int count=1;
		int total=1;
		 SqlSession session = null;
			List<Map<String,Object>> list=null;
//			Map<String, String> map =new HashMap<String, String>();
			Map<String,Integer> map =new HashMap<String, Integer>();
//			Map<String, String> allmap =new HashMap<String, String>();
			try {
				session = getSession();
				PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
				personContactMethod pcm = new personContactMethod();
				//空邮箱数据
				list = mapper.getALLNoEmail();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> obj = list.get(i);
					String pcmid = obj.get("PCMID").toString().trim();
					String name = obj.get("NAME").toString().trim();
					
					String tmp = Cn2Spell.converterToSpell(name);
					tmp = tmp.replaceAll("lu:", "lv");
					
					//判断 空邮箱里面是否可能存在拼音一样的用户名
					if(!map.containsKey(tmp))
			        {
//						 map.put(tmp, tmp);
						 count = 1;
						 map.put(tmp, 0);
//						 allmap.put(tmp, pcmid);
//						 System.out.println(allmap.get(tmp).toString()+" : "+name+map.get(tmp));
			        }
	                else
	                {
//	                	map.put(tmp, map.get(tmp)+count);
	                	map.put(tmp, count);
	                	count++;
//	                	allmap.put(map.get(tmp)+count,pcmid);
//	                	System.out.println(allmap.get(tmp).toString()+" : "+name+map.get(tmp)+count);
	                }
//					pcm.setFid(pcmid);
//					pcm.setCfmail(map.get(tmp)+"@111.com");
//					mapper.updateByPrimaryKeySelective(pcm);
//					session.commit();
					
//					System.out.println(map.get(tmp)+name);
				}
				
				//所有邮箱数据
				List<Map<String,Object>> all = mapper.getALLEmail();
				for (int i = 0; i < all.size(); i++) {
					Map<String, Object> obj = all.get(i);
					String email = obj.get("CFEMAIL").toString().trim().replaceAll("@creditease.cn", "");
					String name = obj.get("NAME").toString().trim();
					String pcmid = obj.get("PCMID").toString().trim();
					//判断新加拼接的邮箱是否和所有邮箱相同
					if(map.containsKey(email)==true)
			        {
//	                	pcm.setFid(allmap.get(email).toString());
//						pcm.setCfmail(email + total +"@111.com");
//						mapper.updateByPrimaryKeySelective(pcm);
//						session.commit();
//	                	System.out.println(allmap.get(email)+name);
//	                	map.put(email, email+total);
	                	map.put(email, total);
	                	total++;
//	                	System.out.println(email+total);
			        }else{
			        	total = 1;
			        }
				}
				
				
				for (int i = 5000; i < list.size(); i++) {
					Map<String, Object> obj = list.get(i);
					String pcmid = obj.get("PCMID").toString().trim();
					String name = obj.get("NAME").toString().trim();
					
					String tmp = Cn2Spell.converterToSpell(name);
					tmp = tmp.replaceAll("lu:", "lv");
//					pcm.setFid(pcmid);
//					pcm.setCfmail(map.get(tmp)+"@111.com");
//					mapper.updateByPrimaryKeySelective(pcm);
//					session.commit();
//					System.out.println(map.get(tmp)+name);
					System.out.println(tmp+map.get(tmp)+name);
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeSession(session);
			}
			 System.out.println("aaaaaaaaaaaaa");
	}
	
	
	public  static void emailTest(){
		 SqlSession session = null;
		List<Map<String,Object>> all=null;
		Map<String,Integer> map =new HashMap<String, Integer>();
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			//所有邮箱数据
			all = mapper.getALLEmailOfCount();
			for (int i = 0; i < all.size(); i++) {
				Map<String, Object> obj = all.get(i); 
				String pyin = obj.get("PYIN").toString().trim();
//				Integer counts = Integer.getInteger(obj.get("COUNTS").toString().trim());
				Integer counts = Integer.parseInt(obj.get("COUNTS").toString().trim());
				map.put(pyin, counts);
			}
			
			
			//空邮箱数据
			List<Map<String,Object>> list= mapper.getNoEmail();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> obj = list.get(i); 
				String pyin = obj.get("PYIN").toString().trim();
				String name = obj.get("NAME").toString().trim();
				if(!map.containsKey(pyin)){
					map.put(pyin, 1);
					System.out.println(pyin+name);
				}else{
					map.put(pyin, map.get(pyin)+1);
					System.out.println(pyin+map.get(pyin)+name);
				}
			}
			
			//排除特殊情况
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	
	/**
	 * 
	 * 描述：判断某个字符串是否存在于数组中
	 * 2013-3-14 下午07:16:28 by xjw
	 * @version
	 *  @param stringArray 原数组
	 *  @param source 查找的字符串
	 *  @return 是否找到
	 */
	public static boolean contains(String[] stringArray, String source) {
		  // 转换为list
		  List<String> tempList = Arrays.asList(stringArray);
		  
		  // 利用list的包含方法,进行判断
		  if(tempList.contains(source))
		  {
			  return true;
		  } else {
			  return false;
		  }
	} 
	
	/**
	 * 描述：
	 * 2013-3-14 下午06:28:03 by xjw
	 * @version
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		getNoEmail();
//		emailTest();
//		emailTest();
//		System.out.println("是否存在:"+findDataByEmail("jiezhu3@creditease.cn"));
//		System.out.println("最大数:"+findCountByEmail("xuce"));
//		ContractUtils utils = new ContractUtils();
//		System.out.println(utils.ContractTest("201301230144"));
		EASLoginProxyServiceLocator loginLocator = new EASLoginProxyServiceLocator();
		EASLoginProxy loginProxy = loginLocator.getEASLogin();
		System.out.println("------ 开始登录服务器 .... ");
		WSContext context = loginProxy.login("user", "usereas88", "eas", "001", "L2", 1);
		System.out.println("-------登录成功....");
		WSTransferDataFacadeSrvProxyServiceLocator ws = new WSTransferDataFacadeSrvProxyServiceLocator();
		WSTransferDataFacadeSrvProxy sercice = ws.getWSTransferDataFacade();
		System.out.println(sercice.addContactMethod("201301230144"));
	}

}
