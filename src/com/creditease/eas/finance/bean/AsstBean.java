package com.creditease.eas.finance.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 辅助核算项关联的Bean
 * @AccountBanks.java
 * created at 2013-5-26 下午02:06:34 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class AsstBean implements Serializable{
	private String fnumber;
	private String	fname;
	//辅助核算项需要另外增加的字段
	//自定义辅助核算项的时候用
	private String fparentNumber;//上级编码
	private String fisenabled;//是否启用： 0：未启用 1:启用
	private String fisleaf;//是否是叶子节点
	private String flevel;//级次
	
	//客户、供应商
	private String feffectedStatus;// 生效状态:暂存=1,生效=2
	private String fusedStatus;//状态:核准=1,禁用=2,未核准=0,已删除=3
	private String ftreeParentNumber;//上级树编码
	private Integer istree;//是否是树  1:是,0:不是
	
//	private Integer fdeletedstatus;//失效状态   普通=1,作废=2
//	private Integer fisousealup;//是否封存    1：封存，0：不封存
	
	private Integer status;   //状态 1：可用、0：不可用
	private String fcompanynumber;//公司编码
	
	private String bankNumber;
	
	private Integer rn;
	
	private String companyname;
	
	
	
	
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public Integer getRn() {
		return rn;
	}
	public void setRn(Integer rn) {
		this.rn = rn;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
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
	public String getFtreeParentNumber() {
		return ftreeParentNumber;
	}
	public void setFtreeParentNumber(String ftreeParentNumber) {
		this.ftreeParentNumber = ftreeParentNumber;
	}
	public Integer getIstree() {
		return istree;
	}
	public void setIstree(Integer istree) {
		this.istree = istree;
	}
	public String getFeffectedStatus() {
		return feffectedStatus;
	}
	public void setFeffectedStatus(String feffectedStatus) {
		this.feffectedStatus = feffectedStatus;
	}
	public String getFusedStatus() {
		return fusedStatus;
	}
	public void setFusedStatus(String fusedStatus) {
		this.fusedStatus = fusedStatus;
	}
	public String getFisenabled() {
		return fisenabled;
	}
	public void setFisenabled(String fisenabled) {
		this.fisenabled = fisenabled;
	}
	public String getFisleaf() {
		return fisleaf;
	}
	public void setFisleaf(String fisleaf) {
		this.fisleaf = fisleaf;
	}
	public String getFlevel() {
		return flevel;
	}
	public void setFlevel(String flevel) {
		this.flevel = flevel;
	}
	private String	ext1;//		扩展字段一（辅助核算项的fid :成本中心/行政组织的id ext1）
	private String	ext2;//		扩展字段二(辅助核算项的fparentId :成本中心/行政组织的id ext2)
	private String	ext3;//		扩展字段三
	private String	ext4;//		扩展字段四
	private String	ext5;//		扩展字段五
	private String	ext6;//		扩展字段六
	private String	ext7;//		扩展字段七
	private String	ext8;//		扩展字段八
	private String	ext9;//		扩展字段九
	private String	ext10;//扩展字段十
	private Date flastUpdateTime;
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
	public String getFparentNumber() {
		return fparentNumber;
	}
	public void setFparentNumber(String fparentNumber) {
		this.fparentNumber = fparentNumber;
	}
	public Date getFlastUpdateTime() {
		return flastUpdateTime;
	}
	public void setFlastUpdateTime(Date flastUpdateTime) {
		this.flastUpdateTime = flastUpdateTime;
	}
}
