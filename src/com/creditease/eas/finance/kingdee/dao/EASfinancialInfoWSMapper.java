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
import com.creditease.eas.finance.bean.Currency;
import com.creditease.eas.finance.bean.Customer;
import com.creditease.eas.finance.bean.ExchangeRate;
import com.creditease.eas.finance.bean.ExchangeTable;
import com.creditease.eas.finance.bean.InnerAccount;
import com.creditease.eas.finance.bean.LedgerBean;
import com.creditease.eas.finance.bean.LedgerCountBean;
import com.creditease.eas.finance.bean.Material;
import com.creditease.eas.finance.bean.Period;
import com.creditease.eas.finance.bean.PeriodType;
import com.creditease.eas.finance.bean.PersonInfo;
import com.creditease.eas.finance.bean.RateOfTaxation;
import com.creditease.eas.finance.bean.VoucherAbstract;
import com.creditease.eas.finance.bean.VoucherTypes;
import com.creditease.eas.hr.bean.oa.WSSupplierBean;
public interface EASfinancialInfoWSMapper {
	/**
	 * 
	 * 描述：核算主体信息查询
	 * 2013-5-7 下午04:43:44 by ygq
	 * @version
	 * @return
	 */
	public List<Company> selectCompanyInfo(Map map);
	/***
	 * 
	 * 描述：凭证类型查询
	 * 2013-5-13 下午07:16:37 by ygq
	 * @version
	 * @return
	 */
	public List<VoucherTypes> selectVoucherTypes(Map map);
	/**
	 * 
	 * 描述：科目表查询
	 * 2013-5-10 下午08:38:02 by ygq
	 * @version
	 * @return
	 */
	public List<AccountTable> selectAccountTable(String beginTime);
	/***
	 * 描述：科目表查询 new
	 * 2013-6-2 下午10:41:32 by ygq
	 * @version
	 * @return
	 */
	public List<AccountTable> selectAccountTableNew(Map map);
	/**
	 * 
	 * 描述：会计科目类型查询
	 * 2013-5-10 下午08:38:02 by ygq
	 * @version
	 * @return
	 */
	public List<AccountType> selectAccountType(Map map);
	/**
	 * 描述：会计科目的接口
	 * 2013-5-8 下午01:39:02 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<AccountView> selectAccountView(Map map);
	/**
	 * 描述：查询会计科目的总条数
	 * 2013-5-10 下午01:26:09 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public int selectAccountViewCount(Map map);
	/**
	 * 
	 * 描述：辅助核算类型
	 * 2013-5-13 下午07:38:22 by ygq
	 * @version
	 * @return
	 */
	public  List<AsstAccount> selectAsstAccount(Map map);
	/***
	 * 
	 * 描述：辅助核算类型对应的辅助核算项的编码
	 * 2013-5-24 下午03:56:23 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<Asstacttype> selectAsstAccountFnumber(@Param(value="fnumber")String fnumber);
	/**
	 * 
	 * 描述：辅助核算项目查询
	 * 2013-5-13 下午07:38:22 by ygq
	 * @version
	 * @return
	 */
	public List<Asstacttype> selectAsstacttype(Map map);
	/**
	 * 
	 * 描述：辅助核算项-客户的数量
	 * 2013-5-20 下午05:17:42 by ygq
	 * @version
	 * @return
	 */
	public Integer selectCustomerCount();
	/**
	 * 
	 * 描述：辅助核算项-客户
	 * 2013-5-20 下午05:17:06 by ygq
	 * @version
	 * @return
	 */
	public List<Customer> selectCustomer(Map map);
	/**
	 * 
	 * 描述：币别信息
	 * 2013-1-21 下午01:39:59 by ygq
	 * @version
	 * @return 
	 */
	public List<Currency> selectCurrency(Map map);
	/**
	 * 
	 * 描述：9.税率设置查询
	 * 2013-5-21 上午10:15:00 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<RateOfTaxation> selectRateOfTaxation(Map map);
	/**
	 * 
	 * 描述：汇率
	 * 2013-5-23 下午10:04:30 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<ExchangeRate> selectExchangeRate(Map map);
	/**
	 * 
	 * 描述：汇率表
	 * 2013-5-23 下午10:04:30 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<ExchangeTable> selectExchangeTable(Map map);
	/**
	 * 
	 * 描述：会计期间
	 * 2013-5-23 下午10:04:30 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<Period> selectPeriod(Map map);
	/**
	 * 
	 * 描述：会计期间类型
	 * 2013-5-23 下午10:04:30 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<PeriodType> selectPeriodType(Map map);
	/**
	 * 
	 * 描述：成本中心 该方法以及废弃
	 * 2013-5-17 上午10:26:28 by guominggao
	 * @version
	 * @return
	 */
//	public List<CostCenter> selectcostCenterQuery(Map map);
	/**
	 * 描述：组织机构 数量
	 * 2013-5-28 上午12:14:34 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public Integer selectBaseUnitCountQuery(Map map);
	/**
	 * 描述：组织机构
	 * 2013-5-28 上午12:14:51 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<BaseUnit> selectBaseUnitQuery(Map map);
	/**
	 * 描述：凭证摘要
	 * 2013-5-28 上午12:14:51 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<VoucherAbstract> selectVoucherAbstractQuery(Map map);
	/***
	 * 描述：辅助核算项-供应商的数量
	 * 2013-5-27 下午05:31:55 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<AccountBanks> selectWSSuppliercount(Map map);
	/***
	 * 描述：辅助核算项-供应商
	 * 2013-5-27 下午05:31:55 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<WSSupplierBean> selectWSSupplierInfo(Map map);
	/***
	 * 描述：辅助核算项-银行账户
	 * 2013-5-27 下午05:31:55 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectAccountBanksQuery(Map map);
	/**
	 * 描述：辅助核算项--员工数量
	 * 2013-5-27 下午11:24:09 by ygq
	 * @version
	 * @return
	 */
	public List<PersonInfo> selectPersonCount(Map map);
	/**
	 * 
	 * 描述：辅助核算项--员工
	 * 2013-5-27 下午11:24:09 by ygq
	 * @version
	 * @return
	 */
	public List<PersonInfo> selectPerson(Map map);
	/**
	 * 描述：辅助核算项--物料
	 * 2013-5-28 上午12:09:40 by ygq
	 * @version
	 * @return
	 */
	public List<Material> materialQuery();
	/**
	 * 
	 * 描述：辅助核算项-行政组织
	 * 2013-5-28 上午12:09:10 by ygq
	 * @version
	 * @return
	 */
	public Integer selectAdminCount(Map map);
	/**
	 * 
	 * 描述：辅助核算项-行政组织
	 * 2013-5-28 上午12:09:10 by ygq
	 * @version
	 * @return
	 */
	public List<AsstBean> selectAdminQuery(Map map);
	/**
	 * 描述：辅助核算项-内部账户
	 * 2013-5-28 上午12:06:51 by ygq
	 * @version
	 */
	public List<InnerAccount> selectInnerAccountQuery();
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
	
	/**
	 * 
	 * 描述：客户信息 分页
	 * 2013-6-7 下午05:13:32 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectCusPageQuery(Map map);
	/**
	 * 
	 * 描述：供应商信息 分页
	 * 2013-7-8 下午09:32:57 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> selectSupPageQuery(Map map);
	//总账系统 核算数量 
	public List<LedgerCountBean> selectLedgerCountList(Map map);
	//总账系统 本位币信息
	public List<LedgerBean> selectLedgerList(Map map);
	
}