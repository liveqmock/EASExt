package com.creditease.eas.recruitment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.recruitment.bean.RecOrgAdmin;
import com.creditease.eas.recruitment.bean.RecOrgPosition;
import com.creditease.eas.recruitment.bean.RecPersonInfo;
import com.creditease.eas.recruitment.kingdee.query.RecruitmentQuery;
import com.creditease.eas.recruitment.service.IRecruitmentWSService;

/**
 * 给招聘系统的基本信息的接口
 * @EASfinancialInfoWSService.java
 * created at 2013-5-7 上午11:21:53 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class RecruitmentWSServiceImpl implements IRecruitmentWSService{
	RecruitmentQuery recQuery = new RecruitmentQuery();
	@Override
	public List<RecOrgAdmin> findOrgAdmin(int page, int size) {
		Map map = new HashMap();
		int min = (page-1)*size + 1;
		int max =  page*size;
		map.put("startRow", min);
		map.put("endRow", max);
		return recQuery.findOrgAdmin(map);
	}
	@Override
	public List<RecOrgPosition> findOrgPositionInfo(int page, int size) {
		Map map = new HashMap();
		int min = (page-1)*size + 1;
		int max =  page*size;
		map.put("startRow", min);
		map.put("endRow", max);
		return recQuery.findOrgPositionInfo(map);
	}
	@Override
	public List<RecPersonInfo> findPersonInfo(int page, int size) {
		Map map = new HashMap();
		int min = (page-1)*size + 1;
		int max =  page*size;
		map.put("startRow", min);
		map.put("endRow", max);
		return recQuery.findPersonInfo(map);
	}
}
