/**
 * 
 */
package com.creditease.eas.warn.service;

import java.util.List;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.BlackListInfo;
import com.creditease.eas.warn.bean.Processmode;
import com.creditease.eas.warn.bean.WaringType;


/**
 * 
 * @BlackListInfoService.java
 * created at 2013-7-2 上午11:06:38 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface BlackListInfoService {
	
	public BlackListInfo selectBlackListInfoByPnumber(String pnumber,int warntype,int modetype) throws Exception ;
	
	public Processmode getProcessmode(Integer mid);
	
	public WaringType getWaringType(Integer wid);
	
	public List<Processmode> modeList();
	
	public List<WaringType> warntypeList();
	
	public int insert(BlackListInfo blackListInfo) throws Exception;
	
	public Pagination queryPage(Pagination page) ;
	
	public int delete(Integer id) throws Exception ;
	
	public int update(BlackListInfo blackListInfo) throws Exception ;
	
	public BlackListInfo getBlackListInfoById(Integer id) throws Exception;
	
}
