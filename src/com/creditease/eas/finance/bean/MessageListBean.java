package com.creditease.eas.finance.bean;

import java.io.Serializable;
import java.util.List;

public class MessageListBean<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errCode;
	private String	errMessage;
	private List<T> obj;
	private int rows;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public List<T> getObj() {
		return obj;
	}
	public void setObj(List<T> obj) {
		this.obj = obj;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
