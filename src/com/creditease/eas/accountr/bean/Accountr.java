package com.creditease.eas.accountr.bean;

import java.util.Date;

public class Accountr {
  private Integer fid;//自增id
  private String accountrNum;//报销编号（报销日期+流水号）
  private String employeesNum;//员工工号
  private String employeesName;//员工名称
  private Date accountrDate;//报销时间
  private String accountrMoney;//报销金额
  private String company;//所属公司
  private String department;//所属部门
  private String email;//邮箱
  private Integer status;//发送状态（0表示未发送，1表示发送失败，2表示发送成功）
  private String personalRemark;//个人备注
  private Integer fcreateuserId;//创建人id
  private String fcreateuserName;//创建人名称
  private Date fcreateTime;//创建时间
  private Integer lastupdaterId;//最后修改人id
  private Date lastupdateTime;//最后修改时间
  //扩展字段
  private String fext1;
  private String fext2;
  private String fext3;
  private String fext4;
  private String fext5;
  private String fext6;
  private String fext7;
  private String fext8;
  private String fext9;
  private String fext10;
public Integer getFid() {
	return fid;
}
public void setFid(Integer fid) {
	this.fid = fid;
}
public String getAccountrNum() {
	return accountrNum;
}
public void setAccountrNum(String accountrNum) {
	this.accountrNum = accountrNum;
}
public String getEmployeesNum() {
	return employeesNum;
}
public void setEmployeesNum(String employeesNum) {
	this.employeesNum = employeesNum;
}
public String getEmployeesName() {
	return employeesName;
}
public void setEmployeesName(String employeesName) {
	this.employeesName = employeesName;
}
public Date getAccountrDate() {
	return accountrDate;
}
public void setAccountrDate(Date accountrDate) {
	this.accountrDate = accountrDate;
}
public String getAccountrMoney() {
	return accountrMoney;
}
public void setAccountrMoney(String accountrMoney) {
	this.accountrMoney = accountrMoney;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public String getPersonalRemark() {
	return personalRemark;
}
public void setPersonalRemark(String personalRemark) {
	this.personalRemark = personalRemark;
}
public Integer getFcreateuserId() {
	return fcreateuserId;
}
public void setFcreateuserId(Integer fcreateuserId) {
	this.fcreateuserId = fcreateuserId;
}
public String getFcreateuserName() {
	return fcreateuserName;
}
public void setFcreateuserName(String fcreateuserName) {
	this.fcreateuserName = fcreateuserName;
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
public String getFext6() {
	return fext6;
}
public void setFext6(String fext6) {
	this.fext6 = fext6;
}
public String getFext7() {
	return fext7;
}
public void setFext7(String fext7) {
	this.fext7 = fext7;
}
public String getFext8() {
	return fext8;
}
public void setFext8(String fext8) {
	this.fext8 = fext8;
}
public String getFext9() {
	return fext9;
}
public void setFext9(String fext9) {
	this.fext9 = fext9;
}
public String getFext10() {
	return fext10;
}
public void setFext10(String fext10) {
	this.fext10 = fext10;
}
public Accountr() {
	super();
	// TODO Auto-generated constructor stub
}
  
}
