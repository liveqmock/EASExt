package com.creditease.eas.adminipurc.util;

import java.util.List;

import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.util.StringUtil;

/***
 * 整合excel对应的表头信息
 *ExcelTitle
 * @author admin 2014-5-21
 */
public class ImportExcel {
	/**
	 * 接口人表头
	 */
	public static final String[] PORTINFOTITLES = { "使用部门", "城市", "末级成本中心","办公室地址","接口人姓名", "接口人邮箱", "是否为总接口人"};
	/***
	 * 合同信息表头
	 */
	public static final String[] CONTRACTINFOTITLES = { "使用部门", "城市", "末级成本中心", "办公室地址", "合同类别", "供应商公司名称",
		"合同额（元）", "开始日期", "结束日期", "无特殊情况自动续签", "月费用（元）",
		"设备押金", "付款方式", "备注"};
	
	/***
	 * 合同信息表头(用于导出)
	 */
	public static final String[] CONTRACTINFOTITLESFOREXPORT = { "合同编码","使用部门", "城市", "末级成本中心", "办公室地址", "合同类别", "供应商公司名称",
		"合同额（元）", "开始日期", "结束日期", "无特殊情况自动续签", "合同期限（月）", "月费用（元）","设备押金", "付款方式", "合同到期跟进","备注"};
	
	/**
	 * 接口人表头(用于验证)
	 */
	public static final String[] PORTINFOTITLESForValid = {"行号","使用部门", "城市", "末级成本中心","办公室地址 ","接口人姓名", "接口人邮箱", "是否为总接口人"};
	/***
	 * 合同信息表头(用于验证)
	 */
	public static final String[] CONTRACTINFOTITLESForValid = {"行号", "使用部门", "城市", "末级成本中心", "办公室地址", "合同类别", "供应商公司名称",
		"合同额（元）", "开始日期", "结束日期", "无特殊情况自动续签","月费用（元）",
		"设备押金", "付款方式", "备注"};
	/***
	 *对接口人信息的特殊的字段信息进行验证
	 */
	public static final String[] SPECIALCONTRACTINFOFORPORTVALID = {"forgName","fcity","fofficeAddr","flastCostcenter"};
	/***
	 * 排除合同信息不需要验证的字段信息
	 */
	public static final String[] EXCLUDECONTRACTINFOTITLESFORVALID = {"fremark"};
	/***
	 *对合同信息的特殊的字段信息进行验证
	 */
	public static final String[] SPECIALCONTRACTINFOFORVALID = {"fdurtime"};
	/**
	 * 描述：根据表头列名称获取列的number
	 *  2014-5-25 下午5:25 by ygq
	 * @version
	 * @param list表头列集合
	 * @return result返回对应表头列的cellNumber
	 */
	public static  int[] getCellNumByName(List<String> list,final String[] titles) {
		int[] result = new int[titles.length];
		for (int j = 0; j < titles.length; j++) {
			for (int i = 0; i < list.size(); i++) {
				if (titles[j].trim().equals(list.get(i))) {
					result[j] = i;
					break;
				}
			}
		}
		return result;
	}
	/***
	 * 获得字典对应的id值
	* @Title: getDictionaryId
	*created at 2014-5-25 下午06:21:45 by ygq  
	* @param title
	* @param list
	* @return
	* @return Integer
	 */
	public static  Integer getDictionaryId(String title,List<DictionaryItem> list) {
		int fid = 0;
		if(null != list && list.size()>0){
			for(int i=0;i<list.size();i++){
				DictionaryItem dicItem = list.get(i);
				if(null!= title && title.equals(dicItem.getItemname())){//需要判断title不为空
					fid = dicItem.getId();
					break;
				}
			}
		}
		return fid;
	}
	/***
	 * 获得字典对应的编码值(itemid)
	* @Title: getDictionaryId
	*created at 2014-5-25 下午06:21:45 by ygq  
	* @param title
	* @param list
	* @return
	* @return Integer
	 */
	public static  String getDictionaryCode(String title,List<DictionaryItem> list) {
		String imeid = null;
		if(null != list && list.size()>0){
			for(int i=0;i<list.size();i++){
				DictionaryItem dicItem = list.get(i);
				if(null!= title && title.equals(dicItem.getItemname())){//需要判断title不为空
					imeid = dicItem.getItemid();
					break;
				}
			}
		}
		return imeid;
	}
	/***
	 * 获得合同的流水号
	* @Title: getfserialNumber
	*created at 2014-5-27 上午11:45:44 by ygq  
	* @param fserialNumber
	* @return
	* @return String
	 */
	public static String getfserialNumber(Integer fserialNumber){
		String strFserialNumber = "";
		if(fserialNumber == null){
			strFserialNumber = "001";
		}else if (fserialNumber < 10){
			strFserialNumber += "00" + fserialNumber;
		}else if (fserialNumber > 10 && fserialNumber < 100){
			strFserialNumber += "0" + fserialNumber;
		}
		return strFserialNumber;
		
	}
	
}
