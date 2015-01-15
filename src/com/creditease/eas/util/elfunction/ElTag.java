package com.creditease.eas.util.elfunction;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.creditease.eas.dictionary.bean.DictionaryItem;
/**
 * EL表达式函数处理类
 */
public class ElTag {
	/***
	 *  验证一个角色是否是管理员权限
	 *  鉴于当前权限设计的现状，验证是否是管理员权限，只能先用角色id进行判断
	 */
	public static boolean validIsAdmin(List list,Integer adminId){
		boolean va = false;
		if(null != list){
			for(int i=0;i<list.size();i++){
				if(Integer.parseInt(list.get(i).toString()) == adminId){
					va = true;
					break;
				}
			}
		}
		return va;
	}
	/***
	 * 获取URl路径
	 * @return
	 */
	public static String catchUrl(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		 String path = request.getContextPath();
		 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		 return basePath;
	}
	/***
	 * 根据字典的id获得字典的值
	* @Title: getDictionaryValue
	*created at 2014-6-2 上午10:58:45 by ygq  
	* @param list
	* @param itemidValue:对应item的id
	* @return
	* @return String
	 */
	public static String getDictionaryValue(List<DictionaryItem> list,Integer itemidValue){
		if("".equals(itemidValue)||null == itemidValue){
			return null;
		}
		String value = null;
		if(null != list &&!"".equals(list)){
			for(int i=0;i<list.size();i++){
				DictionaryItem item = list.get(i);
				if(itemidValue.equals(item.getId())||itemidValue==item.getId()){//itemidValue和id都是Integer类型，所以比较的时候，要用equsls
					value = item.getItemname();
					break;
				}
			}
		}
		return value;
	}
	/***
	 * 根据字典的子项关键字获得字典的值
	* @Title: getDictionaryValue
	*created at 2014-6-2 上午10:58:45 by ygq  
	* @param list
	* @param itemidValue:对应item的id
	* @return
	* @return String
	 */
	public static String getDictionaryValueByItemid(List<DictionaryItem> list,String itemidValue){
		if("".equals(itemidValue)||null == itemidValue){
			return null;
		}
		String value = null;
		if(null != list &&!"".equals(list)){
			for(int i=0;i<list.size();i++){
				DictionaryItem item = list.get(i);
				if(itemidValue.equals(item.getItemid())){//itemidValue和id都是Integer类型，所以比较的时候，要用equsls
					value = item.getItemname();
					break;
				}
			}
		}
		return value;
	}
}
