package com.creditease.eas.hr.kingdee.dao; 
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditease.eas.hr.bean.oa.ProvinceAndCityBean;
import com.creditease.eas.hr.bean.oa.WSSupplierBean;
import com.creditease.eas.hr.bean.oa.WrapperMap; 
public interface WSSupplierMapper {
	/**
	 * 描述：查询供应商的信息
	 * 2013-3-17 下午10:29:19 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> selectSupplierInfo(String fnumber);
	
	
	/**
	 * 描述：查询省市的信息
	 * 2013-3-17 下午10:29:19 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> selectCityInfo();
	
	/**
	 * 
	 * 描述：查询分页供应商的信息
	 * 2013-4-9 下午06:28:44 by guominggao
	 * @version
	 * @param supplierName
	 * @return
	 */
	public List<WSSupplierBean> selectWSSupplierInfo(WrapperMap wra);
	
	/**
	 * 
	 * 描述：得到分页供应商总行数
	 * 2013-4-10 下午04:09:57 by guominggao
	 * @version
	 * @param map
	 * @return
	 */
	public Integer selectWSSuppliercount(WrapperMap wra);
	
	public List<ProvinceAndCityBean > selectProvinceInfo();
	
	public List<ProvinceAndCityBean > selectCityInfoByProvincenumber(String fnumber);
}