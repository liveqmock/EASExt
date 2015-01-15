package com.creditease.eas.projectmanage.bean;

import java.util.Date;

public class AgreementNumber {
  private Integer fid;//自增fid
  private Integer receivableQuantity;//应收数量
  private Integer receivedNumber;//收到数量
  private Integer financialNumber;//财务保存数量
  private Integer archivesNumber;//档案处保存数量
  private Integer sendbackNumber;//寄回数量
  private String remarks;//备注
  private Integer projectFid;//贷款编号
  private Integer agreementFid;//协议id
  private Integer fcreateuserId;//创建人id
  private Date fcreateTime;//创建时间
  private Integer lastupdaterId;//最后修改人id
  private Date lastupdateTime;//最后修改时间
  /*扩展字段1-5*/
  private String fext1;
  private String fext2;
  private String fext3;
  private String fext4;
  private String fext5;
public Integer getFid() {
	return fid;
}
public void setFid(Integer fid) {
	this.fid = fid;
}
public Integer getReceivableQuantity() {
	return receivableQuantity;
}
public void setReceivableQuantity(Integer receivableQuantity) {
	this.receivableQuantity = receivableQuantity;
}
public Integer getReceivedNumber() {
	return receivedNumber;
}
public void setReceivedNumber(Integer receivedNumber) {
	this.receivedNumber = receivedNumber;
}
public Integer getFinancialNumber() {
	return financialNumber;
}
public void setFinancialNumber(Integer financialNumber) {
	this.financialNumber = financialNumber;
}
public Integer getArchivesNumber() {
	return archivesNumber;
}
public void setArchivesNumber(Integer archivesNumber) {
	this.archivesNumber = archivesNumber;
}
public Integer getSendbackNumber() {
	return sendbackNumber;
}
public void setSendbackNumber(Integer sendbackNumber) {
	this.sendbackNumber = sendbackNumber;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public Integer getProjectFid() {
	return projectFid;
}
public void setProjectFid(Integer projectFid) {
	this.projectFid = projectFid;
}
public Integer getAgreementFid() {
	return agreementFid;
}
public void setAgreementFid(Integer agreementFid) {
	this.agreementFid = agreementFid;
}
public Integer getFcreateuserId() {
	return fcreateuserId;
}
public void setFcreateuserId(Integer fcreateuserId) {
	this.fcreateuserId = fcreateuserId;
}
public Date getFcreateTime() {
	return fcreateTime;
}
public void setFcreateTime(Date fcreateTime) {
	this.fcreateTime = fcreateTime;
}
public Integer getLastupdaterId() {
	return lastupdaterId;
}
public void setLastupdaterId(Integer lastupdaterId) {
	this.lastupdaterId = lastupdaterId;
}
public Date getLastupdateTime() {
	return lastupdateTime;
}
public void setLastupdateTime(Date lastupdateTime) {
	this.lastupdateTime = lastupdateTime;
}
public String getFext1() {
	return fext1;
}
public void setFext1(String fext1) {
	this.fext1 = fext1;
}
public String getFext2() {
	return fext2;
}
public void setFext2(String fext2) {
	this.fext2 = fext2;
}
public String getFext3() {
	return fext3;
}
public void setFext3(String fext3) {
	this.fext3 = fext3;
}
public String getFext4() {
	return fext4;
}
public void setFext4(String fext4) {
	this.fext4 = fext4;
}
public String getFext5() {
	return fext5;
}
public void setFext5(String fext5) {
	this.fext5 = fext5;
}
public AgreementNumber() {
	super();
	// TODO Auto-generated constructor stub
}   
  
}
