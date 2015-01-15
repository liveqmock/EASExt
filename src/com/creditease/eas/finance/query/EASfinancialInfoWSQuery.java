package com.creditease.eas.finance.query;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

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
import com.creditease.eas.finance.bean.Customer;
import com.creditease.eas.finance.bean.ExchangeRate;
import com.creditease.eas.finance.bean.ExchangeTable;
import com.creditease.eas.finance.bean.LedgerBean;
import com.creditease.eas.finance.bean.LedgerCountBean;
import com.creditease.eas.finance.bean.Period;
import com.creditease.eas.finance.bean.PeriodType;
import com.creditease.eas.finance.bean.RateOfTaxation;
import com.creditease.eas.finance.bean.VoucherAbstract;
import com.creditease.eas.finance.bean.VoucherTypes;
import com.creditease.eas.finance.kingdee.dao.EASfinancialInfoWSMapper;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.Utils;
/**
 * 辅助核算项 接口的查询
 * @QualifyingMatureQuery001.java
 * created at 2013-1-4 下午02:07:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class EASfinancialInfoWSQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：核算主体
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version 
	 */
	public  List<Company> companyInfoQuery(Map map){
		SqlSession session = null;
		List<Company> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectCompanyInfo(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：科目表查询
	 * 2013-5-10 下午08:38:02 by ygq
	 * @version
	 * @return
	 */
	public List<AccountTable> accountTableQuery(String beginTime){
		SqlSession session = null;
		List<AccountTable> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectAccountTable(beginTime);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：科目表查询
	 * 2013-5-10 下午08:38:02 by ygq
	 * @version
	 * @return
	 */
	public List<AccountTable> accountTableNewQuery(Map map){
		SqlSession session = null;
		List<AccountTable> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectAccountTableNew(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：会计科目类型查询
	 * 2013-5-10 下午08:38:02 by ygq
	 * @version
	 * @return
	 */
	public List<AccountType> accountTypeQuery(Map map){
		SqlSession session = null;
		List<AccountType> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectAccountType(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 描述：会计科目查询
	 * 2013-5-8 下午01:40:11 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<AccountView> accountViewQuery(Map map){
		SqlSession session = null;
		List<AccountView> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectAccountView(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 描述：查询会计科目的数量
	 * 2013-5-9 下午08:56:56 by ygq
	 * @version
	 * @param wra
	 * @return
	 */
	public static Integer accountViewCountQuery(Map map){
		SqlSession session = null;
		Integer count = null ;
		try{ 
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			count = mapper.selectAccountViewCount(map);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return count;
	}
	/***
	 * 
	 * 描述：凭证类型查询
	 * 2013-5-13 下午07:16:37 by ygq
	 * @version
	 * @return
	 */
	public List<VoucherTypes> voucherTypesQuery(Map map){
		SqlSession session = null;
		List<VoucherTypes> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectVoucherTypes(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：辅助核算类型
	 * 2013-5-13 下午07:38:22 by ygq
	 * @version
	 * @return
	 */
	public List<AsstAccount> asstAccountQuery(Map map){
		SqlSession session = null;
		List<AsstAccount> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectAsstAccount(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 描述：辅助核算项的编码
	 * 2013-5-24 下午04:04:52 by ygq
	 * @version
	 * @param asstAccount
	 */
	public List<Asstacttype> asstAccountFnumberQuery(String asstAccount){
		SqlSession session = null;
		List<Asstacttype> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectAsstAccountFnumber(asstAccount);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：辅助核算项目
	 * 2013-5-13 下午07:38:22 by ygq
	 * @version
	 * @return
	 */
	public List<Asstacttype> asstacttypeQuery(Map map){
		SqlSession session = null;
		List<Asstacttype> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectAsstacttype(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：辅助核算项-客户的数量
	 * 2013-5-13 下午07:38:22 by ygq
	 * @version
	 * @return
	 */
	public Integer customerCountQuery(){
		SqlSession session = null;
		int id = 0;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			id = mapper.selectCustomerCount();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return id;
	}
	/**
	 * 
	 * 描述：辅助核算项-客户
	 * 2013-5-13 下午07:38:22 by ygq
	 * @version
	 * @return
	 */
	public List<Customer> customerQuery(Map map){
		SqlSession session = null;
		List<Customer> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectCustomer(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/***
	 * 
	 * 描述：银行账户
	 * 2013-5-27 下午05:38:05 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> accountBanksQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectAccountBanksQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：币别信息
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version 
	 */
	public List<Currency> currencyQuery(Map map){
		SqlSession session = null;
		List<Currency> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectCurrency(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：税率设置查询
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version 
	 */
	public List<RateOfTaxation> rateOfTaxationQuery(Map map){
		SqlSession session = null;
		List<RateOfTaxation> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectRateOfTaxation(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：成本中心信息
	 * 2013-5-17 上午10:28:03 by guominggao
	 * @version
	 * @return
	 */
//	public  List<CostCenter> costCenterQuery(Map map){
//		SqlSession session = null;
//		List<CostCenter> list = null;
//		try {
//			session = getSession();
//			list =  session.selectList("com.creditease.eas.finance.kingdee.dao.EASfinancialInfoWSMapper.selectcostCenterQuery");
//			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
//			list = mapper.selectcostCenterQuery(map);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
	/**
	 * 
	 * 描述：汇率
	 * 2013-5-23 下午10:49:47 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<ExchangeRate> exchangeRateQuery(Map map){
		SqlSession session = null;
		List<ExchangeRate> table = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			table = mapper.selectExchangeRate(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return table;
	}
	/**
	 * 
	 * 描述：汇率表
	 * 2013-5-23 下午10:04:30 by ygq
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<ExchangeTable> exchangeTableQuery(Map map){
		SqlSession session = null;
		List<ExchangeTable> table = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			table = mapper.selectExchangeTable(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return table;
	}
	/**
	 * 
	 * 描述：会计期间
	 * 2013-5-23 下午10:51:07 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<Period> periodQuery(Map map){
		SqlSession session = null;
		List<Period> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectPeriod(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：会计期间类型
	 * 2013-5-23 下午10:51:07 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<PeriodType> periodTypeQuery(Map map){
		SqlSession session = null;
		List<PeriodType> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectPeriodType(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：组织机构数量
	 * 2013-5-23 下午10:51:07 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public Integer baseUnitCountQuery(Map map){
		SqlSession session = null;
		Integer count = 0;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			count = mapper.selectBaseUnitCountQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return count;
	}
	/**
	 * 
	 * 描述：组织机构
	 * 2013-5-23 下午10:51:07 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<BaseUnit> baseUnitQuery(Map map){
		SqlSession session = null;
		List<BaseUnit> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectBaseUnitQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：凭证摘要
	 * 2013-5-23 下午10:51:07 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<VoucherAbstract> voucherAbstractQuery(Map map){
		SqlSession session = null;
		List<VoucherAbstract> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectVoucherAbstractQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 描述：辅助核算项--数量
	 * 2013-5-28 下午02:34:28 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public static Integer asstProgramCountQuery(Map map){
		SqlSession session = null;
		Integer number = 0;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			Map<String,Object> mapInfo = mapper.selectTableNameByAsstActType(map);
			String tableName = mapInfo.get("FREALTIONDATAOBJECT").toString();
			Object fglasstacttypegrpid = mapInfo.get("FGLASSTACTTYPEGRPID");
			map.put("tableName", tableName);
			map.put("fgroupid", fglasstacttypegrpid);
			
			number = mapper.selectAsstProgramCountQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return number;
	}
	/**
	 * 描述：辅助核算项：包括自定义辅助核算项和非自定义辅助核算项
	 * 2013-5-28 下午10:42:09 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<AsstBean> asstProgramQuery(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			Map<String,Object> mapInfo = mapper.selectTableNameByAsstActType(map);
			Object fglasstacttypegrpid = mapInfo.get("FGLASSTACTTYPEGRPID");
			String tableName = mapInfo.get("FREALTIONDATAOBJECT").toString();
			Map mapParameter = new HashMap();
			mapParameter.put("fgroupid", fglasstacttypegrpid);
			mapParameter.put("beginTime", map.get("beginTime"));
			mapParameter.put("min", map.get("min"));
			mapParameter.put("max", map.get("max"));
			mapParameter.put("tableName", tableName);
			mapParameter.put("comanyNumber", map.get("comanyNumber"));
			//根据不同的情况，查询辅助核算项的相关信息
			if(null != fglasstacttypegrpid){
				list = mapper.selectGeneralAsstProgramQuery(mapParameter);
			}else if(null != tableName && tableName.equals("T_BD_Customer")){
				mapParameter.put("fsid", "00000000-0000-0000-0000-000000000002BC122A7F");  //客商分类  客户信息
				int csspCount = mapper.csspCount(mapParameter);//客商信息总页数  	左侧菜单树
				int max = Integer.parseInt(map.get("max").toString());
				int min = Integer.parseInt(map.get("min").toString());
				int pageSize = max - min + 1;
				//差多少数据
				int rem = pageSize - csspCount;
//				System.out.println("pageSize:"+pageSize+" rem: "+rem);
				if(csspCount>min){
					list = mapper.pageCsspQuery(mapParameter);
					mapParameter.put("min", 1);
					mapParameter.put("max", rem);
					
					List<AsstBean> list2 = mapper.selectCusPageQuery(mapParameter);
					list.addAll(list2);
//					System.err.println("-----------------------");
//					for (AsstBean css : list2) {
//						System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//								+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//					}
				}else{
					mapParameter.put("min", min - csspCount);
					mapParameter.put("max", max - csspCount);
					list = mapper.selectCusPageQuery(mapParameter);
//					for (AsstBean css : list) {
//						System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//								+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//					}
				}
			}else if(null != tableName && tableName.equals("T_BD_Supplier")){
				mapParameter.put("fsid", "00000000-0000-0000-0000-000000000001BC122A7F");  //客商分类  供应商信息
//				list = EASfinancialInfoCsspQuery.csspgroupQuery(mapParameter);
//				List<AsstBean> suplist = new ArrayList<AsstBean>();
//				suplist = EASfinancialInfoCsspQuery.supplierQuery(map);
//				if(null != suplist && suplist.size()>0){
//					list.addAll(suplist);
//				}
				int csspCount = mapper.csspCount(mapParameter);//客商信息总页数  	左侧菜单树
//				int custCount = mapper.custCount();//客户信息总页数   	右侧数据
//				int sum = csspCount+custCount;//总页数 
				int max = Integer.parseInt(map.get("max").toString());
				int min = Integer.parseInt(map.get("min").toString());
				int pageSize = max - min + 1;
				//差多少数据
				int rem = pageSize - csspCount;
				System.out.println("pageSize:"+pageSize+" rem: "+rem);
				if(csspCount>min){
					list = mapper.pageCsspQuery(mapParameter);
					mapParameter.put("min", 1);
					mapParameter.put("max", rem);
					List<AsstBean> list2 = mapper.selectSupPageQuery(mapParameter);
					list.addAll(list2);
				}else{
					mapParameter.put("min", min - csspCount);
					mapParameter.put("max", max - csspCount);
					list = mapper.selectSupPageQuery(mapParameter);
				}
				
			}else if(null != tableName && tableName.equals("T_BD_AccountBanks")){
				
				list = accountBanksQuery(map);
			}else if(null != tableName && tableName.equals("T_ORG_Company")){  //公司信息
				list = EASfinancialInfoCsspQuery.companyQuery(mapParameter);
			}else if(null != tableName && tableName.equals("T_ORG_CostCenter")){  //成本中心信息
				list = EASfinancialInfoCsspQuery.costCenterQuery(mapParameter);
			}else if(null != tableName && tableName.equals("T_BD_Person")){  //行政组织+职位+人员
				List<AsstBean> polist = new ArrayList<AsstBean>();
				List<AsstBean> personlist = new ArrayList<AsstBean>();
				list = EASfinancialInfoCsspQuery.orgAdminQuery(map);	//所有组织信息的查询
				polist = EASfinancialInfoCsspQuery.positionQuery(map);	//所有职位信息的查询
				personlist = EASfinancialInfoCsspQuery.personQuery(map);		//所有人员信息的查询 
				if(null != polist && polist.size()>0){
					list.addAll(polist);
				}
				if(null != personlist && personlist.size()>0){
					list.addAll(personlist);
				}
				return list;
			}else if(null != tableName && tableName.equals("T_ORG_Admin")){
				list = mapper.selectAdminQuery(mapParameter);
			}else{
				list = mapper.selectAsstProgramQuery(mapParameter);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：总账系统 核算数量
	 * 2013-7-12 下午05:06:51 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<LedgerCountBean> ledgerCountList(Map map){
		SqlSession session = null;
		List<LedgerCountBean> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectLedgerCountList(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：总账系统 本位币汇总
	 * 2013-7-12 下午05:06:51 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<LedgerBean> ledgerList(Map map){
		SqlSession session = null;
		List<LedgerBean> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			list = mapper.selectLedgerList(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	public void t(){
//		Map map = new HashMap();
//		map.put("fnumber", "00001");
//		map.put("beginTime", null);
//		map.put("tableName", "t_org_admin");
//		map.put("fnumber", value)
//		List<AsstBean> list = asstProgramQuery(map);
//		List<AsstBean> list= EASfinancialInfoCsspQuery.customerQuery(map);
		
//		System.out.println("size\t" + list.size());
//		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
//		List<ExchangeRate> list = query.exchangeRateQuery(map);
//		 List<Company> listCom =  query.companyInfoQuery(null);
//		
//		for(ExchangeRate tmp:list){
//			System.out.println(tmp.getSrcCurfname()+":"+tmp.getSrcCurfnumber()+":"+tmp.getTargetCurfname()+":"+tmp.getTargetCurfnumber());
//		}
//		 System.out.println(list.size());
		Map map = new HashMap();
//		Date beginTime = StringUtil.strToDate("201307-10 11:05:02","yyyy-MM-dd HH:mm:ss");
//		Date endTime = StringUtil.strToDate("2013-07-15 11:05:02","yyyy-MM-dd HH:mm:ss");
//		Date beginTime = Utils.getTimeByStr("2013-07-12 00:05:02");
//		Date endTime = Utils.getTimeByStr("2013-07-12 22:05:02");
//		map.put("beginTime", beginTime);
//		map.put("endTime", endTime);
//		map.put("tableName", "T_BD_GeneralAsstActType");
//		int no = asstProgramCountQuery(map);
//		System.out.println("no\t" + no);
//		map.put("fnumber", "Z04");
		map.put("fnumber", "00007");
		map.put("min", "1");
		map.put("max", "5000");
//		List<AsstBean> list = asstProgramQuery(map);  //辅助核算项
//		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();  //凭证摘要
//		List<VoucherAbstract> list = query.voucherAbstractQuery(map);
//		for(VoucherAbstract vou : list){
//			System.err.println(vou.getFname());
//		}
//		System.out.println(list.size());
//		System.out.println("why");
//		Map map = new HashMap();
////		Date date = StringUtil.strToDate("2012-02-01 11:05:02","yyyy-MM-dd HH:mm:ss");
////		map.put("beginTime", date);
//		map.put("fnumber", "Z02");//产品
////		map.put("fnumber", "Z03");
//		map.put("min", "1");
//		map.put("max", "1000");
//		int count = asstProgramCountQuery(map);
//		System.out.println(count);
		EASfinancialInfoWSQuery aa = new EASfinancialInfoWSQuery();
//		List<AccountTable> list = aa.accountTableNewQuery(map);//可目表
//		List<LedgerCountBean> list = aa.ledgerCountList(map);//总账数量
//		for(LedgerCountBean bean : list){
//			System.out.println(bean.getAccountname());
//		}
//		List<LedgerBean> list = aa.ledgerList(map);
//		for(LedgerBean bean : list){
//			System.out.println(bean.getAccountnumber()+":"+bean.getAccountname());
//		}
		
//		List<Customer> list = aa.customerQuery(map);
//		for(Customer bean:list){
//			System.out.println(bean.getFnumber()+":"+bean.getFname());
//		}
//		List<AsstBean> list = aa.asstProgramQuery(map);//辅助核算项
//		System.out.println("size\t" + list.size());
//		for(int i=0;i<list.size();i++){
//			AsstBean bean = list.get(i);
//			System.out.println("comp\t" + bean.getFcompanynumber() + " "+bean.getFname());
//			System.out.println(bean.getFnumber() + "\t" + bean.getFname() + "\t" + bean.getFisenabled() +"\t" + bean.getFisleaf()
//					+ "\t" + bean.getFlevel() + "\t" + bean.getFlastUpdateTime() + "\t" + bean.getFparentNumber());
//		}
//		System.out.println("size\t" + list.size());
//		EASfinancialInfoWSQuery ws = new EASfinancialInfoWSQuery();
//		List<PeriodType> list =  ws.periodTypeQuery();
//		for(int i=0;i<list.size();i++){
//			PeriodType type = list.get(i);
//			System.out.println(type.getFname() + "\t" + type.getFnumber());
//		}
//		List<AsstBean> list = aa.test(map);
//		
//		System.out.println("start\t" + list.size());
//		for(int i=0;i<list.size();i++){
//		AsstBean bean = list.get(i);
//		System.out.println("comp\t" + bean.getFcompanynumber() + " "+bean.getFname());
//		System.out.println(bean.getFnumber() + "\t" + bean.getFname() + "\t" + bean.getFisenabled() +"\t" + bean.getFisleaf()
//				+ "\t" + bean.getFlevel() + "\t" + bean.getFlastUpdateTime() + "\t" + bean.getFparentNumber());
//		}
//		System.out.println("end");
	}
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("fnumber", "00002");
		map.put("min", "1");
		map.put("max", "5000");
		EASfinancialInfoWSQuery info = new EASfinancialInfoWSQuery();
		List<AsstBean> list = info.test(map);
//		List<AsstBean> list = info.asstProgramQuery(map);
		System.out.println("start\t" + list.size());
		for(int i=0;i<list.size();i++){
		AsstBean bean = list.get(i);
		System.out.println("comp\t" + bean.getFcompanynumber() + " "+bean.getFname());
		System.out.println(bean.getFnumber() + "\t" + bean.getFname() + "\t" + bean.getFisenabled() +"\t" + bean.getFisleaf()
				+ "\t" + bean.getFlevel() + "\t" + bean.getFlastUpdateTime() + "\t" + bean.getFparentNumber());
		}
		System.out.println("end");
	}
	/**
	 * 描述：测试供应商接口的信息
	 * 2013-8-5 下午05:20:18 by ygq
	 * @version
	 */
	public static List<AsstBean> test(Map map){
		SqlSession session = null;
		List<AsstBean> list = null;
		try {
			session = getSession();
			EASfinancialInfoWSMapper mapper = session.getMapper(EASfinancialInfoWSMapper.class);
			Map<String,Object> mapInfo = mapper.selectTableNameByAsstActType(map);
			Object fglasstacttypegrpid = mapInfo.get("FGLASSTACTTYPEGRPID");
			String tableName = mapInfo.get("FREALTIONDATAOBJECT").toString();
			Map mapParameter = new HashMap();
//			mapParameter.put("fgroupid", fglasstacttypegrpid);
//			mapParameter.put("beginTime", map.get("beginTime"));
			mapParameter.put("min", map.get("min"));
			mapParameter.put("max", map.get("max"));
			mapParameter.put("tableName", tableName);
			mapParameter.put("fsid", "00000000-0000-0000-0000-000000000001BC122A7F");  //客商分类  供应商信息
			int csspCount = mapper.csspCount(mapParameter);//客商信息总页数  	左侧菜单树
			int max = Integer.parseInt(map.get("max").toString());
			int min = Integer.parseInt(map.get("min").toString());
			int pageSize = max - min + 1;
			list = mapper.pageCsspQuery(mapParameter);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
//		for(int i=0;i<list.size();i++){
//			AsstBean bean = list.get(i);
//			System.out.println(bean.getFname() + "\t" + bean.getFnumber());
//		}
		return list;
	}
}
