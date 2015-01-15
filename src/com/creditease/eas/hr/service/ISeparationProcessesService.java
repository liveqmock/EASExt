package com.creditease.eas.hr.service;


import javax.jws.WebParam;



public interface ISeparationProcessesService {
	/**
	 * 描述：根据员工编码获取（是否是备用金负责人及获取员工是否借款）
	 */
	public String queryFuumber(@WebParam(name="fnumber")String fnumber);
}
