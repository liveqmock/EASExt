package com.creditease.eas.dictionary.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.dictionary.bean.DictionaryBase;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.util.Pagination;
/**
 * 数据字典基项接口类
 * @IDictionaryBaseService.java
 * created at 2014-3-10 下午03:38:29 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IDictionaryBaseService {
	/**
	 * 
	 * 描述：查询方法
	 * 2014-3-10 下午03:38:50 by zhangxin
	 * @version
	 * @param page
	 * @return 带分页功能的json数据
	 */
	public Pagination queryPage(Pagination page);
	/**
	 * 
	 * 描述：添加数据字典项
	 * 2014-3-11 下午01:55:09 by zhangxin
	 * @version
	 * @param dictionaryBase
	 * @return int类型
	 */
	public int insertDictionaryBase(DictionaryBase dictionaryBase);
	/**
	 * 
	 * 描述：根据id查询具体字典项
	 * 2014-3-11 下午02:41:43 by zhangxin
	 * @version
	 * @param id
	 * @return 数据字典实体bean
	 */
	public DictionaryBase findOneDictionaryBase(Integer id);
	
	/**
	 * 
	 * 描述：修改数据字典项信息
	 * 2014-3-11 下午03:02:28 by zhangxin
	 * @version
	 * @param dictionaryBase
	 */
	public void updateDictionaryBase(DictionaryBase dictionaryBase);
	/**
	 * 
	 * 描述：根据id删除某条字典项
	 * 2014-3-11 下午03:58:19 by zhangxin
	 * @version
	 * @param id
	 */
	public void deleteDictionaryBase(Integer id);
	/**
	 * 
	 * 描述：根据baseid删除子表中的详细信息
	 * 2014-3-14 下午03:47:49 by zhangxin
	 * @version
	 * @param baseid
	 * @return
	 */
	public void deleteItemInfo(Integer baseid);

	/**
	 * 
	 * 描述：检查类型关键字是否重复
	 * 2014-3-26 下午03:44:41 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean selectedIfTypeExists() throws Exception;
	/**
	 * 
	 * 描述：将已显示过的数据字典放在缓存中
	 * typeid 类型关键字
	 * 2014-3-28 下午03:18:54 by zhangxin
	 * @version
	 * @param id
	 * @return
	 */
	public List<DictionaryItem> cacheDictionaryMap(String typeid);
	/**
	 * 
	 * 描述：编辑或删除数据字典时清空缓存中的数据
	 * 2014-3-31 下午03:01:06 by zhangxin
	 * @version
	 * @return
	 */
	public Map<String,List<DictionaryItem>>  changeCacheDataMap(String id);
	
	/**
	 * 
	 * 描述：获得数据值子项Item的key值
	 * 2014-4-1 下午04:00:17 by zhangxin
	 * @version
	 * @param baseid 类型关键字
	 * @param value  下拉框显示的具体值
	 * @return
	 */
	public Integer getItemKey(String baseid,String value);
	/***
	 * 获得数据字典字表（t_dictionary_item）对应的itemid
	* @Title: getItemId
	*created at 2014-7-3 上午11:58:04 by ygq  
	* @param baseid
	* @param value
	* @return
	* @return Integer
	 */
	public String getItemId(String baseid,String value);
	/**
	 * 数据字典中是否存在传入的值
	 * @param baseid
	 * @param value
	 * @return boolean true:存在  false:不存在
	 */
	public boolean isItemHave(String baseid,String value);
	/**
	 * 
	 * 描述：删除数据字典中缓存数据
	 * 2014-4-23 上午11:01:10 by zhangxin
	 * @version
	 */
	public void delectCacheData();
	/**
	 * 
	 * 描述：判断类型关键字是否重复
	 * 2014-5-6 下午04:41:04 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean typeidIfExists() throws Exception ;
	
}
