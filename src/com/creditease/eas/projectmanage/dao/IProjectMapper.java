package com.creditease.eas.projectmanage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.projectmanage.bean.AgreementFile;
import com.creditease.eas.projectmanage.bean.ProjectManage;

public interface IProjectMapper {
   /**
    * 
    * 描述：根据条件查询符合这些条件的数量
    * 2014-3-13 下午02:46:01 by sunxiaofeng
    * @version
    * @param map
    * @return 数量
    */
	public int getTotalCountsByParams(Map map);
    /**
     * 
     * 描述：查询符合条件的项目信息
     * 2014-3-13 下午02:47:15 by sunxiaofeng
     * @version
     * @param mapTo 
     * @return项目信息集合
     */
    public List<Map> queryPageByParams(Map mapTo);
    /**
     * 
     * 描述：加载出借方式
     * 2014-3-13 下午06:18:48 by sunxiaofeng
     * @version
     * @return  Map集合
     */
	public List<Map> findCussource();
	/**
	 * 
	 * 描述：加载还款方式
	 * 2014-3-13 下午06:34:56 by sunxiaofeng
	 * @version
	 * @return  Map集合
	 */
	public List<Map> selectSituationname();
	/**
	 * 
	 * 描述：加载项目状态
	 * 2014-3-13 下午06:43:58 by sunxiaofeng
	 * @version
	 * @return Map集合
	 */
	public List<Map> selectState();
	/**
	 * 
	 * 描述：加载贷后管理负责人(PM)
	 * 2014-3-13 下午06:51:48 by sunxiaofeng
	 * @version
	 * @return  Map集合
	 */
	public List<Map> selectPm();
	/**
	 * 
	 * 描述：添加项目信息
	 * 2014-3-14 下午03:27:30 by sunxiaofeng
	 * @version
	 * @param projectManage
	 * @return  int
	 */
	public int insert(ProjectManage projectManage);
	/**
	 * 
	 * 描述：根据id 查询项目信息
	 * 2014-3-14 下午05:20:30 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return 项目信息对象
	 */
	public ProjectManage edit(Integer fid);
	/**
	 * 
	 * 描述：添加利息返还日
	 * 2014-3-17 下午01:18:03 by sunxiaofeng
	 * @version
	 * @param proMan
	 */
	public void insertInterestTime(Map map);
	/**
	 * 
	 * 描述：修改项目信息
	 * 2014-3-17 下午03:19:52 by sunxiaofeng
	 * @version
	 * @param projectManage
	 * @return
	 */
	public int update(ProjectManage projectManage);

	/**
	 * 
	 * 描述：查看利息返还日
	 * 2014-3-20 上午11:31:30 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public List<Map> queryIinterestTime(Map map);
	/**
	 * 
	 * 描述：修改利息返还其状态
	 * 2014-3-20 下午05:33:48 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public int updateInTimeState(Map map);
	/**
	 * 
	 * 描述：
	 * 2014-3-21 上午11:28:45 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public List<Integer> selectInProjectFid(Map map);
	/**
	 * 
	 * 描述：判断贷款编号是否重复
	 * 2014-3-24 下午03:43:43 by sunxiaofeng
	 * @version
	 * @param setParams
	 * @return
	 */
	public int findExists(Map setParams);
	
	/**
	 * 描述：导出项目信息列表
	 * 2014年3月24日13:21:37 by zhaiwei
	 * @param map
	 * @return
	 */
	public List<HashMap<String,Object>> seletAllPmForExp(Map map);
	/**
	 * 
	 * 描述：根据fid 查询项目信息
	 * 2014-3-24 下午05:36:32 by sunxiaofeng
	 * @version
	 * @param split
	 */
	public List<Map> selectProjectByFid(String[] split);
	/**
	 * 
	 * 描述：查询所有项目信息
	 * 2014-3-25 上午09:44:25 by sunxiaofeng
	 * @version
	 * @return
	 */
	public List<Map> queryAllProject();
	/**
	 * 
	 * 描述：按利息返还日及出借方式，pm分组
	 * 2014-5-9 下午04:50:26 by sunxiaofeng
	 * @version
	 * @return
	 */
	public List<Map> selectProjectGroupBy();
	/**
	 * 
	 * 描述：
	 * 描述：按利息返还日及出借方式，pm和fid查询数据
	 * 2014-5-9 下午05:12:39 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public List<Map> selectProject(Map map);
	
	/**
	 * 
	 * 描述：查询所有利息返还日
	 * 2014-4-23 上午09:15:49 by sunxiaofeng
	 * @version
	 * @return（废弃）
	 */
	public List<Map> queryAllIinterestTime();
	/**
	 * 
	 * 描述：根据利息返还日fid查询
	 * 2014-4-23 上午10:47:26 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public Map selectByTimeFid(Integer fid);
	/**
	 * 
	 * 描述：删除项目信息
	 * 2014-4-18 下午04:30:06 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public int deleteProject(Integer fid);
	/**
	 * 
	 * 描述：删除利息返还日
	 * 2014-4-21 上午11:08:59 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public int deleteTime(Integer fid);
	/**
	 * 
	 * 描述：查询文件数量
	 * 2014-4-22 下午03:38:05 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public int queryFileNumber(Map map);
	/**
	 * 
	 * 描述：查询文件集合
	 * 2014-4-22 下午03:45:17 by sunxiaofeng
	 * @version
	 * @param mapTo
	 * @return
	 */
	public List<Map> queryFileList(Map mapTo);
	/**
	 * 
	 * 描述：新增文件信息
	 * 2014-4-22 下午04:14:18 by sunxiaofeng
	 * @version
	 * @param agreementFile
	 */
	public void insertFile(AgreementFile agreementFile);
	/**
	 * 
	 * 描述：删除文件信息
	 * 2014-4-22 下午06:06:11 by sunxiaofeng
	 * @version
	 * @param parseInt
	 */
	public void deleteFile(int fileFid);
	/**
	 * 
	 * 描述：查询文件信息
	 * 2014-4-22 下午06:33:45 by sunxiaofeng
	 * @version
	 * @param fileFid
	 * @return
	 */
	public AgreementFile findAgreementFile(int fileFid);
}
