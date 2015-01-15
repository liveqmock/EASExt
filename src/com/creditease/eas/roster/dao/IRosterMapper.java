package com.creditease.eas.roster.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.roster.bean.Roster;
import com.creditease.eas.util.BaseDAO;
/**
 * 投三花名册DAO层接口方法
 * @IRosterMapper.java
 * created at 2014-3-17 下午05:17:07 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IRosterMapper extends BaseDAO<Roster, Integer>{
    /**
     * 
     * 描述：删除某条数据信息
     * 2014-3-18 下午02:28:35 by zhangxin
     * @version
     * @param fid
     * @return
     */
	public int deleteByPrimaryKey(Integer fid);
	/**
     * 
     * 描述：插入花名册信息
     * 2014-3-18 下午02:28:35 by zhangxin
     * @version
     * @param fid
     * @return
     */
	public int insert(Roster record);
    /**
     * 
     * 描述：查看某条数据的具体信息
     * 2014-3-18 下午02:18:20 by zhangxin
     * @version
     * @param fid
     * @return
     */
    public Roster selectByPrimaryKey(Integer fid);
    
    /**
     * 
     * 描述：根据邮箱查找数据具体信息
     * 2014-4-7 下午12:50:34 by Administrator
     * @version
     * @param femail
     * @return
     */
    public Roster selectRosterByEmail(String femail);
    /**
     * 
     * 描述：根据条件查询信息
     * 2014-4-1 下午01:55:10 by zhangxin
     * @version
     * @param map
     * @return
     */
    public List<Map> selectDatabyParm(Map map);
    /**
     * 
     * 描述：更新花名册信息
     * 2014-3-19 下午04:08:40 by zhangxin
     * @version
     * @param record
     * @return
     */
   public int updateByPrimaryKey(Roster record);
   /**
    * 
    * 描述：导入时更新操作
    * 2014-6-20 上午11:39:02 by zhangxin
    * @version
    * @param record
    * @return
    */
   public int updateforimport(Roster record);
   /**
    * 
    * 描述：导出时查询出的数据
    * 2014-3-20 下午04:03:02 by zhangxin
    * @version
    * @return
    */
   public List<Map> expQuery(Map map);
   
   /**
    * 
    * 描述：批量新增
    * 2014-4-1 下午02:38:16 by zhangxin
    * @version
    * @param list
    */
   void InsertAll(@Param("list")List<Roster> list);
   /**
    * 
    * 描述：查询12位员工编号是否重复
    * 2014-4-24 上午10:56:16 by zhangxin
    * @version
    * @param map
    * @return
    */
   public int findNumExists(Map map);
   /**
    * 
    * 描述：查询批量修改总条数
    * 2014-6-26 上午10:56:16 by zhangxin
    * @version
    * @param map
    * @return int
    */
   public int getTotalManyEditInfo(Map map);
   /**
    * 
    * 描述：查询批量修改数据信息
    * 2014-6-26 上午10:56:16 by zhangxin
    * @version
    * @param map
    * @return list对象
    */
   public List<Roster> queryPageManyEditInfo(Map params);
   /**
    * 
    * 描述：批量修改花名册数据信息
    * 2014-6-29 下午03:45:31 by zhangxin
    * @version
    * @param params
    * @return 修改条数
    */
   public int updateManyEditData(Map params);
   
}