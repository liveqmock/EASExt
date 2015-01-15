package com.creditease.eas.hr.bean.oa;
/**
 * 供应商对应的银行账户
 * @WSBankBean.java
 * created at 2013-3-21 上午10:35:11 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class WSBankBean {
	private String bankName;
	private String bankAccount;//银行账号
	private String bankAddress;//银行地址
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	
}
