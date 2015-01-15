package com.creditease.eas.finance.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 辅助核算类型
 * @AsstAccount.java
 * created at 2013-5-2 下午09:38:41 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class AsstAccount implements Serializable {
	private String fnumber;
	private String	fname;
	private String fcompanyID; //关联的财务组织
//	private String fasstacttypeid;//关联的辅助核算项目的id
	private String fasstacttypenumber;//关联的辅助核算项目的编码
	private String fasstacttypename;//关联的辅助核算项目的名称
	
	public String getFasstacttypenumber() {
		return fasstacttypenumber;
	}
	public void setFasstacttypenumber(String fasstacttypenumber) {
		this.fasstacttypenumber = fasstacttypenumber;
	}
	public String getFasstacttypename() {
		return fasstacttypename;
	}
	public void setFasstacttypename(String fasstacttypename) {
		this.fasstacttypename = fasstacttypename;
	}
	public String getFcompanyID() {
		return fcompanyID;
	}
	public void setFcompanyID(String fcompanyID) {
		this.fcompanyID = fcompanyID;
	}
//	public String getFasstacttypeid() {
//		return fasstacttypeid;
//	}
//	public void setFasstacttypeid(String fasstacttypeid) {
//		this.fasstacttypeid = fasstacttypeid;
//	}
	private String	errCode;//异常编码（0000：正常，0001：异常)
	private String	errMessage;//异常信息
	private String	ext1;//		扩展字段一
	private String	ext2;//		扩展字段二
	private String	ext3;//		扩展字段三
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	public String getExt6() {
		return ext6;
	}
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}
	public String getExt7() {
		return ext7;
	}
	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}
	public String getExt8() {
		return ext8;
	}
	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}
	public String getExt9() {
		return ext9;
	}
	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}
	public String getExt10() {
		return ext10;
	}
	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}
	private String	ext4;//		扩展字段四
	private String	ext5;//		扩展字段五
	private String	ext6;//		扩展字段六
	private String	ext7;//		扩展字段七
	private String	ext8;//		扩展字段八
	private String	ext9;//		扩展字段九
	private String	ext10;//扩展字段十
	private Date flastUpdateTime;
	public Date getFlastUpdateTime() {
		return flastUpdateTime;
	}
	public void setFlastUpdateTime(Date flastUpdateTime) {
		this.flastUpdateTime = flastUpdateTime;
	}
}
