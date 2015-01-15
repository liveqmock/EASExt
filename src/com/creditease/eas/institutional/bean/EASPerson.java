package com.creditease.eas.institutional.bean;
/**
 * EAS数据库中人员数据的POJO
 * @author lining
 *
 */
public class EASPerson {
	private String email;
	private String enabled;
	private String grade;
	private String levelOneOrg;
	private String levelOldOrgList;
	private String name;
	private String newId;
	private int isPrimary;

	public String getLevelOldOrgList() {
		return levelOldOrgList;
	}
	public void setLevelOldOrgList(String levelOldOrgList) {
		this.levelOldOrgList = levelOldOrgList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getLevelOneOrg() {
		return levelOneOrg;
	}
	public void setLevelOneOrg(String levelOneOrg) {
		this.levelOneOrg = levelOneOrg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
	public int getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(int isPrimary) {
		this.isPrimary = isPrimary;
	}
	
}
