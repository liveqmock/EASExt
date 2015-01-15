package com.creditease.eas.finance.kingdee.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.finance.bean.AccountBanks;
import com.creditease.eas.finance.bean.AccountTable;
import com.creditease.eas.finance.bean.AccountType;
import com.creditease.eas.finance.bean.AccountView;
import com.creditease.eas.finance.bean.Admin;
import com.creditease.eas.finance.bean.AsstAccount;
import com.creditease.eas.finance.bean.AsstBean;
import com.creditease.eas.finance.bean.Asstacttype;
import com.creditease.eas.finance.bean.BaseUnit;
import com.creditease.eas.finance.bean.Company;
import com.creditease.eas.finance.bean.CostCenter;
import com.creditease.eas.finance.bean.CsspgroupBean;
import com.creditease.eas.finance.bean.Currency;
import com.creditease.eas.finance.bean.Customer;
import com.creditease.eas.finance.bean.ExchangeRate;
import com.creditease.eas.finance.bean.ExchangeTable;
import com.creditease.eas.finance.bean.InnerAccount;
import com.creditease.eas.finance.bean.Material;
import com.creditease.eas.finance.bean.Period;
import com.creditease.eas.finance.bean.PeriodType;
import com.creditease.eas.finance.bean.PersonInfo;
import com.creditease.eas.finance.bean.RateOfTaxation;
import com.creditease.eas.finance.bean.VoucherAbstract;
import com.creditease.eas.finance.bean.VoucherTypes;
import com.creditease.eas.hr.bean.oa.WSSupplierBean;
public interface EASAsstProgramWSMapper {
	/**
	 * 描述：根据辅助核算项的编码，查询对应的表名和辅助核算类型的id
	 * 2013-5-28 下午10:46:11 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public Map<String,Object> selectTableNameByAsstActType(Map map);
	/**
	 * 描述：辅助核算项
	 * 2013-5-28 下午02:32:54 by ygq
	 * @version
	 * @return
	 */
	public Integer selectAsstProgramCountQuery(Map map);
	/**
	 * 描述：辅助核算项
	 * 2013-5-28 下午02:32:54 by ygq
	 * @version
	 * @return
	 */
	public List<AsstBean> selectAsstProgramQuery(Map map);
	/***
	 * 描述：自定义辅助核算项
	 * 2013-5-28 下午10:38:50 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectGeneralAsstProgramQuery(Map map);
	/***
	 * 描述：查询供应商或者成本中心的信息
	 * 2013-6-3 下午09:37:45 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectCustomerOrSupplier(Map map);
	
	/**
	 * 
	 * 描述：客商分类查询
	 * 2013-6-5 下午05:09:03 by Administrator
	 * @version
	 * @return
	 */
	public List<AsstBean> selectCsspgroupQuery(Map map);
	
	/**
	 * 
	 * 描述：根据客商分类ID查询客户信息
	 * 2013-6-5 下午05:59:42 by Administrator
	 * @version
	 * @param fid
	 * @return
	 */
	public List<AsstBean> selectCustomerQueryByFnumber(String fid);
	/**
	 * 
	 * 描述：客户信息
	 * 2013-6-7 下午05:13:23 by Administrator
	 * @version
	 * @return
	 */
	public List<AsstBean> selectCustomerQuery(Map map);
	/**
	 * 
	 * 描述：供应商信息
	 * 2013-6-8 上午10:37:24 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectSupplierQuery(Map map);
	/**
	 * 
	 * 描述：客户信息 分页
	 * 2013-6-7 下午05:13:32 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
//	public List<AsstBean> selectCusPageQuery(Map map);
	/**
	 * 
	 * 描述：供应商信息 分页
	 * 2013-7-8 下午09:32:57 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
//	public List<AsstBean> selectSupPageQuery(Map map);
	
	/**
	 * 
	 * 描述：根据客商分类ID查询供应商信息
	 * 2013-6-5 下午08:40:12 by Administrator
	 * @version
	 * @param fid
	 * @return
	 */
	public List<AsstBean> selectSupplierQueryByFnumber(String fid);
	
	/**
	 * 描述：所有组织信息的查询：建伟写的，用于处理左侧组织信息用的
	 * 2013-6-8 下午07:09:34 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectOrgAdminQuery(Map map);
	/**
	 * 
	 * 描述：所有职位信息的查询
	 * 2013-6-8 下午07:09:40 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectPositionQuery(Map map);
	/**
	 * 
	 * 描述：所有人员信息的查询
	 * 2013-6-8 下午07:09:47 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectPersonQuery(Map map);
	
	/**
	 * 
	 * 描述：客商信息 分页
	 * 2013-6-9 下午03:21:15 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> pageCsspQuery(Map map);
	//客商信息 总数
	public Integer csspCount(Map map); 
	//客户信息总数
	public Integer custCount(); 
	/**
	 * 
	 * 描述：公司信息的查询
	 * 2013-6-18 下午06:18:24 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectCompanyQuery(Map map);
	/**
	 * 
	 * 描述：成本中心信息的查询
	 * 2013-6-19 上午11:05:41 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectCostCenterQuery(Map map);
}