/**
 * 
 */
package com.creditease.eas.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;



import _208._203._207._111.ormrpc.services.EASLogin.EASLoginProxy;
import _208._203._207._111.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxy;
import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxyServiceLocator;

import client.WSContext;

import com.creditease.eas.test.SetEmailUtils;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.warn.bean.ConfigInfo;
import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;
import com.creditease.eas.warn.service.ConfigService;
import com.creditease.eas.warn.service.SetEmailService;

/**
 * @SetEmailQuartzJob.java
 * created at 2013-3-22 下午03:24:46 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class SetEmailQuartzJob extends BaseMyBatisDao{
	private static final Logger logger = Logger.getLogger(SetEmailQuartzJob.class);
	@Autowired
	private SetEmailService setEmailServiceImpl;
	
	@Autowired
	private ConfigService configServiceImpl;
	
	/**
	 * 
	 * 描述：拼接邮箱
	 * 2013-3-22 下午02:54:35 by xjw
	 * @version
	 */
//	public void setAllEmail2(){
//		SqlSession session = null;
//		try {
//		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(6);//获取期配置信息
//		if(configInfo.getConfigvalue()==0){
//			System.out.println("开始拼接邮箱");
//			EASLoginProxyServiceLocator loginLocator = new EASLoginProxyServiceLocator();
//			EASLoginProxy loginProxy = loginLocator.getEASLogin();
//			System.out.println("------ 开始登录服务器 .... ");
//			WSContext context = loginProxy.login("user", "usereas88", "eas", "001", "L2", 1);
//			WSTransferDataFacadeSrvProxyServiceLocator ws = new WSTransferDataFacadeSrvProxyServiceLocator();
//			WSTransferDataFacadeSrvProxy sercice = ws.getWSTransferDataFacade();
//			System.out.println("-------登录成功....");
//			List<Map<String,Object>> list = null;
//				session = getSession();
//				PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
//				//空邮箱数据
//				list= mapper.getNoEmail();
//				personContactMethod pcm = new personContactMethod();
//				Map<String,Integer> map =new HashMap<String, Integer>();
//				String[] ss = {"欧阳","太史","端木","上官","司马","东方","独孤","南宫","万俟","闻人","夏侯","诸葛","尉迟"
//						,"公羊","赫连","澹台","皇甫","宗政","濮阳","公冶","太叔","申屠","公孙","慕容","仲孙","钟离","长孙",
//						"宇文","司徒","鲜于","司空","闾丘","子车","亓官","司寇","巫马","公西","颛孙","壤驷","公良","漆雕",
//						"乐正","宰父","谷梁","拓跋","夹谷","轩辕","令狐","段干","百里","呼延","东郭","南门","羊舌","微生",
//						"公户","公玉","公仪","梁丘","公仲","公上","公门","公山","公坚","左丘","公伯","西门","公祖","第五",
//						"公乘","贯丘","公皙","南荣","东里","东宫","仲长","子书","子桑","即墨","达奚","褚师","吴铭" };
//				
//				for (int i = 0; i < list.size(); i++) {
//					Map<String, Object> obj = list.get(i); 
//					setEmailServiceImpl.setAllEmailTest(pcm,map,mapper,obj,sercice,ss);
//				}
//	//			setEmailServiceImpl.setAllEmailTest(list, mapper);
////				session.commit();		//提交修改
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//			//初始化缓存变量
//			TongJi.initCount();
//		}
//	}
	/**
	 * 
	 * 描述：拼接邮箱
	 * 2013-3-22 下午02:54:35 by xjw
	 * @version
	 */
	public void setAllEmail(){
		try {
			setEmailServiceImpl.setAllEmailTest();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
			//初始化缓存变量
			TongJi.initCount();
		}
	}
	
	//关联空联系人
	public static void test(){
		try{
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String users = pr.getProperty("setmail_users"); 
			String pwd = pr.getProperty("setmail_pwd"); 
			String stmail01 = pr.getProperty("stmail01"); 
			String stmail02 = pr.getProperty("stmail02"); 
			int stmail03 = Integer.parseInt(pr.getProperty("stmail03")); 
			String loginpwd = pr.getProperty("loginpwd");
			
			System.out.println("setmail_users: "+users+"  stmail03: "+stmail03);
			
			EASLoginProxyServiceLocator loginLocator = new EASLoginProxyServiceLocator();
			EASLoginProxy loginProxy = loginLocator.getEASLogin();
			System.out.println("------ 开始登录服务器 .... ");
//			WSContext context = loginProxy.login("user", "", "eas", "001", "L2", 1);
			WSContext context = loginProxy.login(users, loginpwd, pwd, stmail01, stmail02, stmail03);
			WSTransferDataFacadeSrvProxyServiceLocator ws = new WSTransferDataFacadeSrvProxyServiceLocator();
			WSTransferDataFacadeSrvProxy sercice = ws.getWSTransferDataFacade();
			System.out.println("-------登录成功....");
			String pcmid = sercice.addContactMethod("201301230144");
			System.out.println(pcmid);
//			List<Map<String,Object>> list = SetEmailUtils.pcmList();
//			for (int i = 0; i < list.size(); i++) {
//				Map<String,Object> obj = list.get(i);
//				String fnumber = obj.get("FNUMBER").toString();
////				System.out.println(fnumber);
//				String pcmid = sercice.addContactMethod(fnumber);
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Map<String,Object>> pcmList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SqlSession session = null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
			//空联系人数据
			list= mapper.getNoPcm();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	//拼接邮箱
	public void setEmail(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(6);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			//拼接企业邮箱
			setEmailServiceImpl.setAllEmailTest();
		}
//		setEmailServiceImpl.setAllEmailTest();
//		return "test";
	}
	//关联空联系人
	public void setEmpty(){
//		setEmailServiceImpl.setEmpty();
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(6);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String users = pr.getProperty("setmail_users"); 
			String pwd = pr.getProperty("setmail_pwd"); 
			String stmail01 = pr.getProperty("stmail01"); 
			String stmail02 = pr.getProperty("stmail02"); 
			int stmail03 = Integer.parseInt(pr.getProperty("stmail03"));
			String loginpwd = pr.getProperty("loginpwd"); 
			try{
				EASLoginProxyServiceLocator loginLocator = new EASLoginProxyServiceLocator();
				EASLoginProxy loginProxy = loginLocator.getEASLogin();
				System.out.println("------ 开始登录服务器 .... ");
	//			WSContext context = loginProxy.login("user", "", "eas", "001", "L2", 1);
				WSContext context = loginProxy.login(users, loginpwd, pwd, stmail01, stmail02, stmail03);
				WSTransferDataFacadeSrvProxyServiceLocator ws = new WSTransferDataFacadeSrvProxyServiceLocator();
				WSTransferDataFacadeSrvProxy sercice = ws.getWSTransferDataFacade();
				System.out.println("-------登录成功....");
//				System.out.println("xxxx: "+ sercice.addContactMethod("201210290765"));
				//查询空的联系人信息
				List<Map<String,Object>> list = SetEmailUtils.pcmList();
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object> obj = list.get(i);
					String fnumber = obj.get("FNUMBER").toString().trim();
					
					setEmailServiceImpl.setEmpty(fnumber,sercice);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	/**
	 * 2013年11月11日:
	 * 去掉用户名的空格，并且把系统中邮箱后缀不是creditease的邮箱设置为空
	 */
	public void trimName(){
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(6);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			setEmailServiceImpl.trimName();
		}
	}
	
	
	public static void main(String[] args) {
//		test();
		SetEmailQuartzJob job = new SetEmailQuartzJob(); 
		job.setEmpty();
	}
}
