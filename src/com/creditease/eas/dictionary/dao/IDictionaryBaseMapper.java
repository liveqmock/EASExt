package com.creditease.eas.dictionary.dao;

import java.util.Map;

import com.creditease.eas.dictionary.bean.DictionaryBase;
import com.creditease.eas.util.BaseDAO;
/**
 * 数据字典基项DAO接口
 * @DictionaryBaseMapper.java
 * created at 2014-3-10 下午03:40:01 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IDictionaryBaseMapper extends BaseDAO<DictionaryBase, Integer>{
	/**
	 * 
	 * 描述：根据id删除某条数据字典信息
	 * 2014-3-11 下午03:37:48 by zhangxin
	 * @version
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Integer id);
	
   /**
    * 
    * 描述：添加数据字典项
    * 2014-3-11 下午02:18:24 by zhangxin
    * @version
    * @param record
    * @return int
    */
   public int insertBase(DictionaryBase record);
   /**
    * 
    * 描述：根据id查询具体字典项
    * 2014-3-11 下午02:18:24 by zhangxin
    * @version
    * @param id
    * @return 字典实体Bean
    */
   public  DictionaryBase selectByPrimaryKey(Integer id);
   
   /**
    * 
    * 描述：保存修改的字典项信息
    * 2014-3-11 下午02:49:39 by zhangxin
    * @version
    * @param record
    * @return
    */
   public int updateByPrimaryKey(DictionaryBase record);
   
   /**
    * 
    * 描述：查询类型关键字是否重复
    * 2014-5-6 下午04:07:15 by zhangxin
    * @version
    * @param map
    * @return
    */
   public int typeidIfExists(Map map);
   /**
    * 
    * 描述：根据类型关键字获取主表id值
    * 2014-5-8 下午01:36:16 by zhangxin
    * @version
    * @param typeid
    * @return
    */
   public int selectidbytypeid(String typeid);
}