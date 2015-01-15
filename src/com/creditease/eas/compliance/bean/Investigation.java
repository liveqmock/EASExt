package com.creditease.eas.compliance.bean;

import java.util.Date;

/**
 * @Investigation.java	合规初步调查实体bean
 * created at 2013-10-8 上午10:14:49 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Investigation {
    /**主键id**/
	private Integer id;
	/**外键案件id**/
	private Integer caseId;
	/**沟通时间**/
	private Date communicateTime;
	/**下一次沟通时间**/
	private Date nextCommunicateTime;
	/**沟通对象**/
	private String communicatePerson;
	/**沟通对象与投诉人的关系**/
	private String cpRelationWAccused;
	/**沟通内容**/
	private String content;
	/**调查方式外键id**/
	private Integer typeId;
	/**下一步方案外键id**/
	private Integer nextSchemeId;
	/**父id**/
	private Integer parentId;
	/**转出理由**/
	private String outReason;
	/**本部门实地调查：计划调查时间**/
	private Date preplanITime;
	/**本部门实地调查：调查方式**/
	private String bbmsddcfs;
	/**调查案件状态表外键id**/
	private Integer statusId;
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
	
	private String srcode;
	private String endcaseTime;
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
	 * @return the caseId
	 */
	public Integer getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the communicateTime
	 */
	public Date getCommunicateTime() {
		return communicateTime;
	}
	/**
	 * @param communicateTime the communicateTime to set
	 */
	public void setCommunicateTime(Date communicateTime) {
		this.communicateTime = communicateTime;
	}
	/**
	 * @return the communicatePerson
	 */
	public String getCommunicatePerson() {
		return communicatePerson;
	}
	/**
	 * @param communicatePerson the communicatePerson to set
	 */
	public void setCommunicatePerson(String communicatePerson) {
		this.communicatePerson = communicatePerson;
	}
	/**
	 * @return the cpRelationWAccused
	 */
	public String getCpRelationWAccused() {
		return cpRelationWAccused;
	}
	/**
	 * @param cpRelationWAccused the cpRelationWAccused to set
	 */
	public void setCpRelationWAccused(String cpRelationWAccused) {
		this.cpRelationWAccused = cpRelationWAccused;
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
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return the nextSchemeId
	 */
	public Integer getNextSchemeId() {
		return nextSchemeId;
	}
	/**
	 * @param nextSchemeId the nextSchemeId to set
	 */
	public void setNextSchemeId(Integer nextSchemeId) {
		this.nextSchemeId = nextSchemeId;
	}
	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the outReason
	 */
	public String getOutReason() {
		return outReason;
	}
	/**
	 * @param outReason the outReason to set
	 */
	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}
	/**
	 * @return the preplanITime
	 */
	public Date getPreplanITime() {
		return preplanITime;
	}
	/**
	 * @param preplanITime the preplanITime to set
	 */
	public void setPreplanITime(Date preplanITime) {
		this.preplanITime = preplanITime;
	}
	/**
	 * @return the bbmsddcfs
	 */
	public String getBbmsddcfs() {
		return bbmsddcfs;
	}
	/**
	 * @param bbmsddcfs the bbmsddcfs to set
	 */
	public void setBbmsddcfs(String bbmsddcfs) {
		this.bbmsddcfs = bbmsddcfs;
	}
	/**
	 * @return the statusId
	 */
	public Integer getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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
//	/**
//	 * @return the typeIds
//	 */
//	public List<Dictionary> getTypeIds() {
//		return typeIds;
//	}
//	/**
//	 * @param typeIds the typeIds to set
//	 */
//	public void setTypeIds(List<Dictionary> typeIds) {
//		this.typeIds = typeIds;
//	}
//	/**
//	 * @return the nextSchemeIds
//	 */
//	public List<Dictionary> getNextSchemeIds() {
//		return nextSchemeIds;
//	}
//	/**
//	 * @param nextSchemeIds the nextSchemeIds to set
//	 */
//	public void setNextSchemeIds(List<Dictionary> nextSchemeIds) {
//		this.nextSchemeIds = nextSchemeIds;
//	}
	/**
	 * @return the nextCommunicateTime
	 */
	public Date getNextCommunicateTime() {
		return nextCommunicateTime;
	}
	/**
	 * @param nextCommunicateTime the nextCommunicateTime to set
	 */
	public void setNextCommunicateTime(Date nextCommunicateTime) {
		this.nextCommunicateTime = nextCommunicateTime;
	}
	public String getSrcode() {
		return srcode;
	}
	public void setSrcode(String srcode) {
		this.srcode = srcode;
	}
	public String getEndcaseTime() {
		return endcaseTime;
	}
	public void setEndcaseTime(String endcaseTime) {
		this.endcaseTime = endcaseTime;
	}
	
}