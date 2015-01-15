/**
 * 
 */
package com.creditease.eas.warn.service;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.page.PageBean;
import com.creditease.eas.warn.bean.Jumbosmsv;


/**
 * @JumbosmsvService.java
 * created at 2012-12-26 下午03:35:29 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface JumbosmsvService {
	public int insert(Jumbosmsv jumbosmsv) throws Exception;
	
	//public PageBean queryPage(PageBean page) throws Exception;
	public Pagination queryPage(Pagination page) ;
	
	public int delete(Integer id) throws Exception ;
	
	public int update(Jumbosmsv jumbosmsv) throws Exception ;
	
	public Jumbosmsv getJumbosmsvById(Integer id) throws Exception;
	
	public Jumbosmsv getJumbosmsvByIsuse(Integer isuse) ;

}
