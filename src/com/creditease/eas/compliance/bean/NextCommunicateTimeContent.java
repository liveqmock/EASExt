package com.creditease.eas.compliance.bean;

public class NextCommunicateTimeContent {
	/** 主键 **/
	private Integer fid;
	/** 案件编号 **/
	private String fnumber;
	/** 案件描述 **/
	private String fdetaildescrip;
	/** 案件负责人 **/
	private String fresponsiblename;
	/** 最近沟通时间 **/
	private String lastcommunicatetime;
	/** 调查ID **/
	private Integer investigatonid;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFnumber() {
		return fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	public String getFdetaildescrip() {
		return fdetaildescrip;
	}

	public void setFdetaildescrip(String fdetaildescrip) {
		this.fdetaildescrip = fdetaildescrip;
	}

	public String getFresponsiblename() {
		return fresponsiblename;
	}

	public void setFresponsiblename(String fresponsiblename) {
		this.fresponsiblename = fresponsiblename;
	}

	public String getLastcommunicatetime() {
		return lastcommunicatetime;
	}

	public void setLastcommunicatetime(String lastcommunicatetime) {
		this.lastcommunicatetime = lastcommunicatetime;
	}

	public Integer getInvestigatonid() {
		return investigatonid;
	}

	public void setInvestigatonid(Integer investigatonid) {
		this.investigatonid = investigatonid;
	}

}
