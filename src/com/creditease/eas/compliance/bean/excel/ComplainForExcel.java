package com.creditease.eas.compliance.bean.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.creditease.eas.compliance.bean.Complain;

public class ComplainForExcel extends Complain {
	private String channelname;
	private int rowCount=1;
	
	private String statusname;
	private Date endtime;
	private String outoflinename;
	private String outooflinelevelname;
	private String reason;
	private String complainantSolution;
	
	private String status;
	
	
	private List<CaseTypeForExcel> iniCaseTypeList =new ArrayList<CaseTypeForExcel>();
	private List<CaseDetailTypeForExcel> iniCaseDetailTypeList =new ArrayList<CaseDetailTypeForExcel>();

	private List<CaseTypeForExcel> endCaseTypeList =new ArrayList<CaseTypeForExcel>();;
	private List<CaseDetailTypeForExcel> endCaseDetailTypeList =new ArrayList<CaseDetailTypeForExcel>();
	
	private List<DepartForExcel> departForExcels=new ArrayList<DepartForExcel>();
	
	//内部人员
	private List<PersonForExcel> personForExcelList=new ArrayList<PersonForExcel>();
	
	//收费
	private List<ChargeInfoForExcel> chargeInfoForExcelList=new ArrayList<ChargeInfoForExcel>();
	
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<CaseTypeForExcel> getIniCaseTypeList() {
		return iniCaseTypeList;
	}
	public void setIniCaseTypeList(List<CaseTypeForExcel> iniCaseTypeList) {
		this.iniCaseTypeList = iniCaseTypeList;
	}
	public List<CaseDetailTypeForExcel> getIniCaseDetailTypeList() {
		return iniCaseDetailTypeList;
	}
	public void setIniCaseDetailTypeList(
			List<CaseDetailTypeForExcel> iniCaseDetailTypeList) {
		this.iniCaseDetailTypeList = iniCaseDetailTypeList;
	}
	public List<CaseTypeForExcel> getEndCaseTypeList() {
		return endCaseTypeList;
	}
	public void setEndCaseTypeList(List<CaseTypeForExcel> endCaseTypeList) {
		this.endCaseTypeList = endCaseTypeList;
	}
	public List<CaseDetailTypeForExcel> getEndCaseDetailTypeList() {
		return endCaseDetailTypeList;
	}
	public void setEndCaseDetailTypeList(
			List<CaseDetailTypeForExcel> endCaseDetailTypeList) {
		this.endCaseDetailTypeList = endCaseDetailTypeList;
	}
	public List<DepartForExcel> getDepartForExcels() {
		return departForExcels;
	}
	public void setDepartForExcels(List<DepartForExcel> departForExcels) {
		this.departForExcels = departForExcels;
	}
	public String getChannelname() {
		return channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	public List<PersonForExcel> getPersonForExcelList() {
		return personForExcelList;
	}
	public void setPersonForExcelList(List<PersonForExcel> personForExcelList) {
		this.personForExcelList = personForExcelList;
	}
	public List<ChargeInfoForExcel> getChargeInfoForExcelList() {
		return chargeInfoForExcelList;
	}
	public void setChargeInfoForExcelList(
			List<ChargeInfoForExcel> chargeInfoForExcelList) {
		this.chargeInfoForExcelList = chargeInfoForExcelList;
	}
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getOutoflinename() {
		return outoflinename;
	}
	public void setOutoflinename(String outoflinename) {
		this.outoflinename = outoflinename;
	}
	public String getOutooflinelevelname() {
		return outooflinelevelname;
	}
	public void setOutooflinelevelname(String outooflinelevelname) {
		this.outooflinelevelname = outooflinelevelname;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getComplainantSolution() {
		return complainantSolution;
	}
	public void setComplainantSolution(String complainantSolution) {
		this.complainantSolution = complainantSolution;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}
