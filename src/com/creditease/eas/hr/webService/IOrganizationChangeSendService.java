package com.creditease.eas.hr.webService;

public interface IOrganizationChangeSendService {
	public void queryOrganizationChangeFromHRToOA();
	/**
	 * 描述：手动推送职位的信息
	 * 2013-5-29 下午09:22:22 by ygq
	 * @version
	 * @param beginTime
	 * @param endTime
	 */
	public void queryOrganizationChangeFromHRToOAHand(String begin,String end,String fnumber);
}
