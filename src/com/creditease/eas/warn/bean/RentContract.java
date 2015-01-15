package com.creditease.eas.warn.bean;

import java.util.Date;


/**
 * @deprecated 房租合同信息bean
 * @info created at 2013-08-01 下午05:04:08 by caoyong
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class RentContract {

	/**主键id**/
	private Integer id;
	/**租赁期限（月）（行政字段）**/
    private String rentyear;
    /**合同金额**/
	private String paymoney;
	/**合同月租金（财务字段）**/
	private String monthmoney;
	/**房租序号**/
    private String rentnumber;
    /**机构序号**/
    private String orgnumber;
    /**使用部门（行政字段）**/
    private String orgname;
    /**地区（行政字段）**/
    private String city;
    /**办公室座落地点（行政字段）**/
    private String officeadd;
    /**成本中心（行政字段）**/
    private String lastcostcenter;
    /**签约方（行政字段）**/
    private String signatory;
    /**付款周期**/
    private String paymentcycle;
    /**付款方式**/
    private String paytype;
    /**付款次数（行政字段）**/
    private Integer paycount;
    /**开始日期（行政字段）**/
    private String rentstarttime;
    /**结束日期（行政字段）**/
    private String rentendtime;
    /**首付日期（财务字段）**/
    private String payfirsttime;
    /**记录创建人**/
    private String creator;
    /**记录创建时间**/
    private Date creatortime;
    /**最后更新人姓名**/
    private String lastupdater;
    /**最后更新时间**/
    private Date lastupdatetime;
    /**备用字段1(标识财富端（2），普惠端（1）记录)**/
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
    private String costcenterNum;
    /**付款部门（财务字段）**/
    private String payorgName;
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
    private String status;
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
	 * @return the rentyear
	 */
	public String getRentyear() {
		return rentyear;
	}
	/**
	 * @param rentyear the rentyear to set
	 */
	public void setRentyear(String rentyear) {
		this.rentyear = rentyear;
	}
	/**
	 * @return the paymoney
	 */
	public String getPaymoney() {
		return paymoney;
	}
	/**
	 * @param paymoney the paymoney to set
	 */
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	/**
	 * @return the monthmoney
	 */
	public String getMonthmoney() {
		return monthmoney;
	}
	/**
	 * @param monthmoney the monthmoney to set
	 */
	public void setMonthmoney(String monthmoney) {
		this.monthmoney = monthmoney;
	}
	/**
	 * @return the rentnumber
	 */
	public String getRentnumber() {
		return rentnumber;
	}
	/**
	 * @param rentnumber the rentnumber to set
	 */
	public void setRentnumber(String rentnumber) {
		this.rentnumber = rentnumber;
	}
	/**
	 * @return the orgnumber
	 */
	public String getOrgnumber() {
		return orgnumber;
	}
	/**
	 * @param orgnumber the orgnumber to set
	 */
	public void setOrgnumber(String orgnumber) {
		this.orgnumber = orgnumber;
	}
	/**
	 * @return the orgname
	 */
	public String getOrgname() {
		return orgname;
	}
	/**
	 * @param orgname the orgname to set
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
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
	 * @return the officeadd
	 */
	public String getOfficeadd() {
		return officeadd;
	}
	/**
	 * @param officeadd the officeadd to set
	 */
	public void setOfficeadd(String officeadd) {
		this.officeadd = officeadd;
	}
	/**
	 * @return the lastcostcenter
	 */
	public String getLastcostcenter() {
		return lastcostcenter;
	}
	/**
	 * @param lastcostcenter the lastcostcenter to set
	 */
	public void setLastcostcenter(String lastcostcenter) {
		this.lastcostcenter = lastcostcenter;
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
	 * @return the paymentcycle
	 */
	public String getPaymentcycle() {
		return paymentcycle;
	}
	/**
	 * @param paymentcycle the paymentcycle to set
	 */
	public void setPaymentcycle(String paymentcycle) {
		this.paymentcycle = paymentcycle;
	}
	/**
	 * @return the paytype
	 */
	public String getPaytype() {
		return paytype;
	}
	/**
	 * @param paytype the paytype to set
	 */
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	/**
	 * @return the paycount
	 */
	public Integer getPaycount() {
		return paycount;
	}
	/**
	 * @param paycount the paycount to set
	 */
	public void setPaycount(Integer paycount) {
		this.paycount = paycount;
	}
	/**
	 * @return the rentstarttime
	 */
	public String getRentstarttime() {
		return rentstarttime;
	}
	/**
	 * @param rentstarttime the rentstarttime to set
	 */
	public void setRentstarttime(String rentstarttime) {
		this.rentstarttime = rentstarttime;
	}
	/**
	 * @return the rentendtime
	 */
	public String getRentendtime() {
		return rentendtime;
	}
	/**
	 * @param rentendtime the rentendtime to set
	 */
	public void setRentendtime(String rentendtime) {
		this.rentendtime = rentendtime;
	}
	/**
	 * @return the payfirsttime
	 */
	public String getPayfirsttime() {
		return payfirsttime;
	}
	/**
	 * @param payfirsttime the payfirsttime to set
	 */
	public void setPayfirsttime(String payfirsttime) {
		this.payfirsttime = payfirsttime;
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
	 * @return the creatortime
	 */
	public Date getCreatortime() {
		return creatortime;
	}
	/**
	 * @param creatortime the creatortime to set
	 */
	public void setCreatortime(Date creatortime) {
		this.creatortime = creatortime;
	}
	/**
	 * @return the lastupdater
	 */
	public String getLastupdater() {
		return lastupdater;
	}
	/**
	 * @param lastupdater the lastupdater to set
	 */
	public void setLastupdater(String lastupdater) {
		this.lastupdater = lastupdater;
	}
	/**
	 * @return the lastupdatetime
	 */
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	/**
	 * @param lastupdatetime the lastupdatetime to set
	 */
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
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
	 * @return the costcenterNum
	 */
	public String getCostcenterNum() {
		return costcenterNum;
	}
	/**
	 * @param costcenterNum the costcenterNum to set
	 */
	public void setCostcenterNum(String costcenterNum) {
		this.costcenterNum = costcenterNum;
	}
	/**
	 * @return the payorgName
	 */
	public String getPayorgName() {
		return payorgName;
	}
	/**
	 * @param payorgName the payorgName to set
	 */
	public void setPayorgName(String payorgName) {
		this.payorgName = payorgName;
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
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
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