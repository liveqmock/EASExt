package com.creditease.eas.warn.vo;

import java.util.Date;

/**
 * 负责人
 * @QualifyingMature.java
 * created at 2012-12-24 下午04:51:42 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Leading {
	//应该将部门经理和部门负责人的邮箱的信息，存储到一张表里面，或者是存储到Map中，根据信息，判断出来那个部门的
	//直接读取出来
	//最好是动态的读取出来这些信息
	private int id;
	private String orgnameCity;//部门名称(城市)
	private String orgname;//部门名称
	private String deptfnumber;//部门编码
	private String city;//城市
	private String employeername;//员工姓名
	private String personfnumber;//人员编码
	private String cfgangwei;
	private String qualifyingMatureDate;//试用期到期日期
	//上级部门编码：p代表是parent
	private String pdeptfnumber;//部门编码
	public String getOrgnameCity() {
		return orgnameCity;
	}
	public void setOrgnameCity(String orgnameCity) {
		this.orgnameCity = orgnameCity;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmployeername() {
		return employeername;
	}
	public void setEmployeername(String employeername) {
		this.employeername = employeername;
	}
	public String getCfgangwei() {
		return cfgangwei;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptfnumber() {
		return deptfnumber;
	}
	public void setDeptfnumber(String deptfnumber) {
		this.deptfnumber = deptfnumber;
	}
	public String getPersonfnumber() {
		return personfnumber;
	}
	public void setPersonfnumber(String personfnumber) {
		this.personfnumber = personfnumber;
	}
	public String getPdeptfnumber() {
		return pdeptfnumber;
	}
	public void setPdeptfnumber(String pdeptfnumber) {
		this.pdeptfnumber = pdeptfnumber;
	}
	public void setCfgangwei(String cfgangwei) {
		this.cfgangwei = cfgangwei;
	}
	public String getQualifyingMatureDate() {
		return qualifyingMatureDate;
	}
	public void setQualifyingMatureDate(String qualifyingMatureDate) {
		this.qualifyingMatureDate = qualifyingMatureDate;
	}
}
