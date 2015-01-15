/**
 * 
 */
package com.creditease.eas.finance.bean;

import java.io.Serializable;

/**
 * @LedgerBean.java
 * created at 2013-7-12 下午03:32:43 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class LedgerCountBean implements Serializable {

	private String accountname;//核算主体
	private String vouchertype;//凭证类型
	private Integer vouchercount;//凭证数量
	private String accountnumber;//核算主体编码
	
	private String vouchernumber;//凭证类型编码
	
	public LedgerCountBean(){
		
	}
	
	
	public String getVouchernumber() {
		return vouchernumber;
	}


	public void setVouchernumber(String vouchernumber) {
		this.vouchernumber = vouchernumber;
	}


	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getVouchertype() {
		return vouchertype;
	}
	public void setVouchertype(String vouchertype) {
		this.vouchertype = vouchertype;
	}
	public Integer getVouchercount() {
		return vouchercount;
	}
	public void setVouchercount(Integer vouchercount) {
		this.vouchercount = vouchercount;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	
	
	
}
