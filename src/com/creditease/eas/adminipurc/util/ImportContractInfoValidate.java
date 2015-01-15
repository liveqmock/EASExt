/**
 * 
 */
package com.creditease.eas.adminipurc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.util.excel.ImportExcelValidConst;
import com.creditease.eas.util.excel.ImportExcelValidate;
import com.creditease.eas.util.excel.RegularValid;
/**
 * 由于合同采购预警的导入存在特殊的校验，所以增加了该方法
 * @Title:ImportContractInfoValidate.java
 * @Package com.creditease.eas.adminipurc.util
 * created at 2014-6-22 下午06:03:57 by ygq
 * @author ygq
 * @version 1.0
 */
public class ImportContractInfoValidate<T> extends ImportExcelValidate<T> {
	private IDictionaryBaseService dictionaryBaseService;
	public ImportContractInfoValidate(){
		
	}
	public ImportContractInfoValidate(IDictionaryBaseService dictionaryBaseService){
		this.dictionaryBaseService = dictionaryBaseService;
	}
	/***
	 * 生成excel验证
	* @Title: excelValidate
	*created at 2014-6-8 下午02:45:29 by ygq  
	* @param startRole
	* @param dataset:数据集
	* @param titles:表头
	* @param clazz:这个bean定义的是验证规则
	* @param Class clazzT:导出数据时，写入到的bean中的信息
	* @param excludeFields:排除不需要验证的字段信息
	* @param specialFields:需要进行特殊验证的字段信息
	* @return
	* @return List<T>
	 */
	public List<T> excelValidateIncludeSpecial(int startRole,List<List<String>> dataset,Class clazz,Class clazzT,String[] excludeFields,String[] specialFields){
		List<T> listValid = new ArrayList<T>();//将验证信息存到Map中
		try{
			if (dataset != null && !dataset.isEmpty()) {// 非空判断
				  for (int i = startRole; i < dataset.size(); i++){  
					int viewNumber = 0;//定义一个观察变量
					 Object obj = clazz.newInstance();//创建一个对象,用于验证
					 
					//创建一个对象,用于保存验证未通过需要导出的信息
					T objExport = (T)clazzT.newInstance();
					//动态的title
					Field[] fields = obj.getClass().getDeclaredFields();
					List<String> cellList = dataset.get(i);//获得数据集
					//验证空行,如果有空行，则不做验证
					boolean validNull = validNullRow(cellList);
					if(validNull){
						continue;
					}
					String fcontracttypeidTemp = null;
					for (int j = 0; j < fields.length-1; j++) {//校验对应的字段信息
				      	if(j==0){
				      	//导出用的方法
					      	Method  methodRowNum = objExport.getClass().getDeclaredMethod("set" + fields[0].getName().substring(0,1).toUpperCase() + fields[0].getName().substring(1), fields[0].getType());//设置行号
					      	methodRowNum.invoke(objExport, i+1);//设置行号
				      	}
				    	String checkValue = cellList.get(j).trim();//字段
				    	Field field = fields[j+1];//获得对应的字段信息
				      	String fieldName = fields[j+1].getName();//获得对应的字段信息
				      	
				    	//排除不用验证的信息
				      	boolean validExcludeField = excludeFiled(fieldName,excludeFields);
				      	if(validExcludeField){
				      		continue;
				      	}
				      	String filedName = field.getName();
				      	//导出用的方法(注意，除了id，其他所有的字段都是字符串)
				    	Method  methodExport = objExport.getClass().getDeclaredMethod("set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1), String.class);
				    	//数据字典:验证使用部门
				    	if(filedName.equals("forgname")){
				      		boolean result = dictionaryBaseService.isItemHave("xzcgcomadmin", checkValue);
				      		if(!result){
				      			methodExport.invoke(objExport, "使用部门不正确");
								viewNumber ++;
								continue;//验证完成，则继续往下走
				      		}
				    	}
				    	if(filedName.equals("fcontracttypeid")){
				      		fcontracttypeidTemp = checkValue;
				      		//数据字典:验证对应的合同类别
				      		boolean result = dictionaryBaseService.isItemHave("xzcgct", checkValue);
				      		if(!result){
				      			methodExport.invoke(objExport, "合同类别不正确");
								viewNumber ++;
								continue;//验证完成，则继续往下走
				      		}
				      	}else if(filedName.equals("fcontractamount")){
				      		boolean result = fcontractamountValid(fcontracttypeidTemp,checkValue);
				      		if(result){//如果是非快递合同、总部框架协议、酒店协议，则不再验证合同额是否为空了
				      			continue;
				      		}else{
				      			methodExport.invoke(objExport,"非快递合同、饮用水合同、总部框架协议、酒店协议,通讯（电话/宽带）协议的合同额不准为空");
								viewNumber ++;
								continue;//验证完成，则继续往下走
				      		}
				      	}//4、	通讯（电话、宽带）协议、饮用水合同，此类别的合同的终止日期可以为空
				      	else if(filedName.equals("fmonthcost")){
				      		boolean result = fcontractamountValid(fcontracttypeidTemp,checkValue);
				      		if(result){//如果是非快递合同、总部框架协议、酒店协议，则不再验证合同额是否为空了
				      			continue;
				      		}else{
				      			methodExport.invoke(objExport,"非快递合同、饮用水合同、总部框架协议、酒店协议,通讯（电话/宽带）协议的月费用不准为空");
								viewNumber ++;
								continue;//验证完成，则继续往下走
				      		}
				      	}
				    	//数据字典:支付方式
				    	if(filedName.equals("fpaytypeid")){
				      		//数据字典:验证对应的付款类别
				    		boolean result = dictionaryBaseService.isItemHave("xzcgpw", checkValue);
				      		if(!result){
				      			methodExport.invoke(objExport, "支付方式不正确");
								viewNumber ++;
								continue;//验证完成，则继续往下走
				      		}
				    	}
				    	//正常情况下对数据信息的校验
				    	if (checkValue != null && !"".equals(checkValue)) {
							String filedTypeName = field.getType().getName();//获取字段的类型值
							//根据字段名获得对应的方法
							boolean result = false;
							if (field.getType().getName().equals("java.util.Date")) {//验证时否是正确的日期格式
									result = RegularValid.validDateFormat(checkValue, "-");
								if(!result){//验证失败
									//导出用的方法
									methodExport.invoke(objExport, ImportExcelValidConst.DATEVALID);
									viewNumber ++;
								}
							}else if (filedTypeName.equals("java.lang.Double")||filedTypeName.equals("java.lang.Integer")||filedTypeName.equals("java.lang.Long")) {//验证时否是正确的数字
								result = RegularValid.numberValid(checkValue);
								if(!result){//验证失败
									//导出用的方法
									methodExport.invoke(objExport, ImportExcelValidConst.NUMBERVALUE);
									viewNumber ++;
						
								}
							}
						}else{//如果为空了，则设置验证变量为空
							//2014-8-18:需求变更：通讯（电话、宽带）协议、饮用水合同，此类别的合同的终止日期可以为空
					    	if(field.getName().equals("fenddate") && (!"通讯（电话/宽带）协议".equals(fcontracttypeidTemp)&&!"饮用水合同".equals(fcontracttypeidTemp))){
					    		//通讯（电话、宽带）协议，饮用水合同
								methodExport.invoke(objExport, ImportExcelValidConst.ISNULL);
								viewNumber ++;
					    		continue;
					    	}
					    	//校验合同额为空的情况
							boolean result = fcontractamountValidNull(fcontracttypeidTemp,checkValue);//验证通过，则继续往下走
							if(result){
								continue;
							}
					    	//正常情况下为空的情况，作为未校验通过的信息处理
							methodExport.invoke(objExport, ImportExcelValidConst.ISNULL);
							viewNumber ++;
						}
					}
					if(viewNumber>0 && viewNumber<fields.length-1){//既要大于0，又不能全部为空
						listValid.add(objExport);//保存验证未通过的数据信息
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return listValid;
	}
	
	/****
	 * 对合同额进行特殊校验
	* @Title: excludeFiled
	*created at 2014-6-8 下午02:44:09 by ygq  
	* @param excludeFields
	* @param checkedFieldName
	* @return
	* @return boolean
	 */
	private boolean fcontractamountValid(String tempValue,String checkedFieldName){
		boolean result = true;
		if(!"快递合同".equals(tempValue) && !"总部框架协议".equals(tempValue)&& !"酒店协议".equals(tempValue) && !"通讯（电话/宽带）协议".equals(tempValue)&&!"饮用水合同".equals(tempValue)){
    		if("".equals(checkedFieldName)||null == checkedFieldName||"0".equals(checkedFieldName) ||"00".equals(checkedFieldName)||"000".equals(checkedFieldName) 
    				||"0.0".equals(checkedFieldName)||"0.00".equals(checkedFieldName)||"0.000".equals(checkedFieldName)){
    			result = false;
    		}
    	}
		return result;
	}
	/***
	 * 校验合同额为空的情况
	* @Title: fcontractamountValidNull
	*created at 2014-6-23 下午05:48:15 by ygq  
	* @param tempValue
	* @param checkedFieldName
	* @return
	* @return boolean
	 */
	private boolean fcontractamountValidNull(String tempValue,String checkedFieldName){
		boolean result = false;
		if("快递合同".equals(tempValue)|| "总部框架协议".equals(checkedFieldName)||"酒店协议".equals(checkedFieldName)||"通讯（电话/宽带）协议".equals(tempValue)|| "饮用水合同".equals(tempValue)){
    		if("".equals(checkedFieldName)||null == checkedFieldName){
    			result = true;
    		}
    	}
		return result;
	}
	
}
