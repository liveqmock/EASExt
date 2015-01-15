package com.creditease.eas.finance.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.creditease.eas.finance.bean.AccountTable;
import com.creditease.eas.finance.bean.AccountType;
import com.creditease.eas.finance.bean.AccountView;
import com.creditease.eas.finance.bean.AsstAccount;
import com.creditease.eas.finance.bean.AsstBean;
import com.creditease.eas.finance.bean.Asstacttype;
import com.creditease.eas.finance.bean.BaseUnit;
import com.creditease.eas.finance.bean.Company;
import com.creditease.eas.finance.bean.CostCenter;
import com.creditease.eas.finance.bean.Currency;
import com.creditease.eas.finance.bean.ExchangeRate;
import com.creditease.eas.finance.bean.ExchangeTable;
import com.creditease.eas.finance.bean.LedgerBean;
import com.creditease.eas.finance.bean.LedgerCountBean;
import com.creditease.eas.finance.bean.Period;
import com.creditease.eas.finance.bean.PeriodType;
import com.creditease.eas.finance.bean.RateOfTaxation;
import com.creditease.eas.finance.bean.VoucherAbstract;
import com.creditease.eas.finance.bean.VoucherTypes;
//@WebService(targetNamespace="http://com/creditease/eas/ws")
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="IEASfinancialInfoWSService")
@SOAPBinding(style = Style.DOCUMENT)
public interface IEASfinancialInfoWSService {
	/**
	 * 描述：1.核算主体
	 * 2013-5-9 下午08:34:20 by ygq
	 * @version
	 * @return
	 */
	List<Company> companyInfoQuery(@WebParam(name="fnumber")String fnumber,@WebParam(name="beginTime")String beginTime);
	/***
	 * 
	 * 描述：2凭证类型查询
	 * 2013-5-13 下午07:16:37 by ygq
	 * @version
	 * @return
	 */
	public List<VoucherTypes> voucherTypesQuery(@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：3.1查询会计科目的数量
	 * 2013-5-13 上午11:05:47 by ygq
	 * @version
	 * @return
	 */
	public Integer accountViewCountQuery(@WebParam(name="faccountTableNumber")String faccountTableNumber,@WebParam(name="faccountTypeNumber")String faccountTypeNumber,@WebParam(name="beginTime")String beginTime,@WebParam(name="comanyNumber")String comanyNumber);
	/**
	 * 描述：3.2会计科目查询
	 * 2013-5-9 下午08:32:04 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public  List<AccountView>  accountViewQuery(@WebParam(name="start")int start,@WebParam(name="limit")int limit,
	@WebParam(name="faccountTableNumber")String faccountTableNumber,@WebParam(name="faccountTypeNumber")String faccountTypeNumber,@WebParam(name="beginTime")String beginTime,@WebParam(name="comanyNumber")String comanyNumber);
	/**
	 * 
	 * 描述：4.科目表查询
	 * 2013-5-10 下午08:38:02 by ygq
	 * @version
	 * @return
	 */
	public List<AccountTable> accountTableQuery(@WebParam(name="beginTime")String beginTime,@WebParam(name="comFnumber")String comFnumber);
	/**
	 * 
	 * 描述：5.会计科目类型查询
	 * 2013-5-10 下午08:38:02 by ygq
	 * @version
	 * @return
	 */
	public List<AccountType> accountTypeQuery(@WebParam(name="faccountTableNumber")String faccountTableNumber,@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：6.辅助核算类型
	 * 2013-5-13 下午07:38:22 by ygq
	 * @version
	 * @return
	 */
	public  List<AsstAccount> asstAccountQuery(@WebParam(name="beginTime")String beginTime);
	/***
	 * 描述：辅助核算类型对应的辅助核算项的编码
	 * 2013-5-24 下午04:41:01 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
//	public List<Asstacttype> selectAsstAccountFnumber(@WebParam(name="fnumber")String fnumber);
	/**
	 * 
	 * 描述：7.辅助核算项目
	 * 2013-5-13 下午07:38:22 by ygq
	 * @version
	 * @return 
	 */
	public List<Asstacttype> asstacttypeQuery(@WebParam(name="asstFnumber")String asstFnumber,@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：8.币别查询
	 * 2013-5-21 上午09:22:39 by ygq
	 * @version
	 * @return
	 */
	public List<Currency> currencyQuery(@WebParam(name="fnumber")String fnumber,@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：9.税率设置查询
	 * 2013-5-21 上午10:15:00 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<RateOfTaxation> rateOfTaxationQuery(@WebParam(name="fnumber")String fnumber,@WebParam(name="beginTime")String beginTime);
	/**
	 * 描述：10.汇率查询
	 * 2013-5-23 下午11:08:05 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<ExchangeRate> exchangeRateQuery(@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：13.汇率表
	 * 2013-5-23 下午10:04:30 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<ExchangeTable> exchangeTableQuery(@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：14.会计期间
	 * 2013-5-23 下午11:05:42 by ygq
	 * @version
	 * @param periodYear
	 * @param beginTime
	 * @return
	 */
	public List<Period> periodQuery(@WebParam(name="periodYear")String periodYear,@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：15.会计期间类型
	 * 2013-5-23 下午11:05:42 by ygq
	 * @version
	 * @param periodYear
	 * @param beginTime
	 * @return
	 */
	public List<PeriodType> periodTypeQuery(@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：16.成本中心（该方法已经废弃)
	 * 2013-5-17 上午10:41:55 by guominggao
	 * @version
	 * @return
	 */
//	public  List<CostCenter> costCenterQuery(@WebParam(name="beginTime")String beginTime);
	/**
	 * 描述：17.1组织机构 数量
	 * 2013-5-28 上午12:14:34 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public Integer baseUnitCountQuery(@WebParam(name="beginTime")String beginTime);
	/**
	 * 描述：17.2组织机构
	 * 2013-5-28 上午12:14:51 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<BaseUnit> baseUnitQuery(@WebParam(name="beginTime")String beginTime,@WebParam(name="start")int start,@WebParam(name="limit")int limit);
	/**
	 * 描述：18.凭证摘要
	 * 2013-5-28 上午12:14:51 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<VoucherAbstract> voucherAbstractQuery(@WebParam(name="beginTime")String beginTime);
	
	/**
	 * 描述：辅助核算项
	 * 2013-5-28 下午02:32:54 by ygq
	 * @version
	 * @return map.put("fnumber", "Z03");
		map.put("min", "1");
		map.put("max", "1000");
	 */
	public Integer asstProgramCountQuery(@WebParam(name="fnumber")String fnumber,@WebParam(name="beginTime")String beginTime);
	/**
	 * 描述：辅助核算项
	 * 2013-5-28 下午02:32:54 by ygq
	 * @version
	 * @return
	 */
	public List<AsstBean> asstProgramQuery(@WebParam(name="fnumber")String fnumber,@WebParam(name="start")int start,@WebParam(name="limit")int limit,@WebParam(name="beginTime")String beginTime,@WebParam(name="comanyNumber")String comanyNumber);
//	/**
//	 * 
//	 * 描述：19.1辅助核算项-客户
//	 * 2013-5-21 上午09:14:38 by ygq
//	 * @version
//	 * @return
//	 */
//	public Integer customerCountQuery();
//	/**
//	 * 
//	 * 描述：19.2辅助核算项-客户
//	 * 2013-5-21 上午09:15:30 by ygq
//	 * @version
//	 * @param map
//	 * @return
//	 */
//	public List<Customer> customerQuery(@WebParam(name="start")int start,@WebParam(name="limit")int limit);
//	/**
//	 * 描述：测试下使用泛型
//	 * 2013-5-10 上午09:53:08 by ygq
//	 * @version
//	 * @param start
//	 * @param limit
//	 * @param faccountTableNumber
//	 * @param faccountTypeNumber
//	 * @return
//	 */
//	public  MessageListBean<AccountView>  accountViewQueryTest(@WebParam(name="start")int start,@WebParam(name="limit")int limit,
//			@WebParam(name="faccountTableNumber")String faccountTableNumber,@WebParam(name="faccountTypeNumber")String faccountTypeNumber);
//	/**
//	 * 描述：核算主体
//	 * 2013-5-9 下午08:34:20 by ygq
//	 * @version
//	 * @return
//	 */
//	MessageListBean<Company> companyInfoQueryTest(String fnumber);
	
	/**
	 * 
	 * 描述：客户信息
	 * 2013-6-8 上午10:50:23 by Administrator
	 * @version
	 * @param beginTime
	 * @return
	 */
	public List<AsstBean> csspQuery(@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：供应商信息
	 * 2013-6-8 上午10:50:23 by Administrator
	 * @version
	 * @param beginTime
	 * @return
	 */
	public List<AsstBean> supQuery(@WebParam(name="beginTime")String beginTime);
	/**
	 * 
	 * 描述：总账系统 核算数量
	 * 2013-7-12 下午05:06:51 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<LedgerCountBean> ledgerCountListQuery(@WebParam(name="beginTime")String beginTime,@WebParam(name="endTime")String endTime);
	/**
	 * 
	 * 描述：总账系统 本位币汇总
	 * 2013-7-12 下午05:06:51 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<LedgerBean> ledgerListQuery(@WebParam(name="beginTime")String beginTime,@WebParam(name="endTime")String endTime);
}
