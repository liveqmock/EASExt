package com.creditease.eas.compliance.bean;

import java.util.Date;

/**
 * @Fieldsurvey.java	合规（初步调查——实地调查情况实体bean）
 * created at 2013-10-8 下午01:34:53 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Fieldsurvey {
    /**主键id**/
	private Integer id;
	/**实际调查时间**/
	private Date fieldsurveyTime;
	/**被调查人（包括个人和机构）**/
	private String investigated;
	/**被调查内容**/
	private String content;
	/**实地调查人（可以填写多人）**/
	private String suveyPersons;
	/**实地调查负责人**/
	private String suveyChargeMan;
	/**调查结果**/
	private String result;
	/**协助落实部门时间**/
	private Date xzlsbmTime;
	/**要求**/
	private String required;
	/**协助落实部门**/
	private String xzlsbm;
	/**协助落实部门负责人**/
	private String xzlsbmChargeMan;
	/**协助落实部门负责人邮箱**/
	private String email;
	/**协助部门反馈时间**/
	private Date xzbmfbTime;
	/**协助部门反馈结果**/
	private String xzbmfbResult;
	/**协助部门反馈结果类型id外键**/
	private Integer xzbmfbResultId;
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
	 * @return the fieldsurveyTime
	 */
	public Date getFieldsurveyTime() {
		return fieldsurveyTime;
	}
	/**
	 * @param fieldsurveyTime the fieldsurveyTime to set
	 */
	public void setFieldsurveyTime(Date fieldsurveyTime) {
		this.fieldsurveyTime = fieldsurveyTime;
	}
	/**
	 * @return the investigated
	 */
	public String getInvestigated() {
		return investigated;
	}
	/**
	 * @param investigated the investigated to set
	 */
	public void setInvestigated(String investigated) {
		this.investigated = investigated;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the xzlsbmTime
	 */
	public Date getXzlsbmTime() {
		return xzlsbmTime;
	}
	/**
	 * @param xzlsbmTime the xzlsbmTime to set
	 */
	public void setXzlsbmTime(Date xzlsbmTime) {
		this.xzlsbmTime = xzlsbmTime;
	}
	/**
	 * @return the required
	 */
	public String getRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(String required) {
		this.required = required;
	}
	/**
	 * @return the xzlsbm
	 */
	public String getXzlsbm() {
		return xzlsbm;
	}
	/**
	 * @param xzlsbm the xzlsbm to set
	 */
	public void setXzlsbm(String xzlsbm) {
		this.xzlsbm = xzlsbm;
	}
	/**
	 * @return the xzlsbmChargeMan
	 */
	public String getXzlsbmChargeMan() {
		return xzlsbmChargeMan;
	}
	/**
	 * @param xzlsbmChargeMan the xzlsbmChargeMan to set
	 */
	public void setXzlsbmChargeMan(String xzlsbmChargeMan) {
		this.xzlsbmChargeMan = xzlsbmChargeMan;
	}
	/**
	 * @return the xzbmfbTime
	 */
	public Date getXzbmfbTime() {
		return xzbmfbTime;
	}
	/**
	 * @param xzbmfbTime the xzbmfbTime to set
	 */
	public void setXzbmfbTime(Date xzbmfbTime) {
		this.xzbmfbTime = xzbmfbTime;
	}
	/**
	 * @return the xzbmfbResult
	 */
	public String getXzbmfbResult() {
		return xzbmfbResult;
	}
	/**
	 * @param xzbmfbResult the xzbmfbResult to set
	 */
	public void setXzbmfbResult(String xzbmfbResult) {
		this.xzbmfbResult = xzbmfbResult;
	}
	/**
	 * @return the xzbmfbResultId
	 */
	public Integer getXzbmfbResultId() {
		return xzbmfbResultId;
	}
	/**
	 * @param xzbmfbResultId the xzbmfbResultId to set
	 */
	public void setXzbmfbResultId(Integer xzbmfbResultId) {
		this.xzbmfbResultId = xzbmfbResultId;
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
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the suveyPersons
	 */
	public String getSuveyPersons() {
		return suveyPersons;
	}
	/**
	 * @param suveyPersons the suveyPersons to set
	 */
	public void setSuveyPersons(String suveyPersons) {
		this.suveyPersons = suveyPersons;
	}
	/**
	 * @return the suveyChargeMan
	 */
	public String getSuveyChargeMan() {
		return suveyChargeMan;
	}
	/**
	 * @param suveyChargeMan the suveyChargeMan to set
	 */
	public void setSuveyChargeMan(String suveyChargeMan) {
		this.suveyChargeMan = suveyChargeMan;
	}
}