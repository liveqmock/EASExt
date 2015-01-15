/**
 * 
 */
package com.creditease.eas.warn.service;

import java.util.List;
import java.util.Map;

/**
 * @BirthdayService.java
 * created at 2013-2-21 下午04:15:11 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface BirthdayService {

	public void sendImages(Map<String, Object> map);
	
	public void sendImagesTest(Map<String, Object> map);

	public void sendHigherMailInfo(Map<String, List<Map<String, Object>>> mpInfo);

	public void sendResponsePersonMailInfo(List<Map<String, Object>> mpPersons,
			List<Map<String, Object>> mpImmPerson);

	public void sendHigherMailInfoTest(Map<String, List<Map<String, Object>>> mpInfo);

	
	public void sendResponsePersonMailInfoTest(List<Map<String, Object>> listHig,
			List<Map<String, Object>> listPersonsInfo);
}
