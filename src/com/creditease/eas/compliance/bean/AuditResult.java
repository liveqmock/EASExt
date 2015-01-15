package com.creditease.eas.compliance.bean;

import java.util.Date;

import com.creditease.eas.admin.bean.User;

/**
 * @Auditresult.java	合规（初步调查——审核结果实体bean）
 * created at 2013-10-8 上午11:31:43 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class AuditResult {
    /**主键id**/
	private Integer id;
	/**是否同意（0：是；1：否）**/
	private Integer isAgree;
	/**审批意见**/
	private String opinion;
	/**审核项目类型：1、转部门协助的审批2、本部门实地调查的审批
		3、结案初审的审批4、结案终审的审批5、结案终审未通过**/
	private Integer auditTypeId;
	/**申请结案外键id**/
	private Integer applysettlementId;
	/**初步调查表外键id**/
	private Integer investigationId;
	/**用户表外键id（创建人id）**/
	private Integer createrId;
	/**创建时间**/
	private Date createTime;
	/**用户表外键id（最后修改人id）**/
	private Integer lastUpdaterId;
	/**最后修改时间**/
	private Date lastUpdateTime;
	/**扩展字段1**/
	private String ext1;
	/**扩展字段2**/
	private String ext2;
	/**扩展字段3**/
	private String ext3;
	/**扩展字段4**/
	private String ext4;
	/**扩展字段5**/
	private String ext5;
	/**扩展字段6**/
	private String ext6;
	/**扩展字段7**/
	private String ext7;
	/**扩展字段8**/
	private String ext8;
	/**扩展字段9**/
	private String ext9;
	/**扩展字段10**/
	private String ext10;
	private User user;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the isAgree
	 */
	public Integer getIsAgree() {
		return isAgree;
	}
	/**
	 * @param isAgree the isAgree to set
	 */
	public void setIsAgree(Integer isAgree) {
		this.isAgree = isAgree;
	}
	/**
	 * @return the opinion
	 */
	public String getOpinion() {
		return opinion;
	}
	/**
	 * @param opinion the opinion to set
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
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
	/**
	 * @return the createrId
	 */
	public Integer getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the lastUpdaterId
	 */
	public Integer getLastUpdaterId() {
		return lastUpdaterId;
	}
	/**
	 * @param lastUpdaterId the lastUpdaterId to set
	 */
	public void setLastUpdaterId(Integer lastUpdaterId) {
		this.lastUpdaterId = lastUpdaterId;
	}
	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * @return the ext1
	 */
	public String getExt1() {
		return ext1;
	}
	/**
	 * @param ext1 the ext1 to set
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	/**
	 * @return the ext2
	 */
	public String getExt2() {
		return ext2;
	}
	/**
	 * @param ext2 the ext2 to set
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	/**
	 * @return the ext3
	 */
	public String getExt3() {
		return ext3;
	}
	/**
	 * @param ext3 the ext3 to set
	 */
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	/**
	 * @return the ext4
	 */
	public String getExt4() {
		return ext4;
	}
	/**
	 * @param ext4 the ext4 to set
	 */
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	/**
	 * @return the ext5
	 */
	public String getExt5() {
		return ext5;
	}
	/**
	 * @param ext5 the ext5 to set
	 */
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	/**
	 * @return the ext6
	 */
	public String getExt6() {
		return ext6;
	}
	/**
	 * @param ext6 the ext6 to set
	 */
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}
	/**
	 * @return the ext7
	 */
	public String getExt7() {
		return ext7;
	}
	/**
	 * @param ext7 the ext7 to set
	 */
	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}
	/**
	 * @return the ext8
	 */
	public String getExt8() {
		return ext8;
	}
	/**
	 * @param ext8 the ext8 to set
	 */
	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}
	/**
	 * @return the ext9
	 */
	public String getExt9() {
		return ext9;
	}
	/**
	 * @param ext9 the ext9 to set
	 */
	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}
	/**
	 * @return the ext10
	 */
	public String getExt10() {
		return ext10;
	}
	/**
	 * @param ext10 the ext10 to set
	 */
	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}