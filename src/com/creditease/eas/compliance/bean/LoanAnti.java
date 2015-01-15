package com.creditease.eas.compliance.bean;

public class LoanAnti {
	/**
	 * 主键
	 */
	private Integer fid;

	/**
	 * 借款编号
	 */
	private String code;

	/**
	 * 客户姓名
	 */
	private String name;

	/**
	 * 身份证号
	 */
	private String idcard;

	/**
	 * 营业部
	 */
	private String saledepart;

	/**
	 * 销售人员
	 */
	private String saleperson;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getSaledepart() {
		return saledepart;
	}

	public void setSaledepart(String saledepart) {
		this.saledepart = saledepart;
	}

	public String getSaleperson() {
		return saleperson;
	}

	public void setSaleperson(String saleperson) {
		this.saleperson = saleperson;
	}

}
