package com.creditease.eas.dictionary.service;


import java.util.List;

import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.util.Pagination;

/**
 * 数据字典子项接口类
 * @IDictionaryItemService.java
 * created at 2014-3-12 下午03:05:36 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IDictionaryItemService {
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
	 * 描述：添加数据字典子项
	 * 2014-3-11 下午01:55:09 by zhangxin
	 * @version
	 * @param dictionaryBase
	 * @return int类型
	 */
	public int insertDictionaryItem(DictionaryItem dictionaryItem);
	/**
	 * 
	 * 描述：根据id查询具体字典子项
	 * 2014-3-11 下午02:41:43 by zhangxin
	 * @version
	 * @param id
	 * @return 数据字典实体bean
	 */
	public DictionaryItem findOneDictionaryItem(Integer id);
	/**
	 * 
	 * 描述：修改数据字典子项信息
	 * 2014-3-11 下午03:02:28 by zhangxin
	 * @version
	 * @param dictionaryBase
	 */
	public void updateDictionaryItem(DictionaryItem dictionaryItem);
	/**
	 * 
	 * 描述：根据id删除某条字典子项信息
	 * 2014-3-11 下午03:58:19 by zhangxin
	 * @version
	 * @param id
	 */
	public void deleteDictionaryItem(Integer id);
	/**
	 * 
	 * 描述：判断数据关键字是否已经存在
	 * 2014-3-14 下午04:34:01 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public boolean itemidIfExists(Integer id);
	/**
	 * 
	 * 描述：判断数据值是否已经存在
	 * 2014-3-14 下午04:34:01 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public boolean itemnameIfExists(Integer id);
	
	 /****
	  * 根据baseid获取字典子项的集合
	 * @Title: seleteItemData
	 *created at 2014-6-2 上午10:47:45 by ygq  
	 * @param baseid
	 * @return
	 * @return List<DictionaryItem>
	  */
	  public List<DictionaryItem> seleteItemData(Integer baseid);
	
}
