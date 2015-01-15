package com.creditease.eas.compliance.bean;

public class CaseDetailType {
	private String id;
	private String name;
	private int ischecked=0;
	
	private String breakLevelId;
	private String breakLevelName;
	private String baseTypeId;
	private String baseTypeName;
	
	
	private String caseTypeId;
	private String caseTypeName;
	
	private Integer complainFid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIschecked() {
		return ischecked;
	}
	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}
	public String getBreakLevelId() {
		return breakLevelId;
	}
	public void setBreakLevelId(String breakLevelId) {
		this.breakLevelId = breakLevelId;
	}
	public String getBreakLevelName() {
		return breakLevelName;
	}
	public void setBreakLevelName(String breakLevelName) {
		this.breakLevelName = breakLevelName;
	}
	public String getBaseTypeId() {
		return baseTypeId;
	}
	public void setBaseTypeId(String baseTypeId) {
		this.baseTypeId = baseTypeId;
	}
	public String getBaseTypeName() {
		return baseTypeName;
	}
	public void setBaseTypeName(String baseTypeName) {
		this.baseTypeName = baseTypeName;
	}
	public Integer getComplainFid() {
		return complainFid;
	}
	public void setComplainFid(Integer complainFid) {
		this.complainFid = complainFid;
	}
	public String getCaseTypeId() {
		return caseTypeId;
	}
	public void setCaseTypeId(String caseTypeId) {
		this.caseTypeId = caseTypeId;
	}
	public String getCaseTypeName() {
		return caseTypeName;
	}
	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
	}
	
	
}
