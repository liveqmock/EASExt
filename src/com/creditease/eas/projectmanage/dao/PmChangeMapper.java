package com.creditease.eas.projectmanage.dao;

import java.util.List;
import java.util.Map;
import com.creditease.eas.projectmanage.bean.PmChange;
import com.creditease.eas.projectmanage.bean.PmInfo;
import com.creditease.eas.util.BaseDAO;

public interface PmChangeMapper extends BaseDAO<PmInfo, Integer> {

	public int getTotalCounts();

	@SuppressWarnings("unchecked")
	public int getPmChangeTotalCounts(Map map);
	
	@SuppressWarnings("unchecked")
	public List<Map>  queryAllPmChangeList(Map map);
     /**
      * 
      * 描述：新增pm修改记录信息
      * 2014-3-17 下午03:05:46 by sunxiaofeng
      * @version
      * @param pmChange
      */
	public void insertPmChange(PmChange pmChange);


}
