package com.creditease.eas.compliance.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.compliance.bean.Applysettlement;
import com.creditease.eas.compliance.bean.Statis;
import com.creditease.eas.util.BaseDAO;
/**
 * 违规统计dao
 * @StatisMapper.java
 * created at 2013-11-5 下午03:05:32 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface StatisMapper extends BaseDAO<Applysettlement, Integer> {

	//获取违规案件总数
	public int getTotalCountsCheckBox();
	//查询违规案件
	public List<Statis> queryPageCheckBox(Map params);
	
}
