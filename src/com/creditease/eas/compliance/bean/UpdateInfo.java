package com.creditease.eas.compliance.bean;

public class UpdateInfo {
	private String fieldName;
	private String fieldDescription;
	private String oldValue;
	private String newValue;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDescription() {
		return fieldDescription;
	}
	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	@Override
	public String toString() {
		return "UpdateInfo [fieldDescription=" + fieldDescription
				+ ", fieldName=" + fieldName + ", newValue=" + newValue
				+ ", oldValue=" + oldValue + "]";
	}
	
	
	
}
