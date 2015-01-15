/**
 * 
 */
package com.creditease.eas.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Dictionary.java 字典工具类，检索数据
 * created at 2013-8-20 下午05:12:34 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class DictionaryUtil {
	
	public static Map<String, Map<String, String>> singleMap;
	
	public static String leaf_key_0 = "0";
	public static String leaf_value_0 = "否";
	public static String leaf_key_1 = "1";
	public static String leaf_value_1 = "是";
	
	public static String leaf = "leaf";
	public static Map<String,String> leaf_map;
	
	public static String level_key_0 = "1";
	public static String level_value_0 = "一级菜单";
	public static String level_key_1 = "2";
	public static String level_value_1 = "二级菜单";

	public static String level = "level";
	public static Map<String,String> level_map;
	
	public static String target_key_0 = "_blank";
	public static String target_value_0 = "新窗口";
	public static String target_key_1 = "_parent";
	public static String target_value_1 = "父窗口";
	public static String target_key_2 = "_self";
	public static String target_value_2 = "默认窗口";
	public static String target_key_3 = "_top";
	public static String target_value_3 = "整个窗口";
	public static String target_key_4 = "main";
	public static String target_value_4 = "框架窗口";
	
	public static String target = "target";
	public static Map<String,String> target_map;
	
	public static String payType_key_0 = "month";
	public static String payType_value_0 = "每月";
	public static String payType_key_1 = "twoMonths";
	public static String payType_value_1 = "两月";
	public static String payType_key_2 = "quarter";
	public static String payType_value_2 = "季度";
	public static String payType_key_3 = "halfYear";
	public static String payType_value_3 = "半年";
	public static String payType_key_4 = "year";
	public static String payType_value_4 = "一年";
	public static String payType_key_5 = "fourMonths";
	public static String payType_value_5 = "四月";
	public static String payType_key_6 = "fiveMonths";
	public static String payType_value_6 = "五月";
	public static String payType_key_7 = "sevenMonths";
	public static String payType_value_7 = "七月";
	public static String payType_key_8 = "eightMonths";
	public static String payType_value_8 = "八月";
	public static String payType_key_9 = "nineMonths";
	public static String payType_value_9 = "九月";
	public static String payType_key_10 = "tenMonths";
	public static String payType_value_10 = "十月";
	public static String payType_key_11 = "elevenyear";
	public static String payType_value_11 = "十一月";
	
	public static String payType = "payType";
	public static Map<String,String> payType_map;
	
	public static String yesno_key_0 = "0";
	public static String yesno_value_0 = "是";
	public static String yesno_key_1 = "1";
	public static String yesno_value_1 = "否";
	
	public static String yesno = "yesno";
	public static Map<String,String> yesno_map;
	
	public static String rentStatus_key_0 = "0";
	public static String rentStatus_value_0 = "在用";
	public static String rentStatus_key_1 = "1";
	public static String rentStatus_value_1 = "停租";
	public static String rentStatus_key_2 = "2";
	public static String rentStatus_value_2 = "转租";
	public static String rentStatus_key_3 = "3";
	public static String rentStatus_value_3 = "变更";
	
	public static String rentStatus = "rentStatus";
	public static Map<String,String> rentStatus_map;
	
	public static String loanBread_key_0 = "0";
	public static String loanBread_key_1 = "1";
	public static String loanBread_key_2 = "2";
	public static String loanBread_key_3 = "3";
	public static String loanBread_key_4 = "4";
	public static String loanBread_key_5 = "5";
	public static String loanBread_key_6 = "6";
	public static String loanBread_key_7 = "7";
	public static String loanBread_key_8 = "8";
	public static String loanBread_key_9 = "9";
	public static String loanBread_key_10 = "10";
	public static String loanBread_key_11 = "11";
	public static String loanBread_key_12 = "12";
	public static String loanBread_key_13 = "13";
	public static String loanBread_key_14 = "14";
	public static String loanBread_key_15 = "15";
	public static String loanBread_key_16 = "16";
	public static String loanBread_key_17 = "17";
	public static String loanBread_key_18 = "18";
	public static String loanBread_key_19 = "19";
	public static String loanBread_key_20 = "20";
	public static String loanBread_key_21 = "21";
	public static String loanBread_value_0 = "新薪贷";
	public static String loanBread_value_1 = "新薪贷（低）";
	public static String loanBread_value_2 = "精英贷";
	public static String loanBread_value_3 = "助学贷";
	public static String loanBread_value_4 = "新薪宜楼贷";
	public static String loanBread_value_5 = "助业宜楼贷";
	public static String loanBread_value_6 = "助业贷";
	public static String loanBread_value_7 = "学信通";
	public static String loanBread_value_8 = "学历贷";
	public static String loanBread_value_9 = "宜商贷";
	public static String loanBread_value_10 = "宜车贷";
	public static String loanBread_value_11 = "小企业贷";
	public static String loanBread_value_12 = "房贷";
	public static String loanBread_value_13 = "微车贷";
	public static String loanBread_value_14 = "融车宝";
	public static String loanBread_value_15 = "农商贷";
	public static String loanBread_value_16 = "付息通";
	public static String loanBread_value_17 = "宜农贷";
	public static String loanBread_value_18 = "宜农贷2.0";
	public static String loanBread_value_19 = "农租宝1号";
	public static String loanBread_value_20 = "农租宝2号";
	public static String loanBread_value_21 = "网商贷";
	
	public static String loanBread = "loanBread";
	public static Map<String,String> loanBread_map;
	
	static{
		leaf_map = new HashMap<String, String>();
		leaf_map.put(leaf_key_0,leaf_value_0);
		leaf_map.put(leaf_key_1,leaf_value_1);
		
		level_map = new HashMap<String, String>();
		level_map.put(level_key_0,level_value_0);
		level_map.put(level_key_1,level_value_1);
		
		target_map = new HashMap<String, String>();
		target_map.put(target_key_0,target_value_0);
		target_map.put(target_key_1,target_value_1);
		target_map.put(target_key_2,target_value_2);
		target_map.put(target_key_3,target_value_3);
		target_map.put(target_key_4,target_value_4);
		
		payType_map = new HashMap<String, String>();
		payType_map.put(payType_key_0,payType_value_0);
		payType_map.put(payType_key_1,payType_value_1);
		payType_map.put(payType_key_2,payType_value_2);
		payType_map.put(payType_key_3,payType_value_3);
		payType_map.put(payType_key_4,payType_value_4);
		payType_map.put(payType_key_5,payType_value_5);
		payType_map.put(payType_key_6,payType_value_6);
		payType_map.put(payType_key_7,payType_value_7);
		payType_map.put(payType_key_8,payType_value_8);
		payType_map.put(payType_key_9,payType_value_9);
		payType_map.put(payType_key_10,payType_value_10);
		payType_map.put(payType_key_11,payType_value_11);
		
		yesno_map = new HashMap<String, String>();
		yesno_map.put(yesno_key_0,yesno_value_0);
		yesno_map.put(yesno_key_1,yesno_value_1);
		
		rentStatus_map = new HashMap<String, String>();
		rentStatus_map.put(rentStatus_key_0,rentStatus_value_0);
		rentStatus_map.put(rentStatus_key_1,rentStatus_value_1);
		rentStatus_map.put(rentStatus_key_2,rentStatus_value_2);
		rentStatus_map.put(rentStatus_key_3,rentStatus_value_3);
		
		loanBread_map = new HashMap<String, String>();
		loanBread_map.put(loanBread_key_0,loanBread_value_0);
		loanBread_map.put(loanBread_key_1,loanBread_value_1);
		loanBread_map.put(loanBread_key_2,loanBread_value_2);
		loanBread_map.put(loanBread_key_3,loanBread_value_3);
		loanBread_map.put(loanBread_key_4,loanBread_value_4);
		loanBread_map.put(loanBread_key_5,loanBread_value_5);
		loanBread_map.put(loanBread_key_6,loanBread_value_6);
		loanBread_map.put(loanBread_key_7,loanBread_value_7);
		loanBread_map.put(loanBread_key_8,loanBread_value_8);
		loanBread_map.put(loanBread_key_9,loanBread_value_9);
		loanBread_map.put(loanBread_key_10,loanBread_value_10);
		loanBread_map.put(loanBread_key_11,loanBread_value_11);
		loanBread_map.put(loanBread_key_12,loanBread_value_12);
		loanBread_map.put(loanBread_key_13,loanBread_value_13);
		loanBread_map.put(loanBread_key_14,loanBread_value_14);
		loanBread_map.put(loanBread_key_15,loanBread_value_15);
		loanBread_map.put(loanBread_key_16,loanBread_value_16);
		loanBread_map.put(loanBread_key_17,loanBread_value_17);
		loanBread_map.put(loanBread_key_18,loanBread_value_18);
		loanBread_map.put(loanBread_key_19,loanBread_value_19);
		loanBread_map.put(loanBread_key_20,loanBread_value_20);
		loanBread_map.put(loanBread_key_21,loanBread_value_21);
		
		singleMap = new HashMap<String, Map<String,String>>();
		singleMap.put(leaf, leaf_map);
		singleMap.put(level, level_map);
		singleMap.put(target, target_map);
		singleMap.put(payType, payType_map);
		singleMap.put(yesno, yesno_map);
		singleMap.put(rentStatus, rentStatus_map);
		singleMap.put(loanBread, loanBread_map);
	}
	
	/**
	 * 
	 * 描述：获取dictionary集合
	 * 2013-8-21 上午10:20:36 by caoyong
	 * @version
	 * @param singleMap 字典数据
	 * @param key		字典查询关键字
	 * @return
	 * @throws Exception
	 */
	public static List<Dictionary> getDictionarys(Map<String, Map<String, String>> singleMap,String key) throws Exception{
		List<Dictionary> dictionaries = new ArrayList<Dictionary>();
		Map<String, String> map = singleMap.get(key);
		for(String keyName:map.keySet()){
			Dictionary dictionary = Dictionary.class.newInstance();
			dictionary.setKey(keyName);
			dictionary.setValue(map.get(keyName));
			dictionaries.add(dictionary);
		}
		//重写collections的排序方法，key的值asc排升序
		Collections.sort(dictionaries,new Comparator<Dictionary>() {
			public int compare(Dictionary o1, Dictionary o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		return dictionaries;
	}
	
	/**
	 * 
	 * 描述：把下拉列表的map键值对转换成字典类集合
	 * 2013-10-9 下午05:57:40 by caoyong
	 * @version
	 * @param list List<Map<String,String>>的键值对map[FID="0",FVALUE="xx"]
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Dictionary> getDictionarys(List<Map> list) throws Exception{
		List<Dictionary> dictionaries = new ArrayList<Dictionary>();
		for(Map map : list){
			Dictionary dictionary = Dictionary.class.newInstance();
			dictionary.setKey(map.get("ID").toString());
			dictionary.setValue(map.get("VALUE").toString());
			dictionaries.add(dictionary);
		}
		return dictionaries;
	}
	
	/**
	 * 
	 * 描述：通过字典value值获取key值
	 * 2013-8-26 下午05:26:53 by caoyong
	 * @version
	 * @param map   字典集合
	 * @param value 字典value
	 * @return
	 */
	public static String getKeyByValue(Map<String, String> map,String value){
		String returnKey = "";
		for(String key:map.keySet()){
			if(value.equals(map.get(key).toString())){
				returnKey = key;
				break;
			}
		}
		return returnKey;
	}
	/**
	 * 
	 * 描述：获取付款周期日期计算对应的步长值
	 * 2013-10-10 上午11:57:14 by caoyong
	 * @version
	 * @param keyName
	 * @return
	 */
	public static int getKeyNum(String keyName){
		int result = 0;
		if(keyName.equals(DictionaryUtil.payType_key_0)) result = 1;//每月
		else if(keyName.equals(DictionaryUtil.payType_key_1)) result = 2;//两月
		else if(keyName.equals(DictionaryUtil.payType_key_2)) result = 3;//季度
		else if(keyName.equals(DictionaryUtil.payType_key_3)) result = 6;//半年
		else if(keyName.equals(DictionaryUtil.payType_key_4)) result = 12;//一年
		else if(keyName.equals(DictionaryUtil.payType_key_5)) result = 4;//四月
		else if(keyName.equals(DictionaryUtil.payType_key_6)) result = 5;//五月
		else if(keyName.equals(DictionaryUtil.payType_key_7)) result = 7;//七月
		else if(keyName.equals(DictionaryUtil.payType_key_8)) result = 8;//八月
		else if(keyName.equals(DictionaryUtil.payType_key_9)) result = 9;//九月
		else if(keyName.equals(DictionaryUtil.payType_key_10)) result = 10;//10月
		else if(keyName.equals(DictionaryUtil.payType_key_11)) result = 11;//11月
		return result;
	}
	public static void main(String[] args) throws Exception {
		DictionaryUtil dictionaryUtil = new DictionaryUtil();
		dictionaryUtil.getDictionarys(new ArrayList());
	}
}
