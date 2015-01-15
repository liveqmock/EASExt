package com.creditease.eas.projectmanage.bean;

import java.util.Date;

/**
 * created at 2014-03-20 下午03:28:26 by admin
 * 
 */
public class EmailHistory {
	private Integer id;
	// 1：结清报告；2：到期还款提醒；3：存续期还款提醒；4：紧急！！！逾期报告；5：紧急！！！逾期报告(含本金)
	private String warnType;// 预警类型
	private String loanNumber;// 贷款编号
	private String head;// 负责人(PM)
	private Date loanTime;// 出借日
	private Date expireTime;// 到期日
	private Date interestTime;// 利息返还日
	private Date sendDate;// 发送时间
	private String sendState;// 发送状态 1表示成功，0表示失败
	// 扩展字段
	private String fext1;
	private String fext2;
	private String fext3;
	private String fext4;
	private String fext5;

	/**
	 * 
	 */
	public EmailHistory() {
		super();
	}

	/**
	 * @param id
	 * @param warnType
	 * @param loanNumber
	 * @param head
	 * @param loanTime
	 * @param expireTime
	 * @param interestTime
	 * @param sendDate
	 * @param sendState
	 * @param fext1
	 * @param fext2
	 * @param fext3
	 * @param fext4
	 * @param fext5
	 */
	public EmailHistory(Integer id, String warnType, String loanNumber,
			String head, Date loanTime, Date expireTime, Date interestTime,
			Date sendDate, String sendState, String fext1, String fext2,
			String fext3, String fext4, String fext5) {
		super();
		this.id = id;
		this.warnType = warnType;
		this.loanNumber = loanNumber;
		this.head = head;
		this.loanTime = loanTime;
		this.expireTime = expireTime;
		this.interestTime = interestTime;
		this.sendDate = sendDate;
		this.sendState = sendState;
		this.fext1 = fext1;
		this.fext2 = fext2;
		this.fext3 = fext3;
		this.fext4 = fext4;
		this.fext5 = fext5;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWarnType() {
		return warnType;
	}

	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Date getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getInterestTime() {
		return interestTime;
	}

	public void setInterestTime(Date interestTime) {
		this.interestTime = interestTime;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
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

}
