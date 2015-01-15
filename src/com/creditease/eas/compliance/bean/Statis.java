package com.creditease.eas.compliance.bean;
/**
 * 违规统计数据javabean
 * @Statis.java
 * created at 2013-11-5 下午02:12:15 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Statis {
	//城市名称
	private String fcityname;
	//部门名称
	private String fdeptname;
	//欺诈伪冒类
	private int qizha;
	//违背诚信类
	private int weichengxin;
	//商业贿赂类
	private int huilu;
	//信息保密类
	private int baomi;
	//服务类
	private int fuwu;
	//其他
	private int other;
	//总数
	private int countnum;
	//多种类型案件数量
	private int manytypecount;
	
	public int getManytypecount() {
		return manytypecount;
	}
	public void setManytypecount(int manytypecount) {
		this.manytypecount = manytypecount;
	}
	public String getFcityname() {
		return fcityname;
	}
	public void setFcityname(String fcityname) {
		this.fcityname = fcityname;
	}
	public String getFdeptname() {
		return fdeptname;
	}
	public void setFdeptname(String fdeptname) {
		this.fdeptname = fdeptname;
	}
	public int getQizha() {
		return qizha;
	}
	public void setQizha(int qizha) {
		this.qizha = qizha;
	}
	public int getWeichengxin() {
		return weichengxin;
	}
	public void setWeichengxin(int weichengxin) {
		this.weichengxin = weichengxin;
	}
	public int getHuilu() {
		return huilu;
	}
	public void setHuilu(int huilu) {
		this.huilu = huilu;
	}
	public int getBaomi() {
		return baomi;
	}
	public void setBaomi(int baomi) {
		this.baomi = baomi;
	}
	public int getFuwu() {
		return fuwu;
	}
	public void setFuwu(int fuwu) {
		this.fuwu = fuwu;
	}
	public int getOther() {
		return other;
	}
	public void setOther(int other) {
		this.other = other;
	}
	public int getCountnum() {
		return countnum;
	}
	public void setCountnum(int countnum) {
		this.countnum = countnum;
	}
	
	
	
	
	
}
