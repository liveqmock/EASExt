package com.creditease.eas.warn.kingdee.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.warn.bean.OrgCostcenter;

public interface OrgCostcenterMapper {
	public int selectCostcenterCount(Map map);
	public List<OrgCostcenter> selectAllCostcenter(Map map);
	public String selectById(String fnumber);
	public List<OrgCostcenter> selectByFname(List<String> fidlist);
	public List<String> selectIdByFname(String fname_l2);
	public OrgCostcenter selectFname(String fid);
	public List<OrgCostcenter> selectByIds(List<String> fidlist);
	
}
