package com.creditease.eas.hr.kingdee.query.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.hr.bean.CapticalAssetsBean;
import com.creditease.eas.hr.kingdee.query.PayMentQuery;
/***
 * 查询固定资产类别相关的信息
 *TestPayMent
 * @author admin 2014-5-20
 */
public class TestPayMent {
	public static void test(){
		String firstClass = "0101";
		String[] parameters = firstClass.split("_");
		Map<String,String> map = new HashMap<String,String>();
		parameters[0] = firstClass+"%";
		if(1==firstClass.length()){   
			map.put("companyId", parameters[0]);
		}else{
			map.put("firstClass",parameters[0]);
		} 
//		mp.put("firstClass", "0101__");
//		mp.put("companyId", "G%");
		PayMentQuery payMentQuery = new PayMentQuery();
		List<CapticalAssetsBean> list = payMentQuery.queryAssetsClassNameAndCodeByFistClass(map);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			CapticalAssetsBean ca = list.get(i);
			System.out.println(ca.getName() + "\t" + ca.getCode());
		}
	}
	public static void main(String[] args) {
	}
}
