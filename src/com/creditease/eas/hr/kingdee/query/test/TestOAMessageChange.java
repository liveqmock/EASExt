package com.creditease.eas.hr.kingdee.query.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.creditease.eas.hr.bean.extoa.WSOAOrganization;
import com.creditease.eas.hr.bean.extoa.WSOAPerson;
import com.creditease.eas.hr.kingdee.query.OAMessageChangeQuery;
import com.creditease.eas.hr.service.IOAMessageSendService;
import com.creditease.eas.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/***
 * 测试OA拉取信息的接口
 *TestOAMessageChange
 * @author admin 2014-5-13
 */
public class TestOAMessageChange {
	/***
	 * 查询组织的相关信息
	 */
	public static void testListChangeOrgInfo(){
		OAMessageChangeQuery oaMessageChangeQuery = new OAMessageChangeQuery();
		Integer startRow = 1;
		Integer endRow = 300;
		String beginTime = "2013-04-30 15:20:58";
		String endTime = "2014-5-21 16:20:58";
		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("beginTime", db);
		map.put("endTime",de);
		map.put("fname", StringUtil.addLikeOperBoth("中心"));
		List<Map<String,Object>> listP =  oaMessageChangeQuery.selectListChangeOrgInfo(map);
		System.out.println(listP.size());
		System.out.println(StringUtil.convertListToGson(listP));
	}
	/***
	 * 查询成本中心的相关信息
	* @Title: testListChangeOrgInfo
	*created at 2014-6-18 下午10:05:34 by ygq  
	* @return void
	 */
	public static void testListChangeCostcenterInfo(){
		OAMessageChangeQuery oaMessageChangeQuery = new OAMessageChangeQuery();
		Integer startRow = 1;
		Integer endRow = 200;
		String beginTime = "2008-04-30 15:20:58";
		String endTime = "2015-5-21 16:20:58";
		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("beginTime", db);
		map.put("endTime",de);
		List<Map<String,Object>> listP =  oaMessageChangeQuery.selectListChangeCostcenterInfo(map);
		System.out.println(StringUtil.convertListToGson(listP));
	}
	/***
	 * 查询职位的相关信息
	 */
	public static void testChangePositionInfo(){
		OAMessageChangeQuery oaMessageChangeQuery = new OAMessageChangeQuery();
		Integer startRow = 1;
		Integer endRow = 200;
		String beginTime = "2013-04-30 15:20:58";
		String endTime = "2014-5-21 16:20:58";
		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("beginTime", db);
		map.put("endTime",de);
		List<Map<String,Object>> listP =  oaMessageChangeQuery.selectChangePositionInfo(map);
		System.out.println(StringUtil.convertListToGson(listP));
	}
	/***
	 * 查询人员的信息
	 */
	public static void testChangePersonInfo(){
		OAMessageChangeQuery oaMessageChangeQuery = new OAMessageChangeQuery();
		Integer startRow = 1;
		Integer endRow = 200;
		String beginTime = "2009-04-30 15:20:58";
		String endTime = "2014-10-30 15:20:58";
		 
		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		Map map = new HashMap();
//		map.put("fid", "rlgAAAAAOruA733t");//人员id
//		map.put("fid", "rlgAAAAADd/M567U");
//		map.put("fnumber", "11TSHZ03002");
		//map.put("fnumber", null);
		//map.put("fnumber", "201210290283");
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("beginTime", db);
		map.put("endTime",de);
		List<Map<String,Object>> listMap =  oaMessageChangeQuery.selectChangePersonInfo(map);
		System.out.println(listMap);
//		String json = StringUtil.convertListToGson(listMap);
//		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		String json = g.toJson(listMap);
//		System.out.println("info\t" + json);
		
	}
	/***
	 * 测试人员关联的合同相关的人员信息
	 */
	public static void testContractInfo(){
		Map map = new HashMap();
		String beginTime = "2012-04-30 15:20:58";
		String endTime = "2013-10-30 15:20:58";
		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd hh:mm:ss");//开始日期
		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		map.put("beginTime", db);
		map.put("endTime",de);
		map.put("min", 1);
		map.put("max", 2000);
		map.put("fpersoncode", "201301180352");
		map.put("fpersonid", null);
		OAMessageChangeQuery oaMessageChangeQuery = new OAMessageChangeQuery();
		List<Map<String,Object>> listMap  = oaMessageChangeQuery.selectContractInfo(map);
		String json = StringUtil.convertListToGson(listMap);
		System.out.println("info\t" + json);
	}
	/***
	 * 测试人员关联的案件负责人的信息
	 */
	public static void testQueryResponPositionInfo(){
		Map map = new HashMap();
		String beginTime = "2012-04-30 15:20:58";
		String endTime = "2014-10-30 15:20:58";
		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd hh:mm:ss");//开始日期
		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		map.put("beginTime", db);
		map.put("endTime",de);
//		map.put("fOrgnumber", "06GDCQ2900104");
		OAMessageChangeQuery oaMessageChangeQuery = new OAMessageChangeQuery();
		List<Map<String,Object>> listMap  = oaMessageChangeQuery.queryResponPositionInfo(map);
		String json = StringUtil.convertListToGson(listMap);
		System.out.println("info\t" + json);
	}
	/***
	 * 测试人员职位中间表的信息
	 */
	public static void testQueryPersonPositionTempInfo(){
		Map map = new HashMap();
//		String beginTime = "2013-03-30 15:20:58";
//		String endTime = "2013-10-30 15:20:58";
//		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd hh:mm:ss");//开始日期
//		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd hh:mm:ss");//结束日期
		String beginTime = "2014-03-4";
		String endTime = "2014-03-9";
		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd");//开始日期
		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd");//结束日期
		map.put("beginTime", db);
		map.put("endTime",de);
		map.put("min", 1);
		map.put("max", 3000);
//		map.put("fpersoncode", "201301180352");
//		map.put("fpersonid", null);
//		map.put("fpositioncode",null);
//		map.put("fpositionid", null);
		OAMessageChangeQuery oaMessageChangeQuery = new OAMessageChangeQuery();
		List<Map<String,Object>> listMap  = oaMessageChangeQuery.queryPersonPositionTempInfo(map);
		String json = StringUtil.convertListToGson(listMap);
		System.out.println("info\t" + json);
	}
	/***
	 * 测试职员任职历史接口
	* @Title: testyWorkExpCur
	*created at 2014-6-12 下午05:58:18 by ygq  
	* @return void
	 */
	public static void testyWorkExpCur(){
		Map map = new HashMap();
		String beginTime = "2012-04-4 17:08:33";
		String endTime = "2014-04-9 17:08:33";
		Date db = StringUtil.strToDate(beginTime,"yyyy-mm-dd hh:mm:ss");//开始日期
		Date de = StringUtil.strToDate(endTime,"yyyy-mm-dd hh:mm:ss");//结束日期
//		map.put("beginTime", db);
//		map.put("endTime",de);
		map.put("min", 1);
		map.put("max", 3);
		map.put("fpersoncode", "201301180352");
//		map.put("fpersonid", null);
//		map.put("fpositioncode",null);
//		map.put("fpositionid", null);
		OAMessageChangeQuery oaMessageChangeQuery = new OAMessageChangeQuery();
		List<Map<String,Object>> listMap  = oaMessageChangeQuery.queryWorkExpCur(map);
		String json = StringUtil.convertListToGson(listMap);
		System.out.println("info\t" + json);
	}
	public static void main(String[] args) throws Exception{
		//查询组织信息
//		testListChangeOrgInfo();
		//查询成本中心的信息
//		testListChangeCostcenterInfo();
		//查询职位相关的信息
//		testChangePositionInfo();
		//测试人员相关的信息
//		testChangePersonInfo();
		
		//查询人员职位中间表的数据
//		testQueryPersonPositionTempInfo();
		
		//查询组织关联的案件负责人的信息
//		testQueryResponPositionInfo();
		//测试人员关联的合同相关的人员信息
//		testContractInfo();
//		testChangePersonInfo();
		//查询任职历史
		testyWorkExpCur();
	}
}
