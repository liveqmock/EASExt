package com.creditease.eas.warn.bean;

public class OrgCostcenter {
  private String fid; //id
  private String fname_l2;//成本中心
  private String fdisplayname_l2;//使用部门
  private String fnumber;
public String getFid() {
	return fid;
}
public void setFid(String fid) {
	this.fid = fid;
}
public String getFname_l2() {
	return fname_l2;
}
public void setFname_l2(String fnameL2) {
	fname_l2 = fnameL2;
}
public String getFdisplayname_l2() {
	return fdisplayname_l2;
}
public void setFdisplayname_l2(String fdisplaynameL2) {
	fdisplayname_l2 = fdisplaynameL2;
}

public String getFnumber() {
	return fnumber;
}
public void setFnumber(String fnumber) {
	this.fnumber = fnumber;
}
public OrgCostcenter() {
	super();
}
public OrgCostcenter(String fid, String fnameL2, String fdisplaynameL2,
		String fnumber) {
	super();
	this.fid = fid;
	fname_l2 = fnameL2;
	fdisplayname_l2 = fdisplaynameL2;
	this.fnumber = fnumber;
}
  
}
