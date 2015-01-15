/**
 * 
 */
package com.creditease.eas.warn.service;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.RentContract;
/**
 * @RentContractService.java
 * 房租合同信息service接口
 * created at 2013-8-1 下午01:25:21 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface RentContractService {
	@SuppressWarnings("deprecation")
	public int insert(RentContract rentContract) throws Exception;
	
	public Pagination queryPage(Pagination page) throws Exception;
	
	public int delete(Integer id) throws Exception ;
	
	@SuppressWarnings("deprecation")
	public int update(RentContract rentContract) throws Exception ;
	
	@SuppressWarnings("deprecation")
	public RentContract getRentContractById(Integer id) throws Exception;
	
	public int selectRentByOfficeadd(String officeadd);
	
}
