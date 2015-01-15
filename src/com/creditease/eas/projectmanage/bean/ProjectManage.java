package com.creditease.eas.projectmanage.bean;

import java.util.Date;

/**
 * @ProjectManage.java
 * created at 2014-3-13 下午01:07:28 by sunxiaofeng
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ProjectManage {

	private Integer fid;//自增id
	private String loanNumber;//贷款编号
	private String companyName;//公司名称
	private String head;//贷后管理负责人
	private Integer loanWayId;//出借方式id
	private Date loanTime;//出借日
	private Date expireTime;//到期日
	private Integer term;//期限(月)
	private Date interestTime;//利息返回日
	private Double money;//金额(万元)
	private Double mlyinterest;//月利率
	private Double mMangExpense;//月管理费
	private String authentication;//认证股权
	private String lenders;//出借方
	private String borrower;//借款方
	private Integer repaymentWayId;//还款方式id
	private String repaymentAccount;//还款账户
	private String bankAccount;//开户行
	private String account;//账户
	private String repaymentMoney;//账户对应还款额
	private Integer stateFid;//状态id
	private Integer pmUserId;//pmid
	private Integer fcreateuserId;//创建人id
	private Date fcreateTime;//创建时间
	private Integer lastupdaterId;//最后修改人id
	private Date lastupdateTime;//最后修改时间
	/*扩展字段1-10*/
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
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Integer getLoanWayId() {
		return loanWayId;
	}
	public void setLoanWayId(Integer loanWayId) {
		this.loanWayId = loanWayId;
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
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public Date getInterestTime() {
		return interestTime;
	}
	public void setInterestTime(Date interestTime) {
		this.interestTime = interestTime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getMlyinterest() {
		return mlyinterest;
	}
	public void setMlyinterest(Double mlyinterest) {
		this.mlyinterest = mlyinterest;
	}
	public Double getmMangExpense() {
		return mMangExpense;
	}
	public void setmMangExpense(Double mMangExpense) {
		this.mMangExpense = mMangExpense;
	}
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public String getLenders() {
		return lenders;
	}
	public void setLenders(String lenders) {
		this.lenders = lenders;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	public Integer getFcreateuserId() {
		return fcreateuserId;
	}
	public void setFcreateuserId(Integer fcreateuserId) {
		this.fcreateuserId = fcreateuserId;
	}
	
	public Integer getRepaymentWayId() {
		return repaymentWayId;
	}
	public void setRepaymentWayId(Integer repaymentWayId) {
		this.repaymentWayId = repaymentWayId;
	}
	public String getRepaymentAccount() {
		return repaymentAccount;
	}
	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getRepaymentMoney() {
		return repaymentMoney;
	}
	public void setRepaymentMoney(String repaymentMoney) {
		this.repaymentMoney = repaymentMoney;
	}
	public Integer getStateFid() {
		return stateFid;
	}
	public void setStateFid(Integer stateFid) {
		this.stateFid = stateFid;
	}
	public Integer getPmUserId() {
		return pmUserId;
	}
	public void setPmUserId(Integer pmUserId) {
		this.pmUserId = pmUserId;
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
	public ProjectManage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
