package com.creditease.eas.accountr.bean;

import java.util.Date;

public class AccountrLog {
	 private Integer fid;//自增id
	  private String accountrNum;//报销编号（报销日期+流水号）
	  private String employeesNum;//员工工号
	  private String employeesName;//员工名称
	  private Date accountrDate;//报销时间
	  private String accountrMoney;//报销金额
	  private String company;//所属公司
	  private String department;//所属部门
	  private String email;//邮箱
	  private String personalRemark;//个人备注
	  private Integer fcreateuserId;//创建人id
	  private String fcreateuserName;//创建人名称
	  private Date fcreateTime;//创建时间
	  private String fext1;
	  private String fext2;
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
	public AccountrLog() {
		super();
		// TODO Auto-generated constructor stub
	}

}
