package com.creditease.eas.warn.bean;

/**
 * @OrgAdmin.java
 * created at 2013-4-2 下午07:10:30 by guominggao
 * 
 * @author guominggao({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class OrgAdmin {
	private String fid; 			//id
	private String fname;		//姓名
	private String fparentid;		//父id 
	private String fnumber;     //编码
	private String flevel;      //级别

	
	public OrgAdmin(){
		
	}
	
	public String getFlevel() {
		return flevel;
	}

	public void setFlevel(String flevel) {
		this.flevel = flevel;
	}

	public String getFnumber() {
		return fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
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

	public String getFparentid() {
		return fparentid;
	}

	public void setFparentid(String fparentid) {
		this.fparentid = fparentid;
	}
	
	
	
}
