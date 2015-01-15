package com.creditease.eas.hr.webService;
/**
 * 人员信息变动的接口
 * @IEmpChangeService.java
 * created at 2013-3-4 上午10:26:38 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IEmpChangeSendService {
	public void queryEmpInfoChangeFromHRToSMPAndOA();
	/**
	 * 描述：开始时间，结束时间
	 * 2013-5-29 下午08:41:33 by ygq
	 * @version
	 * @param beginTime
	 * @param endTime
	 */
	public void queryEmpInfoChangeFromHRToSMPAndOAHand(String beginTime,String endTime);
}
