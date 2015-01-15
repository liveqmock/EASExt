/**
 * 
 */
package com.creditease.eas.util.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * 验证导入的excel
 * @Title:ImportExcelValidate.java
 * @Package com.creditease.eas.adminipurc.util
 * created at 2014-6-4 上午09:15:29 by ygq
 * @author ygq
 * @version 1.0
 */
public class ImportExcelValidate<T> {
	/***
	 * 生成excel验证
	* @Title: excelValidate
	*created at 2014-6-8 下午02:45:29 by ygq  
	* @param startRole
	* @param dataset:数据集
	* @param titles:表头
	* @param clazz:这个bean定义的是验证规则
	* @param excludeFields:排除不需要验证的字段信息
	* @param Class clazzT:导出数据时，写入到的bean中的信息
	* @return
	* @return List<T>
	 */
	public List<T> excelValidate(int startRole,List<List<String>> dataset,Class clazz,String[] excludeFields,Class clazzT){
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
				      	//导出用的方法(注意，除了id，其他所有的字段都是字符串)
				    	Method  methodExport = objExport.getClass().getDeclaredMethod("set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1), String.class);
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
//								System.out.println("checkValue\t" + checkValue);
								result = RegularValid.numberValid(checkValue);
								if(!result){//验证失败
									//导出用的方法
									methodExport.invoke(objExport, ImportExcelValidConst.NUMBERVALUE);
									viewNumber ++;
								}
							}
						}else{//如果为空了，则设置验证变量为空
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
	/***
	 * 验证是否是空行，如果是空行，则直接跳过
	* @Title: validNullRow
	*created at 2014-6-11 下午09:41:01 by ygq  
	* @param cellList
	* @return
	* @return boolean
	 */
	protected boolean validNullRow(List<String> cellList){
		boolean result = false;
		int tempNum = 0;
		if(null != cellList){
			for(int i=0;i<cellList.size();i++){
				String str = cellList.get(i);
				if(str == null || "".equals(str)){
					tempNum ++;
				}
			}
		}
		if(tempNum == cellList.size()){
			result = true;
		}
		return result;
	}
	/****
	 * 排除不需要验证的字段信息
	* @Title: excludeFiled
	*created at 2014-6-8 下午02:44:09 by ygq  
	* @param excludeFields
	* @param checkedFieldName
	* @return
	* @return boolean
	 */
	protected boolean excludeFiled(String checkedFieldName,String[] excludeFields){
		boolean result = false;
		if(null != excludeFields){
      		for(int k=0;k<excludeFields.length;k++){
      			if(checkedFieldName.equals(excludeFields[k])){
      				//System.out.println(checkedFieldName + "\t" + excludeFields[k]);
      				result = true;
      				break;
      			}
      		}
      	}
		return result;
	}
}
