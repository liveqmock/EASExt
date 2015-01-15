/**
 * 
 */
package com.creditease.eas.warn.service;

import java.io.File;
import java.util.List;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.warn.bean.InterfacePerson;
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
public interface InterfacePersonService {

	public int insert(InterfacePerson portInfo) throws Exception;
	
	public Pagination queryPage(Pagination page) throws Exception;
	
	public int delete(Integer id) throws Exception ;
	
	public int update(InterfacePerson portInfo) throws Exception ;
	
	public InterfacePerson getPortInfoById(Integer id) throws Exception;
	
	public void expire();
	
	public void sendPort();
	
	public void payfees();
	
	public String createxcel(String orgname,List<SendPortInfo> list) throws Exception;
	
	public void sendorgmail() throws Exception;
	
	public void sendEmailToYuAndXie(String name,MailSenderInfo ms) throws Exception;
	
	/**
	 * 
	 * 描述：导入数据
	 * 2013-9-16 下午01:47:50 by caoyong
	 * @version
	 * @throws Exception
	 */
	public String importExcel(File excelFile,String filePath) throws Exception;
}
