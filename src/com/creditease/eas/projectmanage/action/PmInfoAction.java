package com.creditease.eas.projectmanage.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.projectmanage.bean.EmailHistory;
import com.creditease.eas.projectmanage.bean.PmInfo;
import com.creditease.eas.projectmanage.service.PmInfoService;
import com.creditease.eas.util.BaseAction;

/**
 * 项目管理模块 — PM信息维护
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
public class PmInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private PmInfoService pmInfoServiceImpl;
	private PmInfo pmInfo;
	private List<PmInfo> pmInfoList;
	private List<EmailHistory> emailHistoryList;
	private static Logger logger=Logger.getLogger(PmInfo.class);
	
	/**
	 * 查询所有pm信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selectAllPm() {
		this.pagination = pmInfoServiceImpl.queryPage(pagination);
		pmInfoList = pagination.getRows();
		return "selectAllPm";
	}
	
	/**
	 * 邮箱绑定加载
	 * @return
	 */
	public String edit(){
		if (pmInfo != null)
			pmInfo = pmInfoServiceImpl.getPmInfoById(pmInfo.getId());
		return "edit";
	}
	
	/**
	 * 更新邮箱
	 */
	public void updatePmEmail(){
		try {
			pmInfoServiceImpl.updatePmEmail(pmInfo);
			this.json = "{\"success\":\"true\"}";
			logger.info("邮箱绑定成功");
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
			logger.info("邮箱绑定失败");
		}finally{
			try {
				this.writerJsonToClient(this.json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 查询所有pm变更记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selectAllPmChange() {
		this.pagination = pmInfoServiceImpl.queryPmChangeList(pagination);
		pmInfoList = pagination.getRows();
		return "selectAllPmChange";
	} 
	
	/**
	 * 查询所有预警发送记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selectAllEmailHistory() {
		this.pagination = pmInfoServiceImpl.queryEmailHistoryList(pagination);
		emailHistoryList = pagination.getRows();
		return "selectAllEmailHistory";
	} 
	
	public PmInfo getPmInfo() {
		return pmInfo;
	}

	public void setPmInfo(PmInfo pmInfo) {
		this.pmInfo = pmInfo;
	}

	public List<PmInfo> getPmInfoList() {
		return pmInfoList;
	}

	public void setPmInfoList(List<PmInfo> pmInfoList) {
		this.pmInfoList = pmInfoList;
	}
	
	public List<EmailHistory> getEmailHistoryList() {
		return emailHistoryList;
	}

	public void setEmailHistoryList(List<EmailHistory> emailHistoryList) {
		this.emailHistoryList = emailHistoryList;
	}
	
	public PmInfoService getPmInfoServiceImpl() {
		return pmInfoServiceImpl;
	}

	public void setPmInfoServiceImpl(PmInfoService pmInfoServiceImpl) {
		this.pmInfoServiceImpl = pmInfoServiceImpl;
	}
}
