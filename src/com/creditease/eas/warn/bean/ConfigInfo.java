package com.creditease.eas.warn.bean;

public class ConfigInfo {
	private Integer id; 					//id
	private String confignumber;		 
	private String configname;				//名称
	private Integer configvalue;			//定时任务开关值，0：开、1：关
	private String markinfo;				//备注
	private String fcreatetime;				//创建时间
	private String fcreatepersonname;		//创建人名称
	private String fupdatetime;				//修改时间
	private String fupdatepersonname;		//修改人名称
	
	public ConfigInfo(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfignumber() {
		return confignumber;
	}

	public void setConfignumber(String confignumber) {
		this.confignumber = confignumber;
	}

	public String getConfigname() {
		return configname;
	}

	public void setConfigname(String configname) {
		this.configname = configname;
	}

	public Integer getConfigvalue() {
		return configvalue;
	}

	public void setConfigvalue(Integer configvalue) {
		this.configvalue = configvalue;
	}

	public String getMarkinfo() {
		return markinfo;
	}

	public void setMarkinfo(String markinfo) {
		this.markinfo = markinfo;
	}

	public String getFcreatetime() {
		return fcreatetime;
	}

	public void setFcreatetime(String fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	public String getFcreatepersonname() {
		return fcreatepersonname;
	}

	public void setFcreatepersonname(String fcreatepersonname) {
		this.fcreatepersonname = fcreatepersonname;
	}

	public String getFupdatetime() {
		return fupdatetime;
	}

	public void setFupdatetime(String fupdatetime) {
		this.fupdatetime = fupdatetime;
	}

	public String getFupdatepersonname() {
		return fupdatepersonname;
	}

	public void setFupdatepersonname(String fupdatepersonname) {
		this.fupdatepersonname = fupdatepersonname;
	}
	
	
	
}
