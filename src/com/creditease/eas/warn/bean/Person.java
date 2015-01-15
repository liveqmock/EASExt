package com.creditease.eas.warn.bean;

public class Person {
	private String fid; 			//id
	private String fname;		//姓名
	private String fgender;		//性别
	private String fbirthday;	//生日
	private String fcell;		//电话
	private String faddress;	//地址
	
	public Person(){
		
	}
	
	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFgender() {
		return fgender;
	}
	public void setFgender(String fgender) {
		this.fgender = fgender;
	}
	public String getFbirthday() {
		return fbirthday;
	}
	public void setFbirthday(String fbirthday) {
		this.fbirthday = fbirthday;
	}
	public String getFcell() {
		return fcell;
	}
	public void setFcell(String fcell) {
		this.fcell = fcell;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	
	
}
