package com.creditease.eas.doc.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Dcontract.java
 * created at 2013-9-10 下午03:28:26 by admin
 * 
 * @author admin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Dcontract {
   private Integer id;
   private String fcontractnum ;//合同编号
   private String fcontractname;//合同/协议名称
   private String ffirstparty;//甲方
   private String fclient;//乙方
   private String ftransact;//办理人
   private Date  fcontractdate;//合同到期时间
   private String  fmailbox;//接口人的邮箱
   private String creator;//创建人
   private Date creatortime;//创建时间
   private String lastupdater;//最后修改人
   private Date lastupdatetime;//最后修改时间
   private String status;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getFcontractnum() {
	return fcontractnum;
}
public void setFcontractnum(String fcontractnum) {
	this.fcontractnum = fcontractnum;
}
public String getFcontractname() {
	return fcontractname;
}
public void setFcontractname(String fcontractname) {
	this.fcontractname = fcontractname;
}
public String getFfirstparty() {
	return ffirstparty;
}
public void setFfirstparty(String ffirstparty) {
	this.ffirstparty = ffirstparty;
}
public String getFclient() {
	return fclient;
}
public void setFclient(String fclient) {
	this.fclient = fclient;
}
public String getFtransact() {
	return ftransact;
}
public void setFtransact(String ftransact) {
	this.ftransact = ftransact;
}
public Date getFcontractdate() {
	
	return fcontractdate;
}
public void setFcontractdate(Date fcontractdate) {
	this.fcontractdate = fcontractdate;
}
public String getFmailbox() {
	return fmailbox;
}
public void setFmailbox(String fmailbox) {
	this.fmailbox = fmailbox;
}

public String getCreator() {
	return creator;
}
public void setCreator(String creator) {
	this.creator = creator;
}
public Date getCreatortime() {
	return creatortime;
}
public void setCreatortime(Date creatortime) {
	this.creatortime = creatortime;
}
public String getLastupdater() {
	return lastupdater;
}
public void setLastupdater(String lastupdater) {
	this.lastupdater = lastupdater;
}
public Date getLastupdatetime() {
	return lastupdatetime;
}
public void setLastupdatetime(Date lastupdatetime) {
	this.lastupdatetime = lastupdatetime;
}
public Dcontract(Integer id, String fcontractnum, String fcontractname,
		String ffirstparty, String fclient, String ftransact,
		Date fcontractdate, String fmailbox, String creator, Date creatortime,
		String lastupdater, Date lastupdatetime, String status) {
	super();
	this.id = id;
	this.fcontractnum = fcontractnum;
	this.fcontractname = fcontractname;
	this.ffirstparty = ffirstparty;
	this.fclient = fclient;
	this.ftransact = ftransact;
	this.fcontractdate = fcontractdate;
	this.fmailbox = fmailbox;
	this.creator = creator;
	this.creatortime = creatortime;
	this.lastupdater = lastupdater;
	this.lastupdatetime = lastupdatetime;
	this.status = status;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Dcontract() {
	super();
}
   
}
