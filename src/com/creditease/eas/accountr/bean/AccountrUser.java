package com.creditease.eas.accountr.bean;

import java.util.Date;

/**
 * 
 * @AccountrUser.java
 * created at 2014-1-6 下午03:25:29 by sunxiaofeng
 * 
 *设置汇总邮件收件人bean
 * @version $Revision$</br>
 * update: $Date$
 */
public class AccountrUser {
  private Integer fid;//自增id
  private String fusername;//收件人名称
  private String fuseremail;//收件人邮箱
  private Integer fstatus;//状态  选中状态为1反之为0
  private Integer fcreateuserid;//创建人id
  private Date fcreatetime;//创建时间
  //扩展字段
  private String fext1;
  private String fext2;
  private String fext3;
  private String fext4;
  private String fext5;
  private String fext6;
  private String fext7;
  private String fext8;
  private String fext9;
  private String fext10;
public Integer getFid() {
	return fid;
}
public void setFid(Integer fid) {
	this.fid = fid;
}
public String getFusername() {
	return fusername;
}
public void setFusername(String fusername) {
	this.fusername = fusername;
}
public String getFuseremail() {
	return fuseremail;
}
public void setFuseremail(String fuseremail) {
	this.fuseremail = fuseremail;
}
public Integer getFstatus() {
	return fstatus;
}
public void setFstatus(Integer fstatus) {
	this.fstatus = fstatus;
}
public Integer getFcreateuserid() {
	return fcreateuserid;
}
public void setFcreateuserid(Integer fcreateuserid) {
	this.fcreateuserid = fcreateuserid;
}
public Date getFcreatetime() {
	return fcreatetime;
}
public void setFcreatetime(Date fcreatetime) {
	this.fcreatetime = fcreatetime;
}
public String getFext1() {
	return fext1;
}
public void setFext1(String fext1) {
	this.fext1 = fext1;
}
public String getFext2() {
	return fext2;
}
public void setFext2(String fext2) {
	this.fext2 = fext2;
}
public String getFext3() {
	return fext3;
}
public void setFext3(String fext3) {
	this.fext3 = fext3;
}
public String getFext4() {
	return fext4;
}
public void setFext4(String fext4) {
	this.fext4 = fext4;
}
public String getFext5() {
	return fext5;
}
public void setFext5(String fext5) {
	this.fext5 = fext5;
}
public String getFext6() {
	return fext6;
}
public void setFext6(String fext6) {
	this.fext6 = fext6;
}
public String getFext7() {
	return fext7;
}
public void setFext7(String fext7) {
	this.fext7 = fext7;
}
public String getFext8() {
	return fext8;
}
public void setFext8(String fext8) {
	this.fext8 = fext8;
}
public String getFext9() {
	return fext9;
}
public void setFext9(String fext9) {
	this.fext9 = fext9;
}
public String getFext10() {
	return fext10;
}
public void setFext10(String fext10) {
	this.fext10 = fext10;
}
public AccountrUser() {
	super();
}
  
}
