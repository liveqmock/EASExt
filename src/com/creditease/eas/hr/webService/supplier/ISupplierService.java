package com.creditease.eas.hr.webService.supplier;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.creditease.eas.hr.bean.oa.ProvinceAndCityBean;
import com.creditease.eas.hr.bean.oa.WSSupplierBean;
import com.creditease.eas.hr.bean.oa.WSSupplierListBean;
import com.creditease.eas.hr.bean.oa.WrapperMap; 
/**
 * 查询供应商信息的接口
 * @ISupplierService.java
 * created at 2013-3-25 下午06:04:46 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@WebService(targetNamespace="supplierService")
public interface ISupplierService {
	/**
	 * 描述：
	 * 2013-3-25 下午06:05:28 by ygq
	 * @version
	 * @return
	 */
	public WSSupplierBean selectSupplierInfoById(String supplierId);
	
	/**
	 * 
	 * 描述：
	 * 2013-4-10 上午10:27:45 by guominggao
	 * @version
	 * @param supplierId
	 * @return
	 */
	public  List<WSSupplierBean> selectSupplierInfoByNames(WrapperMap wra);
	
	/**
	 * 
	 * 描述：分页供应商信息
	 * 2013-4-10 下午07:04:46 by guominggao
	 * @version
	 * @param wra
	 * @return
	 */
	public WSSupplierListBean selectSupplierList(WrapperMap wra);
	
	/**
	 * 
	 * 描述：省
	 * 2013-4-16 上午10:50:29 by guominggao
	 * @version
	 * @return
	 */
	public List<ProvinceAndCityBean> selectProvinceList();
//	
//	/**
//	 * 
//	 * 描述： 根据省，得到市
//	 * 2013-4-16 上午10:51:03 by guominggao
//	 * @version
//	 * @param fnumber
//	 * @return
//	 */
	public List<ProvinceAndCityBean> selectCityList(String fnumber);
}
