package com.creditease.eas.finance.bean;

import java.io.Serializable;
import java.util.Date;

/***
 * 核算主体
 * @Company.java
 * created at 2013-5-2 下午09:01:48 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Company implements Serializable{
	private String  fname;//	公司名称
	private String  fnumber;//	公司名称
	private String  fisGrouping;//	是否是组
	private Date  feffectDate; //	生效日期
	private Date  finvalidDate;//	实效日期
	private String  fbankID;//	银行账户
	private String  faddressID;//	账户地址
	private String  accountNo;//	账号
	private String  accountName;//	账户名称
	private String	faccountTableID;//	科目id
	private String	fjuridicalPersonID;//	法人代表id
	private String	fisFreeze;//	是否冻结
	private String	fisCompanyOrgUnit;//	是否是财务组织
	private String	fisAdminOrgUnit;//	是否是行政组织
	private String	fisSaleOrgUnit;//	是否是销售组织
	private String	fisPurchaseOrgUnit;//	是否是采购组织
	private String	fisStorageOrgUnit;//	是否是库存组织
	private String	fisProfitOrgUnit;//	是否是利润中心
	private String	fisCostOrgUnit;//	是否是成本中心
	private String	fisUnion;//	是否合并范围
	private String	fisHROrgUnit;//	是否是HR组织单元
	private String	fisTransportOrgUnit;//	是否是发运组织单元
	private String	fisQualityOrgUnit;//	是否是质检组织单元
	private String	fisStart;//	是否启用
	private String	fisOUSealUp;//	是否封存
	private String	fisLeaf;//	是否叶子节点
	private String	flevel;//	级次
	private String	forgTypeStr;//	组织类型信息
	private String	fdescription;//	描述
	private String	fsimpleName;//	简称
	private String	fbaseExgTableID;//	基本核算汇率表
	private String	fadjustExgTableID;//	期末调汇汇率表
	private String	fbaseCurrencyIDName;//	本位币名称
	private String	fbaseCurrencyIDCode;//	本位币编码
	private String fPeriodTypename;//会计期间类型名称
	private String fisBizUnit;//是否是实体组织
	
	private Integer	status;//	是否封存
	
	 
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFisBizUnit() {
		return fisBizUnit;
	}
	public void setFisBizUnit(String fisBizUnit) {
		this.fisBizUnit = fisBizUnit;
	}
	public String getfPeriodTypename() {
		return fPeriodTypename;
	}
	public void setfPeriodTypename(String fPeriodTypename) {
		this.fPeriodTypename = fPeriodTypename;
	}
	public String getfPeriodTypenumber() {
		return fPeriodTypenumber;
	}
	public void setfPeriodTypenumber(String fPeriodTypenumber) {
		this.fPeriodTypenumber = fPeriodTypenumber;
	}
	private String fPeriodTypenumber;//会计期间类型编码
	public String getFparentNumber() {
		return fparentNumber;
	}
	public void setFparentNumber(String fparentNumber) {
		this.fparentNumber = fparentNumber;
	}
	private String	freportCurrencyIDName;//	报告币名称
	private String	freportCurrencyIDCode;//	报告币编码
	private String  freportExgTableID;
	private String  fparentNumber;//上级组织编码
	public String getFreportExgTableID() {
		return freportExgTableID;
	}
	public void setFreportExgTableID(String freportExgTableID) {
		this.freportExgTableID = freportExgTableID;
	}
	private String	errCode;//异常编码（0000：正常，0001：异常)
	private String	errMessage;//异常信息
	private String	ext1;//		扩展字段一
	private String	ext2;//		扩展字段二
	private String	ext3;//		扩展字段三
	private String	ext4;//		扩展字段四
	private String	ext5;//		扩展字段五
	private String	ext6;//		扩展字段六
	private String	ext7;//		扩展字段七
	private String	ext8;//		扩展字段八
	private String	ext9;//		扩展字段九
	private String	ext10;//扩展字段十
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFisGrouping() {
		return fisGrouping;
	}
	public void setFisGrouping(String fisGrouping) {
		this.fisGrouping = fisGrouping;
	}
	public Date getFeffectDate() {
		return feffectDate;
	}
	public void setFeffectDate(Date feffectDate) {
		this.feffectDate = feffectDate;
	}
	public Date getFinvalidDate() {
		return finvalidDate;
	}
	public void setFinvalidDate(Date finvalidDate) {
		this.finvalidDate = finvalidDate;
	}
	public String getFbankID() {
		return fbankID;
	}
	public void setFbankID(String fbankID) {
		this.fbankID = fbankID;
	}
	public String getFaddressID() {
		return faddressID;
	}
	public void setFaddressID(String faddressID) {
		this.faddressID = faddressID;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getFaccountTableID() {
		return faccountTableID;
	}
	public void setFaccountTableID(String faccountTableID) {
		this.faccountTableID = faccountTableID;
	}
	public String getFjuridicalPersonID() {
		return fjuridicalPersonID;
	}
	public void setFjuridicalPersonID(String fjuridicalPersonID) {
		this.fjuridicalPersonID = fjuridicalPersonID;
	}
	public String getFisFreeze() {
		return fisFreeze;
	}
	public void setFisFreeze(String fisFreeze) {
		this.fisFreeze = fisFreeze;
	}
	public String getFisCompanyOrgUnit() {
		return fisCompanyOrgUnit;
	}
	public void setFisCompanyOrgUnit(String fisCompanyOrgUnit) {
		this.fisCompanyOrgUnit = fisCompanyOrgUnit;
	}
	public String getFisAdminOrgUnit() {
		return fisAdminOrgUnit;
	}
	public void setFisAdminOrgUnit(String fisAdminOrgUnit) {
		this.fisAdminOrgUnit = fisAdminOrgUnit;
	}
	public String getFisSaleOrgUnit() {
		return fisSaleOrgUnit;
	}
	public void setFisSaleOrgUnit(String fisSaleOrgUnit) {
		this.fisSaleOrgUnit = fisSaleOrgUnit;
	}
	public String getFisPurchaseOrgUnit() {
		return fisPurchaseOrgUnit;
	}
	public void setFisPurchaseOrgUnit(String fisPurchaseOrgUnit) {
		this.fisPurchaseOrgUnit = fisPurchaseOrgUnit;
	}
	public String getFisStorageOrgUnit() {
		return fisStorageOrgUnit;
	}
	public void setFisStorageOrgUnit(String fisStorageOrgUnit) {
		this.fisStorageOrgUnit = fisStorageOrgUnit;
	}
	public String getFisProfitOrgUnit() {
		return fisProfitOrgUnit;
	}
	public void setFisProfitOrgUnit(String fisProfitOrgUnit) {
		this.fisProfitOrgUnit = fisProfitOrgUnit;
	}
	public String getFisCostOrgUnit() {
		return fisCostOrgUnit;
	}
	public void setFisCostOrgUnit(String fisCostOrgUnit) {
		this.fisCostOrgUnit = fisCostOrgUnit;
	}
	public String getFisUnion() {
		return fisUnion;
	}
	public void setFisUnion(String fisUnion) {
		this.fisUnion = fisUnion;
	}
	public String getFisHROrgUnit() {
		return fisHROrgUnit;
	}
	public void setFisHROrgUnit(String fisHROrgUnit) {
		this.fisHROrgUnit = fisHROrgUnit;
	}
	public String getFisTransportOrgUnit() {
		return fisTransportOrgUnit;
	}
	public void setFisTransportOrgUnit(String fisTransportOrgUnit) {
		this.fisTransportOrgUnit = fisTransportOrgUnit;
	}
	public String getFisQualityOrgUnit() {
		return fisQualityOrgUnit;
	}
	public void setFisQualityOrgUnit(String fisQualityOrgUnit) {
		this.fisQualityOrgUnit = fisQualityOrgUnit;
	}
	public String getFisStart() {
		return fisStart;
	}
	public void setFisStart(String fisStart) {
		this.fisStart = fisStart;
	}
	public String getFisOUSealUp() {
		return fisOUSealUp;
	}
	public void setFisOUSealUp(String fisOUSealUp) {
		this.fisOUSealUp = fisOUSealUp;
	}
	public String getFisLeaf() {
		return fisLeaf;
	}
	public void setFisLeaf(String fisLeaf) {
		this.fisLeaf = fisLeaf;
	}
	public String getFlevel() {
		return flevel;
	}
	public void setFlevel(String flevel) {
		this.flevel = flevel;
	}
	public String getForgTypeStr() {
		return forgTypeStr;
	}
	public void setForgTypeStr(String forgTypeStr) {
		this.forgTypeStr = forgTypeStr;
	}
	public String getFdescription() {
		return fdescription;
	}
	public void setFdescription(String fdescription) {
		this.fdescription = fdescription;
	}
	public String getFsimpleName() {
		return fsimpleName;
	}
	public void setFsimpleName(String fsimpleName) {
		this.fsimpleName = fsimpleName;
	}
	public String getFbaseExgTableID() {
		return fbaseExgTableID;
	}
	public void setFbaseExgTableID(String fbaseExgTableID) {
		this.fbaseExgTableID = fbaseExgTableID;
	}
	public String getFadjustExgTableID() {
		return fadjustExgTableID;
	}
	public void setFadjustExgTableID(String fadjustExgTableID) {
		this.fadjustExgTableID = fadjustExgTableID;
	}
	public String getFbaseCurrencyIDName() {
		return fbaseCurrencyIDName;
	}
	public void setFbaseCurrencyIDName(String fbaseCurrencyIDName) {
		this.fbaseCurrencyIDName = fbaseCurrencyIDName;
	}
	public String getFbaseCurrencyIDCode() {
		return fbaseCurrencyIDCode;
	}
	public void setFbaseCurrencyIDCode(String fbaseCurrencyIDCode) {
		this.fbaseCurrencyIDCode = fbaseCurrencyIDCode;
	}
	public String getFreportCurrencyIDName() {
		return freportCurrencyIDName;
	}
	public void setFreportCurrencyIDName(String freportCurrencyIDName) {
		this.freportCurrencyIDName = freportCurrencyIDName;
	}
	public String getFreportCurrencyIDCode() {
		return freportCurrencyIDCode;
	}
	public void setFreportCurrencyIDCode(String freportCurrencyIDCode) {
		this.freportCurrencyIDCode = freportCurrencyIDCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	public String getExt6() {
		return ext6;
	}
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}
	public String getExt7() {
		return ext7;
	}
	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}
	public String getExt8() {
		return ext8;
	}
	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}
	public String getExt9() {
		return ext9;
	}
	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}
	public String getExt10() {
		return ext10;
	}
	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}
	private Date flastUpdateTime;
	public Date getFlastUpdateTime() {
		return flastUpdateTime;
	}
	public void setFlastUpdateTime(Date flastUpdateTime) {
		this.flastUpdateTime = flastUpdateTime;
	}
}
