package com.creditease.eas.hr.bean.oa;


/**
 * 供应商的基本信息
 * @WSSupplierBean.java
 * created at 2013-3-21 上午10:35:31 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class WSSupplierBean {
	//一个供应商可能有多个开户行
	private String supplierNumber;//供应商编码
	private String supplierName;//供应商名称
	private String provinceNumber;//供应商所在省编码
	private String provinceName;//供应商所在省名称
	private String cityNumber;//供应商所在市编码
	private String cityName;//供应商所在市名称	
	private String bankName;
	private String bankAccount;//银行账号
	private String bankAddress;//银行地址
	
	protected String exp1;
	protected String exp2;
	protected String exp3;
	protected String exp4;
	protected String exp5;
	protected String exp6;
	protected String exp7;
	protected String exp8;
	
	//private List<WSBankBean> list = new ArrayList<WSBankBean>();
	public String getSupplierNumber() {
		return supplierNumber;
	}
	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getProvinceNumber() {
		return provinceNumber;
	}
	public void setProvinceNumber(String provinceNumber) {
		this.provinceNumber = provinceNumber;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

//	public List<WSBankBean> getList() {
//		return list;
//	}
//	public void setList(List<WSBankBean> list) {
//		this.list = list;
//	}
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
	public String getExp1() {
		return exp1;
	}
	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}
	public String getExp2() {
		return exp2;
	}
	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}
	public String getExp3() {
		return exp3;
	}
	public void setExp3(String exp3) {
		this.exp3 = exp3;
	}
	public String getExp4() {
		return exp4;
	}
	public void setExp4(String exp4) {
		this.exp4 = exp4;
	}
	public String getExp5() {
		return exp5;
	}
	public void setExp5(String exp5) {
		this.exp5 = exp5;
	}
	public String getExp6() {
		return exp6;
	}
	public void setExp6(String exp6) {
		this.exp6 = exp6;
	}
	public String getExp7() {
		return exp7;
	}
	public void setExp7(String exp7) {
		this.exp7 = exp7;
	}
	public String getExp8() {
		return exp8;
	}
	public void setExp8(String exp8) {
		this.exp8 = exp8;
	}
	public String getCityNumber() {
		return cityNumber;
	}
	public void setCityNumber(String cityNumber) {
		this.cityNumber = cityNumber;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	

}
