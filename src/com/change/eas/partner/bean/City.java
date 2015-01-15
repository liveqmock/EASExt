package com.change.eas.partner.bean;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = -4279580518391234689L;
	private long id;
	private String partherCode;
	private String partherName;
	private String cityName;
	private String cityCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPartherCode() {
		return partherCode;
	}

	public void setPartherCode(String partherCode) {
		this.partherCode = partherCode;
	}

	public String getPartherName() {
		return partherName;
	}

	public void setPartherName(String partherName) {
		this.partherName = partherName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Override
	public String toString() {
		return "City [cityCode=" + cityCode + ", cityName=" + cityName
				+ ", id=" + id + ", partherCode=" + partherCode
				+ ", partherName=" + partherName + "]";
	}
	
}
