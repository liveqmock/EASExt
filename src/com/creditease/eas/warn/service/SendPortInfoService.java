/**
 * 
 */
package com.creditease.eas.warn.service;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.SendPortInfo;


/**
 * 
 * @PortInfoService.java
 * created at 2013-7-17 下午04:43:41 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface SendPortInfoService {
	
	
	
	public int insert(SendPortInfo sendPortInfo) throws Exception;
	
	public Pagination queryPage(Pagination page) throws Exception;
	
	public int delete(Integer id) throws Exception ;
	
	public int update(SendPortInfo sendPortInfo) throws Exception ;
	
	public SendPortInfo getSendPortInfoById(Integer id) throws Exception;
	
}
