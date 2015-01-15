package com.creditease.eas.accountr.action;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.accountr.bean.Accountr;
import com.creditease.eas.accountr.bean.AccountrRemarks;
import com.creditease.eas.accountr.bean.AccountrUser;
import com.creditease.eas.accountr.service.IAccountrService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.action.FinanceRentContractAction;
/**
 * 报销提醒功能
 * @AccountrAction.java
 * created at 2014-1-6 下午03:43:13 by sunxiaofeng
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class AccountrAction extends BaseAction {
	
	@Autowired
	private IAccountrService accountrService;
	private AccountrUser accountrUser;//收件人实例bean
	private AccountrRemarks accountrRemarks;//公共备注实例bean
	private Accountr accountr;//报销信息实例
	/**要导入的excel源数据**/
	private File excelFile;
	/**要导入的excel源数据路径**/
	private String filePath;
	
	private static Logger logger = Logger.getLogger(AccountrAction.class);
    /**
     * 
     * 描述：查询所有汇总收件人
     * 2014-1-7 上午11:38:37 by sunxiaofeng
     * @version
     */
	public void findAccountrUser() {
		try{
			String json = accountrService.findAccountrUser();
			writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * 描述：新增汇总收件人
	 * 2014-1-7 上午11:44:59 by sunxiaofeng
	 * @version
	 * @throws Exception 
	 */
	public void addAccountrUser() throws Exception{
		try{
		accountrUser.setFcreateuserid(Integer.parseInt(findUser().getId().toString()));
		accountrUser.setFstatus(0);//0表示没有选中（默认）
		accountrService.addAccountrUser(accountrUser);
		this.json = "{\"success\":\"true\"}";
		}catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 
	 * 描述：删除汇总邮件收人
	 * 2014-1-7 下午02:34:42 by sunxiaofeng
	 * @version
	 * @throws Exception 
	 */
	public void deleteAccountrUser() throws Exception{
		String fid=request.getParameter("fid");
		accountrService.deleteAccountrUser(fid);
		logger.info("删除id为"+fid+"数据成功");
	}
	/**
	 * 
	 * 描述：查看公共备注
	 * 2014-1-7 下午04:04:38 by sunxiaofeng
	 * @version
	 */
	public String findAccountrRemarks(){
		try{
			accountrRemarks = accountrService.findAccountrRemarks();
			//writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "findAccountrRemarks";
	}
/*	public void findAccountrRemarks(){
		try{
			String json = accountrService.findAccountrRemarks();
			writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}*/
	/**
	 * 
	 * 描述：保存选中的汇总邮件收件人及公共备注
	 * 2014-1-7 下午04:38:14 by sunxiaofeng
	 * @version
	 * @throws Exception 
	 */
	public void updateAccountrUserRemarks() throws Exception{
		try{
			String fid=request.getParameter("auserfid");
			String fremarks=request.getParameter("fremarks");
			AccountrRemarks accountrRemarks=new AccountrRemarks();
			accountrRemarks.setFremarks(fremarks);
			accountrService.updateAccountrUserRemarks(fid.split(","),accountrRemarks);
			this.json = "{\"success\":\"true\"}";
			}catch (Exception e) {
				e.printStackTrace();
				this.json = "{\"success\":\"false\"}";
			}finally{
				this.writerJsonToClient(this.json);
			}
	}
	/**
	 * 
	 * 描述：查询报销列表信息
	 * 2014-1-8 上午10:58:36 by sunxiaofeng
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson() throws Exception {
		this.pagination = accountrService.queryPage(pagination,request);
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：跳转编辑页面
	 * 2014-1-8 下午03:39:34 by sunxiaofeng
	 * @version
	 * @return
	 */
	public String edit(){
		if(null != accountr){
			accountr=accountrService.findAccountr(accountr.getFid());
		}
		return "edit";
	}
	/**
	 * 
	 * 描述：新增报销信息
	 * 2014-1-8 下午04:06:22 by sunxiaofeng
	 * @version
	 * @throws Exception
	 */
    public void insert() throws Exception{
    	try{
    		accountr.setFcreateuserId(Integer.parseInt(findUser().getId().toString()));
    		accountr.setStatus(0);
    		accountr.setFcreateuserName(findUser().getUsername());
			accountrService.insert(accountr);
			this.json = "{\"success\":\"true\"}";
			}catch (Exception e) {
				e.printStackTrace();
				this.json = "{\"success\":\"false\"}";
			}finally{
				this.writerJsonToClient(this.json);
			}	
    }
    /**
     * 
     * 描述：修改报销信息
     * 2014-1-8 下午04:06:45 by sunxiaofeng
     * @version
     * @throws Exception
     */
    public void update() throws Exception{
    	try{
    		accountr.setLastupdaterId(Integer.parseInt(findUser().getId().toString()));
			accountrService.update(accountr);
			this.json = "{\"success\":\"true\"}";
			}catch (Exception e) {
				e.printStackTrace();
				this.json = "{\"success\":\"false\"}";
			}finally{
				this.writerJsonToClient(this.json);
			}	
    }
    /**
     * 
     * 描述：删除报销信息
     * 2014-1-8 下午05:29:46 by sunxiaofeng
     * @version
     */
    public void deleteAccountr(){
    	accountrService.deleteAccountr(accountr.getFid());
    }
    
    public void importExcelValidation() throws Exception{
    	try {
			json = accountrService.importExcelValidation(excelFile,filePath);
		} catch (Exception e) {
			json = "{\"success\":\"false\", \"message\":\"</br>数据异常\"}";
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
    }
    /**
     * 
     * 描述：
     * 2014-1-9 上午10:22:53 by sunxiaofeng
     * @version
     * @return
     */
	public void importExcel() throws Exception{
		try {
			json = accountrService.importExcel(excelFile,filePath);
		} catch (Exception e) {
			json = "{\"success\":\"false\", \"message\":\"</br>导入异常\"}";
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
	}
	/**
	 * 
	 * 描述：补发邮件
	 * 2014-1-16 上午09:29:15 by sunxiaofeng
	 * @version
	 * @throws Exception 
	 */
	public void reissueEmail() throws Exception{
		try {
			String fids=request.getParameter("fid");
			accountrService.reissueEmail(fids);
				this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			this.json = "{\"success\":\"false\"}";
			e.printStackTrace();
		}finally{
			this.writerJsonToClient(this.json);
		}	
	}
	/**
	 * 
	 * 描述：批量发送邮件及汇总邮件
	 * 2014-1-20 下午01:45:38 by sunxiaofeng
	 * @version
	 * @throws Exception 
	 */
	public void batchEmail() throws Exception{
		try {
			String fids=request.getParameter("fid");
			accountrService.batchEmail(fids,request);
				this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			this.json = "{\"success\":\"false\"}";
			e.printStackTrace();
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	public AccountrUser getAccountrUser() {
		return accountrUser;
	}
	public void setAccountrUser(AccountrUser accountrUser) {
		this.accountrUser = accountrUser;
	}
	public AccountrRemarks getAccountrRemarks() {
		return accountrRemarks;
	}
	public void setAccountrRemarks(AccountrRemarks accountrRemarks) {
		this.accountrRemarks = accountrRemarks;
	}
	public Accountr getAccountr() {
		return accountr;
	}
	public void setAccountr(Accountr accountr) {
		this.accountr = accountr;
	}
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	
}
