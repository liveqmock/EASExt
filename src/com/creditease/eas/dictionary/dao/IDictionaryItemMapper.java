package com.creditease.eas.dictionary.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.dictionary.bean.DictionaryItem;
/**
 * 数据字典子项接口类
 * @IDictionaryItemMapper.java
 * created at 2014-3-12 下午02:20:25 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IDictionaryItemMapper{
   
	/**
	 * 
	 * 描述：根据id删除某条数据字典子项信息
	 * 2014-3-11 下午03:37:48 by zhangxin
	 * @version
	 * @param id
	 * @return int
	 */
	public int deleteByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：根据数据字典类型关键字删除具体的数据信息
	 * 2014-3-14 下午03:42:56 by zhangxin
	 * @version
	 * @param baseid
	 * @return
	 */
	public int deleteDataInfo(Integer baseid);
	
	/**
	 * 
	 * 描述：添加数据字典子项
	 * 2014-3-11 下午02:18:24 by zhangxin
	 * @version
	 * @param record
	 * @return int
	 */
    public int insertItem(DictionaryItem record);
   
   /**
    * 
    * 描述：根据id查询具体数据字典子项
    * 2014-3-11 下午02:18:24 by zhangxin
    * @version
    * @param id
    * @return 字典子项实体Bean
    */
   public DictionaryItem selectByPrimaryKey(Integer id);
   
   
   /**
    * 
    * 描述：保存修改的字典项信息
    * 2014-3-11 下午02:49:39 by zhangxin
    * @version
    * @param record
    * @return int
    */
   public int updateByPrimaryKey(DictionaryItem dictionaryItem);
   /**
    * 
    * 描述：查询数据是否重复
    * 2014-3-14 下午04:21:12 by zhangxin
    * @version
    * @param map 参数
    * @return int类型
    */
   public int itemidIfExists(Map map);
   /**
    * 
    * 描述：查询字段值是否重复
    * 2014-3-27 下午04:07:15 by zhangxin
    * @version
    * @param map
    * @return
    */
   public int itemnameIfExists(Map map);
   
   /**
    * 
    * 描述：查询总数
    * 2014-3-27 上午10:32:39 by zhangxin
    * @version
    * @param map
    * @return
    */
   public int getTotalCountsByParams(Map map);
   /**
    * 
    * 描述：查询列表信息
    * 2014-3-27 上午10:33:39 by zhangxin
    * @version
    * @param params
    * @return
    */
   public List<Map> queryPageByParams(Map params);
   /**
    * 
    * 描述：根据baseid获取字典子项的集合
    * 2014-3-28 下午04:42:03 by zhangxin
    * @version
    * @param baseid
    * @return
    */
   public List<DictionaryItem> seleteItemData(Integer baseid);
   
   
   
   
}