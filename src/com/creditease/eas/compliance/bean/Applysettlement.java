package com.creditease.eas.compliance.bean;

import java.util.Date;

/**
 * @Applysettlement.java	合规（申请结案记录实体bean）
 * created at 2013-10-8 上午10:15:10 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Applysettlement {
    /**主键id**/
	private Integer id;
	/**申请人**/
	private String applyPerson;
	/**申请时间**/
	private Date applyTime;
	/**案件处理方案**/
	private String arocessingScheme;
	/**是否违规外键id**/
	private Integer outofLineId;
	/**理由**/
	private String reason;
	/**违规级别外键id**/
	private Integer outofLineLevelId;
	/**被投诉人**/
	private String complainants;
	/**直接领导姓名**/
	private String directChargeMan;
	/**直接领导处罚结果**/
	private String dcmpResult;
	/**间接领导姓名**/
	private String indirectChargeMan;
	/**间接领导处罚结果**/
	private String idcmpResult;
	/**对投诉人解决方案**/
	private String complainantSolution;
	/**对被投诉人处罚结果**/
	private String cspResult;
	/**转部门协助调查反馈结果表外键id**/
	private Integer feedBackResultId;
	/**再次协助落实表外键id**/
	private Integer zcxzlsId;
	/**初步调查表外键id**/
	private Integer investigationId;
	/**本部门实地调查表外键id**/
	private Integer fieldSurveyId;
	/**特殊结案归类外键id**/
	private Integer seClasssifyId;
	/**特殊结案归类其他的备注**/
	private String seClasssifyOther;
	/**理由（此项为文本框，填写具体的结案原因）**/
	private String seReason;
	/**创建人id用户表外键**/
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
	/**是否特殊结案（0：否；1：是）**/
	private Integer specialEndCase;
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
	 * @return the applyPerson
	 */
	public String getApplyPerson() {
		return applyPerson;
	}
	/**
	 * @param applyPerson the applyPerson to set
	 */
	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}
	/**
	 * @return the applyTime
	 */
	public Date getApplyTime() {
		return applyTime;
	}
	/**
	 * @param applyTime the applyTime to set
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * @return the arocessingScheme
	 */
	public String getArocessingScheme() {
		return arocessingScheme;
	}
	/**
	 * @param arocessingScheme the arocessingScheme to set
	 */
	public void setArocessingScheme(String arocessingScheme) {
		this.arocessingScheme = arocessingScheme;
	}
	/**
	 * @return the outofLineId
	 */
	public Integer getOutofLineId() {
		return outofLineId;
	}
	/**
	 * @param outofLineId the outofLineId to set
	 */
	public void setOutofLineId(Integer outofLineId) {
		this.outofLineId = outofLineId;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the outofLineLevelId
	 */
	public Integer getOutofLineLevelId() {
		return outofLineLevelId;
	}
	/**
	 * @param outofLineLevelId the outofLineLevelId to set
	 */
	public void setOutofLineLevelId(Integer outofLineLevelId) {
		this.outofLineLevelId = outofLineLevelId;
	}
	/**
	 * @return the complainants
	 */
	public String getComplainants() {
		return complainants;
	}
	/**
	 * @param complainants the complainants to set
	 */
	public void setComplainants(String complainants) {
		this.complainants = complainants;
	}
	/**
	 * @return the directChargeMan
	 */
	public String getDirectChargeMan() {
		return directChargeMan;
	}
	/**
	 * @param directChargeMan the directChargeMan to set
	 */
	public void setDirectChargeMan(String directChargeMan) {
		this.directChargeMan = directChargeMan;
	}
	/**
	 * @return the dcmpResult
	 */
	public String getDcmpResult() {
		return dcmpResult;
	}
	/**
	 * @param dcmpResult the dcmpResult to set
	 */
	public void setDcmpResult(String dcmpResult) {
		this.dcmpResult = dcmpResult;
	}
	/**
	 * @return the indirectChargeMan
	 */
	public String getIndirectChargeMan() {
		return indirectChargeMan;
	}
	/**
	 * @param indirectChargeMan the indirectChargeMan to set
	 */
	public void setIndirectChargeMan(String indirectChargeMan) {
		this.indirectChargeMan = indirectChargeMan;
	}
	/**
	 * @return the idcmpResult
	 */
	public String getIdcmpResult() {
		return idcmpResult;
	}
	/**
	 * @param idcmpResult the idcmpResult to set
	 */
	public void setIdcmpResult(String idcmpResult) {
		this.idcmpResult = idcmpResult;
	}
	/**
	 * @return the complainantSolution
	 */
	public String getComplainantSolution() {
		return complainantSolution;
	}
	/**
	 * @param complainantSolution the complainantSolution to set
	 */
	public void setComplainantSolution(String complainantSolution) {
		this.complainantSolution = complainantSolution;
	}
	/**
	 * @return the cspResult
	 */
	public String getCspResult() {
		return cspResult;
	}
	/**
	 * @param cspResult the cspResult to set
	 */
	public void setCspResult(String cspResult) {
		this.cspResult = cspResult;
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
	 * @return the feedBackResultId
	 */
	public Integer getFeedBackResultId() {
		return feedBackResultId;
	}
	/**
	 * @param feedBackResultId the feedBackResultId to set
	 */
	public void setFeedBackResultId(Integer feedBackResultId) {
		this.feedBackResultId = feedBackResultId;
	}
	/**
	 * @return the zcxzlsId
	 */
	public Integer getZcxzlsId() {
		return zcxzlsId;
	}
	/**
	 * @param zcxzlsId the zcxzlsId to set
	 */
	public void setZcxzlsId(Integer zcxzlsId) {
		this.zcxzlsId = zcxzlsId;
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
	 * @return the fieldSurveyId
	 */
	public Integer getFieldSurveyId() {
		return fieldSurveyId;
	}
	/**
	 * @param fieldSurveyId the fieldSurveyId to set
	 */
	public void setFieldSurveyId(Integer fieldSurveyId) {
		this.fieldSurveyId = fieldSurveyId;
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
	/**
	 * @return the seClasssifyId
	 */
	public Integer getSeClasssifyId() {
		return seClasssifyId;
	}
	/**
	 * @param seClasssifyId the seClasssifyId to set
	 */
	public void setSeClasssifyId(Integer seClasssifyId) {
		this.seClasssifyId = seClasssifyId;
	}
	/**
	 * @return the seClasssifyOther
	 */
	public String getSeClasssifyOther() {
		return seClasssifyOther;
	}
	/**
	 * @param seClasssifyOther the seClasssifyOther to set
	 */
	public void setSeClasssifyOther(String seClasssifyOther) {
		this.seClasssifyOther = seClasssifyOther;
	}
	/**
	 * @return the seReason
	 */
	public String getSeReason() {
		return seReason;
	}
	/**
	 * @param seReason the seReason to set
	 */
	public void setSeReason(String seReason) {
		this.seReason = seReason;
	}
	/**
	 * @return the specialEndCase
	 */
	public Integer getSpecialEndCase() {
		return specialEndCase;
	}
	/**
	 * @param specialEndCase the specialEndCase to set
	 */
	public void setSpecialEndCase(Integer specialEndCase) {
		this.specialEndCase = specialEndCase;
	}
}