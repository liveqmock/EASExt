package com.creditease.eas.hr.bean.oa;

import java.io.Serializable;
import java.util.List;

public class WSSupplierListBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer count;
	private List<WSSupplierBean> wssbean; 
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<WSSupplierBean> getWssbean() {
		return wssbean;
	}
	public void setWssbean(List<WSSupplierBean> wssbean) {
		this.wssbean = wssbean;
	}

	
}
