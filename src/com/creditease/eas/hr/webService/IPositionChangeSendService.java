package com.creditease.eas.hr.webService;
/**
 * 职位信息变动的接口
 * @IEmpChangeService.java
 * created at 2013-3-4 上午10:26:38 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$ 
 */
public interface IPositionChangeSendService {
	public void queryPositionInfoChangeFromHRToOA();
	/**
	 * 描述：手动推送职位的信息
	 * 2013-5-29 下午09:19:24 by ygq
	 * @version
	 */
	public void queryPositionInfoChangeFromHRToOAHand(String begin,String end,String fnumber);
}
