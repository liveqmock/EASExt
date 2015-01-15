/**
 * 
 */
package com.creditease.eas.util.excel;

import java.util.List;

/**
 * @Title:ExcelTitleValidate.java
 * @Package com.creditease.eas.util.excel
 * created at 2014-7-1 下午02:32:41 by ygq
 * @author ygq
 * @version 1.0
 */
public class ExcelTitleValidate {
	/***
	 * 验证excel的表头
	* @Title: titleValid
	*created at 2014-7-1 下午02:27:49 by ygq  
	* @param listTitle
	* @return
	* @return boolean
	 */
	public  static boolean titleValid(List<String> listTitle,String[] titles){
		boolean va = true;
		if(null != listTitle && !listTitle.isEmpty()){
			if(listTitle.size() == titles.length){//列数要对应
				for(int i=0;i<listTitle.size();i++){
					if(!listTitle.get(i).trim().equals(titles[i].trim())){
						va = false;
					}
				}
			}else{
				va = false;
			}
		}
		return va;
	}
}
