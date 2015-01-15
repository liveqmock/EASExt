package com.creditease.eas.accountr.dao;

import java.util.List;
import java.util.Map;

public interface GeneratFileMapper {

	/**
	 * 
	 * 描述：添加文件信息
	 * 2014-7-23 下午04:26:22 by sunxiaofeng
	 * @version
	 * @param map
	 */
	public void insertFile(Map map);
	/**
	 * 
	 * 描述：查询文件数量
	 * 2014-7-23 下午05:09:38 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public int getFileCountsByParams(Map map);
	/**
	 * 
	 * 描述：查询文件信息
	 * 2014-7-23 下午05:16:14 by sunxiaofeng
	 * @version
	 * @param mapTo
	 * @return
	 */
	public List<Map> queryPageByParamsFile(Map mapTo);
	/**
	 * 
	 * 描述：删除文件信息
	 * 2014-7-23 下午07:18:48 by sunxiaofeng
	 * @version
	 * @param parseInt
	 */
	public void deleteFile(int fid);
	/**
	 * 
	 * 描述：查询文件信息
	 * 2014-7-23 下午01:43:55 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public Map findFileInfo(int fid);
}
