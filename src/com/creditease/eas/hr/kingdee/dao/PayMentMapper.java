package com.creditease.eas.hr.kingdee.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.creditease.eas.hr.bean.CapticalAssetsBean;
public interface PayMentMapper {
	/**
	 * 描述：查询所有的，宜信公司下包含的是所有的
	 * 2013-3-22 下午03:30:55 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<CapticalAssetsBean> selectAssetsClassNameAndCodeByFistClassAll(String first_class);
	/**	
	 * 通过一级大类【G固定资产、D低值耐用品】获取固定资产类别名称与固定资产类别编码	
	 */	
//	public List<CapticalAssetsBean> selectAssetsClassNameAndCodeByFistClass(String first_class);
	public List<CapticalAssetsBean> selectAssetsClassNameAndCodeByFistClass(Map<String,String> map);
	/**
	 * 描述：查询供应商的信息
	 * 2013-3-17 下午10:29:19 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> selectSupplierInfo();
	/**
	 * 描述：查询省市的信息
	 * 2013-3-17 下午10:29:19 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> selectCityInfo();
	/**
	 * 描述：查询计量单位的信息
	 * 2013-3-18 上午11:19:20 by ygq
	 * @version
	 * @return
	 */
	public List<CapticalAssetsBean> selectMeasureUnitInfo(Map map);
	
}