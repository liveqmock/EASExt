package com.creditease.eas.warn.vo;
/**
 * 查询 参数包装类
 * @author Administrator
 *
 */
public class QueryParameters {

	private String beginTime;
	private String endTime;
	private Integer typeId;
	private Integer wayId;
	
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getWayId() {
		return wayId;
	}
	public void setWayId(Integer wayId) {
		this.wayId = wayId;
	}
	
	
}
