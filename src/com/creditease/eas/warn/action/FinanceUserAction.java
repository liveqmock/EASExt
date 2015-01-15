package com.creditease.eas.warn.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.service.UserService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.bean.FinanceUser;
import com.creditease.eas.warn.service.FinanceUserService;
/**
 * @FinanceUserAction.java	财务房租合同信息用户action
 * created at 2013-9-17 下午02:24:06 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class FinanceUserAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	/**要自动装配的财务房屋合同用户接口**/
	@Autowired
	private FinanceUserService financeUserServiceImpl;
	@Autowired
	private UserService userService;

	/**财务房租合同用户bean**/
	private FinanceUser financeUser;
	private String[] choseCompanylist;//已经选择的负责公司
	
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@SuppressWarnings("unchecked")
	private List<Map> financeGroups;
	@SuppressWarnings("unchecked")
	private List<Map> signatorys;//所负责公司集合
	@SuppressWarnings("unchecked")
	private List<Map> systemUsers;
	@SuppressWarnings("unused")
	private List<Map> systemAllUsers;
	
	private static final Logger logger = Logger
	.getLogger(FinanceUserAction.class);
	/**
	 * 描述：查询列表页数据
	 * 2013-9-17 下午02:26:24 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson() throws Exception {
		this.pagination = financeUserServiceImpl.queryPage(pagination);
		return "queryPageJson";
	}
	/**
	 * 描述：查看Entity详细信息
	 * 2013-9-17 下午02:26:47 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
		this.selectAllusers();
		this.selectByPrimaryKey();
		this.selectFinanceGroups();
		financeUser = financeUserServiceImpl.getFinanceUserById(financeUser.getId());
		return "view";
	}
	/**
	 * 描述：删除Entity
	 * 2013-9-17 下午02:27:42 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		try {
			//邮件通知相关人员（通知用户直接领导(组长和王祺)）
			boolean success =financeUserServiceImpl.delete(financeUser.getId(),findUser().getUsername());
			if(success){ 
				logger.info("删除用户邮件通知该用户直接领导成功！");
			
			}else{ 
				logger.info("删除用户邮件通知该用户直接领导失败！");
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "delete";
	}
	/**
	 * 描述：跳转到编辑页面
	 * 2013-9-17 下午02:28:29 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		this.selectAllusers();
		signatorys = financeUserServiceImpl.selectAllSignatorys();//签署合同公司集合
		this.selectFinanceGroups();
		if(financeUser!=null){ 
			financeUser = financeUserServiceImpl.getFinanceUserById(financeUser.getId());
			String tempchoseCompanylist = financeUser.getChargeCompanies();
			if(null != tempchoseCompanylist){
				choseCompanylist = tempchoseCompanylist.split(",");
			}
		}
		return "edit";
	}
	/**
	 * 描述：修改
	 * 2013-9-17 下午02:29:35 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void update() throws Exception{
		try {
			financeUser.setLastUpdater(this.findUser().getUsername());
			financeUserServiceImpl.update(financeUser);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
		
	}
	/**
	 * 描述：新增财务房屋合同用户记录
	 * 2013-9-17 下午02:30:00 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void insert() throws Exception{
		try {
			financeUser.setCreator(this.findUser().getUsername());
			boolean success=financeUserServiceImpl.insert(financeUser,findUser().getUsername());
			//邮件通知相关人员（通知用户直接领导(组长和王祺)）
			if(success)
				logger.info("新增用户邮件通知该用户直接领导成功！");
			else
				logger.info("新增用户邮件通知该用户直接领导失败！");
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
			logger.error(e.getMessage());
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	
	/**
	 * 
	 * 描述：查询所有财务房屋合同组表id和组名的name的键值对
	 * 2013-9-18 下午05:30:51 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void selectFinanceGroups() throws Exception{
		financeGroups = financeUserServiceImpl.selectFinanceGroups();
	}
	/**
	 * 
	 * 描述：所有签署合同公司下拉列表
	 * 2013-9-18 下午05:35:26 by caoyong
	 * @version
	 * @throws Exception
	 */
	/*public void selectAllSignatorys() throws Exception{
		signatorys = financeUserServiceImpl.selectAllSignatorys();
		response.getWriter().print(GsonBuilder.class.newInstance().create().toJson(this.signatorys));
	}*/
	/**
	 * 
	 * 描述：所有未添加到财务表中的系统用户下拉列表
	 * 2013-9-22 下午12:37:17 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void selectAllusers() throws Exception{
		this.systemUsers = financeUserServiceImpl.selectAllusers();	
	}
	public void selectByPrimaryKey() throws Exception{
		financeUser = financeUserServiceImpl.getFinanceUserById(financeUser.getId());
		user = userService.getUserById(Long.valueOf(financeUser.getUserId()));
	}
	
	/**
	 * @return the financeUser
	 */
	public FinanceUser getFinanceUser() {
		return financeUser;
	}
	/**
	 * @param financeUser the financeUser to set
	 */
	public void setFinanceUser(FinanceUser financeUser) {
		this.financeUser = financeUser;
	}
	/**
	 * @return the financeGroups
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getFinanceGroups() {
		return financeGroups;
	}
	/**
	 * @param financeGroups the financeGroups to set
	 */
	@SuppressWarnings("unchecked")
	public void setFinanceGroups(List<Map> financeGroups) {
		this.financeGroups = financeGroups;
	}
	/**
	 * @return the signatorys
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getSignatorys() {
		return signatorys;
	}
	/**
	 * @param signatorys the signatorys to set
	 */
	@SuppressWarnings({ "unchecked" })
	public void setSignatorys(List<Map> signatorys) {
		this.signatorys = signatorys;
	}
	/**
	 * @return the systemUsers
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getSystemUsers() {
		return systemUsers;
	}
	/**
	 * @param systemUsers the systemUsers to set
	 */
	@SuppressWarnings("unchecked")
	public void setSystemUsers(List<Map> systemUsers) {
		this.systemUsers = systemUsers;
	}
	public List<Map> getSystemAllUsers() {
		return systemAllUsers;
	}
	public void setSystemAllUsers(List<Map> systemAllUsers) {
		this.systemAllUsers = systemAllUsers;
	}
	public String[] getChoseCompanylist() {
		return choseCompanylist;
	}
	public void setChoseCompanylist(String[] choseCompanylist) {
		this.choseCompanylist = choseCompanylist;
	}
	
}
