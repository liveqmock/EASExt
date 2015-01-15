package com.creditease.eas.finance.bean;

import java.io.Serializable;
/**
 * 客商分类关联的Bean
 * @CsspgroupBean.java
 * created at 2013-6-5 下午04:43:59 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class CsspgroupBean implements Serializable{
	private String fid;
	private String fnumber;
	private String	fname;
	private String fisleaf;//是否是叶子节点
	private String flevel;//级次
	
	private String fparentNumber;//上级编码 
	
	private String ftreeParentNumber;//上级树编码
	private Integer istree;//是否是树
	
	
	
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
	public String getFparentNumber() {
		return fparentNumber;
	}
	public void setFparentNumber(String fparentNumber) {
		this.fparentNumber = fparentNumber;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
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
	
	
}
