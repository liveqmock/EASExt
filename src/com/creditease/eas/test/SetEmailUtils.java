/**
 * 
 */
package com.creditease.eas.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

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
public class SetEmailUtils  extends BaseMyBatisDao{
	

	/**
	 * 
	 * 描述：
	 * 2013-3-14 下午07:17:17 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public  static void emailTest(){
		SqlSession session = null;
		List<Map<String,Object>> all=null;
		List<Map<String,Object>> list= null;
		Map<String,Integer> map =new HashMap<String, Integer>();
//		Map<String,Integer> newmap =new HashMap<String, Integer>();
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			//所有邮箱数据
			all = mapper.getALLEmailOfCount();
			for (int i = 0; i < all.size(); i++) {
				Map<String, Object> obj = all.get(i); 
				String pyin = obj.get("PYIN").toString().trim();
				Integer counts = Integer.parseInt(obj.get("COUNTS").toString().trim());
				map.put(pyin, counts);
			}
			
			
			//空邮箱数据
			list= mapper.getNoEmail();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> obj = list.get(i); 
				String pyin = obj.get("PYIN").toString().trim();
//				String name = obj.get("NAME").toString().trim();
				if(!map.containsKey(pyin)){
					map.put(pyin, 1);
//					System.out.println(pyin+name);
				}else{
					map.put(pyin, map.get(pyin)+1);
//					newmap.put(pyin+map.get(pyin),1);
//					System.out.println(pyin+map.get(pyin)+name);
					String tmpmail = pyin + map.get(pyin)+1 + "@creditease.cn";
					if(mapper.findEmail(tmpmail)>=1){
						System.out.println(tmpmail);
					}
				}
			}
			
			//排除特殊情况
//			List<Map<String,Object>> tmplist = mapper.getALLEmail();
//			for (int i = 0; i < tmplist.size(); i++) {
//				Map<String, Object> obj = tmplist.get(i);
//				String pyin = obj.get("CFEMAIL").toString().trim().replaceAll("@creditease.cn", "");
//				//判断从邮箱中截取的拼音是否和hashmap中存放的拼音相同
//				if(map.containsKey(pyin)==true)
//		        {
//					map.put(pyin, map.get(pyin)+1);
//		        }
//			}
//			//修改邮箱数据
//			for (int i = 5000; i < list.size(); i++) {
//				Map<String, Object> obj = list.get(i); 
//				String pyin = obj.get("PYIN").toString().trim();
//				String name = obj.get("NAME").toString().trim();
////				if(map.get(pyin)==1){
////					System.out.println(pyin+name);
////				}else{
////					System.out.println(pyin+map.get(pyin)+name);
////				}
//				if(map.get(pyin)>1){
//					System.out.println(pyin+map.get(pyin)+name);
//				}
//			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	/**
	 * 
	 * 描述：拼接邮箱
	 * 2013-3-22 下午01:48:22 by xjw
	 * @version
	 */
	public  static void setEmailTest(){
		SqlSession session = null;
		List<Map<String,Object>> list= null;
		Map<String,Integer> map =new HashMap<String, Integer>();
		try {
			String[] ss = {"欧阳","太史","端木","上官","司马","东方","独孤","南宫","万俟","闻人","夏侯","诸葛","尉迟"
					,"公羊","赫连","澹台","皇甫","宗政","濮阳","公冶","太叔","申屠","公孙","慕容","仲孙","钟离","长孙",
					"宇文","司徒","鲜于","司空","闾丘","子车","亓官","司寇","巫马","公西","颛孙","壤驷","公良","漆雕",
					"乐正","宰父","谷梁","拓跋","夹谷","轩辕","令狐","段干","百里","呼延","东郭","南门","羊舌","微生",
					"公户","公玉","公仪","梁丘","公仲","公上","公门","公山","公坚","左丘","公伯","西门","公祖","第五",
					"公乘","贯丘","公皙","南荣","东里","东宫","仲长","子书","子桑","即墨","达奚","褚师","吴铭" };
//			EASLoginProxyServiceLocator loginLocator = new EASLoginProxyServiceLocator();
//			EASLoginProxy loginProxy = loginLocator.getEASLogin();
//			System.out.println("------ 开始登录服务器 .... ");
//			WSContext context = loginProxy.login("user", "", "eas", "001", "L2", 1);
//			System.out.println("-------登录成功....");
			
			String str1 = "";
			String str2 = "";
			String namess = "";
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			personContactMethod pcm = new personContactMethod();
//			ContractUtils utils = new ContractUtils();
			//空邮箱数据
			list= mapper.getNoEmail();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> obj = list.get(i); 
//				String pyin = obj.get("PYIN").toString().trim();
				String name = obj.get("NAME").toString().trim();
//				String fid = obj.get("FID").toString().trim();
				String fid = "";
//				pyin = pyin.replaceAll("\\W", "");
				
				namess = name.substring(0,2);
				if(contains(ss,namess)==true){
//					System.err.println("name: "+name+"   复姓: "+name.substring(0,2));
					str1 = name.substring(0,2);
					str2 = name.substring(2);
					name = str2+str1;
				}else{
					str1 = name.substring(0,1);
					str2 = name.substring(1);
//					System.out.println("姓： "+str1+" 名："+str2);
					name = str2+str1;
				}

				String pyin = Cn2Spell.converterToSpell(name);
				pyin = pyin.replaceAll("lu:", "lv");
//				System.out.println(pyin+name);
//				if(obj.get("FID")!=null){
//					fid = obj.get("FID").toString().trim();
//				}else{
////					System.out.println(name+ " : "+i);
////					fid = utils.ContractTest("201301230144");
////					System.out.println("fid:"+fid);
//					WSTransferDataFacadeSrvProxyServiceLocator ws = new WSTransferDataFacadeSrvProxyServiceLocator();
//					WSTransferDataFacadeSrvProxy sercice = ws.getWSTransferDataFacade();
//					System.out.println(sercice.addContactMethod("201301230144"));
//				}
				System.err.println(pyin);
				int count = mapper.findMaxByEmail(pyin);		//截取邮箱最大的排序号
				if(count == 0){					//所有邮箱中该邮箱存在一个
					if(!map.containsKey(pyin)){ //不存在 第一次存
						map.put(pyin, 1);
					}else{ //存在
						map.put(pyin, map.get(pyin)+1);
					}
				}else if(count>0){				//所有邮箱中该邮箱存在多个
					if(!map.containsKey(pyin)){ //不存在 第一次存
						map.put(pyin, count+1);
					}else{ //存在
						map.put(pyin, map.get(pyin)+1);
					}
				}else if(count == -1){ 			//所有邮箱中没有该邮箱
//					map.put(pyin, 0);
					if(!map.containsKey(pyin)){ //不存在 第一次存
						map.put(pyin, 0);
					}else{ //存在
						map.put(pyin, map.get(pyin)+1);
					}
				}
				
				if(map.get(pyin)==0){
					pcm.setFid(fid);
					pcm.setCfmail(pyin+"@111.com");
					pcm.setFlastupdatetime(new Date());
					pcm.setFzdy1("拼接邮箱"+pyin);
//					mapper.updateByPrimaryKeySelective(pcm);
//					session.commit();
					System.out.println(pyin+name);
				}else{
					pcm.setFid(fid);
					pcm.setCfmail(pyin+map.get(pyin)+"@111.com");
					pcm.setFlastupdatetime(new Date());
					pcm.setFzdy1("拼接邮箱"+pyin);
//					mapper.updateByPrimaryKeySelective(pcm);
//					session.commit();
					System.out.println(pyin+map.get(pyin)+name);
				}
			}
//			session.commit();		//提交修改
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	
	
	public static int findDataByEmail(String email){
		SqlSession session = null;
		int flag = -1;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			flag = mapper.findEmail(email);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return flag;
	}
	
	public static Integer findCountByEmail(String str){
		SqlSession session = null;
		Integer flag = -1;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//			if(mapper.findMaxByEmail(str)!=null){
				flag = mapper.findMaxByEmail(str);
//			}else{
//			System.out.println("xxxxxxxxxxxx");	
//			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return flag;
	}
	
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
	//空联系人数据
	public static List<Map<String,Object>> pcmList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SqlSession session = null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			//空联系人数据
			list= mapper.getNoPcm();
//			System.out.println("size: "+list.size());
//			for (int i = 0; i < list.size(); i++) {
//				Map<String,Object> obj = list.get(i);
//				System.out.println(obj.get("FID").toString());
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
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
//		setEmailTest();
		pcmList();
	}

}
