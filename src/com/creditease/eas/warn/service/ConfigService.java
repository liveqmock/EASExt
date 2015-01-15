/**
 * 
 */
package com.creditease.eas.warn.service;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.ConfigInfo;

/**
 * @VariablesService.java
 * created at 2012-12-26 下午03:35:29 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface ConfigService {

	public int insert(ConfigInfo configInfo) throws Exception;
	
	//public PageBean queryPage(PageBean page) throws Exception;
	
	public int delete(Integer id) throws Exception ;
	
	public int update(ConfigInfo ConfigInfo) throws Exception ;
	
	public ConfigInfo getConfigInfoById(Integer id) ;
	
	public Pagination queryPage(Pagination page);

	public int totalCount();
	
	public int maxId();
}
