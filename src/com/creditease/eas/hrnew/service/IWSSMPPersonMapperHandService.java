package com.creditease.eas.hrnew.service;

import java.util.Map;

/**
 *  手动推送接口
 * @IWSSMPPersonMapperHandService.java
 * created at 2013-8-12 下午03:16:23 by guominggao
 * 
 * @author guominggao({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IWSSMPPersonMapperHandService {

	/**
	 * 
	 * 描述：手动推送异动信息
	 * 2013-8-12 下午03:25:34 by guominggao
	 * @version
	 * @return
	 */
	public Map<String, Object> queryFluctuation(String begin,String end);
	
	/**
	 * 
	 * 描述：手动推送人员信息
	 * 2013-8-12 下午03:25:24 by guominggao
	 * @version
	 * @return
	 */
	public Map<String, Object> queryPerson(String begin,String end);
	
	/**
	 * 
	 * 描述：手动推送组织信息
	 * 2013-8-12 下午03:26:23 by guominggao
	 * @version
	 * @return
	 */
	public Map<String, Object> orgAdminQuery(String begin,String end);
	
	/**
	 * 
	 * 描述：手动推送职位信息
	 * 2013-8-12 下午03:26:35 by guominggao
	 * @version
	 * @return
	 */
	public Map<String, Object> orgPositionQuery(String begin,String end);
}
