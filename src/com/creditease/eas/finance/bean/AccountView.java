package com.creditease.eas.finance.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 会计科目
 * @AccountView.java
 * created at 2013-5-2 下午09:00:41 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class AccountView implements Serializable{
	private String fnumber;
	private String	fname;
	private String fcaaname;//本级辅助账类型名称
	private String fcaanumber;//本级辅助账类型编码
	private String	fgaaname;//上级辅助帐类型名称
	private String	fgaanumber;//上级辅助帐类型编码
	private String	fparentAAIDNumber; //上级科目ID
	private String	fdc;//余额方向
	private String	faccountTypeNumber;//科目类型id
	private String	faccountTableNumber;//科目表id
	private String fisLeaf;//是否明细
	private String fcompanynumber;//公司编码
	private Integer status;
	
	public String getFcompanynumber() {
		return fcompanynumber;
	}
	public void setFcompanynumber(String fcompanynumber) {
		this.fcompanynumber = fcompanynumber;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFisLeaf() {
		return fisLeaf;
	}
	public void setFisLeaf(String fisLeaf) {
		this.fisLeaf = fisLeaf;
	}
	private String	ext1;//		扩展字段一
	private String	ext2;//		扩展字段二
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
	public String getFcaaname() {
		return fcaaname;
	}
	public void setFcaaname(String fcaaname) {
		this.fcaaname = fcaaname;
	}
	public String getFcaanumber() {
		return fcaanumber;
	}
	public void setFcaanumber(String fcaanumber) {
		this.fcaanumber = fcaanumber;
	}
	public String getFgaaname() {
		return fgaaname;
	}
	public void setFgaaname(String fgaaname) {
		this.fgaaname = fgaaname;
	}
	public String getFgaanumber() {
		return fgaanumber;
	}
	public void setFgaanumber(String fgaanumber) {
		this.fgaanumber = fgaanumber;
	}
	public String getFparentAAIDNumber() {
		return fparentAAIDNumber;
	}
	public void setFparentAAIDNumber(String fparentAAIDNumber) {
		this.fparentAAIDNumber = fparentAAIDNumber;
	}
	public String getFaccountTypeNumber() {
		return faccountTypeNumber;
	}
	public void setFaccountTypeNumber(String faccountTypeNumber) {
		this.faccountTypeNumber = faccountTypeNumber;
	}
	public String getFaccountTableNumber() {
		return faccountTableNumber;
	}
	public void setFaccountTableNumber(String faccountTableNumber) {
		this.faccountTableNumber = faccountTableNumber;
	}
	public String getFdc() {
		return fdc;
	}
	public void setFdc(String fdc) {
		this.fdc = fdc;
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
	private String	ext3;//		扩展字段三
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
