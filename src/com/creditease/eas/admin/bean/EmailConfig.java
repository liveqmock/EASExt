package com.creditease.eas.admin.bean;

public class EmailConfig {
	/** Fid **/
	private Integer fid;
	/** 模块名 **/
	private String fmodule;
	/** 角色 **/
	private String frole;
	/** EMAIL **/
	private String femail;
	/** 更新人 **/
	private Integer fupdateid;
	/** 更新时间 **/
	private String fupdatetime;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFmodule() {
		return fmodule;
	}

	public void setFmodule(String fmodule) {
		this.fmodule = fmodule;
	}

	public String getFrole() {
		return frole;
	}

	public void setFrole(String frole) {
		this.frole = frole;
	}

	public String getFemail() {
		return femail;
	}

	public void setFemail(String femail) {
		this.femail = femail;
	}

	public Integer getFupdateid() {
		return fupdateid;
	}

	public void setFupdateid(Integer fupdateid) {
		this.fupdateid = fupdateid;
	}

	public String getFupdatetime() {
		return fupdatetime;
	}

	public void setFupdatetime(String fupdatetime) {
		this.fupdatetime = fupdatetime;
	}

}