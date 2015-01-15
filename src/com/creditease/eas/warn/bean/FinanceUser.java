package com.creditease.eas.warn.bean;

import java.util.Date;
/**
 * @FinanceUser.java	财务房租合同信息组用户
 * created at 2013-9-17 下午01:32:40 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class FinanceUser {

	/**主键id**/
	private Integer id;
	/**外键用户表id**/
	private Integer userId;
	/**外键财务房屋合同组表id**/
	private Integer financeGroupId;
	/**姓名**/
	private String userName;
	/**email**/
	private String userEmail;
	/**是否组长（0：组员；1：组长）**/
	private Integer isGroupLeader;
	/**所负责公司（多个公司以  ;  分开（目前保存公司的中文名字）**/
	private String chargeCompanies;

	/**记录创建人**/
    private String creator;
    /**记录创建时间**/
    private Date creatorTime;
    /**最后更新人姓名**/
    private String lastUpdater;
    /**最后更新时间**/
    private Date lastUpdateTime;
   
    /**备用字段1**/
    private String ext1;
    /**备用字段2**/
    private String ext2;
    /**备用字段3**/
    private String ext3;
    /**备用字段4**/
    private String ext4;
    /**备用字段5**/
    private String ext5;
    /**备用字段6**/
    private String ext6;
    /**备用字段7**/
    private String ext7;
    /**备用字段8**/
    private String ext8;
    /**备用字段9**/
    private String ext9;
    /**备用字段10**/
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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the financeGroupId
	 */
	public Integer getFinanceGroupId() {
		return financeGroupId;
	}
	/**
	 * @param financeGroupId the financeGroupId to set
	 */
	public void setFinanceGroupId(Integer financeGroupId) {
		this.financeGroupId = financeGroupId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the isGroupLeader
	 */
	public Integer getIsGroupLeader() {
		return isGroupLeader;
	}
	/**
	 * @param isGroupLeader the isGroupLeader to set
	 */
	public void setIsGroupLeader(Integer isGroupLeader) {
		this.isGroupLeader = isGroupLeader;
	}
	/**
	 * @return the chargeCompanies
	 */
	public String getChargeCompanies() {
		return chargeCompanies;
	}
	/**
	 * @param chargeCompanies the chargeCompanies to set
	 */
	public void setChargeCompanies(String chargeCompanies) {
		this.chargeCompanies = chargeCompanies;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return the creatorTime
	 */
	public Date getCreatorTime() {
		return creatorTime;
	}
	/**
	 * @param creatorTime the creatorTime to set
	 */
	public void setCreatorTime(Date creatorTime) {
		this.creatorTime = creatorTime;
	}
	/**
	 * @return the lastUpdater
	 */
	public String getLastUpdater() {
		return lastUpdater;
	}
	/**
	 * @param lastUpdater the lastUpdater to set
	 */
	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
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
}