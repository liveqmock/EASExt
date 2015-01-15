package com.creditease.eas.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;

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
import com.creditease.eas.finance.query.EASfinancialInfoCsspQuery;
import com.creditease.eas.finance.query.EASfinancialInfoWSQuery;
import com.creditease.eas.finance.service.IEASfinancialInfoWSService;
import com.creditease.eas.util.EASfinancialInfoWSUtil;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.Utils;

/**
 * 给AMS提供的财务的基本信息的接口
 * @EASfinancialInfoWSService.java
 * created at 2013-5-7 上午11:21:53 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
//@WebService(endpointInterface = "com.creditease.eas.finance.service.IEASfinancialInfoWSService",targetNamespace="http://com/creditease/eas/ws")
//@SOAPBinding(style = Style.RPC)
public class EASfinancialInfoWSServiceImpl implements IEASfinancialInfoWSService{
	private EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
	
	private EASfinancialInfoCsspQuery csspQuery = new EASfinancialInfoCsspQuery();
	
	@Override
	public List<Company> companyInfoQuery(String fnumber,String beginTime) {
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		map.put("fnumber", fnumber);
		map.put("beginTime", date);
		return query.companyInfoQuery(map);
	}
	@Override
	public List<AccountTable> accountTableQuery(String beginTime,String comFnumber) {
		// TODO Auto-generated method stub
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fnumber", comFnumber);
		map.put("beginTime", date);
		return query.accountTableNewQuery(map);
	}
	@Override
	public List<AccountType> accountTypeQuery(String faccountTableNumber,String beginTime) {
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fnumber", faccountTableNumber);
		map.put("beginTime", date);
		// TODO Auto-generated method stub
		return query.accountTypeQuery(map);
	}
	@Override
	public Integer accountViewCountQuery(String faccountTableNumber, String faccountTypeNumber,String beginTime,String comanyNumber) {
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		map.put("beginTime", date);
		map.put("faccountTableNumber", faccountTableNumber);
		map.put("faccountTypeNumber", faccountTypeNumber);
		map.put("comanyNumber", comanyNumber);
		return query.accountViewCountQuery(map);
	}
	@Override
	public List<AccountView> accountViewQuery(int start, int limit,
			String faccountTableNumber, String faccountTypeNumber,String beginTime,String comanyNumber ) {
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		int page = EASfinancialInfoWSUtil.accountOfPage(start, limit);
		int min = (page-1)*limit + 1;
		int max =  page*limit;
		map.put("min", min);
		map.put("max", max);
		map.put("faccountTableNumber", faccountTableNumber);
		map.put("faccountTypeNumber", faccountTypeNumber);
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		 map.put("beginTime", date);
		 map.put("comanyNumber", comanyNumber);
		return query.accountViewQuery(map);
	}
	@Override
	public List<VoucherTypes> voucherTypesQuery(String beginTime) {
		// TODO Auto-generated method stub
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginTime", date);
		return query.voucherTypesQuery(map);
	}
	@Override
	public List<AsstAccount> asstAccountQuery(String beginTime) {
		// TODO Auto-generated method stub
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginTime", date);
		return query.asstAccountQuery(map);
	}
	
//	@Override
//	public List<CostCenter> costCenterQuery(String beginTime) {
//		// TODO Auto-generated method stub
//		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("beginTime", date);
//		return query.costCenterQuery(map);
//	}
	@Override
	public List<Asstacttype>  asstacttypeQuery(String asstFnumber,String beginTime) {
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		map.put("fnumber", asstFnumber);
		map.put("beginTime", date);
		return query.asstacttypeQuery(map);
	}
//	@Override
//	public MessageListBean<Company> companyInfoQueryTest(String fnumber) {
//		try{
//			//List<Company> list =  query.companyInfoQuery(fnumber);
//			List<Company> list = new ArrayList<Company>();
//			Company com = new Company();
//			com.setAccountName("abc");
//			Company com2 = new Company();
//			com2.setAccountName("bcd");
//			list.add(com);
//			list.add(com2);
//			MessageListBean<Company> message = new MessageListBean<Company>();
//			message.setObj(list);
//			message.setErrCode("0000");
//			message.setRows(10);
//			return message;
//		}catch(Exception ex){
//			MessageListBean<Company> message = new MessageListBean<Company>();
//			message.setErrCode("0001");
//			message.setErrMessage(ex.getMessage());
//			return message;
//		}
//	}
//	@Override
//	public MessageListBean<AccountView> accountViewQueryTest(int start, int limit,
//			String faccountTableNumber, String faccountTypeNumber) {
//			try{
//				Map<String,Object> map = new HashMap<String,Object>();
//				//这个需要考虑最大页数的查询
//				int page = EASfinancialInfoWSUtil.accountOfPage(start, limit);
//				int min = (page-1)*limit + 1;
//				int max =  page*limit;
//				map.put("min", min);
//				map.put("max", max);
//				map.put("faccountTableNumber", faccountTableNumber);
//				map.put("faccountTypeNumber", faccountTypeNumber);
//				List<AccountView> list =  query.accountViewQuery(map);
//				MessageListBean<AccountView> message = new MessageListBean<AccountView>();
//				message.setObj(list);
//				message.setErrCode("0000");
//				message.setRows(10);
//				return message;
//			}catch(Exception ex){
//				MessageListBean<AccountView> message = new MessageListBean<AccountView>();
//				message.setErrCode("0001");
//				message.setErrMessage(ex.getMessage());
//				return message;
//			}
//	}
//	public static void main(String[] args) {
//		EASfinancialInfoWSServiceImpl query = new EASfinancialInfoWSServiceImpl();
//		List<Asstacttype> type = query.asstacttypeQuery("34", null);
//		System.out.println(type.size());
		//.accountViewQuery(1, 10, null, null);
//		System.out.println(bean.size());
//		voucherTypesQuery();
//		List listC = new EASfinancialInfoWSServiceImpl().companyInfoQuery(null);
//		System.out.println(listC.size());
//		EASfinancialInfoWSServiceImpl impl  = new EASfinancialInfoWSServiceImpl();
//		MessageListBean bean = impl.accountViewQueryTest(1, 10, null, null);
//		System.out.println(bean.getObj());
//		
//		MessageListBean bean2 = impl.companyInfoQuery(null);
//		System.out.println(bean2.getObj());
		
//		List<ExchangeRate> list = query.exchangeRateQuery(null);
//		System.out.println(list.size());
//		for(int i=0;i<list.size();i++){
//			ExchangeRate p = list.get(i);
//			System.out.println(p.getFname() + "\t" + p.getFnumber() + "\t" + p.getFlastUpdateTime());
//		}
//		String beginTime = "2011-05-23 22:22:22";
//		List<ExchangeTable> table = query.exchangeTableQuery(beginTime);
//		System.out.println(table.size());
//		List<Period> per = query.periodQuery("2013", "2012-10-15 11:12:15");
//		System.out.println("size\t" + per.size());
//		for(int i=0;i<per.size();i++){
//			Period p = per.get(i);
//			System.out.println(p.getFisAdjustPeriod() + "\t" + p.getFperiodQuarter() + "\t" + p.getFperiodYear()
//			+ "\t" + p.getFtypeID() + "\t" + p.getFperiodNumber() + "\t" + p.getFbeginDate() + "\t" + p.getFendDate()
//			+ "\t" + p.getFlastUpdateTime());
//			System.out.println(p.getFperiodYear());
//		}
//	}
//	@Override
//	public Integer customerCountQuery() {
//		// TODO Auto-generated method stub
//		return query.customerCountQuery();
//	}
	@Override
	public List<Currency> currencyQuery(String fnumber,String beginTime) {
		// TODO Auto-generated method stub
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("fnumber", fnumber);
		map.put("beginTime", date);
		return query.currencyQuery(map);
	}
	@Override
	public List<RateOfTaxation> rateOfTaxationQuery(String fnumber,String beginTime) {
		// TODO Auto-generated method stub
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("fnumber", fnumber);
		map.put("beginTime", date);
		return query.rateOfTaxationQuery(map);
	}
//	@Override
//	public List<Customer> customerQuery(int start, int limit) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		//这个需要考虑最大页数的查询
//		int page = EASfinancialInfoWSUtil.accountOfPage(start, limit);
//		int min = (page-1)*limit + 1;
//		int max =  page*limit;
//		map.put("min", min);
//		map.put("max", max);
//		return query.customerQuery(map);
//	}
	@Override
	public List<ExchangeRate> exchangeRateQuery(String beginTime) {
		// TODO Auto-generated method stub
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("beginTime", date);
		return query.exchangeRateQuery(map);
	}
	@Override
	public List<ExchangeTable> exchangeTableQuery(String beginTime) {
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("beginTime", date);
		return query.exchangeTableQuery(map);
	}
	@Override
	public List<Period> periodQuery(String periodYear, String beginTime) {
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("periodYear", periodYear);
		map.put("beginTime", date);
		return query.periodQuery(map);
	}
	@Override
	public List<PeriodType> periodTypeQuery(String beginTime) {
		// TODO Auto-generated method stub
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("beginTime", date);
		return query.periodTypeQuery(map);
	}
	@Override
	public Integer baseUnitCountQuery(String beginTime) {
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		//这个需要考虑最大页数的查询
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginTime", date);
		return query.baseUnitCountQuery(map);
	}
	@Override
	public List<BaseUnit> baseUnitQuery(String beginTime,int start,int limit) {
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		//这个需要考虑最大页数的查询
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		int page = EASfinancialInfoWSUtil.accountOfPage(start, limit);
		int min = (page-1)*limit + 1;
		int max =  page*limit;
		map.put("min", min);
		map.put("max", max);
		map.put("beginTime", date);
		return query.baseUnitQuery(map);
	}
	@Override
	public List<VoucherAbstract> voucherAbstractQuery(String beginTime) {
		Date date = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		//这个需要考虑最大页数的查询
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginTime", date);
		return query.voucherAbstractQuery(map);
	}
	@Override
	public Integer asstProgramCountQuery(String fnumber, String beginTime) {
		Map map = new HashMap();
		Date date = StringUtil.strToDate(beginTime,"yyyy-MM-dd HH:mm:ss");
		map.put("beginTime", date);
		map.put("fnumber", fnumber);//产品
		return query.asstProgramCountQuery(map);
	}
	@Override
	public List<AsstBean> asstProgramQuery(String fnumber, int start,
			int limit, String beginTime,String comanyNumber) {
		Map map = new HashMap();
		int page = EASfinancialInfoWSUtil.accountOfPage(start, limit);
		int min = (page-1)*limit + 1;
		int max =  page*limit;
		Date date = StringUtil.strToDate(beginTime,"yyyy-MM-dd HH:mm:ss");
		map.put("beginTime", date);
		map.put("fnumber", fnumber);//产品
		map.put("min", min);
		map.put("max", max);
		map.put("comanyNumber", comanyNumber);
//		System.out.println("max\t" + max);
//		System.out.println("what\t" + query.asstProgramQuery(map).size());
		return query.asstProgramQuery(map);
	}
	/**
	 * 
	 * 描述：客户信息接口
	 * 2013-6-7 下午05:15:30 by Administrator
	 * @version
	 * @return
	 */
	public List<AsstBean> csspQuery(String beginTime){
		Map map = new HashMap();
		Date date = StringUtil.strToDate(beginTime,"yyyy-MM-dd HH:mm:ss");
		map.put("beginTime", date);
		map.put("fsid", "00000000-0000-0000-0000-000000000002BC122A7F");  //客商分类  客户信息
		List<AsstBean> list = new ArrayList<AsstBean>();
		List<AsstBean> custlist = new ArrayList<AsstBean>();
		list = EASfinancialInfoCsspQuery.csspgroupQuery(map);
		custlist = EASfinancialInfoCsspQuery.customerQuery(map);
		list.addAll(custlist);
		return list;
	}
	/**
	 * 
	 * 描述：客商分类+供应商信息
	 * 2013-6-8 上午10:45:56 by Administrator
	 * @version
	 * @param beginTime
	 * @return
	 */
	public List<AsstBean> supQuery(String beginTime){
		Map map = new HashMap();
		Date date = StringUtil.strToDate(beginTime,"yyyy-MM-dd HH:mm:ss");
		map.put("beginTime", date);
		map.put("fsid", "00000000-0000-0000-0000-000000000001BC122A7F");  //客商分类  供应商信息
		List<AsstBean> list = new ArrayList<AsstBean>();
		List<AsstBean> suplist = new ArrayList<AsstBean>();
		list = EASfinancialInfoCsspQuery.csspgroupQuery(map);
		suplist = EASfinancialInfoCsspQuery.supplierQuery(map);
		list.addAll(suplist);
		return list;
	}
	/**
	 * 
	 * 描述：行政组织+职位+人员
	 * 2013-6-8 下午07:34:07 by Administrator
	 * @version
	 * @return
	 */
	public List<AsstBean> orgQuery(String beginTime){
		Map map = new HashMap();
		Date date = StringUtil.strToDate("2013-1-22 13:57:06","yyyy-MM-dd HH:mm:ss");
		map.put("beginTime", date);
		map.put("beginTime", null);
		List<AsstBean> orglist = new ArrayList<AsstBean>();
		List<AsstBean> polist = new ArrayList<AsstBean>();
		List<AsstBean> personlist = new ArrayList<AsstBean>();
		orglist = EASfinancialInfoCsspQuery.orgAdminQuery(map);	//所有组织信息的查询
		polist = EASfinancialInfoCsspQuery.positionQuery(map);	//所有职位信息的查询
		personlist = EASfinancialInfoCsspQuery.personQuery(map);		//所有人员信息的查询 
		orglist.addAll(polist);
		orglist.addAll(personlist);
		return orglist;
	}
	/**
	 * 总账系统 核算数量
	 */
	@Override
	public List<LedgerCountBean> ledgerCountListQuery(String beginTime,String endTime) {
		// TODO Auto-generated method stub
//		Date begindate = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
//		Date enddate = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Date begindate = Utils.getTimeByStr(beginTime);
		Date enddate = Utils.getTimeByStr(endTime);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginTime", begindate);
		map.put("endTime", enddate);
		return query.ledgerCountList(map);
	}
	/**
	 * 总账系统 本位币汇总
	 */
	@Override
	public List<LedgerBean> ledgerListQuery(String beginTime,String endTime) {
		// TODO Auto-generated method stub
//		Date begindate = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
//		Date enddate = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Date begindate = Utils.getTimeByStr(beginTime);
		Date enddate = Utils.getTimeByStr(endTime);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginTime", begindate);
		map.put("endTime", enddate);
		return query.ledgerList(map);
	}
	
	public static void main(String[] args) {
		EASfinancialInfoWSServiceImpl service = new EASfinancialInfoWSServiceImpl();
//		List list = service.asstacttypeQuery("", "");
//		System.out.println(list.size());
		
//		List list = service.ledgerListQuery("","");
//		System.out.println(list.size());
//		List<AsstBean> list = service.csspQuery("2013-1-22 13:57:06"); //客户信息
//		List<AsstBean> list = service.supQuery("2013-1-22 13:57:06"); //供应商信息
		
//		List<AsstBean> list = service.orgQuery(); //行政组织+职位+人员
//		for(AsstBean css:list){
//			System.out.println(css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//					+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//		}
//		List list =service.asstProgramQuery("00005", 1, 100000, ""); 
//		System.out.println("size\t" + list.size());
	}
}
