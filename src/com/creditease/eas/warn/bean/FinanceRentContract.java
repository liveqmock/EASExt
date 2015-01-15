package com.creditease.eas.warn.bean;

import java.util.Date;

/**
 * @FinanceRentContract.java	财务房租合同信息
 * created at 2013-9-16 上午09:55:00 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class FinanceRentContract {

	/**主键id**/
	private Integer id;
	/**使用部门编号（行政字段）**/
	private String orgNumber;
	/**使用部门（行政字段）**/
	private String orgName;
	/**地区（行政字段）**/
	private String city;
	/**办公室座落地点（行政字段）**/
	private String officeAdd;
	/**成本中心（行政字段）**/
	private String lastCostCenter;
	/**签署合同公司（财务字段）**/
	private String signatory;
	/**合同金额**/
	private String payMoney;
	/**合同月租金（财务字段）**/
	private String monthMoney;
	/**付款方式（行政字段）**/
	private String paymentCycle;
	/**付款次数（行政字段）**/
	private Integer payCount;
	/**开始日期（行政字段）**/
	private Date rentStartTime;
	/**结束日期（行政字段）**/
	private Date rentEndTime;
	/**租赁期限（月）（行政字段）**/
    private String rentYear;
    /**首付日期（财务字段）**/
    private Date payFirstTime;
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

    
    /**纸质合同编号（财务字段）**/
    private String contractNum;
    /**成本中心编号（财务字段）**/
    private String costCenterNum;
    /**付款部门（财务字段）**/
    private String payOrgName;
    /**汇入帐户（业主）（财务字段）**/
    private String toAccount;
    /**面积（平方米）（行政字段）**/
    private String areaSqm;
    /**房屋押金（财务字段）**/
    private String housingDeposit;
    /**物业押金（财务字段）**/
    private String propertyDeposit;
    /**免租期(月)（财务字段）**/
    private String rentFreePeriod;
    /**年租金（元）（财务字段）**/
    private String annualRent;
    /**状态**/
    private Integer status;
    /**备注*/
    private String remark;
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
	 * @return the orgNumber
	 */
	public String getOrgNumber() {
		return orgNumber;
	}
	/**
	 * @param orgNumber the orgNumber to set
	 */
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the officeAdd
	 */
	public String getOfficeAdd() {
		return officeAdd;
	}
	/**
	 * @param officeAdd the officeAdd to set
	 */
	public void setOfficeAdd(String officeAdd) {
		this.officeAdd = officeAdd;
	}
	/**
	 * @return the lastCostCenter
	 */
	public String getLastCostCenter() {
		return lastCostCenter;
	}
	/**
	 * @param lastCostCenter the lastCostCenter to set
	 */
	public void setLastCostCenter(String lastCostCenter) {
		this.lastCostCenter = lastCostCenter;
	}
	/**
	 * @return the signatory
	 */
	public String getSignatory() {
		return signatory;
	}
	/**
	 * @param signatory the signatory to set
	 */
	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}
	/**
	 * @return the payMoney
	 */
	public String getPayMoney() {
		return payMoney;
	}
	/**
	 * @param payMoney the payMoney to set
	 */
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	/**
	 * @return the monthMoney
	 */
	public String getMonthMoney() {
		return monthMoney;
	}
	/**
	 * @param monthMoney the monthMoney to set
	 */
	public void setMonthMoney(String monthMoney) {
		this.monthMoney = monthMoney;
	}
	/**
	 * @return the paymentCycle
	 */
	public String getPaymentCycle() {
		return paymentCycle;
	}
	/**
	 * @param paymentCycle the paymentCycle to set
	 */
	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	/**
	 * @return the payCount
	 */
	public Integer getPayCount() {
		return payCount;
	}
	/**
	 * @param payCount the payCount to set
	 */
	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}
	/**
	 * @return the rentStartTime
	 */
	public Date getRentStartTime() {
		return rentStartTime;
	}
	/**
	 * @param rentStartTime the rentStartTime to set
	 */
	public void setRentStartTime(Date rentStartTime) {
		this.rentStartTime = rentStartTime;
	}
	/**
	 * @return the rentEndTime
	 */
	public Date getRentEndTime() {
		return rentEndTime;
	}
	/**
	 * @param rentEndTime the rentEndTime to set
	 */
	public void setRentEndTime(Date rentEndTime) {
		this.rentEndTime = rentEndTime;
	}
	
	/**
	 * @return the rentYear
	 */
	public String getRentYear() {
		return rentYear;
	}
	/**
	 * @param rentYear the rentYear to set
	 */
	public void setRentYear(String rentYear) {
		this.rentYear = rentYear;
	}
	/**
	 * @return the payFirstTime
	 */
	public Date getPayFirstTime() {
		return payFirstTime;
	}
	/**
	 * @param payFirstTime the payFirstTime to set
	 */
	public void setPayFirstTime(Date payFirstTime) {
		this.payFirstTime = payFirstTime;
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
	/**
	 * @return the contractNum
	 */
	public String getContractNum() {
		return contractNum;
	}
	/**
	 * @param contractNum the contractNum to set
	 */
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	/**
	 * @return the costCenterNum
	 */
	public String getCostCenterNum() {
		return costCenterNum;
	}
	/**
	 * @param costCenterNum the costCenterNum to set
	 */
	public void setCostCenterNum(String costCenterNum) {
		this.costCenterNum = costCenterNum;
	}
	/**
	 * @return the payOrgName
	 */
	public String getPayOrgName() {
		return payOrgName;
	}
	/**
	 * @param payOrgName the payOrgName to set
	 */
	public void setPayOrgName(String payOrgName) {
		this.payOrgName = payOrgName;
	}
	/**
	 * @return the toAccount
	 */
	public String getToAccount() {
		return toAccount;
	}
	/**
	 * @param toAccount the toAccount to set
	 */
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	/**
	 * @return the areaSqm
	 */
	public String getAreaSqm() {
		return areaSqm;
	}
	/**
	 * @param areaSqm the areaSqm to set
	 */
	public void setAreaSqm(String areaSqm) {
		this.areaSqm = areaSqm;
	}
	/**
	 * @return the housingDeposit
	 */
	public String getHousingDeposit() {
		return housingDeposit;
	}
	/**
	 * @param housingDeposit the housingDeposit to set
	 */
	public void setHousingDeposit(String housingDeposit) {
		this.housingDeposit = housingDeposit;
	}
	/**
	 * @return the propertyDeposit
	 */
	public String getPropertyDeposit() {
		return propertyDeposit;
	}
	/**
	 * @param propertyDeposit the propertyDeposit to set
	 */
	public void setPropertyDeposit(String propertyDeposit) {
		this.propertyDeposit = propertyDeposit;
	}
	/**
	 * @return the rentFreePeriod
	 */
	public String getRentFreePeriod() {
		return rentFreePeriod;
	}
	/**
	 * @param rentFreePeriod the rentFreePeriod to set
	 */
	public void setRentFreePeriod(String rentFreePeriod) {
		this.rentFreePeriod = rentFreePeriod;
	}
	/**
	 * @return the annualRent
	 */
	public String getAnnualRent() {
		return annualRent;
	}
	/**
	 * @param annualRent the annualRent to set
	 */
	public void setAnnualRent(String annualRent) {
		this.annualRent = annualRent;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}