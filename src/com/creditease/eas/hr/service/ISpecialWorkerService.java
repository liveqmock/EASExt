package com.creditease.eas.hr.service;

import javax.jws.WebParam;
public interface ISpecialWorkerService {
	/*
	 * 特殊人员接口根据员工编码返回职位编码
	 */
	public String getSpecialWorkerServiceBycode(@WebParam(name="fnumber")String fnumber);

}
