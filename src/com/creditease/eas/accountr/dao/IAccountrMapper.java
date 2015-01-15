package com.creditease.eas.accountr.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.accountr.bean.Accountr;

public interface IAccountrMapper {
     /**
      * 
      * 描述：查询符合条件的数量
      * 2014-1-8 上午11:07:40 by sunxiaofeng
      * @version
      * @param map条件
      * @return
      */
	public int getTotalCountsByParams(Map map);
     /**
      * 
      * 描述：查询符合条件的数据
      * 2014-1-8 上午11:08:19 by sunxiaofeng
      * @version
      * @param mapTo
      * @return集合
      */
	public List<Accountr> queryPageByParams(Map mapTo);
	/**
	 * 
	 * 描述：根据id查询报销信息
	 * 2014-1-8 下午03:41:31 by sunxiaofeng
	 * @version
	 * @param fid报销信息id
	 * @return报销信息对象
	 */
	public Accountr findAccountr(Integer fid);
	/**
	 * 
	 * 描述：新增报销信息
	 * 2014-1-8 下午03:54:58 by sunxiaofeng
	 * @version
	 * @param accountr报销信息对象
	 */
	public void insert(Accountr accountr);
	/**
	 * 
	 * 描述：修改报销信息
	 * 2014-1-8 下午04:09:52 by sunxiaofeng
	 * @version
	 * @param accountr报销信息对象
	 */
	public void update(Accountr accountr);
	/**
	 * 
	 * 描述：删除报销信息
	 * 2014-1-8 下午05:33:04 by sunxiaofeng
	 * @version
	 * @param fid 报销信息id
	 */
	public void deleteAccountr(Integer fid);
	/**
	 * 
	 * 描述：导入数据的批量新增
	 * 2014-1-9 上午11:46:52 by sunxiaofeng
	 * @version
	 * @param accountrList 报销信息集合
	 */
     void InsertAll(@Param("list") List<Accountr> list);
     /**
      * 
      * 描述：查询该报销日期中，最大的报销编号
      * 2014-1-9 下午05:23:38 by sunxiaofeng
      * @version
      * @param accountrdate
      * @return
      */
	public String getAccountrNumByDate(String accountrdate);
	/**
	 * 
	 * 描述：根据fid查询报销信息
	 * 2014-1-15 下午02:05:00 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public List<Accountr> getAccountrById(String[] fid);
	/**
	 * 
	 * 描述：修改发送状态
	 * 2014-1-15 下午03:28:18 by sunxiaofeng
	 * @version
	 * @param accountr
	 */
	public void updateById(Accountr accountr);
	/**
	 * 
	 * 描述：根据报销时间及不发送报销信息的id查询要发送报销信息
	 * 2014-1-20 下午02:24:04 by sunxiaofeng
	 * @version
	 * @param map
	 * @return  报销信息集合
	 */
	public List<Accountr> getAccountrByIdBatch(Map map);
	/**
	 * 
	 *描述：保存删除的信息
	 * 2014-2-17 下午04:29:51 by sunxiaofeng
	 * @version
	 * @param accountr
	 */
	public void insertAccountrLog(Accountr accountr);

}
