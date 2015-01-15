package com.creditease.eas.projectmanage.dao;

import java.util.List;
import java.util.Map;
import com.creditease.eas.projectmanage.bean.PmInfo;
import com.creditease.eas.util.BaseDAO;

public interface PmInfoMapper extends BaseDAO<PmInfo, Integer> {

	public int getTotalCounts();
	
	@SuppressWarnings("unchecked")
	public List<PmInfo> queryPage(Map map2);
	
	/**
	 * 描述：根据id获取PM信息
	 * @param id
	 * @return
	 */
	public PmInfo getPMInfoById(int id);
	
	/**
	 * 描述：更新PM的Email
	 * @param pmInfo
	 */
	public void updatePmEmail(PmInfo pmInfo);
	
	/**
	 * 描述：根据用户ID获取PM信息
	 * @param userId
	 * @return
	 */
	public PmInfo queryPmInfoByUserId(Integer userId);
	
	/**
	 * 描述：新增PM信息
	 * @param pmInfo
	 */
	public void insertPmInfo(PmInfo pmInfo);
	
	/**
	 * 描述：删除PM信息
	 * @param userId
	 */
	public void deletePmInfo(Integer userId);
    /**
     * 
     * 描述：查询所有管理员
     * 2014-5-10 上午10:05:27 by sunxiaofeng
     * @version
     * @return
     */
	public List<PmInfo> selectByRoleid();



}
