/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtilNew;
import com.creditease.eas.warn.bean.FinanceUser;
import com.creditease.eas.warn.dao.FinanceUserMapper;
import com.creditease.eas.warn.service.FinanceUserService;
/**
 * @FinanceUserServiceImpl.java	财务房租合同信息用户service实现类
 * created at 2013-9-17 下午02:13:48 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class FinanceUserServiceImpl implements FinanceUserService{
	@Autowired
	private FinanceUserMapper financeUserMapper;
	private FinanceUser financeUser;
	private static final Logger logger = Logger
	.getLogger(FinanceUserServiceImpl.class);
	@Override
	public boolean insert(FinanceUser financeUser,String username) throws Exception {
		financeUser.setChargeCompanies(this.getCompList());
		financeUserMapper.insert(financeUser);
		if(financeUser.equals("")){};
		String content="系统在试运行阶段，如有问题请及时反馈。          你好： 用户："+username+"新增了 名称为"+financeUser.getUserName()+"用户。";
		return this.sendMaile(content, financeUser);
	}
	private boolean sendMaile(String content,FinanceUser financeUser){
	  MailSenderInfo ms = new MailSenderInfo();
	  Properties pr = ms.getProperties();
	  String fromAddress = pr.getProperty("FINANCE_USERNAME");
	  String password=pr.getProperty("FINANCE_PASSWORD");;
	  //String username=findUser().getUsername();//获得系统用户名称
	  // String [] toAddress = {"qiwang@creditease.cn"};
	  String[] toAddress=null;
	  List<FinanceUser>  financeUserList= financeUserMapper.getFinanGroupLeader(financeUser.getFinanceGroupId());
	  if(financeUser.getIsGroupLeader()==1){//判断是不是组长
		  toAddress=new String[1];
		 toAddress[0] ="qiwang@creditease.cn";
	  }else{
		  String email="";
		  for (int i = 0; i <financeUserList.size(); i++) {
		        if(financeUserList.get(i).getUserEmail()!=null||!financeUserList.get(i).getUserEmail().equals("")){
		        	email+=financeUserList.get(i).getUserEmail()+",";
		        }
		 }
		  if(email!=""){
			  String userEmail[]=email.split(",");
			  toAddress=new String[userEmail.length+1];
			  toAddress[0] ="qiwang@creditease.cn";
			 for (int i = 1; i <= userEmail.length; i++) {
				 toAddress[i]=userEmail[i-1];
			 }
		  }else{
			  toAddress=new String[1];
			 toAddress[0] ="qiwang@creditease.cn";
		  }
		
	  }
	  if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
			toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
		}
			//邮件通知相关人员（通知用户直接领导(组长和王祺)）
		boolean success = SendMailUtilNew.sendMailToManyPerson(fromAddress,password,toAddress,"更新用户提醒",content);		
		return success;
}
	public boolean delete(Integer id,String username) throws Exception {
		financeUser=financeUserMapper.getFinanceUser(id);
		String content="系统在试运行阶段，如有问题请及时反馈。    你好： 用户："+username+"删除了 名称为"+financeUser.getUserName()+"用户。";
		int i = financeUserMapper.deleteByPrimaryKey(id);
		return this.sendMaile(content, financeUser);
	}
	
	public int update(FinanceUser financeUser) throws Exception {
		financeUser.setChargeCompanies(this.getCompList());
		int i = financeUserMapper.updateByPrimaryKey(financeUser);
		return i;
	}
	
	public FinanceUser getFinanceUserById(Integer id) throws Exception {
		financeUser = financeUserMapper.selectByPrimaryKey(id);
		return financeUser;
	}

	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		Map map = HashMap.class.newInstance();
//		map.put("orgName",request.getParameter("orgName"));
//		map.put("startDate",request.getParameter("startDate"));
//		map.put("paymentCycle",request.getParameter("paymentCycle"));
//		map.put("lastCostCenter",request.getParameter("lastCostCenter"));
//		map.put("endDate",request.getParameter("endDate"));
		//查询总行数的方法
		int totalCounts = financeUserMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map1 = PageUtil.getMap(page);
//		map1.putAll(map);
		List list = financeUserMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
	
	private String getCompList(){
		HttpServletRequest request= ServletActionContext.getRequest();
		String[] choseCompStrings = request.getParameterValues("chosecomplist");//已勾选的理财规划师持证
		String compString = "";
		for(int i =0 ;i < choseCompStrings.length; i ++){
			compString = compString + "," + choseCompStrings[i];
		}
		compString = compString.substring(1);
		return compString;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Map> selectFinanceGroups(){
		return financeUserMapper.selectFinanceGroups();
	}
	@SuppressWarnings("unchecked")
	public List<Map> selectAllSignatorys(){
    	return financeUserMapper.selectAllSignatorys();
    }
	@SuppressWarnings("unchecked")
	public List<Map> selectAllusers() {
		return financeUserMapper.selectAllusers();
	}

	@Override
	public FinanceUser getFinanceUser(Integer id) {
		return financeUserMapper.getFinanceUser(id);
	}

	@Override
	public FinanceUser selectByUserid(Integer userid) {
		return financeUserMapper.selectByUserid(userid);
	}

	@Override
	public List<FinanceUser> selectByGroupid(Integer groupid) {
		return financeUserMapper.selectByGroupid(groupid);
	}

	@Override
	public  List<FinanceUser> getFinanGroupLeader(Integer financeGroupId) {
		
		return financeUserMapper.getFinanGroupLeader(financeGroupId);
	}
}
