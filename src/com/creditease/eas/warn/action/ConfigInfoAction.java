/**
 * 
 */
package com.creditease.eas.warn.action;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import _208._203._207._111.ormrpc.services.EASLogin.EASLoginProxy;
import _208._203._207._111.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxy;
import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxyServiceLocator;
import client.WSContext;

import com.creditease.eas.test.SetEmailUtils;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.warn.bean.ConfigInfo;
import com.creditease.eas.warn.bean.personContactMethod;
import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;
import com.creditease.eas.warn.service.ConfigService;
import com.creditease.eas.warn.service.SetEmailService;

/**
 * @JumbosmsvAction.java
 * created at 2012-12-26 下午05:04:08 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class ConfigInfoAction extends BaseAction{
	private HttpServletResponse response = ServletActionContext.getResponse();
	
	private ConfigInfo configInfo;
	
	

	public ConfigInfo getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(ConfigInfo configInfo) {
		this.configInfo = configInfo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	@Autowired
	private ConfigService configServiceImpl;
	
	@Autowired
	private SetEmailService setEmailServiceImpl;
	
	public String queryPageJson() throws Exception {
		this.pagination = configServiceImpl.queryPage(pagination);
	  // 将new出来的 分页对象 付给 Action的属性对象里
		return "queryPageJson";
	}
	
	
	/**
	 * 插入
	 * 描述：
	 * 2012-12-27 下午06:17:08 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		return "add";
	}
	/**
	 * 保存
	 * 描述：
	 * 2012-12-27 下午06:16:52 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void save() throws Exception{
		int id = configServiceImpl.maxId();
		configInfo.setId(id+1);
		configServiceImpl.insert(configInfo);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<script type='text/javascript'>parent.test();</script>");
		pw.flush();
		pw.close();
	}
	/**
	 * 删除
	 * 描述：
	 * 2012-12-27 下午06:16:44 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		configServiceImpl.delete(configInfo.getId());
		return "queryPageJsonBack";
	}
	/**
	 * 编辑
	 * 描述：
	 * 2012-12-27 下午06:15:50 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		configInfo = configServiceImpl.getConfigInfoById(configInfo.getId());
		return "edit";
	}
	/**修改
	 * x
	 * 描述：
	 * 2012-12-27 下午06:15:59 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void update() throws Exception{
		configServiceImpl.update(configInfo);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<script type='text/javascript'>parent.test();</script>");
		pw.flush();
		pw.close();
	}
	/**
	 * 查看
	 * 描述：
	 * 2012-12-27 下午06:16:18 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
		configInfo = configServiceImpl.getConfigInfoById(configInfo.getId());
		return "view";
	}
	//是否启用
	public String isuse() throws Exception{
		int cofigvalue = configInfo.getConfigvalue();
		configInfo = configServiceImpl.getConfigInfoById(configInfo.getId());
		if(cofigvalue==0){
			configInfo.setConfigvalue(1);
		}
		else{
			configInfo.setConfigvalue(0);
		}
		configServiceImpl.update(configInfo);
		return "queryPageJsonBack";
	}
	
	public static SqlSession getSession(){
		SqlSession session = null;
		try {
			//1、指定MyBaties配置文件   
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");   
			//2、创建SqlSessionFactory()   
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);   
			session = sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	
	//拼接邮箱方案一
	public void setEmail(){
			setEmailServiceImpl.setAllEmailTest();
	}
	//拼接邮箱方案二：
//	public void setEmail2(){
//		setEmailServiceImpl.setAllEmailTest();
//		SqlSession session = null;
//		System.out.println("开始拼接邮箱");
//		List<Map<String,Object>> list = null;
//			session = getSession();
//			PersonDataMapper personDataMapper = session.getMapper(PersonDataMapper.class);
//			//空邮箱数据
//			list= personDataMapper.getNoEmail();
//			personContactMethod pcm = new personContactMethod();
//			Map<String,Integer> map =new HashMap<String, Integer>();
//			String[] ss = {"欧阳","太史","端木","上官","司马","东方","独孤","南宫","万俟","闻人","夏侯","诸葛","尉迟"
//					,"公羊","赫连","澹台","皇甫","宗政","濮阳","公冶","太叔","申屠","公孙","慕容","仲孙","钟离","长孙",
//					"宇文","司徒","鲜于","司空","闾丘","子车","亓官","司寇","巫马","公西","颛孙","壤驷","公良","漆雕",
//					"乐正","宰父","谷梁","拓跋","夹谷","轩辕","令狐","段干","百里","呼延","东郭","南门","羊舌","微生",
//					"公户","公玉","公仪","梁丘","公仲","公上","公门","公山","公坚","左丘","公伯","西门","公祖","第五",
//					"公乘","贯丘","公皙","南荣","东里","东宫","仲长","子书","子桑","即墨","达奚","褚师","吴铭" };
//			for (int i = 0; i < list.size(); i++) {
//				Map<String, Object> obj = list.get(i); 
//				setEmailServiceImpl.setAllEmailTest2(pcm,map,personDataMapper,obj,ss,session);
//			}
//		
//	}
	
	//关联空联系人
	public String setEmpty(){
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
			//不知道这段代码是否有用，貌似没有用到
			WSContext context = loginProxy.login(users, loginpwd, pwd, stmail01, stmail02, stmail03);
			
			WSTransferDataFacadeSrvProxyServiceLocator ws = new WSTransferDataFacadeSrvProxyServiceLocator();
			WSTransferDataFacadeSrvProxy sercice = ws.getWSTransferDataFacade();

			System.out.println("-------登录成功....");
//			System.out.println("xxxx: "+ sercice.addContactMethod("201210290765"));
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
			return "test";
	}
	//去掉用户名的空格
	public String trimName(){
		setEmailServiceImpl.trimName();
		return "test";
	}
}
