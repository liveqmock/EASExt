package com.creditease.eas.hr.bean.oa;

import java.io.Serializable; 

public class ProvinceAndCityBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private String cityNumber;
	 private String cityName;
	 private String provinceNumber; 
	 private String provinceName;
	public String getCityNumber() {
		return cityNumber;
	}
	public void setCityNumber(String cityNumber) {
		this.cityNumber = cityNumber;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceNumber() {
		return provinceNumber;
	}
	public void setProvinceNumber(String provinceNumber) {
		this.provinceNumber = provinceNumber;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 

	
}
