package com.creditease.eas.compliance.bean;

public class ChargeInfo {
	/**
	 * 主键
	 */
	private Long fid;

	/**
	 * 收费接收方类别
	 */
	private String freceiveType;
	/**
	 * 收费类别
	 */
	private String ftype;
	/**
	 * 收费形式
	 */
	private String fmode;
	/**
	 * 收费金额
	 */
	private String famount;
	/**
	 * 潜在风险评估等级
	 */
	private String friskvaluate;
	/**
	 * 提交被退回次数
	 */
	private String fbackcount;
	/**
	 * 被投诉人员业绩
	 */
	private String fcomplanantPerformance;
	/**
	 * 所在团队业绩
	 */
	private String ftermPerformance;
	/**
	 * 案件ID(外键)
	 */
	private int fcomplainId;

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getFreceiveType() {
		return freceiveType;
	}

	public void setFreceiveType(String freceiveType) {
		this.freceiveType = freceiveType;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getFmode() {
		return fmode;
	}

	public void setFmode(String fmode) {
		this.fmode = fmode;
	}

	public String getFamount() {
		return famount;
	}

	public void setFamount(String famount) {
		this.famount = famount;
	}

	public String getFriskvaluate() {
		return friskvaluate;
	}

	public void setFriskvaluate(String friskvaluate) {
		this.friskvaluate = friskvaluate;
	}

	public String getFbackcount() {
		return fbackcount;
	}

	public void setFbackcount(String fbackcount) {
		this.fbackcount = fbackcount;
	}

	public String getFcomplanantPerformance() {
		return fcomplanantPerformance;
	}

	public void setFcomplanantPerformance(String fcomplanantPerformance) {
		this.fcomplanantPerformance = fcomplanantPerformance;
	}

	public String getFtermPerformance() {
		return ftermPerformance;
	}

	public void setFtermPerformance(String ftermPerformance) {
		this.ftermPerformance = ftermPerformance;
	}

	public int getFcomplainId() {
		return fcomplainId;
	}

	public void setFcomplainId(int fcomplainId) {
		this.fcomplainId = fcomplainId;
	}

}
