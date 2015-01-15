package com.creditease.eas.hr.webService.payment.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import com.creditease.eas.hr.bean.CapticalAssetsBean;
import com.creditease.eas.hr.kingdee.query.PayMentQuery;
import com.creditease.eas.hr.webService.payment.ICapticalAssetService;
@WebService(endpointInterface = "com.creditease.eas.hr.webService.payment.ICapticalAssetService",targetNamespace="capticalAsset",portName="capticalAssetImpl")
public class CapticalAssetServiceImpl implements ICapticalAssetService {
	@Override
	public List<CapticalAssetsBean> queryAssetsClassNameAndCodeByFistClass(
			String firstClass) {
		if(null == firstClass || "".equals(firstClass)){
			return null;
		}
//		System.out.println("firstClass\t" + firstClass);
//		String[] parameters = firstClass.split("_");
//		Map<String,String> map = new HashMap<String,String>();
//		if(parameters[0].equals("G")){
//			parameters[0] = "01___%";//查询固定资产
//		}else{
//			parameters[0] = "02___%";//查询低值易耗
//		}
//		map.put("firstClass",parameters[0]);
//		map.put("companyId", parameters[1]);
		//更改查询逻辑 
		String number = ""; //编码
		Map<String,String> map = new HashMap<String,String>(); //参数
		
		// 逻辑更改，传多个值，逗号分割,查询所有
		String[] parameters = firstClass.trim().split("#");
		if(1<parameters.length){ 
			Map<String,String[]> maps = new HashMap<String,String[]>(); //参数
			for (int i = 0; i < parameters.length; i++) {
				parameters[i] = parameters[i]+"_%";
			}
			if(3==parameters[0].length()){  //为第一次查询时
				maps.put("companyIdS", parameters);
				List<CapticalAssetsBean> list = PayMentQuery.queryAssetsClassNameAndCodeByFistClassS(maps);   //调用传多个值方法
				return list;
			}else{ // 为第二次调用 
				number = " and (";
				for (int i = 0; i < parameters.length; i++) {
					number = number +"t.fnumber like '" + parameters[i] +"' or ";
				}
				number = number.substring(0,number.length()-3);
				number = number +")";
				
				map.put("firstClassS",number);
			}
			
		}
		
		else if(1==firstClass.length()){   //如果参数长度为1,为第一次调用
			number = firstClass+"_%";
			map.put("companyId", number);
		}else{  //参数长度不为1，为第二次调用
			number = firstClass+"_%";
			map.put("firstClass",number);
		} 
		
		List<CapticalAssetsBean> list = PayMentQuery.queryAssetsClassNameAndCodeByFistClass(map);
		return list;
	}
	@Override
	public List<CapticalAssetsBean> queryMeasureUnitInfo(String catFnumber) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("fnumber", catFnumber);
		List<CapticalAssetsBean> list = PayMentQuery.queryMeasureUnitInfo(map);
		return list;
	}
//	以后改变编码习惯，不在service里进行代码的测试
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
//		D_08CJGLF
//		map.put("firstClass","02___%");
//		map.put("companyId", "08CJGLF");
		
//		map.put("firstClass","0101%");
//		map.put("companyId", "G%");
//		List<CapticalAssetsBean> list = PayMentQuery.queryAssetsClassNameAndCodeByFistClass(map);
//		System.out.println(list.size());
		
		
		CapticalAssetServiceImpl temp = new CapticalAssetServiceImpl();
//		List<CapticalAssetsBean> list = temp.queryAssetsClassNameAndCodeByFistClass("0101,0102");
		List<CapticalAssetsBean> list = temp.queryAssetsClassNameAndCodeByFistClass("G#D");
		for (int i = 0; i < list.size(); i++) {
			CapticalAssetsBean tempinfo = list.get(i);
			System.out.println(i+"_________"+tempinfo.getName()+"___"+tempinfo.getCode());
		}
	}
 

}
