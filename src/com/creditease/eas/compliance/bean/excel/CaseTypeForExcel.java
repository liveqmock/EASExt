package com.creditease.eas.compliance.bean.excel;

import java.util.ArrayList;
import java.util.List;

import com.creditease.eas.compliance.bean.CaseType;

public class CaseTypeForExcel extends CaseType{
	private int rowCount=1;
	
	private List<CaseDetailTypeForExcel> caseDetailTypeForExcelList =new ArrayList<CaseDetailTypeForExcel>();

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<CaseDetailTypeForExcel> getCaseDetailTypeForExcelList() {
		return caseDetailTypeForExcelList;
	}

	public void setCaseDetailTypeForExcelList(
			List<CaseDetailTypeForExcel> caseDetailTypeForExcelList) {
		this.caseDetailTypeForExcelList = caseDetailTypeForExcelList;
	}
	
}
