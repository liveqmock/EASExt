package com.creditease.eas.finance.query.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.finance.bean.AccountView;
import com.creditease.eas.finance.bean.AsstBean;
import com.creditease.eas.finance.bean.Customer;
import com.creditease.eas.finance.bean.ExchangeRate;
import com.creditease.eas.finance.bean.RateOfTaxation;
import com.creditease.eas.finance.query.EASfinancialInfoWSQuery;

public class EASfinancialInfoWSQueryTest {
	public static void testMethod1(){
		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
		//会计科目:它的fparentID有的为空，有的不为空
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("min", "1");
		map.put("max", "200000");
//		map.put("faccountTableNumber", "01");//宜信科目表
//		map.put("faccountTypeNumber", "01");//损益
		List<AsstBean> list = query.asstProgramQuery(map);
		System.out.println(list.size());
//		for(int i=0;i<list.size();i++){
//			AccountView acc = list.get(i);
//			System.out.println(acc.getFcompanynumber());
//			System.out.println(acc.getFnumber() + "\t" + acc.getFcaaname() + "\t" + acc.getFcaanumber()+
//					"\t" + acc.getFgaaname() + "\t" + acc.getFgaanumber() + "\t" + acc.getFparentAAIDNumber() + "\t" + acc.getFdc()
//					+ "\t" + acc.getFaccountTypeNumber() + "\t" + acc.getFaccountTableNumber()
//					+ "\t" + acc.getFlastUpdateTime() 
//			);
//		}
	}
	public static void testMethod2(){
		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
//		List<AsstAccount> test = query.asstAccountQuery();
//		System.out.println(test.size());
//		List<VoucherTypes> types = query.voucherTypesQuery();
//		System.out.println(types.size());
//		List<Asstacttype> list = query.asstacttypeQuery();
//		System.out.println(list.size());
		//辅助核算项
		//客户
		int count = query.customerCountQuery();
		System.out.println("count\t" + count);
		Map map = new HashMap();
		map.put("min", 10);
		map.put("max", 100);
		List<Customer> listC = query.customerQuery(map);
		for(int i=0;i<listC.size();i++){
			Customer cus = listC.get(i);
			System.out.println(cus.getFname() + "\t" + cus.getFnumber() + "\t" + cus.getFparentNumber() + "\t" + cus.getFlastUpdateTime());
		}
	}
	public static void testMethod3(Map map){
		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
		//币别
//		List<Currency> list = query.currencyQuery(fnumber);
//		for(int i=0;i<list.size();i++){
//			Currency cu = list.get(i);
//			System.out.println(cu.getFbaseUnit() + "\t" + cu.getFdeletedStatus() + "\t" + cu.getFisocode() + "\t" + cu.getFname()
//					+ "\t" + cu.getFnumber() + "\t" + cu.getFprecision() + "\t" + cu.getFlastUpdateTime());
//		}
//		System.out.println("end");
		
		List<RateOfTaxation> list = query.rateOfTaxationQuery(map);
		for(int i=0;i<list.size();i++){
			RateOfTaxation cu = list.get(i);
			System.out.println(cu.getFdescription() + "\t" + cu.getFisEnabled() + "\t" + cu.getFname() + "\t" + cu.getFnumber() + "\t" + cu.getFlastUpdateTime());
		}
		System.out.println("end");
//		List<CostCenter> test = query.costCenterQuery();
//		System.out.println("大小为："+test.size());
//		System.out.println("大小为："+test.get(0).getFname());
	}
	
	public static void testMethod5(){
//		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
		testMethod1();
//		testMethod2();
//		testMethod3(null);
//		List list = currencyQuery();
//		System.out.println(list);
//		List list = companyInfoQuery(null);
//		System.out.println("size\t" + list);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("min", "1");
//		map.put("max", "10");
//		List<AccountView> list = query.accountViewQuery(map);
//		System.out.println(list);
//		for(int i=0;i<list.size();i++){
//			AccountView acc = list.get(i);
//			System.out.println(acc.getFnumber() + "\t" + acc.getFcaaname() + "\t" + acc.getFcaanumber()+
//					"\t" + acc.getFgaaname() + "\t" + acc.getFgaanumber() + "\t" + acc.getFparentAAIDNumber() + "\t" + acc.getFdc()
//					+ "\t" + acc.getFaccountTypeNumber() + "\t" + acc.getFaccountTableNumber()
//					+ "\t" + acc.getFlastUpdateTime()
//			);
//		}
//		List<Company> list = query.companyInfoQuery(null);
//		System.out.println(list.size());
//		for(int i=0;i<list.size();i++){
//			Company com = list.get(i);
//			System.out.println( com.getFbaseExgTableID() + "\t" 
//				+ com.getFadjustExgTableID() + "\t" + com.getFreportExgTableID());
//		}
	}
	public static void testMethod4(){
		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
//		Map map = new HashMap();
//		map.put("beginTime", new Date());
		List<ExchangeRate> listRate = query.exchangeRateQuery(null);
		System.out.println("size\t" + listRate.size());
		for(int i=0;i<listRate.size();i++){
			ExchangeRate rate = listRate.get(i);
			System.out.println( rate.getFnumber() + "\t" + rate.getFlastUpdateTime());
		}
//		List<Company> list = query.companyInfoQuery(null);
//		Company com = list.get(10);
//		System.out.println("comp\t" +com.getFparentNumber());
		//		List<ExchangeTable> list = query.selectExchangeTable(map);
//		List<AsstAccount> listAccount = query.asstAccountQuery();
//		for(int i=0;i<listAccount.size();i++){
//			AsstAccount rate = listAccount.get(i);
//			System.out.println(rate.getFname() + "\t" + rate.getFnumber() + "\t" + rate.getFlastUpdateTime());
//		}
//		List<Asstacttype> listType = query.asstAccountFnumberQuery("34");
//		for(int i=0;i<listType.size();i++){
//			Asstacttype rate = listType.get(i);
//			System.out.println(rate.getFname() + "\t" + rate.getFnumber());
//		}
	}
	
	public static void testAccountView(){
		Map map = new HashMap();
		map.put("min", "500");
		map.put("max", "1000");
		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
		List<AccountView> list = query.accountViewQuery(map);
		for(int i=0;i<list.size();i++){
			AccountView acc = list.get(i);
//			System.out.println(acc.getFnumber() + "\t" + acc.getFcaaname() + "\t" + acc.getFcaanumber()+
//					"\t" + acc.getFgaaname() + "\t" + acc.getFgaanumber() + "\t" + acc.getFparentAAIDNumber() + "\t" + acc.getFdc()
//					+ "\t" + acc.getFaccountTypeNumber() + "\t" + acc.getFaccountTableNumber()
//					+ "\t" + acc.getFlastUpdateTime()
//			);
			System.out.println(acc.getExt1() + "\t" + acc.getExt2());
		}
	}
	public static void testMethod6(){
		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
//		List<PeriodType> list = query.periodTypeQuery();
//		for(int i=0;i<list.size();i++){
//			PeriodType com = list.get(i);
//			System.out.println(com.getFnumber() + "\t" + com.getFname() + "\t" + com.getFlastUpdateTime());
//		}
//		Map map = new HashMap();
//		Date date = StringUtil.strToDate("2013-4-17 12:00:00", "yyyy-MM-dd HH:mm:ss");
//		map.put("beginTime", date);
//		map.put("min","1");
//		map.put("max", "100");
//		int count = query.baseUnitCountQuery(map);
//		System.out.println("count\t" + count);
//		List<BaseUnit> list = query.baseUnitQuery(map);
//		System.out.println(list.size());
//		for(int i=0;i<list.size();i++){
//			BaseUnit baseUnit = list.get(i);
//			System.out.println(baseUnit.getFdescription() + "\t" + baseUnit.getFdisplayName() + "\t"
//			+ baseUnit.getFisAssistantOrg() + "\t" + baseUnit.getFname() + "\t" + baseUnit.getFnumber() + "\t"
//			+ baseUnit.getForgTypeStr() + "\t" + baseUnit.getFparentNumber() + "\t" + baseUnit.getFisLeaf() + "\t"
//			+ baseUnit.getFisOUSealUp() + "\t" + baseUnit.getFlevel() + "\t" + baseUnit.getFlastUpdateTime()
//			);
//		}
	}
	public static void main(String[] args) {
//		testMethod4();
//		testMethod1();
//				testMethod5();
		//		testMethod6();
		testAccountView();
	}
}
