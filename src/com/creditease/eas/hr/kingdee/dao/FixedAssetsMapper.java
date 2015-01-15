package com.creditease.eas.hr.kingdee.dao;

import java.util.HashMap;
import java.util.List;

public interface FixedAssetsMapper {
	public List<HashMap<String,Object>> getFixedAssetsByOrgId(String fid);
	
	public List<HashMap<String,Object>> getParentId(String fid);
	
	public List<HashMap<String,Object>> getFixedAssetsByCode(String fnumber);

}
