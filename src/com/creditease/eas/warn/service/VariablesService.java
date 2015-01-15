/**
 * 
 */
package com.creditease.eas.warn.service;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.page.PageBean;
import com.creditease.eas.warn.bean.Variables;

/**
 * @VariablesService.java
 * created at 2012-12-26 下午03:35:29 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface VariablesService {

	public int insert(Variables variables) throws Exception;
	
	//public PageBean queryPage(PageBean page) throws Exception;
	
	public int delete(Integer id) throws Exception ;
	
	public int update(Variables variables) throws Exception ;
	
	public Variables getVariablesById(Integer id) throws Exception;
	
	public Pagination queryPage(Pagination page);

	public int totalCount();
}
