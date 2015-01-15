package com.creditease.eas.projectmanage.bean;

import java.util.Date;

/**
 * @PmChange.java created at 2014-03-13 下午03:28:26 by admin
 * 
 */
public class PmChange {
	private Integer id;
	private String beforePm;// 修改前pm名称
	private Integer beforePmId;// 修改前pmId
	private String afterPm;// 修改后pm名称
	private Integer afterPmId;// 修改后pmID
	private String loanNumber;// 贷款编号
	private Integer fCreateUserId;// 创建人ID
	private String fCreateUserName;// 创建人名称
	private Date fCreateTime;// 创建时间

	// 扩展字段
	private String fext1;
	private String fext2;
	private String fext3;
	private String fext4;
	private String fext5;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBeforePm() {
		return beforePm;
	}

	public void setBeforePm(String beforePm) {
		this.beforePm = beforePm;
	}

	public Integer getBeforePmId() {
		return beforePmId;
	}

	public void setBeforePmId(Integer beforePmId) {
		this.beforePmId = beforePmId;
	}

	public String getAfterPm() {
		return afterPm;
	}

	public void setAfterPm(String afterPm) {
		this.afterPm = afterPm;
	}

	public Integer getAfterPmId() {
		return afterPmId;
	}

	public void setAfterPmId(Integer afterPmId) {
		this.afterPmId = afterPmId;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public Integer getfCreateUserId() {
		return fCreateUserId;
	}

	public void setfCreateUserId(Integer fCreateUserId) {
		this.fCreateUserId = fCreateUserId;
	}

	public String getfCreateUserName() {
		return fCreateUserName;
	}

	public void setfCreateUserName(String fCreateUserName) {
		this.fCreateUserName = fCreateUserName;
	}

	public Date getfCreateTime() {
		return fCreateTime;
	}

	public void setfCreateTime(Date fCreateTime) {
		this.fCreateTime = fCreateTime;
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

	public PmChange() {
		super();
	}

	public PmChange(Integer id, String beforePm, Integer beforePmId,
			String afterPm, Integer afterPmId, String loanNumber,
			Integer fCreateUserId, String fCreateUserName, Date fCreateTime,
			String fext1, String fext2, String fext3, String fext4, String fext5) {
		super();
		this.id = id;
		this.beforePm = beforePm;
		this.beforePmId = beforePmId;
		this.afterPm = afterPm;
		this.afterPmId = afterPmId;
		this.loanNumber = loanNumber;
		this.fCreateUserId = fCreateUserId;
		this.fCreateUserName = fCreateUserName;
		this.fCreateTime = fCreateTime;
		this.fext1 = fext1;
		this.fext2 = fext2;
		this.fext3 = fext3;
		this.fext4 = fext4;
		this.fext5 = fext5;
	}

}
