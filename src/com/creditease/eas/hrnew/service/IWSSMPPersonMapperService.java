package com.creditease.eas.hrnew.service;

public interface IWSSMPPersonMapperService {

	/**
	 *  异动
	 * @return
	 */
	public Integer queryFluctuation();
	
	/*
	 * 职员
	 */
	public Integer queryPerson();
	
	/*
	 * 组织信息
	 */
	public Integer orgAdminQuery();
	
	/*
	 * 职位信息
	 */
	public Integer orgPositionQuery();
}
