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
public class LedgerBean implements Serializable {

	private String accountname;//核算主体
	private String subnumber;//科目编码（会计科目）
	private String subname;//科目名称
	private double debitmoney;//本位币借方金额
	private double lendermoney;//本位币贷方金额
	private String accountnumber;//核算主体编码
	
	public LedgerBean(){
		
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getSubnumber() {
		return subnumber;
	}

	public void setSubnumber(String subnumber) {
		this.subnumber = subnumber;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public double getDebitmoney() {
		return debitmoney;
	}

	public void setDebitmoney(double debitmoney) {
		this.debitmoney = debitmoney;
	}

	public double getLendermoney() {
		return lendermoney;
	}

	public void setLendermoney(double lendermoney) {
		this.lendermoney = lendermoney;
	}
	
	
}
