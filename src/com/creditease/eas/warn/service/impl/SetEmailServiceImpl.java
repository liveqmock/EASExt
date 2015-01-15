/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import transferdatafacade.client.WSInvokeException;
import _208._203._207._111.ormrpc.services.EASLogin.EASLoginProxy;
import _208._203._207._111.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxy;
import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxyServiceLocator;
import client.WSContext;

import com.creditease.eas.test.Cn2Spell;
import com.creditease.eas.test.SetEmailUtils;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.warn.bean.personContactMethod;
import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;
import com.creditease.eas.warn.service.SetEmailService;

/**
 * @SetEmailServiceImpl.java
 * created at 2013-3-22 下午02:37:39 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class SetEmailServiceImpl extends BaseMyBatisDao implements SetEmailService {
	
//	@Autowired
//	private PersonDataMapper personDataMapper;
	
	private static final Logger logger = Logger.getLogger(SetEmailServiceImpl.class);
	
	
		


	
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
	//关联空联系人
	public void setEmpty(){
		try{
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String users = pr.getProperty("setmail_users"); 
			String pwd = pr.getProperty("setmail_pwd"); 
			String stmail01 = pr.getProperty("stmail01"); 
			String stmail02 = pr.getProperty("stmail02"); 
			int stmail03 = Integer.parseInt(pr.getProperty("stmail03")); 
			
			EASLoginProxyServiceLocator loginLocator = new EASLoginProxyServiceLocator();
			EASLoginProxy loginProxy = loginLocator.getEASLogin();
			System.out.println("------ 开始登录服务器 .... ");
//			WSContext context = loginProxy.login("user", "", "eas", "001", "L2", 1);
			WSContext context = loginProxy.login(users, "", pwd, stmail01, stmail02, stmail03);
			WSTransferDataFacadeSrvProxyServiceLocator ws = new WSTransferDataFacadeSrvProxyServiceLocator();
			WSTransferDataFacadeSrvProxy sercice = ws.getWSTransferDataFacade();
			System.out.println("-------登录成功....");
			
			List<Map<String,Object>> list = SetEmailUtils.pcmList();
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> obj = list.get(i);
				String fnumber = obj.get("FNUMBER").toString().trim();
				System.out.println("fnumber: "+ fnumber);
				String pcmid = sercice.addContactMethod(fnumber);
//				String pcmid = sercice.addContactMethod("201301230144");
				System.out.println(pcmid);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//关联空联系人
	public void setEmpty(String fnumber,WSTransferDataFacadeSrvProxy sercice){
		String pcmid;
		try {
			pcmid = sercice.addContactMethod(fnumber);
			System.out.println(pcmid);
		} catch (WSInvokeException e) {
			System.out.println("fnumber: "+fnumber);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("fnumber: "+fnumber);
			e.printStackTrace();
		}
	}
	//去掉用户名的空格
	public void trimName(){
		SqlSession session = null;
		try {
			session = getSession();
			PersonDataMapper personDataMapper = session.getMapper(PersonDataMapper.class);
			personDataMapper.trimName();    //去掉用户名的空格
			personDataMapper.trimMail();		
			session.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	//拼接邮箱方案一
	public void setAllEmailTest() {
		SqlSession session = null;
				System.out.println("开始拼接邮箱");
				List<Map<String,Object>> list = null;
					session = getSession();
					PersonDataMapper personDataMapper = session.getMapper(PersonDataMapper.class);
					//空邮箱数据
					list= personDataMapper.getNoEmail();
					personContactMethod pcm = new personContactMethod();
					Map<String,Integer> map =new HashMap<String, Integer>();
					String[] ss = {"欧阳","太史","端木","上官","司马","东方","独孤","南宫","万俟","闻人","夏侯","诸葛","尉迟"
							,"公羊","赫连","澹台","皇甫","宗政","濮阳","公冶","太叔","申屠","公孙","慕容","仲孙","钟离","长孙",
							"宇文","司徒","鲜于","司空","闾丘","子车","亓官","司寇","巫马","公西","颛孙","壤驷","公良","漆雕",
							"乐正","宰父","谷梁","拓跋","夹谷","轩辕","令狐","段干","百里","呼延","东郭","南门","羊舌","微生",
							"公户","公玉","公仪","梁丘","公仲","公上","公门","公山","公坚","左丘","公伯","西门","公祖","第五",
							"公乘","贯丘","公皙","南荣","东里","东宫","仲长","子书","子桑","即墨","达奚","褚师","吴铭" };
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> obj = list.get(i); 
//					String pyin = obj.get("PYIN").toString().trim();
					String name = obj.get("NAME").toString().trim();
					String fnumber = obj.get("FNUMBER").toString().trim();
					String pcmpersonid = obj.get("PERSONID").toString().trim();
					String fid = "";
					if(obj.get("FID")!=null){
						fid = obj.get("FID").toString().trim();
					}
//					pyin = pyin.replaceAll("\\W", "");
					String namess = name.substring(0,2);
					if(contains(ss,namess)==true){
//						System.err.println("name: "+name+"   复姓: "+name.substring(0,2));
//						name = name.substring(0,2)+name.substring(2);
						name = Utils.toTrim( name.substring(2) + name.substring(0,2) );
					}else{
//						System.out.println("姓： "+str1+" 名："+str2);
						name = Utils.toTrim(  name.substring(1) + name.substring(0,1) );
					}
					String pyin = "";
					try{
						pyin = Cn2Spell.converterToSpell(name);
					}catch (Exception e) {
						e.printStackTrace();
						continue;
					}
					pyin = pyin.replaceAll("lu:", "lv");
					int count = personDataMapper.findMaxByEmail(pyin);		//截取邮箱最大的排序号
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
//							map.put(pyin, 0);
						if(!map.containsKey(pyin)){ //不存在 第一次存
							map.put(pyin, 0);
						}else{ //存在
							map.put(pyin, map.get(pyin)+1);
						}
					}
					
					if(map.get(pyin)==0){
						pcm.setFid(fid);
						pcm.setCfmail(pyin+"@creditease.cn");
						pcm.setFlastupdatetime(new Date());
						pcm.setFzdy1("拼接邮箱"+pyin);
						pcm.setFpersonid(pcmpersonid); //绑定人员信息ID
						personDataMapper.updateByPrimaryKeySelective(pcm);
						System.out.println(pyin+name);
					}else{
						pcm.setFid(fid);
						pcm.setCfmail(pyin+map.get(pyin)+"@creditease.cn");
						pcm.setFlastupdatetime(new Date());
						pcm.setFzdy1("拼接邮箱"+pyin);
						pcm.setFpersonid(pcmpersonid); //绑定人员信息ID
						personDataMapper.updateByPrimaryKeySelective(pcm);
						System.out.println(pyin+map.get(pyin)+name);
					}
					session.commit();
				}
				closeSession(session);
			}

	//拼接邮箱方案二	
	@Override
	public void setAllEmailTest2(personContactMethod pcm,Map<String, Integer> map,PersonDataMapper mapper,Map<String, Object> obj, String[] ss, SqlSession session) {
		
		String name = obj.get("NAME").toString().trim();
//		String fnumber = obj.get("FNUMBER").toString().trim();
		String pcmpersonid = obj.get("PERSONID").toString().trim();
		String fid = "";
		if(obj.get("FID")!=null){
			fid = obj.get("FID").toString().trim();
		}
		String namess = name.substring(0,2);
		if(contains(ss,namess)==true){
			name = name.substring(0,2)+name.substring(2);
		}else{
			name = name.substring(0,1)+name.substring(1);
		}

		String pyin = "";
		try{
			pyin = Cn2Spell.converterToSpell(name);
			pyin = pyin.replaceAll("lu:", "lv");
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
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
			if(!map.containsKey(pyin)){ //不存在 第一次存
				map.put(pyin, 0);
			}else{ //存在
				map.put(pyin, map.get(pyin)+1);
			}
		}
		
		if(map.get(pyin)==0){
			pcm.setFid(fid);
			pcm.setCfmail(pyin+"@creditease.cn");
			pcm.setFlastupdatetime(new Date());
			pcm.setFzdy1("拼接邮箱"+pyin);
			pcm.setFpersonid(pcmpersonid); //绑定人员信息ID
			mapper.updateByPrimaryKeySelective(pcm);
			System.out.println(pyin+name);
		}else{
			pcm.setFid(fid);
			pcm.setCfmail(pyin+map.get(pyin)+"@creditease.cn");
			pcm.setFlastupdatetime(new Date());
			pcm.setFzdy1("拼接邮箱"+pyin);
			pcm.setFpersonid(pcmpersonid); //绑定人员信息ID
			mapper.updateByPrimaryKeySelective(pcm);
			System.out.println(pyin+map.get(pyin)+name);
		}
		session.commit();
		
	}
	
	
	
	
}
