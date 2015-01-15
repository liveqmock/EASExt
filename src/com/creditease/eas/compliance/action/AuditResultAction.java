package com.creditease.eas.compliance.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.AuditResult;
import com.creditease.eas.compliance.service.AuditResultService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.util.BaseAction;
/**
 * @AuditresultAction.java	合规（初步调查——审核结果action）
 * created at 2013-10-8 下午01:30:24 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class AuditResultAction extends BaseAction{
	/**序列化**/
	private static final long serialVersionUID = 1L;
	/**要自动装配的service接口**/
	@Autowired
	private AuditResultService auditResultServiceImpl;
	/**合规（初步调查——审核结果bean）**/
	private AuditResult auditResult;
	/**日志**/
	private static Logger logger = Logger.getLogger(AuditResultAction.class);
	/**审核项目类型：1、转部门协助的审批2、本部门实地调查的审批
		3、结案初审的审批4、结案终审的审批5、结案终审未通过**/
	private Integer auditTypeId;
	/**申请结案外键id**/
	private Integer applysettlementId;
	/**初步调查表外键id**/
	private Integer investigationId;
	/**要自动装配的初步调查service接口**/
	@Autowired
	private InvestigationService investigationServiceImpl;
	/**
	 * 
	 * 描述：查询列表页数据
	 * 2013-9-16 上午11:02:39 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson(){
		try {
			this.pagination = auditResultServiceImpl.queryPage(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：查看Entity详细信息
	 * 2013-9-16 上午11:28:30 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view(){
		try {
			auditResult = auditResultServiceImpl.getAuditResultById(auditResult.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	
	/**
	 * 
	 * 描述：删除Entity
	 * 2013-9-16 上午11:28:58 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String delete(){
		try {
			auditResultServiceImpl.delete(auditResult.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}
	/**
	 * 
	 * 描述：跳转到编辑页面
	 * 2013-9-16 上午11:31:32 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		try {
			if(request.getParameter("auditTypeId")!=null)
				this.auditTypeId = Integer.parseInt(request.getParameter("auditTypeId"));
			if(request.getParameter("investigationId")!=null){
				this.investigationId = Integer.parseInt(request.getParameter("investigationId"));
				if(this.auditTypeId==3 || this.auditTypeId==4 || this.auditTypeId==5)
					this.applysettlementId = auditResultServiceImpl.getApplysettlementId(this.investigationId);
			}
			if(auditResult!=null) 
				auditResult = auditResultServiceImpl.getAuditResultById(auditResult.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	/**
	 * 
	 * 描述：修改
	 * 2013-9-16 上午11:32:41 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void update() throws Exception{
		try {
			auditResult.setLastUpdaterId(this.findUser().getId().intValue());
			auditResultServiceImpl.update(auditResult);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	/**
	 * 
	 * 描述：新增记录
	 * 2013-9-16 上午11:33:59 by caoyong
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void insert() throws Exception{
		try {
			auditResult.setCreaterId(this.findUser().getId().intValue());
			auditResultServiceImpl.insert(auditResult);
			Map map = new HashMap();
			map.put("id", auditResult.getInvestigationId());
			if(auditResult.getAuditTypeId() == 1){//1、转部门协助的审批
				if(auditResult.getIsAgree()==0) map.put("statusId", 5);//是时审批通过修改案件状态为"被投诉部门调查处理审批已通过"（5）
				else if(auditResult.getIsAgree()==1) map.put("statusId", 4);//否时审批通过修改案件状态为"被投诉部门调查处理审批未通过"（4）
			}else if(auditResult.getAuditTypeId() == 2){//2、本部门实地调查的审批
				if(auditResult.getIsAgree()==0) map.put("statusId", 9);//是时审批通过修改案件状态为"本部门实地调查审批已通过"（9）
				else if(auditResult.getIsAgree()==1) map.put("statusId", 10);//否时审批通过修改案件状态为"本部门实地调查审批未通过"（10）
			}else if(auditResult.getAuditTypeId() == 3){//3、结案初审的审批
				if(auditResult.getIsAgree()==0) map.put("statusId", 15);//是时审批通过修改案件状态为"待结案终审"（15）
				else if(auditResult.getIsAgree()==1) map.put("statusId", 14);//否时审批通过修改案件状态为"结案初审未通过"（14）
			}else if(auditResult.getAuditTypeId() == 4){//4、结案终审的审批
				if(auditResult.getIsAgree()==0) map.put("statusId", 17);//是时审批通过修改案件状态为"已结案"（17）
				else if(auditResult.getIsAgree()==1) map.put("statusId", 16);//否时审批通过修改案件状态为"结案终审未通过"（16）
			}else if(auditResult.getAuditTypeId() == 5){//5、结案终审未通过的退回审批
				map.put("statusId", 14);//否时审批通过修改案件状态为"结案初审未通过"（14）
			}
			investigationServiceImpl.updateStatus(map);
			boolean sendSuccess = investigationServiceImpl.sendEmail(map);//发送案件进度邮件
			logger.info(sendSuccess?"给案件负责人发送邮件成功！":"给案件负责人发送邮件失败！");
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * @return the auditResult
	 */
	public AuditResult getAuditResult() {
		return auditResult;
	}
	/**
	 * @param auditResult the auditResult to set
	 */
	public void setAuditResult(AuditResult auditResult) {
		this.auditResult = auditResult;
	}
	/**
	 * @return the auditTypeId
	 */
	public Integer getAuditTypeId() {
		return auditTypeId;
	}
	/**
	 * @param auditTypeId the auditTypeId to set
	 */
	public void setAuditTypeId(Integer auditTypeId) {
		this.auditTypeId = auditTypeId;
	}
	/**
	 * @return the applysettlementId
	 */
	public Integer getApplysettlementId() {
		return applysettlementId;
	}
	/**
	 * @param applysettlementId the applysettlementId to set
	 */
	public void setApplysettlementId(Integer applysettlementId) {
		this.applysettlementId = applysettlementId;
	}
	/**
	 * @return the investigationId
	 */
	public Integer getInvestigationId() {
		return investigationId;
	}
	/**
	 * @param investigationId the investigationId to set
	 */
	public void setInvestigationId(Integer investigationId) {
		this.investigationId = investigationId;
	}
}
