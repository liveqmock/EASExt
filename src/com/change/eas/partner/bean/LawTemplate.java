package com.change.eas.partner.bean;

import java.io.Serializable;
import java.sql.Blob;

public class LawTemplate implements Serializable {
	private static final long serialVersionUID = -4282757363615978482L;
	private long templateID;
	private long recordID;
	private String fileName;
	private String fileType;
	private long fileSize;
	private java.sql.Date fileDate;
	private byte[] fileBody;
	private String filePath;
	private String userName;
	private String descript;
	private long cityID;

	public long getTemplateID() {
		return templateID;
	}

	public void setTemplateID(long templateID) {
		this.templateID = templateID;
	}

	public long getRecordID() {
		return recordID;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public java.sql.Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(java.sql.Date fileDate) {
		this.fileDate = fileDate;
	}


	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public long getCityID() {
		return cityID;
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;
	}

	public byte[] getFileBody() {
		return fileBody;
	}

	public void setFileBody(byte[] fileBody) {
		this.fileBody = fileBody;
	}


}
