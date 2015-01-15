package com.creditease.eas.finance.query.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.finance.bean.AsstBean;
import com.creditease.eas.finance.query.EASfinancialInfoWSQuery;

public class AsstProgramQueryTest {
	/**
	 * 描述：查询辅助核算项的信息
	 * 2013-8-26 下午02:38:43 by ygq
	 * @version
	 */
	public static void testAsstProgramQuery(){
		EASfinancialInfoWSQuery query = new EASfinancialInfoWSQuery();
//		会计科目:它的fparentID有的为空，有的不为空
		Date date = new Date();
		date.setYear(date.getYear()-1);
		Map map = new HashMap();
		map.put("fnumber", "00008");
//		map.put("fnumber", "00002");
		map.put("beginTime", date);
		map.put("min", "1");
		map.put("max", "2000");
//		map.put("faccountTableNumber", "01");//宜信科目表
//		map.put("faccountTypeNumber", "01");//损益
		List<AsstBean> list = query.asstProgramQuery(map);
		System.out.println("size\t" + list.size());
		System.out.println("查询客商分类表里的数据信息");
//		for (AsstBean css : list) {
//			System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//					+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
//		}
		System.out.println("查询供应商表的数据信息");
		for (AsstBean css : list) {
//			System.out.println(css.getRn()+":"+css.getFname()+":"+css.getFnumber()+":"+css.getFparentNumber()+":"
//					+css.getFtreeParentNumber()+":"+css.getIstree()+":"+css.getFisleaf()+":"+css.getFlevel());
			System.out.println(css.getFtreeParentNumber() + "\t" + css.getExt1() + "\t" + css.getExt2()+ "\t" + css.getExt3() + "\t" + css.getExt4());
		}
	}
	public static void main(String[] args) {
		testAsstProgramQuery();
	}
}
