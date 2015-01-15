package com.creditease.eas.recruitment.kingdee.dao; 
import java.util.List;
import java.util.Map;

import com.creditease.eas.recruitment.bean.RecOrgAdmin;
import com.creditease.eas.recruitment.bean.RecOrgPosition;
import com.creditease.eas.recruitment.bean.RecPersonInfo;
public interface RecruitmentMapper {
	/**
	 * 描述：查询组织的信息
	 * 2013-3-17 下午10:29:19 by ygq
	 * @version
	 * @return
	 */
	public List<RecOrgAdmin> selectOrgAdminInfo(Map map);
	/***
	 * 描述：查询职位的信息
	 * 2013-8-7 下午11:01:32 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<RecOrgPosition> selectOrgPositionInfo(Map map);
	
	/***
	 * 描述：查询人员的信息
	 * 2013-8-7 下午11:01:32 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<RecPersonInfo> selectPersonInfo(Map map);
}