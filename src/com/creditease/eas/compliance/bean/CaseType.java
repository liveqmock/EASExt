package com.creditease.eas.compliance.bean;

import java.util.ArrayList;
import java.util.List;

public class CaseType {
	private String id;
	private String name;
	private int ischecked=0;
	private String detailcasetypeid;
	private String detailcasetypename;
	
	private List<CaseDetailType> detailCaseType=new ArrayList<CaseDetailType>();
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
	public List<CaseDetailType> getDetailCaseType() {
		return detailCaseType;
	}
	public void setDetailCaseType(List<CaseDetailType> detailCaseType) {
		this.detailCaseType = detailCaseType;
	}
	public int getIschecked() {
		return ischecked;
	}
	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}
	public String getDetailcasetypeid() {
		return detailcasetypeid;
	}
	public void setDetailcasetypeid(String detailcasetypeid) {
		this.detailcasetypeid = detailcasetypeid;
	}
	public String getDetailcasetypename() {
		return detailcasetypename;
	}
	public void setDetailcasetypename(String detailcasetypename) {
		this.detailcasetypename = detailcasetypename;
	}
	
}
